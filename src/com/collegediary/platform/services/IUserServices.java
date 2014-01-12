/************************************************************************
 * 
 * 	FileName	:  IUserServices.java
 *  
 *  Description :  This is interface class that provides different services
 *  			   to user to provide database access. 
 *  			    
 *  Revision History:
 *  ---------------------------------------------------------------------
 *  	DATE	 	NAME			MODULE 			  Changes
 *  ---------------------------------------------------------------------
 *  07-12-2013	Gaurav Khullar 	IUser Services	 createNewUser added
 *  14-12-2013  Gorav Dhiman	IUser Services   delete,update,find & 
 *  											 authenticate User added
 *  
 ************************************************************************/
package com.collegediary.platform.services;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.collegediary.model.user.MasterUser;

/**
 * @author gaurav.khullar
 * 
 */
public interface IUserServices {
	/**
	 * This method is used to create the Master user in the CollegeDiary Application 
	 * A Master user also has a UserDetails Object
	 * 
	 * @param masterUser
	 * @return MasterUser that is updated in database.
	 */
	public MasterUser createNewUser(MasterUser masterUser);
	/**
	 * This is method used to delete given user record from database. 
	 * 
	 * @param MasterUser
	 *           The bean for the user details that to be delete in database.
	 * 
	 * @return : void
	 * 
	 **/
	public void deleteUser(MasterUser masterUser);
	/**
	 * This is method used to update given user record from database. 
	 * 
	 * @param MasterUser
	 *           The bean for the user details that to be update in database.
	 * 
	 * @return : void
	 * 
	 **/
	public void updateUser(MasterUser masterUser);
	/**
	 * This method is used to find the list of all users. 
	 * 
	 * @return : List of all users.
	 * 
	 **/	
	public List<MasterUser> findUsers();
	/**
	 * This is method that check whether user is registered or not. 
	 * 
	 * @param MasterUser
	 *           The bean for the user details.
	 *           
	 * @param HttpServletRequest   
	 *        Request object to get session
	 *           
	 * @param HttpServletResponse
	 * 			 Response object to add cookie
	 * 
	 * @return :  SUCCESS or FAILURE
	 * 
	 **/
	public boolean authenticateUser(MasterUser masterUser,HttpServletRequest request,HttpServletResponse response);
	/**
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
	 **/
	public boolean fileUpload(MultipartHttpServletRequest request);
	/**
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
	 **/
	public boolean webcamUpload(HttpServletRequest request);
}
