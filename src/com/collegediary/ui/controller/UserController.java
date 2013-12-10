package com.collegediary.ui.controller;

/**
 * 
 * @author gaurav.khullar
 *
 **/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.collegediary.model.user.MasterUser;
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
		CollegeDiaryLogger.debug(CLASS_NAME, "findUser", "Inside createNewUser method");
		return returnMap;
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody Map<String, String> deleteUser(@RequestBody MasterUser masterUser){
		CollegeDiaryLogger.debug(CLASS_NAME, "deleteUser", "Inside deleteUser method");
		Map<String, String> returnMap = new HashMap<String,String>();
		try{
			 userService.deleteUser(masterUser);
		} 
		catch (Exception e) {
			CollegeDiaryLogger.error(CLASS_NAME, "deleteUser", e,true);
		}
		CollegeDiaryLogger.debug(CLASS_NAME, "findUser", "Exiting deleteUser method");
		return returnMap;
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody Map<String, String> updateUser(@RequestBody MasterUser masterUser){
		CollegeDiaryLogger.debug(CLASS_NAME, "updateUser", "Inside updateUser method");
		Map<String, String> returnMap = new HashMap<String,String>();
		try{
			 userService.updateUser(masterUser);
		} 
		catch (Exception e) {
			CollegeDiaryLogger.error(CLASS_NAME, "updateUser", e,true);
		}
		CollegeDiaryLogger.debug(CLASS_NAME, "findUser", "Exiting updateUser method");
		return returnMap;
	}

	@RequestMapping(value = "/authenticateUser", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody Map<String, String> authenticateUser(@RequestBody MasterUser masterUser,HttpServletResponse response){
		CollegeDiaryLogger.debug(CLASS_NAME, "loggingUser", "Inside loggingUser method");
		Map<String, String> returnMap = new HashMap<String,String>();
		try{
			 userService.authenticateUser(masterUser,response);
			 System.out.println(masterUser.getId());
		} 
		catch (Exception e) {
			e.printStackTrace();
			CollegeDiaryLogger.error(CLASS_NAME, "authenticateUser catch", e,true);
		}
		CollegeDiaryLogger.debug(CLASS_NAME, "authenticateUser", "Inside authenticateUser method");
		return returnMap;
	}
	

	@RequestMapping(value = "/findUser", method = RequestMethod.POST, headers = { "Accept=application/json" }, produces = "application/json")
	public @ResponseBody ArrayList<MasterUser> findUser(@RequestBody MasterUser masterUser){
		CollegeDiaryLogger.debug(CLASS_NAME, "findUser", "Inside findUser method");
		Map<String, String> returnMap = new HashMap<String,String>();
		ArrayList<MasterUser> usersList = null;
		MasterUser user = null;
		try{
			 usersList = (ArrayList) userService.findUsers();
			  for(int i=0; i < usersList.size(); i++)
			 {
				 user = (MasterUser)usersList.get(i);
				 System.out.println(user.getUsername());
			 }
			 
		} 
		catch (Exception e) {
			CollegeDiaryLogger.error(CLASS_NAME, "findUser catch", e,true);
		}
		CollegeDiaryLogger.debug(CLASS_NAME, "findUser", "Exiting findUser method");
		return usersList;
	}

	
}