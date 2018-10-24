package com.list.shaddock.generator.code.db;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
	
	private String driverClass;
	
	private String connectionUrl;
	
	private String userId;
	
	private String password;
	
	private String table;
	
	private String rootSrcPath;
	
	private String javaPackage;
	
	private String boName;
	
	public DBConfig() {
		super();
	}
	
	public DBConfig(String file) {
		super();
		loadProp(file);
	}

	private void loadProp(String file) {
		try {
			
			InputStream is = new FileInputStream(file);
			Properties prop = new Properties();
			prop.load(is);
			is.close();
			
			this.driverClass = prop.getProperty("driverClass");
			this.connectionUrl = prop.getProperty("connectionURL");
			this.userId = prop.getProperty("userId");
			this.password = prop.getProperty("password");
			this.table = prop.getProperty("table");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getRootSrcPath() {
		return rootSrcPath;
	}

	public void setRootSrcPath(String rootSrcPath) {
		this.rootSrcPath = rootSrcPath;
	}

	public String getJavaPackage() {
		return javaPackage;
	}

	public void setJavaPackage(String javaPackage) {
		this.javaPackage = javaPackage;
	}

	public String getBoName() {
		return boName;
	}

	public void setBoName(String boName) {
		this.boName = boName;
	}
	
}
