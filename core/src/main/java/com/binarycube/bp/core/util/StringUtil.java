package com.binarycube.bp.core.util;

public class StringUtil {
	
	//Ensures a string is returned and not null
	public static String getString(String src) {
		return src == null ? "" : src;
	}
}
