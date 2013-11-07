package com.galebo.common;


import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import freemarker.template.Configuration;

public class SpringContext {

	private SpringContext() {
	};

	static public void setSpringContext(ApplicationContext _content) {
		content = _content;
	}
	static public void setServletContext(ServletContext _content) {
		servletContext = _content;
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}

	protected static ApplicationContext content = null;
	protected static ServletContext servletContext = null;

	public static ApplicationContext getApplicationContext() {
		if (content == null) {
			content = new ClassPathXmlApplicationContext("classpath:*/applicationContext-*.xml");
		}
		return content;
	}

	protected static JdbcTemplate jdbcTemplate = null;

	static public JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			jdbcTemplate = (JdbcTemplate) SpringContext.getApplicationContext().getBean("jdbcTemplate");
		}
		return jdbcTemplate;
	}
	protected static SqlMapClientTemplate SqlMapClientTemplate = null;
	static public SqlMapClientTemplate getSqlMapClientTemplate() {
		if (SqlMapClientTemplate == null) {
			SqlMapClientTemplate = (SqlMapClientTemplate) SpringContext.getApplicationContext().getBean("sqlMapClientTemplate");
		}
		return SqlMapClientTemplate;
	}

	static Configuration configuration =null;
	static public  Configuration getConfiguration() {
		if (configuration == null) {
			configuration =(Configuration) SpringContext.getApplicationContext().getBean("freeMarkerConfiguration");
		}
		return configuration;
	}
	
}
