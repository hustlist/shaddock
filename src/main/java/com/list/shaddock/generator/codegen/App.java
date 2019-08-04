package com.list.shaddock.generator.codegen;

import java.util.ArrayList;
import java.util.List;

import com.list.shaddock.generator.code.annotation.parser.BoField;
import com.list.shaddock.generator.code.annotation.parser.BoInfo;
import com.list.shaddock.generator.code.db.DBConfig;
import com.list.shaddock.generator.code.db.DbColumn;
import com.list.shaddock.generator.code.db.DbUtil;
import com.list.shaddock.generator.code.freemarker.CodeUtil;

public class App {

	public static void main(String[] args) {
		run();
//		Step1.run();
		// Step2.run();
		// Step3.run();
	}

	/**
	 * 根据数据库实体对象直接生成controller/service和dao代码
	 */
	public static void run() {
		// 配置MySQL数据库连接
		DBConfig config = new DBConfig();
		config.setDriverClass("com.mysql.cj.jdbc.Driver");
		config.setConnectionUrl("jdbc:mysql://192.168.1.184/uat?useUnicode=true&characterEncoding=UTF-8&nullCatalogMeansCurrent=true");
		config.setUserId("uat");
		config.setPassword("Uat@123456");

		// 设置代码生成绝对路径和包路径
		// 注意生成源代码的路径，要添加到本项目工程的代码构建路径中
		String srcPath = "D:\\workspace\\ideajavaspace\\list\\demo\\src\\main\\java";
		String javaPackage = "com.list.demo";

		DbUtil dbUtil = new DbUtil(config);

		 List<String> tables = new ArrayList<String>();
		tables.add("direct_intent_info");
//		List<String> tables = dbUtil.getTables();

		for (String table : tables) {
			// 设置实际表名
			config.setTable(table);
			// 设置代码生成路径，这里是绝对路径
			config.setRootSrcPath(srcPath);

			String boName = table.replaceFirst("frm_", "");
			boName = table.replaceFirst("hr_", "");
			if (boName.endsWith("_v") || boName.endsWith("_V")) {
				boName = boName.substring(0, boName.length() - 2);
			}

			boName = dbUtil.upperFirstChar(dbUtil.DealSpecialChar(boName.toLowerCase()));
			System.out.println(table + "->" + boName);

			config.setBoName(boName);
			config.setJavaPackage(javaPackage + "." + boName.toLowerCase());

			// 对dbconfig做一些分析
			if (config.getBoName() == null && config.getTable() != null) {
				// 如果没有设置BoName,则根据表名设置
				config.setBoName(upperFirstChar(DealSpecialChar(config.getTable().toLowerCase())));
			}

			List<DbColumn> dbColumns = dbUtil.getColumns();

			BoInfo boInfo = new BoInfo();

			boInfo.setRootSrcPath(config.getRootSrcPath());
			boInfo.setPackageName(config.getJavaPackage());
			boInfo.setBoName(config.getBoName());
			boInfo.setDaoName(config.getBoName() + "Dao");
			boInfo.setDaoInfName(config.getBoName() + "Dao");
			boInfo.setServiceName(config.getBoName() + "Service");
			boInfo.setServiceInfName("I" + config.getBoName() + "Service");
			boInfo.setControllerName(config.getBoName() + "Controller");
			boInfo.setFormDataName(config.getBoName() + "FormData");
			boInfo.setJspName(config.getBoName() + "List.jsp");

			boInfo.setTableName(config.getTable().toLowerCase());

			if (boInfo.getPackageName() != null) {
				String packageName = boInfo.getPackageName();
				String[] names = packageName.split("\\.");
				if (names != null && names.length >= 3) {
					String compName = names[names.length - 2];
					String appName = names[names.length - 3];

					boInfo.setCompName(compName);
					boInfo.setAppName(appName);
				}
			}

			for (DbColumn field : dbColumns) {
				BoField boField = new BoField();
				boField.setFieldName(field.getJavaName());
				boField.setFieldType(field.getJavaType());
				boField.setMyField(true);

				boField.setDbFieldName(field.getName());
				boField.setDbFieldType(field.getType());
				boField.setDbFieldLen(field.getLength());
				boField.setDbFieldPrecision(field.getPrecision());
				boField.setDbPK(field.isPk());
				boField.setDbFieldComment(field.getComment());

				boField.setSearch(true);
				boField.setVisible(true);
				boField.setOrder(true);

				boInfo.getFields().add(boField);
			}

			for (BoField boField : boInfo.getFields()) {
				if (boField.isDbPK()) {
					boInfo.getPks().add(boField);
				}
			}
			if (boInfo.getBoName() == null || boInfo.getPackageName() == null || boInfo.getFields() == null
					|| boInfo.getFields().size() == 0) {
				continue;
			}
			
			System.out.println("包名路径" + boInfo.getPackageName());

			boolean hasPk = false;
			for (BoField field : boInfo.getFields()) {
				if (field.isDbPK()) {
					hasPk = true;
					break;
				}
			}

			if (!hasPk) {
				System.out.println("检测不到逻辑逐渐，必须在实体类注解中设置一个属性为逻辑逐渐，才能自动生成代码");
				continue;
			}

			CodeUtil codeUtil = new CodeUtil(boInfo);
			codeUtil.createJvaBean();
			codeUtil.createDaoInf();

			codeUtil.createMysqlMapper();
			codeUtil.createServiceInf();
			codeUtil.createService();
			codeUtil.createController();

		}

	}

	public static String DealSpecialChar(String param) {
		String ret = null;

		if (param != null && param.length() > 0) {
			String[] strs = param.split("_");
			ret = strs[0];
			for (int i = 1; i < strs.length; i++) {
				if (strs[i] != null && strs[i].length() > 0) {
					ret += upperFirstChar(strs[i]);
				}
			}
		}
		return ret;
	}

	public static String upperFirstChar(String param) {
		String ret = null;

		if (param != null && param.length() > 0) {
			ret = param.substring(0, 1).toUpperCase() + param.substring(1);
		}

		return ret;
	}

}
