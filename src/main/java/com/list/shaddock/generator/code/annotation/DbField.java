package com.list.shaddock.generator.code.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface DbField {
	
	public String name() default "colum1";
	public String type() default "varchar";
	public String length() default "20";
	public String precision() default "0";
	public boolean pk() default false;
	public String comment() default "注释";

}
