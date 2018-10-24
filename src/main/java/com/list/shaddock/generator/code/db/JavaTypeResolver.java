package com.list.shaddock.generator.code.db;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JavaTypeResolver {

	protected static boolean forceBigDecimals;

	static final int NVARCHAR = -9;
	static final int NCHAR = -15;
	static final int NCLOB = 2011;

	protected static Map<Integer, JdbcTypeInformation> typeMap = new HashMap<Integer, JdbcTypeInformation>();

	static {
		typeMap.put(Types.ARRAY, new JdbcTypeInformation("ARRAY", Object.class.getSimpleName()));
		typeMap.put(Types.BIGINT, new JdbcTypeInformation("BIGINT", Long.class.getSimpleName()));
		typeMap.put(Types.BINARY, new JdbcTypeInformation("BINARY", "byte[]"));
		typeMap.put(Types.BIT, new JdbcTypeInformation("BIT", Boolean.class.getSimpleName()));
		typeMap.put(Types.BLOB, new JdbcTypeInformation("BLOB", "byte[]"));
		typeMap.put(Types.BOOLEAN, new JdbcTypeInformation("BOOLEAN", Boolean.class.getSimpleName()));
		typeMap.put(Types.CHAR, new JdbcTypeInformation("CHAR", String.class.getSimpleName()));
		typeMap.put(Types.CLOB, new JdbcTypeInformation("CLOB", String.class.getSimpleName()));
		typeMap.put(Types.DATALINK, new JdbcTypeInformation("DATALINK", Object.class.getSimpleName()));
		typeMap.put(Types.DATE, new JdbcTypeInformation("DATE", Date.class.getSimpleName()));
		typeMap.put(Types.DECIMAL, new JdbcTypeInformation("DECIMAL", BigDecimal.class.getSimpleName()));
		typeMap.put(Types.DISTINCT, new JdbcTypeInformation("DISTINCT", Object.class.getSimpleName()));
		typeMap.put(Types.DOUBLE, new JdbcTypeInformation("DOUBLE", Double.class.getSimpleName()));
		typeMap.put(Types.FLOAT, new JdbcTypeInformation("FLOAT", Float.class.getSimpleName()));
		typeMap.put(Types.INTEGER, new JdbcTypeInformation("INTEGER", Integer.class.getSimpleName()));
		typeMap.put(Types.JAVA_OBJECT, new JdbcTypeInformation("JAVA_OBJECT", Object.class.getSimpleName()));
		typeMap.put(Types.LONGVARBINARY, new JdbcTypeInformation("LONGVARBINARY", "byte[]"));
		typeMap.put(Types.LONGNVARCHAR, new JdbcTypeInformation("LONGNVARCHAR", String.class.getSimpleName()));
		typeMap.put(Types.LONGVARCHAR, new JdbcTypeInformation("LONGVARCHAR", String.class.getSimpleName()));
		typeMap.put(Types.NCHAR, new JdbcTypeInformation("NCHAR", String.class.getSimpleName()));
		typeMap.put(Types.NCLOB, new JdbcTypeInformation("NCLOB", String.class.getSimpleName()));
		typeMap.put(Types.NVARCHAR, new JdbcTypeInformation("NVARCHAR", String.class.getSimpleName()));
		typeMap.put(Types.NULL, new JdbcTypeInformation("NULL", Object.class.getSimpleName()));
		typeMap.put(Types.OTHER, new JdbcTypeInformation("OTHER", Object.class.getSimpleName()));
		typeMap.put(Types.NUMERIC, new JdbcTypeInformation("NUMERIC", Object.class.getSimpleName()));
		typeMap.put(Types.REAL, new JdbcTypeInformation("REAL", Float.class.getSimpleName()));
		typeMap.put(Types.REF, new JdbcTypeInformation("REF", Object.class.getSimpleName()));
		typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT", Short.class.getSimpleName()));
		typeMap.put(Types.STRUCT, new JdbcTypeInformation("STRUCT", Object.class.getSimpleName()));
		typeMap.put(Types.TIME, new JdbcTypeInformation("TIME", Date.class.getSimpleName()));
		typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP", Date.class.getSimpleName()));
		typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", Byte.class.getSimpleName()));
		typeMap.put(Types.VARBINARY, new JdbcTypeInformation("VARBINARY", "byte[]"));
		typeMap.put(Types.VARCHAR, new JdbcTypeInformation("VARCHAR", String.class.getSimpleName()));
	}

	private JavaTypeResolver() {
		super();
	}

	public static String calculateJavaType(DbColumn column) {
		String answer;

		int jdbcType = JdbcTypeResolver.getJdbcType(column.getType());
		JdbcTypeInformation jdbcTypeInformation = typeMap.get(jdbcType);

		if (jdbcTypeInformation == null) {
			switch (jdbcType) {
			case Types.DECIMAL:
			case Types.NUMERIC:
				if (Integer.parseInt(column.getPrecision()) > 0 || Integer.parseInt(column.getLength()) > 18
						|| forceBigDecimals) {
					answer = Long.class.getSimpleName();
				} else if (Integer.parseInt(column.getLength()) > 9) {
					answer = Long.class.getSimpleName();
				} else if (Integer.parseInt(column.getLength()) > 4) {
					answer = Integer.class.getSimpleName();
				} else {
					answer = Short.class.getSimpleName();
				}
				break;
			default:
				answer = null;
				break;
			}
		} else {
			answer = jdbcTypeInformation.getFullyQualifiedJavaType();
		}

		return answer;
	}
	
	
	public static String calculateJdbcTypeName(DbColumn column) {
		String answer;

		int jdbcType = JdbcTypeResolver.getJdbcType(column.getType());
		JdbcTypeInformation jdbcTypeInformation = typeMap.get(jdbcType);

		if (jdbcTypeInformation == null) {
			switch (jdbcType) {
			case Types.DECIMAL:
				answer = "DECIMAL";
				break;
			case Types.NUMERIC:
				answer = "NUMBERIC";
				break;
			default:
				answer = null;
				break;
			}
		} else {
			answer = jdbcTypeInformation.getJdbcTypeName();
		}

		return answer;
	}

	private static class JdbcTypeInformation {
		private String jdbcTypeName;

		private String fullyQualifiedJavaType;

		public JdbcTypeInformation(String jdbcTypeName, String fullyQualifiedJavaType) {
			super();
			this.jdbcTypeName = jdbcTypeName;
			this.fullyQualifiedJavaType = fullyQualifiedJavaType;
		}

		public String getJdbcTypeName() {
			return jdbcTypeName;
		}

		public void setJdbcTypeName(String jdbcTypeName) {
			this.jdbcTypeName = jdbcTypeName;
		}

		public String getFullyQualifiedJavaType() {
			return fullyQualifiedJavaType;
		}

		public void setFullyQualifiedJavaType(String fullyQualifiedJavaType) {
			this.fullyQualifiedJavaType = fullyQualifiedJavaType;
		}

	}
}
