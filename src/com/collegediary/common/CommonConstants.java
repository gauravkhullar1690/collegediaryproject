package com.collegediary.common;

public class CommonConstants 
{
	/**
	 * User type constants
	 */
	public interface UserTypes {
		Integer USER_TYPE_STUDENT = 1;
		Integer USER_TYPE_FACULTY = 2;
		Integer USER_TYPE_ALUMNI = 3;
	}

	public static String SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY_REST_SERVICES = "collegeDiaryRemmberMeKey";
	public static String REST_SERVICES_COOKIE_KEY = "collegeDiary_REST_SERVICE";
	public static int EXPIRYTIME = 1800;
	public static String DOMAIN = "http://localHost:8080/";
}
