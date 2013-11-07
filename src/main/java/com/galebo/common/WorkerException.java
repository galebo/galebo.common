package com.galebo.common;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

public class WorkerException extends RuntimeException {
	
	private static final long serialVersionUID = 7963010954460474275L;
	public static String componentName="Exceptions";

	public WorkerException(String msg) {
		super(msg);
	}

	public static void handleNoThrowNew(Exception e) {
		handle(e, false/* ifThrowNew */, true/* save2DB */);
	}

	public static void handle(Exception e) {
		handle(e, true/* ifThrowNew */, true/* save2DB */);
	}
	
	//10内网  192开头公网  127 玄幻
	private static void handle(Exception e,boolean ifThrowNew,boolean save2DB) {
		e.printStackTrace();
		
/*		Map ipMap = OSUtil.getLocalAllIps();
		String serverIp = getServerIp(ipMap);
		String ExceptionTrace = getExceptionTrace(e);
		
		if(ipMap!=null&&ipMap.size()>0)
		    MailService.send("serverIP" + ipMap + "\n" + ExceptionTrace errMsg ,  serverIp+"-" + componentName * defult subject *);
         else 
        	 MailService.send(ExceptionTrace errMsg , componentName * defult subject *);*/
		
		if (ifThrowNew) {
			throw new WorkerException(e.getMessage());
		}
	}
	
	private static String getServerIp(Map<String,String> ipMap) {
		String publicNetIp = "";
		String privateNetIp = "";
		Set<String> set = ipMap.keySet();
		for (String ip : set)
			if (!ip.startsWith("10.")) {
				publicNetIp = ip;
				break;
			} else {
				privateNetIp = ip;
			}
		if (publicNetIp == null || publicNetIp.length() == 0) return privateNetIp;
		else
			return publicNetIp;
	}
	
	static private String getExceptionTrace(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		StringBuffer sbuffer = sw.getBuffer();
		return sbuffer.toString();
	}
}
