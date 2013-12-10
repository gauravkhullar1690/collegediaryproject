package com.collegediary.platform.hbm;

public class StringUtils
{
	public static Boolean  isNotNullOrNotEmpty(String str)
	{
		if( null == str || str.equals("")){
			return false;
		}
		return true;
	}
}
