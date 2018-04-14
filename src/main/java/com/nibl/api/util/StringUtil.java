package com.nibl.api.util;

import java.util.regex.Pattern;

public class StringUtil {
	public static Pattern manipulateQuery(String query){
		if( null == query ) {
			return null;
		}

		String a = query.trim().replaceAll("\\s+", " ");

		StringBuilder sb = new StringBuilder();
		for( String b : a.split("\\s+") ) {
			sb.append(Pattern.quote(b));
			sb.append(".*");
		}

		// return Pattern.compile(Pattern.quote( query.trim().replaceAll(" ", ".*") ), Pattern.CASE_INSENSITIVE);
		return Pattern.compile(sb.toString(), Pattern.CASE_INSENSITIVE);
	}
}
