package com.list.shaddock.generator.codegen;

import com.list.shaddock.generator.code.annotation.parser.BoField;
import com.list.shaddock.generator.code.annotation.parser.BoInfo;
import com.list.shaddock.generator.code.annotation.parser.Parser;
import com.list.shaddock.generator.code.freemarker.CodeUtil;
import com.list.shaddock.generator.code.java.PackageUtil;

import java.util.List;

public class Step2 {

	public static void run() {

		try {
		//设置父包路径，递归扫描所有子包
		String javaPackage = "com.list.demo";
		//设置数据库格式
		String dbType = "mysql";
		
		List<String> classNames = PackageUtil.getClassName(javaPackage,true);
		if(classNames != null) {
			for(String className:classNames) {
				System.out.println(className);
				
				//取出子包名和类名
				int pos = className.lastIndexOf(".");
				pos = className.lastIndexOf(".",pos-1);
				String newName = null;
				if(pos > -1) {
					newName = javaPackage + className.substring(pos);
				}
				System.out.println(newName);
				
				BoInfo boInfo = Parser.parse(Class.forName(newName,true,Thread.currentThread().getContextClassLoader()));
				
				if(boInfo.getBoName() == null || boInfo.getPackageName() == null || boInfo.getFields() == null || boInfo.getFields().size() == 0) {
					continue;
				}
				
				boolean hasPk = false;
				for(BoField field:boInfo.getFields()) {
					if(field.isDbPK()) {
						hasPk = true;
						break;
					}
				}
				
				if(!hasPk) {
					System.out.println("检测不到逻辑逐渐，必须在实体类注解中设置一个属性为逻辑逐渐，才能自动生成代码");
					continue;
				}
				
				CodeUtil codeUtil = new CodeUtil(boInfo);
				codeUtil.createJvaBean();
				codeUtil.createDaoInf();
				
				if("oracle".equalsIgnoreCase(dbType)) {
					codeUtil.createOracleMapper();
				}else {
					codeUtil.createMysqlMapper();
				}
				codeUtil.createServiceInf();
				codeUtil.createService();
				codeUtil.createController();
			}
		}
		
	}catch(Exception ex) {
		ex.printStackTrace();
	}
		
	}

}
