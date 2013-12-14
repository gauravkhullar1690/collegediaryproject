/**
 * 
 */
package com.collegediary.platform.services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import com.collegediary.common.CommonConstants;
import com.collegediary.common.StringUtils;
import com.collegediary.model.user.MasterUser;
import com.collegediary.platform.dao.UserDAO;

/**
 * @author gaurav.khullar
 * 
 */
public class UserServices implements IUserServices {

	private UserDAO userDAO;
	

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public MasterUser createNewUser(MasterUser masterUser) {
		return userDAO.createNewUser(masterUser);
	}

	public void deleteUser(MasterUser masterUser) {
		userDAO.deleteUser(masterUser);
	}

	public void updateUser(MasterUser masterUser) {
		userDAO.updateUser(masterUser);
	}
	

	/**
	 * @param masterUser
	 */
	public void authenticateUser(MasterUser masterUser,HttpServletResponse response) {
		
		if(StringUtils.isNotNullOrNotEmpty(null)){
			String token = new String(Base64.decodeBase64(null));
			System.out.println(token.substring(0, token.indexOf(':')));
			System.out.println(token.substring(token.lastIndexOf(':')));
			
			/** I have username here fetching record & matching it provides login **/
			/** Have selectAll function providing allUser list can do we that also **/
		    
		}
		else if (StringUtils.isNotNullOrNotEmpty(null)){ /*&& 
				masterUser.getRemmberme().equalsIgnoreCase("on"))*/ 

			String signatureValue = DigestUtils.md5Hex(masterUser.getUsername()
					+ ":" + CommonConstants.EXPIRYTIME + ":"
					+ masterUser.getPassword() + ":"
					+ CommonConstants.REST_SERVICES_COOKIE_KEY);
			String tokenValue = masterUser.getUsername() + ":"
					+ CommonConstants.EXPIRYTIME + ":" + signatureValue;
			String tokenValueBase64 = new String(Base64.encodeBase64(tokenValue
					.getBytes()));
			
			Cookie cookie = new Cookie(
					CommonConstants.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY_REST_SERVICES,	
					tokenValueBase64);
			if (StringUtils.isNotNullOrNotEmpty(CommonConstants.DOMAIN)) {
				//cookie.setDomain(CommonConstants.DOMAIN);
			}
			System.out.println("Cookie = "+ tokenValue + " token = "+ tokenValueBase64 + "sign value = "+ signatureValue);
			cookie.setPath("/");
			//masterUser.setToken(tokenValueBase64);
			response.addCookie(cookie);
			
		}
		userDAO.updateUser(masterUser);
	}
}
