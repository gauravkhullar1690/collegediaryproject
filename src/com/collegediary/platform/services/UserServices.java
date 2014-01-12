/************************************************************************
 * 
 * 	FileName	:  UserServices.java
 *  
 *  Description :  This is class that provides different services to user
 *  			   to provide database access. 
 *  			    
 *  Revision History:
 *  ---------------------------------------------------------------------
 *  	DATE	 	NAME			MODULE 			  Changes
 *  ---------------------------------------------------------------------
 *  07-12-2013	Gaurav Khullar 	User Services	 createNewUser added
 *  14-12-2013  Gorav Dhiman	User Services    delete,update,find & 
 *  											 authenticate User added
 *  
 ************************************************************************/

package com.collegediary.platform.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.collegediary.model.user.FileMeta;
import com.collegediary.model.user.MasterUser;
import com.collegediary.platform.dao.UserDAO;
import com.collegediary.platform.hbm.CommonConstants;
import com.collegediary.platform.hbm.StringUtils;
import com.collegediary.platform.logging.CollegeDiaryLogger;

/**
 * @author gaurav.khullar
 * 
 */
public class UserServices implements IUserServices {

	private UserDAO userDAO;
	private HttpSession session = null;
	private final String CLASS_NAME = this.getClass().getName();

	/*****************************************************************************
	 * -----------------------------------------------------------------------
	 * Public Methods (getUserDAO)
	 * -----------------------------------------------------------------------
	 * This is getter method for setting userDAO variable
	 * 
	 * @return : UserDAO The initialised class variable is returned.
	 * 
	 ***************************************************************************/

	public UserDAO getUserDAO() {
		return userDAO;
	}

	/*****************************************************************************
	 * -----------------------------------------------------------------------
	 * Public Methods (setUserDAO)
	 * -----------------------------------------------------------------------
	 * This is setter method for setting userDAO variable
	 * 
	 * @param UserDAO
	 *            The object that is used to initialise class variable.
	 * 
	 * @return : void
	 * 
	 ***************************************************************************/

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/*****************************************************************************
	 * -----------------------------------------------------------------------
	 * Public Methods (createNewUser)
	 * -----------------------------------------------------------------------
	 * This is method used to create given user record in database.
	 * 
	 * @param MasterUser
	 *            The bean for the user details that to be create in database.
	 * 
	 * @return : Record that is added.
	 * 
	 ***************************************************************************/

	public MasterUser createNewUser(MasterUser masterUser) {
		return userDAO.createNewUser(masterUser);
	}

	/*****************************************************************************
	 * -----------------------------------------------------------------------
	 * Public Methods (deleteUser)
	 * -----------------------------------------------------------------------
	 * This is method used to delete given user record from database.
	 * 
	 * @param MasterUser
	 *            The bean for the user details that to be delete in database.
	 * 
	 * @return : void
	 * 
	 ***************************************************************************/

	public void deleteUser(MasterUser masterUser) {
		userDAO.deleteUser(masterUser);
	}

	/*****************************************************************************
	 * -----------------------------------------------------------------------
	 * Public Methods (updateUser)
	 * -----------------------------------------------------------------------
	 * This is method used to update given user record in database.
	 * 
	 * @param MasterUser
	 *            The bean for the user details that to be updated in database.
	 * 
	 * @return : void
	 * 
	 ***************************************************************************/

	public void updateUser(MasterUser masterUser) {
		userDAO.updateUser(masterUser);
	}

	/*****************************************************************************
	 * -----------------------------------------------------------------------
	 * Public Methods (findUsers)
	 * -----------------------------------------------------------------------
	 * This method is used to find the list of all users.
	 * 
	 * @return : List of all users.
	 * 
	 ***************************************************************************/

	public List<MasterUser> findUsers() {
		return userDAO.findUsers();
	}

	/*****************************************************************************
	 * -----------------------------------------------------------------------
	 * Public Methods (authenticateUser)
	 * -----------------------------------------------------------------------
	 * This is method that check whether user is registered or not.
	 * 
	 * @param MasterUser
	 *            The bean for the user details.
	 *
	 * @param HttpServletRequest   
	 *        Request object to get session
	 *           
	 * @param HttpServletResponse
	 *            Response object to add cookie
	 * 
	 * @return : SUCCESS or FAILURE
	 * 
	 ***************************************************************************/

