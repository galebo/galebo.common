package com.galebo.common.jgrid;


import java.util.ArrayList;
import java.util.List;

/*
 * JGrid使用的bean形式
 */
public class JGridBean {
    long total;
    long page;
    long records;
	List<JGridRow> rows;
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getRecords() {
		return records;
	}

	public void setRecords(long records) {
		this.records = records;
	}

	public List<JGridRow> getRows() {
		return rows;
	}

	public void setRows(List<JGridRow> rows) {
		this.rows = rows;
	}

	static public JGridBean fromList(List<? extends JGridAble > list,int page,int pageSize,int total) {
		ArrayList<JGridRow> beans=new ArrayList<JGridRow>();
		for (int i = 0; i < list.size(); i++) {
			JGridRow jgridRow=new JGridRow();
			JGridAble object = list.get(i);
			jgridRow.setCell(object.toSimpleJson());
			beans.add(jgridRow);
		}
		JGridBean bean=new JGridBean();
		bean.setRows(beans);
		bean.setPage(page);
		bean.setRecords(total);
		bean.setTotal((total+pageSize-1)/pageSize);
		return bean;
	}
}
