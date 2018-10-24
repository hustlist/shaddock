package com.list.shaddock.common.datasource;

public class DatabaseContextHolder {
	
	public static final String UAC_DB = "uacdb";
	
	public static final String CMS_DB = "cmsdb";
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static ThreadLocal<String> getContextholder() {
		return contextHolder;
	}

	public static void setDatabaseType(String type) {
		contextHolder.set(type);
	}
	
	public static void clear() {
		contextHolder.remove();
	}
	
}
