package com.xh.xmlPars;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.transform.sax.TransformerHandler;

import org.xml.sax.InputSource;
import org.xml.sax.helpers.AttributesImpl;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.annotation.SuppressLint;
import android.util.Xml;

/**
 * @author xh E-mail:825378291@qq.com
 * @version 创建时间：2017-2-12 下午2:18:27
 * 
 *          &、<、>
 */
@SuppressLint("NewApi")
class PullParas extends Paras {
	public PullParas() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<NodeTree> paras(File file) {
		// TODO Auto-generated method stub
		try {
			if (file.exists() && !file.isDirectory())
				return paras(new FileInputStream(file));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<NodeTree> paras(String uri) {
		// TODO Auto-generated method stub
		try {
			if (uri != null && !uri.isEmpty()) {
				URI uri1 = new URI(uri);
				return paras(uri1.toURL());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<NodeTree> paras(URL url) {
		// TODO Auto-generated method stub
		if (url != null) {
			try {
				return paras(url.openStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}

	@Override
	public List<NodeTree> paras(InputSource is) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NodeTree> paras(InputStream is) {
		// TODO Auto-generated method stub
		if (is == null)
			return null;
		System.out.println("==============");
		List<NodeTree> nodeTrees = null;
		try {
			XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
			parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
			NodeTree nodeTree = null;
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					nodeTrees = new ArrayList<NodeTree>();
					break;
				case XmlPullParser.TEXT:
					if (nodeTree == null)
						nodeTree = getNodeTree(nodeTrees);
					if (nodeTree != null) {
						nodeTree.setValue(parser.getText());
					}
					break;
				case XmlPullParser.START_TAG:
					String name = parser.getName();
					nodeTree = new NodeTree();
					nodeTree.setName(name);
					int index = parser.getAttributeCount();
					if (index > 0) {
						Map<String, String> attributs = new HashMap<String, String>();
						for (int i = 0; i < index; i++) {
							String key = parser.getAttributeName(i);
							String value = parser.getAttributeValue(i);
							attributs.put(key, value);
						}
						nodeTree.setAttributes(attributs);
					}
					NodeTree f_node_tree = getNodeTree(nodeTrees);
					if (f_node_tree != null) {
						if (f_node_tree.hasChildNodeTrees()) {
							f_node_tree.getChildNodeTrees().add(nodeTree);
						} else {
							List<NodeTree> child_node_trees = new ArrayList<NodeTree>();
							child_node_trees.add(nodeTree);
							f_node_tree.setChildNodeTrees(child_node_trees);
						}
					} else
						nodeTrees.add(nodeTree);
					break;
				case XmlPullParser.END_TAG:
					if (nodeTree == null)
						nodeTree = getNodeTree(nodeTrees);
					if (nodeTree != null
							&& parser.getName().equals(nodeTree.getName())) {
						nodeTree.setIs_end(true);
						nodeTree = null;
					}
					break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			is.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return nodeTrees;
	}

	@Override
	public String nodeTree2string(NodeTree nodeTree) {
		try {
			XmlSerializer serializer = Xml.newSerializer(); // 由android.util.Xml创建一个XmlSerializer实例
			StringWriter writer = new StringWriter();
			serializer.setOutput(writer); // 设置输出方向为writer
			serializer.startDocument("UTF-8", true);
			serialize(nodeTree, serializer);
			serializer.endDocument();
			return writer.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	private void serialize(NodeTree nodeTree, XmlSerializer serializer)
			throws Exception {
		serializer.startTag("", nodeTree.getName());
		serializer.text(nodeTree.getValue());
		Map<String, String> attributes = nodeTree.getAttributes();
		if (attributes != null && attributes.size() > 0) {
			Set<String> set = attributes.keySet();
			Iterator<String> i = set.iterator();
			while (i.hasNext()) {
				String key = i.next();
				String value = String.valueOf(attributes.get(key));
				serializer.attribute("", key, value + "");
			}
		}
		List<NodeTree> objects = nodeTree.getChildNodeTrees();
		if (objects != null && objects.size() > 0) {
			for (NodeTree inputParseObject : objects) {
				serialize(inputParseObject, serializer);
			}
		}
		serializer.endTag("", nodeTree.getName());
	}

	@Override
	public String list2string(List<NodeTree> nodeTrees) {
		try {
			XmlSerializer serializer = Xml.newSerializer(); // 由android.util.Xml创建一个XmlSerializer实例
			StringWriter writer = new StringWriter();
			serializer.setOutput(writer); // 设置输出方向为writer
			serializer.startDocument("UTF-8", true);
			for (NodeTree inputParseObject : nodeTrees) {
				serialize(inputParseObject, serializer);
			}
			serializer.endDocument();
			return writer.toString();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	/**
	 * 保存一个nodetree
	 * 
	 * @param uri
	 * @param localName
	 * @param nodeTree
	 * @param handler
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void start_element(String uri, String localName, NodeTree nodeTree,
			TransformerHandler handler) throws Exception {
		if (nodeTree == null)
			return;
		Map<String, String> attributes = nodeTree.getAttributes();
		if (attributes == null || attributes.size() == 0)
			handler.startElement(uri, localName, nodeTree.getName(), null);
		else {
			AttributesImpl attrs = new AttributesImpl();
			Set<String> set = attributes.keySet();
			Iterator<String> i = set.iterator();
			while (i.hasNext()) {
				String key = i.next();
				Object o = attributes.get(key);
				String type = null;
				if (o instanceof Integer)
					type = "int";
				else if (o instanceof Byte)
					type = "byte";
				else if (o instanceof Long)
					type = "long";
				else if (o instanceof Float)
					type = "float";
				else if (o instanceof Double)
					type = "double";
				else if (o instanceof Boolean)
					type = "boolean";
				else if (o instanceof String)
					type = "string";
				if (type != null)
					attrs.addAttribute(uri, localName, key, type,
							String.valueOf(o));// 添加一个名为id的属性(type影响不大,这里设为string)
			}
			handler.startElement(uri, localName, nodeTree.getName(), attrs);
		}
		String value = nodeTree.getValue();
		if (value != null) {
			char[] ch = ch = value.toCharArray();
			handler.characters(ch, 0, ch.length); // 设置name元素的文本节点
		}
		List<NodeTree> nodeTrees = nodeTree.getChildNodeTrees();
		if (nodeTrees != null && nodeTrees.size() > 0) {
			for (NodeTree node : nodeTrees) {
				start_element(uri, localName, node, handler);
			}
		}
		handler.endElement(uri, localName, nodeTree.getName());
	}

}
