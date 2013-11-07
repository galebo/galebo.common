package com.galebo.common.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class TreeUtil {
    private static final Log log = LogFactory.getLog(TreeUtil.class);
	public static String getXMLFromTreeNode(List<TreeItemNode> data) {
		return getXMLFromTreeNode(data, false, "0");
	}

	public static String getXMLFromTreeNode(List<TreeItemNode> data, boolean ifDynamicsTree) {
		return getXMLFromTreeNode(data, ifDynamicsTree, "0");
	}

	public static String getXMLFromTreeNode(List<TreeItemNode> data, boolean ifDynamicsTree, String id) {
		String text = java2xml(data, ifDynamicsTree, id);
		return text;
	}

	private static String java2xml(List<TreeItemNode> pin, boolean ifDynamicsTree, String id){
		// 创建根节点 list;
		Element root = new Element("tree");
		root.setAttribute("id", id);
		// 根节点添加到文档中；
		Document Doc = new Document(root);

		Iterator<TreeItemNode> it = pin.iterator();
		while (it.hasNext()) {
			TreeItemNode p = (TreeItemNode) it.next();
			Element e = recursive(p, ifDynamicsTree);
			root.addContent(e);
		}
		Format f = Format.getPrettyFormat();
		f.setEncoding("utf-8");
		f.setTextMode(Format.TextMode.TRIM);

		XMLOutputter XMLOut = new XMLOutputter(f);

		return XMLOut.outputString(Doc);
	}

	private static Element recursive(TreeItemNode treeItemNode, boolean ifDynamicsTree) {
		Element element = new Element("item");
		element.setAttribute("id", treeItemNode.getItemId());
		element.setAttribute("text", treeItemNode.getItemName());
		// e.setAttribute("im0","leaf.gif");
		// e.setAttribute("im1", "tombs_open.gif");
		// e.setAttribute("im2", "tombs.gif");
		// e.setAttribute("tooltip",pin.getItemName());
		if (treeItemNode.getOpen() == 1) {
			element.setAttribute("open", "1");
		}
		if (treeItemNode.getSelected() == 1) {
			element.setAttribute("select", "1");
		}
		if (treeItemNode.getRadio() == 1) {
			element.setAttribute("radio", "1");
		}
		if (treeItemNode.getNocheckbox() == 1) {
			element.setAttribute("nocheckbox", "1");
		}
		if (ifDynamicsTree) {
			// if(pin.getItemId()!=null && pin.getItemId())
			element.setAttribute("child", "1");
		} else {
			if (treeItemNode.getChildren() != null && treeItemNode.getChildren().size() > 0) {
				element.setAttribute("child", "1");
			}
		}
		if (treeItemNode.getChecked() == 1) {
			element.setAttribute("checked", "1");
		}

		element = addUserData(element, treeItemNode);
		Iterator<TreeItemNode> it = treeItemNode.getChildren().iterator();
		while (it.hasNext()) {
			TreeItemNode p = it.next();
			element.addContent(recursive(p, ifDynamicsTree));
		}
		return element;
	}
	private static Element addUserData(Element pElement, TreeItemNode pTreeItemNode) {
		if (pTreeItemNode.getProperty() != null && !pTreeItemNode.getProperty().isEmpty()) {
			Iterator<Map.Entry<String, String>> it = pTreeItemNode.getProperty().entrySet().iterator();
			while (it.hasNext()) {
				Element userdata = new Element("userdata");
				Map.Entry<String, String> s = it.next();
				userdata.setAttribute("name", s.getKey());
				userdata.setText(s.getValue());
				pElement.addContent(userdata);
			}
		}
		return pElement;
	}

	
	
	

	// 递归函数，将无序的list转为有上下级关系的根节点的集合
	public static List<TreeItemNode> SortTree(List<TreeItemNode> tin) {
		return SortTree(tin, "");
	}

	public static List<TreeItemNode> SortTree(List<TreeItemNode> lists, String root) {
		List<TreeItemNode> rtn = new ArrayList<TreeItemNode>();
		for (TreeItemNode treeItemNode : lists) {
			if (StringUtils.isNotBlank(treeItemNode.getParentItemId())
					&& !"0".equals(treeItemNode.getParentItemId())
					&& !root.equals(treeItemNode.getParentItemId())) {
				for (TreeItemNode treeItemNode2 : lists) {
					log.info("treeItemNode.getParentItemId():"+treeItemNode.getParentItemId()+"treeItemNode2.getItemId():"+treeItemNode2.getItemId());
					if (treeItemNode.getParentItemId().equals(treeItemNode2.getItemId())) {
						treeItemNode2.getChildren().add(treeItemNode);
						break;
					}
				}
			} else {
				rtn.add(treeItemNode);
			}
		}
		return rtn;
	}

	
	/*
	 * 打开选中xml
	 */

	public static boolean setTreeSelected(final List<TreeItemNode> tin,final String defaultVal) {
		return setTreeSelected(tin, defaultVal, 0);
	}

	public static boolean setTreeSelected(final List<TreeItemNode> list,final String defaultVal, int dynamicsTreeType) {
		if (StringUtils.isNotEmpty(defaultVal)) {
			for (TreeItemNode node : list) {
				if (defaultVal.equals(node.getItemId())) {
					log.info("选中：" + node.getItemName());
					node.setSelected(1);
					if (dynamicsTreeType == 0) {
						node.setOpen(1);
					}
					return true;
				} else {
					boolean have = setTreeSelected(node.getChildren(), defaultVal, dynamicsTreeType);
					if (have) {
						if (dynamicsTreeType == 0) {
							node.setOpen(1);
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean setTreeSelectedForMore(List<TreeItemNode> tin, String defaultVal) {
		return setTreeSelectedForMore(tin, defaultVal, 0);
	}

	public static boolean setTreeSelectedForMore(List<TreeItemNode> list, String defaultVal, int dynamicsTreeType) {
		if (StringUtils.isNotEmpty(defaultVal)) {
			String[] defaultValArry = defaultVal.split(",");
			for (TreeItemNode node : list) {
				for (int k = 0; k < defaultValArry.length; k++) {
					if (defaultValArry[k].equals(node.getItemId())) {
						log.info("选中1：" + node.getItemName());
						node.setSelected(1);
						node.setOpen(1);
						return true;
					} else {
						boolean have = setTreeSelectedForMore(node.getChildren(), defaultValArry[k], dynamicsTreeType);
						if (have) {
							node.setOpen(1);
						}
					}
				}
				if (defaultVal.equals(node.getItemId())) {
					log.info("选中2：" + node.getItemName());
					node.setSelected(1);
					node.setOpen(1);
					return true;
				} else {
					boolean have = setTreeSelected(node.getChildren(), defaultVal, dynamicsTreeType);
					if (have) {
						node.setOpen(1);
					}
				}
			}
		}
		return false;
	}
	
	

	// 展开第一层子节点
	public static void OpenLevelOne(List<TreeItemNode> list) {
		for (TreeItemNode one : list) {
			one.setOpen(1);
		}
	}

	// 展开层级节点
	public static void OpenLevelAll(List<TreeItemNode> list) {
		for (TreeItemNode one : list) {
			one.setOpen(1);
		}
	}
}
