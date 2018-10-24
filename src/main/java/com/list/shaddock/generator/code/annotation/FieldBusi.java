package com.list.shaddock.generator.code.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface FieldBusi {
	
	public boolean search() default false;
	
	public boolean visible() default true;
	
	public boolean order() default false;

}
