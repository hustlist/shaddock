package com.list.shaddock.generator.code.db;

public class DbColumn {
	
	/**
	 * 数据库字段名
	 */
	private String name;
	
	/**
	 * 数据库字段类型
	 */
	public String type;
	
	/**
	 * 数据库字段长度
	 */
	public String length;
	
	/**
	 * 数据库字段对应精度
	 */
	public String precision;
	
	/**
	 * 是否主键标志
	 */
	public boolean pk = false;
	
	/**
	 * 数据库字段注释
	 */
	public String comment;
	
	/**
	 * 对应java属性类型
	 */
	public String javaType;
	
	/**
	 * 对应java属性名
	 */
	public String javaName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public boolean isPk() {
		return pk;
	}

	public void setPk(boolean pk) {
		this.pk = pk;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getJavaName() {
		return javaName;
	}

	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}
	
}
