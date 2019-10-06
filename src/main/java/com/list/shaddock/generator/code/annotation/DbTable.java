package com.list.shaddock.generator.code.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface DbTable {
	
	public String name() default "table1";

}
