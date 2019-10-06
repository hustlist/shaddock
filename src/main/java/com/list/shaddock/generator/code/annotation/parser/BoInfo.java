package com.list.shaddock.generator.code.annotation.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析实体注解后，汇总的分析信息
 * 
 * @author Administrator
 *
 */
public class BoInfo {
	
	//路径信息
	private String rootSrcPath;
	
	//业务模块相关信息
	private String packageName;
	private String daoName;
	private String daoInfName;
	private String serviceName;
	private String serviceInfName;
	private String controllerName;
	private String formDataName;
	private String jspName;
	
	//系统名，一级组件名
	private String appName;
	private String compName;
	
	//业务实体对象名称
	private String boName;
	
	//表名
	private String tableName;
	
	private List<BoField> pks = new ArrayList<>(100);
	
	private List<BoField> fields = new ArrayList<>(100);
	
	public List<BoField> getPks() {
		return pks;
	}

	public void setPks(List<BoField> pks) {
		this.pks = pks;
	}

	public List<BoField> getFields() {
		return fields;
	}

	public void setFields(List<BoField> fields) {
		this.fields = fields;
	}

	public String getRootSrcPath() {
		return rootSrcPath;
	}

	public void setRootSrcPath(String rootSrcPath) {
		this.rootSrcPath = rootSrcPath;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getDaoName() {
		return daoName;
	}

	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}

	public String getDaoInfName() {
		return daoInfName;
	}

	public void setDaoInfName(String daoInfName) {
		this.daoInfName = daoInfName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceInfName() {
		return serviceInfName;
	}

	public void setServiceInfName(String serviceInfName) {
		this.serviceInfName = serviceInfName;
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public String getFormDataName() {
		return formDataName;
	}

	public void setFormDataName(String formDataName) {
		this.formDataName = formDataName;
	}

	public String getJspName() {
		return jspName;
	}

	public void setJspName(String jspName) {
		this.jspName = jspName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getBoName() {
		return boName;
	}

	public void setBoName(String boName) {
		this.boName = boName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
