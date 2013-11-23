/**
 * 
 */
package com.collegediary.platform.dao;

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
}
