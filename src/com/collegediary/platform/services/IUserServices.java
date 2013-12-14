/**
 * 
 */
package com.collegediary.platform.services;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
	 * @return
	 */
	public MasterUser createNewUser(MasterUser masterUser);
	public void deleteUser(MasterUser masterUser);
	public void updateUser(MasterUser masterUser);
	public void authenticateUser(MasterUser masterUser,HttpServletResponse response);
}
