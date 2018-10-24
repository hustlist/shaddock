package com.list.shaddock.generator.code.annotation.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.list.shaddock.generator.code.annotation.DbField;
import com.list.shaddock.generator.code.annotation.DbTable;
import com.list.shaddock.generator.code.annotation.FieldBusi;
import com.list.shaddock.generator.code.annotation.ModelBusi;
import com.list.shaddock.generator.code.util.StringUtil;

public class Parser {

	public static BoInfo parse(Class<?> clazz) {
		BoInfo boInfo = new BoInfo();
		
		ModelBusi modelBusi = clazz.getAnnotation(ModelBusi.class);
		if(modelBusi != null) {
			boInfo.setRootSrcPath(modelBusi.rootSrcPath());
			boInfo.setPackageName(modelBusi.packageName());
			boInfo.setBoName(modelBusi.boName());
			boInfo.setDaoInfName(modelBusi.datInfName());
			boInfo.setDaoName(modelBusi.daoName());
			boInfo.setServiceInfName(modelBusi.serviceInfName());
			boInfo.setServiceName(modelBusi.serviceName());
			boInfo.setControllerName(modelBusi.actionName());
			boInfo.setJspName(modelBusi.jspName());
		}
		
		if(StringUtil.isEmpty(boInfo.getPackageName())) {
			boInfo.setPackageName(clazz.getPackage().getName());
		}
		
		if(StringUtil.isEmpty(boInfo.getDaoName())) {
			boInfo.setDaoName(boInfo.getBoName() + "Dao");
		}
		
		if(StringUtil.isEmpty(boInfo.getDaoInfName())) {
			boInfo.setDaoInfName(boInfo.getBoName() + "Dao");
		}
		
		if(StringUtil.isEmpty(boInfo.getServiceName())) {
			boInfo.setServiceName(boInfo.getBoName() + "Service");
		}
		
		if(StringUtil.isEmpty(boInfo.getServiceInfName())) {
			boInfo.setServiceInfName("I" + boInfo.getBoName() + "Service");
		}
		
		if(StringUtil.isEmpty(boInfo.getControllerName())) {
			boInfo.setControllerName(boInfo.getBoName() + "Controller");
		}
		
		if(StringUtil.isEmpty(boInfo.getFormDataName())) {
			boInfo.setFormDataName(boInfo.getBoName() + "FormData");
		}
		
		if(StringUtil.isEmpty(boInfo.getJspName())) {
			boInfo.setJspName(boInfo.getBoName() + "List.jsp");
		}
		
		DbTable table = (DbTable)clazz.getAnnotation(DbTable.class);
		
		if(table != null) {
			boInfo.setTableName(table.name());
		}
		
		if(boInfo.getPackageName() != null) {
			String packageName = boInfo.getPackageName();
			String[] names = packageName.split("\\.");
			if(names != null && names.length >= 3) {
				String compName = names[names.length - 2];
				String appName = names[names.length -3];
				
				boInfo.setCompName(compName);
				boInfo.setAppName(appName);
			}
		}
		
		List<Field> fieldList = new ArrayList<Field>();
		Hashtable<String,Boolean> nameTable = new Hashtable<String,Boolean>();
		for(Field field:clazz.getDeclaredFields()) {
			fieldList.add(field);
			nameTable.put(field.getName(), true);
		}
		
		if(clazz.getSuperclass()!= null) {
			for(Field field:clazz.getSuperclass().getDeclaredFields()) {
				//排除同名属性
				if(nameTable.get(field.getName()) == null) {
					fieldList.add(field);
					nameTable.put(field.getName(), false);
				}
			}
		}
		
		for(Field field:fieldList) {
			BoField boField = new BoField();
			boField.setFieldName(field.getName());
			boField.setFieldType(field.getType().getSimpleName());
			boField.setMyField(nameTable.get(field.getName()));
			
			DbField dbField = field.getAnnotation(DbField.class);
			if(dbField != null) {
				boField.setDbFieldName(dbField.name());
				boField.setDbFieldType(dbField.type());
				boField.setDbFieldLen(dbField.length());
				boField.setDbFieldPrecision(dbField.precision());
				boField.setDbPK(dbField.pk());
				boField.setDbFieldComment(dbField.comment());
			}
			
			FieldBusi busi = field.getAnnotation(FieldBusi.class);
			if(busi != null) {
				boField.setSearch(busi.search());
				boField.setVisible(busi.visible());
				boField.setOrder(busi.order());
			}
			
			boInfo.getFields().add(boField);
		}
		
		for(BoField boField:boInfo.getFields()) {
			if(boField.isDbPK()) {
				boInfo.getPks().add(boField);
			}
		}
		
		return boInfo;
	}

}
