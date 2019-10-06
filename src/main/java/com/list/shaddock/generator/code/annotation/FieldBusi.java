package com.list.shaddock.generator.code.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface FieldBusi {
	
	public boolean search() default false;
	
	public boolean visible() default true;
	
	public boolean order() default false;

}
