package com.galebo.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;

public class UtilsCommon {

	   static public SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   static public SimpleDateFormat sdf_yyyyMMddHHmm=new SimpleDateFormat("yyyyMMddHHmm");
	   static public SimpleDateFormat sdf_yyyyMMddhhmmssSSS=new SimpleDateFormat("yyyyMMddhhmmssSSS");
	   static public void mkdir(String fileFullName) throws IOException
	   {
			String dir = fileFullName.substring(0,fileFullName.lastIndexOf("/"));
			FileUtils.forceMkdir(new File(dir));
	   }
}