	public boolean authenticateUser(MasterUser masterUser,HttpServletRequest request,HttpServletResponse response) {
		boolean result = CommonConstants.FAILURE;
		MasterUser user = null;
		String username = null;
		String password = null;
		List<MasterUser> usersList = null;
		
		if (StringUtils.isNotNullOrNotEmpty(masterUser.getToken())) {
			String token = new String(Base64.decodeBase64(masterUser.getToken()));
			username = token.substring(0, token.indexOf(':'));
			usersList = userDAO.findUserByName(username);
			System.out.println(token.substring(0, token.indexOf(':')));
			System.out.println(token.substring(token.lastIndexOf(':')));

			/**
			 * I have username here fetching record & matching it provides login
			 **/
			/**
			 * Have selectAll function providing allUser list can do we that
			 * also
			 **/

			for (int i = 0; i < usersList.size(); i++) {
				user = usersList.get(i);
				if (token.equals(user.getToken())) {
					result = CommonConstants.SUCCESS;
				}
			}
		} else if (StringUtils.isNotNullOrNotEmpty(masterUser.getRemmberme())
				&& masterUser.getRemmberme().equalsIgnoreCase("on")) {
			username = masterUser.getUsername();
			password = masterUser.getPassword();
			usersList = userDAO.findUsers();
			for (int i = 0; i < usersList.size(); i++) {
				user = usersList.get(i);
				if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
					result = CommonConstants.SUCCESS;
					break;
				}
			}
			
			if(result){
				String signatureValue = DigestUtils.md5Hex(masterUser.getUsername()
						+ ":" + CommonConstants.EXPIRYTIME + ":"
						+ masterUser.getPassword() + ":" + CommonConstants.REST_SERVICES_COOKIE_KEY);
				String tokenValue = masterUser.getUsername() + ":"
					+ CommonConstants.EXPIRYTIME + ":" + signatureValue;
				String tokenValueBase64 = new String(Base64.encodeBase64(tokenValue.getBytes()));

				Cookie cookie = new Cookie(CommonConstants.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY_REST_SERVICES,
											tokenValueBase64);
				if (StringUtils.isNotNullOrNotEmpty(CommonConstants.DOMAIN)) {
					// cookie.setDomain(CommonConstants.DOMAIN);
				}
				System.out.println("Cookie = " + tokenValue + " token = "
						+ tokenValueBase64 + "sign value = " + signatureValue);
				cookie.setPath("/");
				masterUser.setToken(tokenValueBase64);
				response.addCookie(cookie);
				userDAO.updateUser(masterUser);
				result = CommonConstants.SUCCESS;
			}
		}else{
			username = masterUser.getUsername();
			password = masterUser.getPassword();
			usersList = userDAO.findUsers();
			for (int i = 0; i < usersList.size(); i++) {
				user = usersList.get(i);
				if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
					result = CommonConstants.SUCCESS;
					break;
				}
			}

		}
		if(result){
			session = request.getSession();
			session.setAttribute("username", username);
		}
		return result;
	}

	/*********************************************************************************
	 * -----------------------------------------------------------------------
	 * Public Methods (fileUpload)
	 * -----------------------------------------------------------------------
	 * This is method that uploads the file.
	 * 
	 * @param MultipartHttpServletRequest
	 *            Request that uploads multiple files.
	 * 
	 * @return : SUCCESS or FAILURE
	 * 
	 ********************************************************************************/
	public boolean fileUpload(MultipartHttpServletRequest request) {
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		FileMeta fileMeta = null;
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		boolean resp = CommonConstants.SUCCESS;
		
		session = request.getSession();

		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			fileMeta = new FileMeta();
			fileMeta.setFileName(mpf.getOriginalFilename());
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());
			
			try {
				fileMeta.setBytes(mpf.getBytes());
				if (!(new File(CommonConstants.PATH + String.valueOf(session.getAttribute("username"))).exists())) {
	                new File(CommonConstants.PATH + String.valueOf(session.getAttribute("username"))).mkdir();
	            }
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(
						CommonConstants.PATH + String.valueOf(session.getAttribute("username")) + "/" + mpf.getOriginalFilename()));
				files.add(fileMeta);
			} catch (IOException e) {
				e.printStackTrace();
				CollegeDiaryLogger.error(CLASS_NAME, "fileUpload for "+ fileMeta.getFileName() + " failed", e, true);
				resp = CommonConstants.FAILURE;
			}
		}
		return resp;
	}

	/**********************************************************************************
	 * -----------------------------------------------------------------------
	 * Public Methods (webcamUpload)
	 * -----------------------------------------------------------------------
	 * This is method that uploads the file.
	 * 
	 * @param HttpServletRequest
	 *            Request object that uploads  webcam taken snapshot
	 * 
	 * @return : SUCCESS or FAILURE
	 * 
	 *********************************************************************************/
	public boolean webcamUpload(HttpServletRequest request) {
		String imageDataString = null;
		boolean resp = CommonConstants.SUCCESS;
		byte[] imageByteArray = null;
		imageDataString = request.getParameter("txtPic").toString();
		FileOutputStream imageOutFile = null;
		try {
			session = request.getSession();
			// Converting a Base64 String into Image byte array
			imageByteArray = Base64.decodeBase64(imageDataString);
			// Write a image byte array into file system
			if (!(new File(CommonConstants.PATH + String.valueOf(session.getAttribute("username"))).exists())) {
                new File(CommonConstants.PATH + String.valueOf(session.getAttribute("username"))).mkdir();
            }
			imageOutFile = new FileOutputStream(CommonConstants.PATH + String.valueOf(session.getAttribute("username")) + "/profilePicture.jpeg" );
			imageOutFile.write(imageByteArray);
			imageOutFile.close();
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image in webcamUpload " + ioe);
			resp = CommonConstants.FAILURE;
		}
		return resp;
	}
}
