package com.list.shaddock.generator.codegen;

import java.util.List;

import com.list.shaddock.generator.code.db.DBConfig;
import com.list.shaddock.generator.code.db.DbUtil;

public class Step1 {

	public static void run() {
		
		//配置MySQL数据库连接
		DBConfig config = new DBConfig();
		config.setDriverClass("com.mysql.jdbc.Driver");
		config.setConnectionUrl("jdbc:mysql://192.168.1.182/uac?useUnicode=true&amp;characterEncoding=UTF-8");
		config.setUserId("root");
		config.setPassword("root");
		
		//设置代码生成绝对路径和包路径
		//注意生成源代码的路径，要添加到本项目工程的代码构建路径中
		String srcPath = "F:/workspace/javaspace/springboot/list-shaddock/src/main/java";
		String javaPackage = "com.list.demo";
		
		DbUtil dbUtil = new DbUtil(config);
		
//		List<String> tables = new ArrayList<String>();
		List<String> tables = dbUtil.getTables();
		
		for(String table:tables) {
			//设置实际表名
			config.setTable(table);
			//设置代码生成路径，这里是绝对路径
			config.setRootSrcPath(srcPath);
			
			String boName = table.replaceFirst("frm_", "");
			boName = table.replaceFirst("hr_", "");
			if(boName.endsWith("_v") || boName.endsWith("_V")) {
				boName = boName.substring(0,boName.length() - 2);
			}
			
			boName = dbUtil.upperFirstChar(dbUtil.DealSpecialChar(boName.toLowerCase()));
			System.out.println(table + "->" + boName);
			
			config.setBoName(boName);
			config.setJavaPackage(javaPackage + "." + boName.toLowerCase());
			
			dbUtil.db2JavaBean();
		}
		
	}

}
