package com.list.shaddock.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.list.shaddock.common.datasource.DatabaseContextHolder;
import com.list.shaddock.common.datasource.DynamicDataSource;

@Configuration
@PropertySource("classpath:datasource.properties")
@MapperScan(basePackages="com.list.**.dao")
@EnableTransactionManagement
public class MybatisBaseConfig  implements EnvironmentAware  {
	
	@Autowired
	private Environment env;

	public void setEnvironment(Environment environment) {
		this.env = environment;
	}
	
	/**
	 * 创建数据源（数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称，该名称也就是数据库的序号）
	 * @return
	 * @throws Exception
	 */
	@Bean
	public DataSource firstDataSource() throws Exception{
		DruidDataSource dataSource = new DruidDataSource();
		Properties props = new Properties();
		props.put("url", env.getProperty("jdbc1.url"));
		props.put("username", env.getProperty("jdbc1.username"));
		props.put("password", env.getProperty("jdbc1.password"));
		this.setCommonJDBCProperties(props);
		DruidDataSourceFactory.config(dataSource, props);
		return dataSource;
	}
	
	/**
	 * 创建数据源（数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称，该名称也就是数据库的序号）
	 * @return
	 * @throws Exception
	 */
	@Bean
	public DataSource secondDataSource() throws Exception{
		DruidDataSource dataSource = new DruidDataSource();
		Properties props = new Properties();
		dataSource.setUrl(env.getProperty("jdbc2.url"));
		props.put("username", env.getProperty("jdbc2.username"));
		props.put("password", env.getProperty("jdbc2.password"));
		this.setCommonJDBCProperties(props);
		DruidDataSourceFactory.config(dataSource, props);
		return dataSource;
	}
	
	@Bean
	@Primary
	public DynamicDataSource dataSource(@Qualifier("firstDataSource") DataSource firstDataSource,@Qualifier("secondDataSource") DataSource secondDataSource) {
		Map<Object,Object> targetDataSources = new HashMap<Object,Object>();
		targetDataSources.put(DatabaseContextHolder.UAC_DB, firstDataSource);
		targetDataSources.put(DatabaseContextHolder.CMS_DB, secondDataSource);
		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setTargetDataSources(targetDataSources);
		dataSource.setDefaultTargetDataSource(firstDataSource);
		return dataSource;
	}
	
//	@Bean(name="sqlSessionFaction")
//	public SqlSessionFactory sqlSessionFactoryBean(DynamicDataSource ds) {
//		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//		bean.setDataSource(ds);
//		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		try {
//			bean.setTypeAliasesPackage("com.list.**.model");
////			bean.setConfigLocation(resolver.getResource("classpath:mybatis-setting.xml"));
//			bean.setMapperLocations(resolver.getResources("classpath:com/list/**/*.xml"));
//			return bean.getObject();
//		}catch(Exception ex) {
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}
//	}
	
	
	@Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("firstDataSource") DataSource firstDataSource,@Qualifier("secondDataSource") DataSource secondDataSource) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(this.dataSource(firstDataSource, secondDataSource));
        fb.setTypeAliasesPackage("com.list.**.model");
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/list/**/*.xml"));
        return fb.getObject();
    }
	
	/**
	 * 配置事务管理
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception{
		return new DataSourceTransactionManager(dataSource);
	}

	private void setCommonJDBCProperties(Properties props) {
		props.put("driverClassName", env.getProperty("jdbc.driverClassName"));
		props.put("initialSize", env.getProperty("jdbc.initialSize"));
		props.put("minIdle", env.getProperty("jdbc.minIdle"));
		props.put("maxActive", env.getProperty("jdbc.maxActive"));
		props.put("maxWait", env.getProperty("jdbc.maxWait"));
		props.put("validationQuery", env.getProperty("jdbc.validationQuery"));
		props.put("testOnBorrow", env.getProperty("jdbc.testOnBorrow"));
		props.put("testOnReturn", env.getProperty("jdbc.testOnReturn"));
		props.put("testWhileIdle", env.getProperty("jdbc.testWhileIdle"));
		props.put("timeBetweenEvictionRunsMillis", env.getProperty("jdbc.timeBetweenEvictionRunsMillis"));
		props.put("minEvictableIdleTimeMillis", env.getProperty("jdbc.minEvictableIdleTimeMillis"));
		props.put("removeAbandoned", env.getProperty("jdbc.removeAbandoned"));
		props.put("removeAbandonedTimeout", env.getProperty("jdbc.removeAbandonedTimeout"));
		props.put("logAbandoned", env.getProperty("jdbc.logAbandoned"));
		props.put("poolPreparedStatements", env.getProperty("jdbc.poolPreparedStatements"));
		props.put("maxPoolPreparedStatementPerConnectionSize", env.getProperty("jdbc.maxPoolPreparedStatementPerConnectionSize"));
		props.put("filters", env.getProperty("jdbc.filters"));
	}
	

}
