package com.list.shaddock.generator.code.annotation.parser;


public class BoField {
	
	private String fieldType;
	private String fieldName;
	
	private String dbFieldType;
	private String dbFieldName;
	private String dbFieldLen;
	private String dbFieldPrecision;
	private String dbFieldComment;
	private boolean isDbPK;
	
	
	private boolean isMyField;
	
	private boolean isSearch;
	private String inputType;
	private boolean isVisible;
	private boolean isOrder;
	private String defaultValue;
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getDbFieldType() {
		return dbFieldType;
	}
	public void setDbFieldType(String dbFieldType) {
		this.dbFieldType = dbFieldType;
	}
	public String getDbFieldName() {
		return dbFieldName;
	}
	public void setDbFieldName(String dbFieldName) {
		this.dbFieldName = dbFieldName;
	}
	public String getDbFieldLen() {
		return dbFieldLen;
	}
	public void setDbFieldLen(String dbFieldLen) {
		this.dbFieldLen = dbFieldLen;
	}
	public String getDbFieldPrecision() {
		return dbFieldPrecision;
	}
	public void setDbFieldPrecision(String dbFieldPrecision) {
		this.dbFieldPrecision = dbFieldPrecision;
	}
	public String getDbFieldComment() {
		return dbFieldComment;
	}
	public void setDbFieldComment(String dbFieldComment) {
		this.dbFieldComment = dbFieldComment;
	}
	public boolean isDbPK() {
		return isDbPK;
	}
	public void setDbPK(boolean isDbPK) {
		this.isDbPK = isDbPK;
	}
	public boolean isMyField() {
		return isMyField;
	}
	public void setMyField(boolean isMyField) {
		this.isMyField = isMyField;
	}
	public boolean isSearch() {
		return isSearch;
	}
	public void setSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}
	public String getInputType() {
		return inputType;
	}
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	public boolean isOrder() {
		return isOrder;
	}
	public void setOrder(boolean isOrder) {
		this.isOrder = isOrder;
	}
	public String getDefaultValue() {
		if(this.defaultValue == null) {
			int dblen = 0;
			try {
				dblen = Integer.parseInt(dbFieldLen);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			if(isDbPK) {
				if("java.util.Date".equalsIgnoreCase(fieldType)) {
					defaultValue = "\"2018-05-27 12:00:00\"";
				}else if("java.lang.String".equalsIgnoreCase(fieldType)) {
					String tempStr = "abcdef";
					defaultValue = "\"" + tempStr.substring(0, (dblen > tempStr.length())?tempStr.length():dblen)+ "\"";
				}else {
					defaultValue = "123";
				}
			}else {
				if("java.util.Date".equalsIgnoreCase(fieldType)) {
					defaultValue = "\"2018-05-27 12:00:00\"";
				}else if("java.lang.String".equalsIgnoreCase(fieldType)) {
					if(fieldName != null && (fieldName.toLowerCase().indexOf("flag") > -1 || fieldName.toLowerCase().indexOf("enable") > -1)) {
						defaultValue = "\"Y\"";
					}else {
						String tempStr = null;
						if(dbFieldComment != null && dbFieldLen != null) {
							tempStr = dbFieldComment + "_测试数据";
						}else {
							tempStr="测试数据";
						}
						defaultValue = "\"" + tempStr.substring(0, (dblen > tempStr.length())?tempStr.length():dblen)+ "\"";
					}
				}else {
					defaultValue = "123";
				}
			}
		}
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
}
