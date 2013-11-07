package com.galebo.common;

public class Result {
	String result;

	public Result(String string) {
		result=string;
	}

	public String getResult() {
		return result;
	}

	static public Result Sucess=new Result("执行成功");
}
