package com.galebo.common.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * 元素类型代号:2位
 * 目录：01节点：02产品包：03 产品： 04
 *	关系代号：9位
 *前2位：元素类型代号，后六位：元素id，分隔符'-'
 *是否主产品：
 *true:是，false：否
 */
public class TreeItemNode {
	private String parentItemId;
	
	private String itemId;
	private String type;
	private String itemName;
	private String path;
	
	private Map<String,String> property=new HashMap<String, String>();
	private List<TreeItemNode> children=new LinkedList<TreeItemNode>();
	private int checked;
	private int nocheckbox;
	private int radio;
	private int selected=0;
	private int open=0;
	
	public int getNocheckbox() {
		return nocheckbox;
	}
	public void setNocheckbox(int nocheckbox) {
		this.nocheckbox = nocheckbox;
	}
	public int getRadio() {
		return radio;
	}
	public void setRadio(int radio) {
		this.radio = radio;
	}

	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	public String getParentItemId() {
		return parentItemId;
	}
	public void setParentItemId(String parentItemId) {
		this.parentItemId = parentItemId;
	}
	public int getOpen() {
		return open;
	}
	public void setOpen(int open) {
		this.open = open;
	}
	public int getSelected() {
		return selected;
	}
	public void setSelected(int selected) {
		this.selected = selected;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Map<String, String> getProperty() {
		return property;
	}
	public void setProperty(Map<String, String> property) {
		this.property = property;
	}
	public List<TreeItemNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeItemNode> children) {
		this.children = children;
	}
}
