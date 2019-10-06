package com.list.shaddock.generator.code.freemarker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import com.list.shaddock.generator.code.annotation.parser.BoInfo;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class CodeUtil {

	private BoInfo boInfo;



	public BoInfo getBoInfo() {
		return boInfo;
	}

	public void setBoInfo(BoInfo boInfo) {
		this.boInfo = boInfo;
	}

	public CodeUtil(BoInfo boInfo) {
		this.boInfo = boInfo;
	}

	public void createJvaBean() {
		Configuration cfg = new Configuration();
		try {
			//设置模板所在classpath目录
			cfg.setClassForTemplateLoading(CodeUtil.class, "../template");
			//添加一个“宏”共享变量，在模板中使用，用来将属性名首字母大写
			cfg.setSharedVariable("upperFC", new UpperFirstCharacter());
			//添加一个“宏”共享变量，在模板中使用，用来将属性名首字母小写
			cfg.setSharedVariable("lowerFC", new LowerFirstCharacter());
			//添加一个“宏”共享变量，在模板中使用，用来处理下划线字符
			cfg.setSharedVariable("lowerAC", new LowerAllCharacter());
			//制定模板
			Template template = cfg.getTemplate("javabean.ftl");

			//输出文件路径=dbConfig.getOutFilePath + 包路径
			String filePath = boInfo.getRootSrcPath();
			filePath += ((filePath.endsWith("/")||filePath.endsWith("\\"))?"":"/");
			filePath += boInfo.getPackageName().replace('.', '/');
			filePath += "/model/";

			//判断文件是否存在，不存在，创建文件路径
			File tempFile = new File(filePath);
			if(!tempFile.exists()) {
				tempFile.mkdirs();
			}

			FileOutputStream fos = new FileOutputStream(new File(filePath + boInfo.getBoName() + ".java"));

			//模拟数据源
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("boInfo", boInfo);

			//根据模板生成代码
			template.process(map, new OutputStreamWriter(fos,"utf-8"));

			fos.flush();
			fos.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void createDaoInf() {
		Configuration cfg = new Configuration();
		try {
			//设置模板所在classpath目录
			cfg.setClassForTemplateLoading(CodeUtil.class, "../template");
			//添加一个“宏”共享变量，在模板中使用，用来将属性名首字母大写
			cfg.setSharedVariable("upperFC", new UpperFirstCharacter());
			//添加一个“宏”共享变量，在模板中使用，用来将属性名首字母小写
			cfg.setSharedVariable("lowerFC", new LowerFirstCharacter());
			//添加一个“宏”共享变量，在模板中使用，用来处理下划线字符
			cfg.setSharedVariable("lowerAC", new LowerAllCharacter());
			//制定模板
			Template template = cfg.getTemplate("javadaointerface.ftl");

			//输出文件路径=dbConfig.getOutFilePath + 包路径
			String filePath = boInfo.getRootSrcPath();
			filePath += ((filePath.endsWith("/")||filePath.endsWith("\\"))?"":"/");
			filePath += boInfo.getPackageName().replace('.', '/');
			filePath += "/dao/";

			//判断文件是否存在，不存在，创建文件路径
			File tempFile = new File(filePath);
			if(!tempFile.exists()) {
				tempFile.mkdirs();
			}

			FileOutputStream fos = new FileOutputStream(new File(filePath + boInfo.getDaoInfName() + ".java"));

			//模拟数据源
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("boInfo", boInfo);

			//根据模板生成代码
			template.process(map, new OutputStreamWriter(fos,"utf-8"));

			fos.flush();
			fos.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void createOracleMapper() {
		Configuration cfg = new Configuration();
		try {
			//设置模板所在classpath目录
			cfg.setClassForTemplateLoading(CodeUtil.class, "../template");
			//添加一个“宏”共享变量，在模板中使用，用来将属性名首字母大写
			cfg.setSharedVariable("upperFC", new UpperFirstCharacter());
			//添加一个“宏”共享变量，在模板中使用，用来将属性名首字母小写
			cfg.setSharedVariable("lowerFC", new LowerFirstCharacter());
			//添加一个“宏”共享变量，在模板中使用，用来处理下划线字符
			cfg.setSharedVariable("lowerAC", new LowerAllCharacter());
			//制定模板
			Template template = cfg.getTemplate("oraclemybatismapper.ftl");

			//输出文件路径=dbConfig.getOutFilePath + 包路径
			String filePath = boInfo.getRootSrcPath();
			filePath += ((filePath.endsWith("/")||filePath.endsWith("\\"))?"":"/");
			filePath += boInfo.getPackageName().replace('.', '/');
			filePath += "/model/";

			//判断文件是否存在，不存在，创建文件路径
			File tempFile = new File(filePath);
			if(!tempFile.exists()) {
				tempFile.mkdirs();
			}

			FileOutputStream fos = new FileOutputStream(new File(filePath + boInfo.getDaoInfName() + ".xml"));

			//模拟数据源
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("boInfo", boInfo);

			//根据模板生成代码
			template.process(map, new OutputStreamWriter(fos,"utf-8"));

			fos.flush();
			fos.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void createMysqlMapper() {
		Configuration cfg = new Configuration();
		try {
			//设置模板所在classpath目录
			cfg.setClassForTemplateLoading(CodeUtil.class, "../template");
			//添加一个“宏”共享变量，在模板中使用，用来将属性名首字母大写
			cfg.setSharedVariable("upperFC", new UpperFirstCharacter());
			//添加一个“宏”共享变量，在模板中使用，用来将属性名首字母小写
			cfg.setSharedVariable("lowerFC", new LowerFirstCharacter());
			//添加一个“宏”共享变量，在模板中使用，用来处理下划线字符
			cfg.setSharedVariable("lowerAC", new LowerAllCharacter());
			//制定模板
			Template template = cfg.getTemplate("mysqlmybatismapper.ftl");

			//输出文件路径=dbConfig.getOutFilePath + 包路径
			String filePath = boInfo.getRootSrcPath();
			filePath += ((filePath.endsWith("/")||filePath.endsWith("\\"))?"":"/");
			filePath += boInfo.getPackageName().replace('.', '/');
			filePath += "/dao/";

			//判断文件是否存在，不存在，创建文件路径
			File tempFile = new File(filePath);
			if(!tempFile.exists()) {
				tempFile.mkdirs();
			}

			FileOutputStream fos = new FileOutputStream(new File(filePath + boInfo.getDaoInfName() + ".xml"));

			//模拟数据源
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("boInfo", boInfo);

			//根据模板生成代码
			template.process(map, new OutputStreamWriter(fos,"utf-8"));

			fos.flush();
			fos.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void createServiceInf() {
		Configuration cfg = new Configuration();
		try {
			//设置模板所在classpath目录
			cfg.setClassForTemplateLoading(CodeUtil.class, "../template");
			//添加一个“宏”共享变量，在模板中使用，用来将属性名首字母大写
			cfg.setSharedVariable("upperFC", new UpperFirstCharacter());
			//添加一个“宏”共享变量，在模板中使用，用来将属性名首字母小写
			cfg.setSharedVariable("lowerFC", new LowerFirstCharacter());
			//添加一个“宏”共享变量，在模板中使用，用来处理下划线字符
			cfg.setSharedVariable("lowerAC", new LowerAllCharacter());
			//制定模板
			Template template = cfg.getTemplate("javaserviceinterface.ftl");

			//输出文件路径=dbConfig.getOutFilePath + 包路径
			String filePath = boInfo.getRootSrcPath();
			filePath += ((filePath.endsWith("/")||filePath.endsWith("\\"))?"":"/");
			filePath += boInfo.getPackageName().replace('.', '/');
			filePath += "/service/";

			//判断文件是否存在，不存在，创建文件路径
			File tempFile = new File(filePath);
			if(!tempFile.exists()) {
				tempFile.mkdirs();
			}

			FileOutputStream fos = new FileOutputStream(new File(filePath + boInfo.getServiceInfName() + ".java"));

			//模拟数据源
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("boInfo", boInfo);

			//根据模板生成代码
			template.process(map, new OutputStreamWriter(fos,"utf-8"));

			fos.flush();
			fos.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void createService() {
		Configuration cfg = new Configuration();
		try {
			//设置模板所在classpath目录
			cfg.setClassForTemplateLoading(CodeUtil.class, "../template");
			//添加一个“宏”共享变量，在模板中使用，用来将属性名首字母大写
			cfg.setSharedVariable("upperFC", new UpperFirstCharacter());
			//添加一个“宏”共享变量，在模板中使用，用来将属性名首字母小写
			cfg.setSharedVariable("lowerFC", new LowerFirstCharacter());
			//添加一个“宏”共享变量，在模板中使用，用来处理下划线字符
			cfg.setSharedVariable("lowerAC", new LowerAllCharacter());
			//制定模板
			Template template = cfg.getTemplate("javaservice.ftl");

			//输出文件路径=dbConfig.getOutFilePath + 包路径
			String filePath = boInfo.getRootSrcPath();
			filePath += ((filePath.endsWith("/")||filePath.endsWith("\\"))?"":"/");
			filePath += boInfo.getPackageName().replace('.', '/');
			filePath += "/service/";

			//判断文件是否存在，不存在，创建文件路径
			File tempFile = new File(filePath);
			if(!tempFile.exists()) {
				tempFile.mkdirs();
			}

			FileOutputStream fos = new FileOutputStream(new File(filePath + boInfo.getServiceName() + ".java"));

			//模拟数据源
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("boInfo", boInfo);

			//根据模板生成代码
			template.process(map, new OutputStreamWriter(fos,"utf-8"));

			fos.flush();
			fos.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void createController() {
		Configuration cfg = new Configuration();
		try {
			//设置模板所在classpath目录
			cfg.setClassForTemplateLoading(CodeUtil.class, "../template");
			//添加一个“宏”共享变量，在模板中使用，用来将属性名首字母大写
			cfg.setSharedVariable("upperFC", new UpperFirstCharacter());
			//添加一个“宏”共享变量，在模板中使用，用来将属性名首字母小写
			cfg.setSharedVariable("lowerFC", new LowerFirstCharacter());
			//添加一个“宏”共享变量，在模板中使用，用来处理下划线字符
			cfg.setSharedVariable("lowerAC", new LowerAllCharacter());
			//制定模板
			Template template = cfg.getTemplate("javacontroller.ftl");

			//输出文件路径=dbConfig.getOutFilePath + 包路径
			String filePath = boInfo.getRootSrcPath();
			filePath += ((filePath.endsWith("/")||filePath.endsWith("\\"))?"":"/");
			filePath += boInfo.getPackageName().replace('.', '/');
			filePath += "/controller/";

			//判断文件是否存在，不存在，创建文件路径
			File tempFile = new File(filePath);
			if(!tempFile.exists()) {
				tempFile.mkdirs();
			}

			FileOutputStream fos = new FileOutputStream(new File(filePath + boInfo.getControllerName() + ".java"));

			//模拟数据源
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("boInfo", boInfo);

			//根据模板生成代码
			template.process(map, new OutputStreamWriter(fos,"utf-8"));

			fos.flush();
			fos.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
