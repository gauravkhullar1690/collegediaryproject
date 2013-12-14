package com.collegediary.common;

public class StringUtils
{
	public static boolean isNotNullOrNotEmpty(String str){
		if(str != null && str.trim().length() > 0){
			return true;
		} else {
			return false;
		}
	}
}
