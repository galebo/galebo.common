package com.galebo.common.freemarker;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.galebo.common.dao.SSIILL;

import freemarker.cache.TemplateLoader;

public class DbTemplateLoader implements TemplateLoader {
	public static final String Start= "(";
	public static final String End = ")";
	public static final String DbStart = "db"+Start;
	public static final String DbStartNo = Start;
	protected final Log log = LogFactory.getLog(getClass());
	public static boolean  isNewImport=false;
	SqlMapClientTemplate sqlMapClientTemplate;
	static Map<String,Object> ftlMap=new HashMap<String,Object>();
	TemplateLoader templateLoader;
	public DbTemplateLoader(TemplateLoader TemplateLoader,SqlMapClientTemplate sqlMapClientTemplate)
	{
		templateLoader=TemplateLoader;
		this.sqlMapClientTemplate=sqlMapClientTemplate;
	}


	public void closeTemplateSource(Object templateSource) throws IOException {
		if(templateSource instanceof DbTemplateSource)
			return;
		else
			templateLoader.closeTemplateSource(templateSource);
	}

	public Object findTemplateSource(String param) throws IOException {
		boolean isSelfFtl=param.indexOf("self")>-1;
		if(param.indexOf(DbStart)>-1||isSelfFtl)
		{
			String template_name=param.replaceAll("//", "/");
			if(param.indexOf("_")>-1)
				template_name=param.substring(0, param.indexOf("_"));
			String real_template_name=template_name.substring(param.indexOf(End)+2);
			if(isNewImport)
			{
				isNewImport=false;
				ftlMap.clear();
				isNewImport=false;
			}
			if(isSelfFtl)
			{
				int indexOf = param.indexOf(End);
				Long userId = Long.valueOf(param.substring(param.indexOf(Start)+Start.length(),indexOf));
				if(ftlMap.get(template_name)==null)
				{
					Object html=sqlMapClientTemplate.queryForObject("selectFtlByUserId",SSIILL.create(real_template_name, null, null, null, userId, null));
					if(html==null)
					{
						html=sqlMapClientTemplate.queryForObject("selectFtl",real_template_name);
					}
					if(html==null)
					{
						html="";
					}
					ftlMap.put(template_name, new DbTemplateSource((String)html));
					log.debug(template_name);
				}
				return ftlMap.get(template_name);
			}
			else
			{
				if(ftlMap.get(template_name)==null)
				{
					Object html=sqlMapClientTemplate.queryForObject("selectFtl",real_template_name);
					if(html==null)
						html="";
					ftlMap.put(template_name, new DbTemplateSource((String)html));
					log.debug(template_name);
				}
				return ftlMap.get(template_name);
				
			}
		}
		else
		{
			param=param.substring(param.indexOf(End)+1);
			return templateLoader.findTemplateSource(param);
		}
	}

	public long getLastModified(Object templateSource) {
		if(templateSource instanceof DbTemplateSource)
			return -1l;
		else
			return templateLoader.getLastModified(templateSource);
	}

	public Reader getReader(Object templateSource, String encodeType) throws IOException {
		if(templateSource instanceof DbTemplateSource)
			return new StringReader(((DbTemplateSource) templateSource).getText());
		else
			return templateLoader.getReader(templateSource,encodeType);
	}
}
