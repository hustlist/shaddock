package com.list.shaddock.generator.code.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.list.shaddock.generator.code.freemarker.DealSpecialCharacter;
import com.list.shaddock.generator.code.freemarker.LowerFirstCharacter;
import com.list.shaddock.generator.code.freemarker.UpperFirstCharacter;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class DbUtil {

	DBConfig dbConfig = null;

	public DbUtil(DBConfig dbConfig) {
		this.dbConfig = dbConfig;
	}

	private Connection getConnection() {
		Connection connection = null;

		Properties props = new Properties();
		props.put("remarksReporting", "true");
		props.put("user", dbConfig.getUserId());
		props.put("password", dbConfig.getPassword());

		// 设置可以获取remarks信息
		props.setProperty("remarks", "true");
		// 设置可以获取tables remarks信息
		props.setProperty("userInformationSchema", "true");

		try {
			Class.forName(dbConfig.getDriverClass());
			connection = DriverManager.getConnection(dbConfig.getConnectionUrl(), props);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	
	private void closeConnection(Connection connection) {
		if(connection != null) {
			try {
				connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void closeResultSet(ResultSet resultSet) {
		if(resultSet != null) {
			try {
				resultSet.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取数据库中表列表
	 * @return 表列表
	 */
	public List<String> getTables(){
		List<String> tables = new ArrayList<String>();
		Connection connection = null;
		try {
			connection = getConnection();
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			
			String schema = dbConfig.getUserId();
			String tableName = dbConfig.getTable();
			String dataBaseType = databaseMetaData.getDatabaseProductName();
			if(dataBaseType != null && dataBaseType.toLowerCase().indexOf("oracle") >= 0) {
				schema = schema.toUpperCase();
				tableName = tableName.toUpperCase();
			}
			
			ResultSet rs = databaseMetaData.getTables(null, schema, tableName, null);
			
			while(rs.next()) {
				String table = rs.getString("TABLE_NAME");
				tables.add(table);
			}
			
			closeResultSet(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(connection);
		}
		
		return tables;
	}
	
	
	/**
	 * 获取数据库表字段
	 * @return
	 */
	public List<DbColumn> getColumns(){
		List<DbColumn> columns = new ArrayList<DbColumn>();
		
		String tableName = dbConfig.getTable();
		String schema = dbConfig.getUserId();
		
		Connection connection = null;
		
		try {
			connection = getConnection();
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			
			//获取表的字段信息
			ResultSet rs = databaseMetaData.getColumns(null, "%", tableName, "%");
			
			while(rs.next()) {
				DbColumn column =new DbColumn();
				
				column.setName(rs.getString("COLUMN_NAME"));
				column.setType(JdbcTypeResolver.getJdbcTypeName(rs.getInt("DATA_TYPE")));
				column.setLength(String.valueOf(rs.getInt("COLUMN_SIZE")));
				column.setPrecision(String.valueOf(rs.getInt("DECIMAL_DIGITS")));
				column.setComment(rs.getString("REMARKS"));
				
				column.setJavaType(JavaTypeResolver.calculateJavaType(column));
				column.setJavaName(DealSpecialChar(column.getName().toLowerCase()));
				
				columns.add(column);
			}
			closeResultSet(rs);
			
			//获取表的主键
			ResultSet pkrs = databaseMetaData.getPrimaryKeys(null, "%", tableName);
			while(pkrs.next()) {
				String pkColumn = pkrs.getString("COLUMN_NAME");
				if(pkColumn != null) {
					for(DbColumn column:columns) {
						if(pkColumn.equalsIgnoreCase(column.getName())) {
							column.setPk(true);
						}
					}
				}
			}
			closeResultSet(pkrs);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			closeConnection(connection);
		}
		
		return columns;
	}
	
	public void db2JavaBean() {
		//对dbconfig做一些分析
		if(dbConfig.getBoName() == null && dbConfig.getTable() != null) {
			//如果没有设置BoName,则根据表名设置
			dbConfig.setBoName(upperFirstChar(DealSpecialChar(dbConfig.getTable().toLowerCase())));
		}
		
		Configuration cfg = new Configuration();
		try {
			//设置模板所在classpath目录
			cfg.setClassForTemplateLoading(DbUtil.class, "../template");
			//添加一个“宏”共享变量，在模板中使用，用来将属性名首字母大写
			cfg.setSharedVariable("upperFC", new UpperFirstCharacter());
			//添加一个“宏”共享变量，在模板中使用，用来将属性名首字母小写
			cfg.setSharedVariable("lowerFC", new LowerFirstCharacter());
			//添加一个“宏”共享变量，在模板中使用，用来处理下划线字符
			cfg.setSharedVariable("dealSpecialC", new DealSpecialCharacter());
			//制定模板
			Template template = cfg.getTemplate("db2javabean.ftl");
			
			//输出文件路径=dbConfig.getOutFilePath + 包路径
			String filePath = dbConfig.getRootSrcPath();
			filePath += ((filePath.endsWith("/")||filePath.endsWith("\\"))?"":"/");
			filePath += dbConfig.getJavaPackage().replace('.', '/');
			filePath += "/";
			
			//判断文件是否存在，不存在，创建文件路径
			File tempFile = new File(filePath);
			if(!tempFile.exists()) {
				tempFile.mkdirs();
			}
			
			//文件名处理，实体对象+“_For_CodeBuilder”
			String fileName = upperFirstChar(DealSpecialChar(dbConfig.getTable().toLowerCase())) + "_For_CodeBuilder";
			
			List<DbColumn> dbColumns = getColumns();
			FileOutputStream fos = new FileOutputStream(new File(filePath + fileName + ".java"));
			
			//模拟数据源
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("dbTable",dbConfig.getTable().toLowerCase());
			map.put("FileName",fileName);
			map.put("dbColumns", dbColumns);
			map.put("dbconfig", dbConfig);
			
			//根据模板生成代码
			template.process(map, new OutputStreamWriter(fos,"utf-8"));
			
			fos.flush();
			fos.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public String upperFirstChar(String param) {
		String ret = null;
		
		if(param != null && param.length() > 0) {
			ret = param.substring(0, 1).toUpperCase() + param.substring(1);
		}
		
		return ret;
	}

	public String DealSpecialChar(String param) {
		String ret = null;
		
		if(param != null && param.length() > 0) {
			String[] strs = param.split("_");
			ret = strs[0];
			for(int i = 1; i < strs.length; i++) {
				if(strs[i] != null && strs[i].length() > 0) {
					ret += upperFirstChar(strs[i]);
				}
			}
		}
		return ret;
	}
	

}
