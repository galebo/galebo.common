package com.galebo.common.jgrid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.galebo.common.jgrid.JGridRow;
/**
 * 
 * @author liyanzhao
 * 用于jGrid的tree型显示
 */

public abstract class JGridTreeHelper {

	public JGridBean getJGridBean(long parentId,int page,int pageSize) {
		List<JGridRow> beans=new ArrayList<JGridRow>();
		_getSonData(beans,parentId,0);
		
		JGridBean bean=new JGridBean();
		bean.setRows(beans);
		bean.setPage(page);
		bean.setRecords(beans.size());
		bean.setTotal((getSonSize(parentId)+pageSize)/pageSize);
		return bean;
	}
	private boolean _getSonData(List<JGridRow> beans,long parentId,int level) {
		List<? extends JGridAble> sonRows = getSons(parentId,level);
		for (Iterator<? extends JGridAble> iterator = sonRows.iterator(); iterator.hasNext();) {
			JGridAble row =iterator.next();
			JGridRow JGridBean1=new JGridRow();
			JGridBean1.setCell(row.toSimpleJson());
			beans.add(JGridBean1);
			int size = beans.size();
			boolean haveSon=_getSonData(beans,row.getId(),level+1);

	        beans.get(size-1).getCell().add(level);
	        beans.get(size-1).getCell().add(parentId);
	        beans.get(size-1).getCell().add(!haveSon);
	        beans.get(size-1).getCell().add(level<1?true:false);
	        beans.get(size-1).getCell().add(true);
		}
		return sonRows.size()>0;
	}
	protected abstract List<? extends JGridAble>  getSons(long ParentId, int level);
	protected abstract Long  getSonSize(long ParentId);
	
}
