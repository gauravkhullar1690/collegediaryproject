/**
 * 
 */
package com.collegediary.platform.services;

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
}
