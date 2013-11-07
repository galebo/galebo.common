package com.galebo.common.freemarker;

import java.io.IOException;
import java.io.StringWriter;

import com.galebo.common.SpringContext;
import com.galebo.common.WorkerException;

import freemarker.template.Template;

public class FreeMarkerTemplateUtils {
	public static String processTemplateIntoString(Template template, Object model){
		StringWriter result = new StringWriter();
		try {
			template.process(model, result);
		} catch (Exception e) {
			WorkerException.handle(e);
		}
		return result.toString();
	}
	public static String processTemplateIntoString(String template, Object model){
		if(!template.endsWith(".ftl"))
			template=template+".ftl";
		StringWriter result = new StringWriter();
		try {
			getTemplate(template).process(model, result);
		} catch (Exception e) {
			WorkerException.handle(e);
		}
		return result.toString();
	}
	public static Template getTemplate(String name)
	{
		try {
			return SpringContext.getConfiguration().getTemplate(name);
		} catch (IOException e) {
			WorkerException.handle(e);
		}
		return null;
	}
	public static Template getTemplate(String name,String encoding)
	{
		try {
			return SpringContext.getConfiguration().getTemplate(name,encoding);
		} catch (IOException e) {
			WorkerException.handle(e);
		}
		return null;
	}
}
