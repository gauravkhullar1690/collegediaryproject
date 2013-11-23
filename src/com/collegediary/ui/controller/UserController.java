package com.collegediary.ui.controller;

/**
 * @author gaurav.khullar
 *
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.collegediary.model.user.MasterUser;
import com.collegediary.platform.dao.UserDAO;
import com.collegediary.platform.hbm.ServiceLocator;
import com.collegediary.platform.logging.CollegeDiaryLogger;
import com.collegediary.platform.services.IUserServices;

@Controller
@RequestMapping("/user")
public class UserController implements Serializable {

	private static final long serialVersionUID = 1L;
	// Properties
	private final String CLASS_NAME = this.getClass().getName();
	
	 @Autowired
	 private IUserServices userService;

	@RequestMapping(value = "/createNewUser", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody Map<String, String> createNewUser(@RequestBody MasterUser masterUser){
		CollegeDiaryLogger.debug(CLASS_NAME, "createNewUser", "Inside createNewUser method");
		Map<String, String> returnMap = new HashMap<String,String>();
		try{
			 MasterUser a = userService.createNewUser(masterUser);
			 System.out.println(a.getId());
		} 
		catch (Exception e) {
			CollegeDiaryLogger.error(CLASS_NAME, "createNewUser", e,true);
		}
		return returnMap;
	}

}