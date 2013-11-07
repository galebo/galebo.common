package com.galebo.common.freemarker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import freemarker.cache.TemplateLoader;

public class FreeMarkerConfigurer extends org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer{
	SqlMapClientTemplate sqlMapClientTemplate;
	@Autowired
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
    protected TemplateLoader getAggregateTemplateLoader(List<TemplateLoader> templateLoaders) {
        return new DbTemplateLoader(super.getAggregateTemplateLoader(templateLoaders),sqlMapClientTemplate);
    }
}