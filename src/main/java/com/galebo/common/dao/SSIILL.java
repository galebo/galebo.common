package com.galebo.common.dao;

public class SSIILL {
	String string1;
	String string2;
	String string3;
	Long long1;
	Long long2;
	Long long3;
	Integer int1;
	Integer int2;
	Integer int3;
	Integer startNum;
	Integer pageSize;
	public String getString3() {
		return string3;
	}
	public void setString3(String string3) {
		this.string3 = string3;
	}
	public Long getLong3() {
		return long3;
	}
	public void setLong3(Long long3) {
		this.long3 = long3;
	}
	public Integer getInt3() {
		return int3;
	}
	public void setInt3(Integer int3) {
		this.int3 = int3;
	}
	public void setString1(String string1) {
		this.string1 = string1;
	}
	public void setString2(String string2) {
		this.string2 = string2;
	}
	public void setLong1(Long long1) {
		this.long1 = long1;
	}
	public void setLong2(Long long2) {
		this.long2 = long2;
	}
	public void setInt1(Integer int1) {
		this.int1 = int1;
	}
	public void setInt2(Integer int2) {
		this.int2 = int2;
	}

	public Integer getStartNum() {
		return startNum;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getInt1() {
		return int1;
	}
	public Integer getInt2() {
		return int2;
	}
	public Long getLong1() {
		return long1;
	}
	public Long getLong2() {
		return long2;
	}
	public String getString1() {
		return string1;
	}
	public String getString2() {
		return string2;
	}

	static public SSIILL create(String string1,String string2,Integer int1,Integer int2,Long long1,Long long2) {
		SSIILL stringStringLongLong = new SSIILL();
		stringStringLongLong.string1 = string1;
		stringStringLongLong.string2 = string2;
		stringStringLongLong.long1 = long1;
		stringStringLongLong.long2 = long2;
		stringStringLongLong.int1 = int1;
		stringStringLongLong.int2 = int2;
		return stringStringLongLong;
	}
	static public SSIILL create(String string1,String string2,Integer int1,Integer int2,Long long1,Long long2,Integer pageSize,Integer page) {
		SSIILL stringStringLongLong=create(string1, string2, int1, int2, long1, long2);
		stringStringLongLong.pageSize = pageSize;
		stringStringLongLong.startNum = (page-1)*pageSize;
		return stringStringLongLong;
	}
	static public SSIILL create(String string1,String string2,String string3,Integer int1,Integer int2,Integer int3,Long long1,Long long2,Long long3) {
		SSIILL stringStringLongLong=create(string1, string2, int1, int2, long1, long2);
		stringStringLongLong.string3 = string3;
		stringStringLongLong.long3 = long3;
		stringStringLongLong.int3 = int3;
		return stringStringLongLong;
	}
}
