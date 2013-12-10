/**
 * 
 */
package com.collegediary.platform.dao;

import java.util.Collection;
import java.util.List;

import com.collegediary.model.user.MasterUser;
import com.collegediary.platform.hbm.AbstractDataAccessObject;

/**
 * @author gaurav.khullar
 *
 */
public class UserDAO extends AbstractDataAccessObject {
	public MasterUser createNewUser(MasterUser masterUser) {
		return (MasterUser)persistenceService.save(masterUser);
	}
	
	public void deleteUser(MasterUser masterUser) {
		persistenceService.remove(masterUser);
		
	}
	
	public void updateUser(MasterUser masterUser) {
		persistenceService.saveOrUpdate(masterUser);
	}

	public List<MasterUser> findUsers() {
		return persistenceService.findUsers();
	}
}
