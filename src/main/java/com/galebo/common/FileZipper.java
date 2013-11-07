package com.galebo.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import com.galebo.common.WorkerException;

public class FileZipper {
	static final int BUFFER = 1024;
	public void zip(String zipFileName, String inputFile) throws FileNotFoundException  {
		ZipOutputStream out = null;
		FileInputStream in=null;

		out = new ZipOutputStream(new FileOutputStream(zipFileName));
		out.setEncoding("GBK");
		try {
			zip(out,in,new File(inputFile), "");
		} catch (IOException e) {
			WorkerException.handleNoThrowNew(e);
		}
		finally
		{
			try {
				if(in!=null)
					in.close();
				out.close();
			} catch (IOException e) {
				WorkerException.handleNoThrowNew(e);
			}
		}
	}

	private void zip(ZipOutputStream out,FileInputStream in, File f, String base) throws IOException {
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < files.length; i++) {
				zip(out,in, files[i], base +files[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			in = new FileInputStream(f);
			int len;
			byte[] buf = new byte[BUFFER];
			while ((len = in.read(buf, 0, BUFFER)) != -1) {
				out.write(buf, 0, len); // 写入到压缩包
			}
			in.close();
			in=null;
		}
	}

}