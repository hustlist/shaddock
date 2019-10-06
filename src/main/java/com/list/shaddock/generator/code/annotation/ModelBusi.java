package com.list.shaddock.generator.code.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface ModelBusi {
	
	/**
	 * "根名称"
	 * @return
	 */
	public String rootSrcPath() default "";
	
	/**
	 * 包名
	 * @return
	 */
	public String packageName() default "";
	
	/**
	 * 业务实体对象
	 * @return
	 */
	public String boName() default "";
	
	/**
	 * dao接口名称
	 * @return
	 */
	public String datInfName() default "";
	
	/**
	 * dao文件名称
	 * @return
	 */
	public String daoName() default "";
	
	/**
	 * 服务名称
	 * @return
	 */
	public String serviceName() default "";
	
	/**
	 * 服务接口名称
	 * @return
	 */
	public String serviceInfName() default "";
	
	/**
	 * "根名称"
	 * @return
	 */
	public String actionName() default "";
	
	/**
	 * "根名称"
	 * @return
	 */
	public String jspName() default "";
}
