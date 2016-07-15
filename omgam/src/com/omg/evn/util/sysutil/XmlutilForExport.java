package com.omg.evn.util.sysutil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * Dom4j封装类
 * 
 * @version 1.0
 */

public class XmlutilForExport {
	static Logger log = Logger.getLogger(XmlutilForExport.class);
	/** XML文件路径 */
	private String		XMLPath		= null;
	/** XML文档 */
	private Document	document	= null;

	public XmlutilForExport() {
	}

	public String getXMLPath() {
		return XMLPath;
	}

	public void setXMLPath(String path) {
		XMLPath = path;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	/**
	 * 初始化xml文件
	 * 
	 * @param XMLPath
	 *            文件路径
	 */
	public XmlutilForExport(String XMLPath) {
		this.XMLPath = XMLPath;
	}

	/**
	 * 转换Sting类型的XML为dom4j对象
	 * 
	 * @param source
	 */
	public void paseXML(String source) {
		try {
			this.document = DocumentHelper.parseText(source);
		} catch (Exception e) {
			log.error("paseXML() Exception:" + e.getMessage());
		}
	}

	/**
	 * 打开文档
	 */
	public void openXML() {
		try {
			SAXReader reader = new SAXReader();
			this.document = reader.read(this.XMLPath);
		} catch (Exception e) {
			log.error("openXML() Exception:" + e.getMessage());
		}
	}

	/**
	 * 打开文档
	 * 
	 * @param filePath
	 *            文档路径
	 */
	public void openXML(String filePath) {
		try {
			SAXReader saxReader = new SAXReader();
			this.document = saxReader.read(filePath);
		} catch (Exception e) {
			log.error("openXML() Exception:" + e.getMessage());
		}
	}
	public void openXMLs(String filePath) {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream in = null;
		in = cl.getResourceAsStream(filePath);
//		Document doc = new SAXReader().read(in);
		try {
			SAXReader saxReader = new SAXReader();
			this.document = saxReader.read(in);
		} catch (Exception e) {
			log.error("openXML() Exception:" + e.getMessage());
		}
	}

	/**
	 * 创建文档
	 * 
	 * @param rootName
	 *            根节点名称
	 */
	public void createXML(String rootName) {
		try {
			document = DocumentHelper.createDocument();
			document.addElement(rootName);
		} catch (Exception e) {
			log.error("createXML() Exception:" + e.getMessage());
		}
	}

	/**
	 * 添加根节点的child
	 * 
	 * @param nodeName
	 *            节点名
	 * @param nodeValue
	 *            节点值
	 */
	public Element addNodeFromRoot(String nodeName, String nodeValue) {
		Element root = this.document.getRootElement();
		Element level1 = root.addElement(nodeName);
		level1.addText(nodeValue);
		return level1;
	}

	/**
	 * 保存文档
	 */
	public void saveXML() {
		XMLWriter writer = null;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");// 指定XML编码
			writer = new XMLWriter(new FileOutputStream("1.xml"), format);
			writer.write(getDocument());
		} catch (Exception e1) {
			log.error("saveXML() Exception:" + e1.getMessage());
		} finally {
			try {
				if(writer != null){
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 保存文档
	 * 
	 * @param toFilePath
	 *            保存路径
	 */
	public void saveXML(String toFilePath) {
		XMLWriter writer = null;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");// 指定XML编码
			writer = new XMLWriter(new FileOutputStream(toFilePath), format);
			writer.write(getDocument());
		} catch (Exception e1) {
			log.error("saveXML() Exception:" + e1.getMessage());
		} finally {
			try {
				if(writer != null){
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获得某个节点的值
	 * 
	 * @param nodeName
	 *            节点名称
	 */
	public String getElementValue(String nodeName) {
		try {
			Node node = document.selectSingleNode("//" + nodeName);
			return node.getText();
		} catch (Exception e1) {
			log.error("getElementValue() Exception：" + e1.getMessage());
			return null;
		}
	}

	/**
	 * 获得某个节点的子节点的值
	 * 
	 * @param nodeName
	 * @param childNodeName
	 * @return
	 */
	public String getElementValue(String nodeName, String childNodeName) {
		try {
			Node node = this.document.selectSingleNode("//" + nodeName + "/" + childNodeName);
			return node.getText();
		} catch (Exception e1) {
			log.error("getElementValue() Exception：" + e1.getMessage());
			return null;
		}
	}

	/**
	 * 设置一个节点的text
	 * 
	 * @param nodeName
	 *            节点名
	 * @param nodeValue
	 *            节点值
	 */
	public void setElementValue(String nodeName, String nodeValue) {
		try {
			Node node = this.document.selectSingleNode("//" + nodeName);
			node.setText(nodeValue);
		} catch (Exception e1) {
			log.error("setElementValue() Exception:" + e1.getMessage());
		}
	}

	/**
	 * 设置一个节点值
	 * 
	 * @param nodeName
	 *            父节点名
	 * @param childNodeName
	 *            节点名
	 * @param nodeValue
	 *            节点值
	 */
	public void setElementValue(String nodeName, String childNodeName, String nodeValue) {
		try {
			Node node = this.document.selectSingleNode("//" + nodeName + "/" + childNodeName);
			node.setText(nodeValue);
		} catch (Exception e1) {
			log.error("setElementValue() Exception:" + e1.getMessage());
		}
	}

	/** *根据 结构查找 到节点** */
	public Element getNodeByTag(String tagName) {
		Element childNode = null;
		if (document != null) {
			List childNodes = document.selectNodes(tagName);
			for (Object obj : childNodes) {
				childNode = (Element) obj;
			}
		} else {
			log.error("没有找到xml文件!");
		}
		return childNode;
	}

	public List getNodesByTag(String tagName) {
		List childNodes = null;
		if (document != null) {
			childNodes = document.selectNodes(tagName);
		} else {
			log.error("没有找到xml文件!");
		}
		return childNodes;
	}

	/**
	 * 在xml中查找节点
	 * 
	 * @param el
	 *            Element
	 * @param subName
	 *            String
	 * @return Element
	 */
	public Element getElementByName(Element el, String subName) {
		Element e = null;
		if (el == null) {
			el = document.getRootElement();
		}
		if (el.getQName().getName().toString().equalsIgnoreCase(subName)) {
			return el;
		}
		Element sub = null;
		Iterator it = el.elementIterator();
		while (it.hasNext()) {
			sub = (Element) it.next();
			// System.out.println(sub.getQName().getName().toString());
			if (sub.getQName().getName().toString().equalsIgnoreCase(subName)) {
				e = sub;
				return e;
			}
		}
		// 为了提高效率，每次先查询同级节点，如果没有，再下级查询
		it = el.elementIterator();
		while (it.hasNext()) {
			sub = (Element) it.next();
			e = getElementByName(sub, subName);
			if (e != null) {
				return e;
			}

		}
		return e;
	}

	/**
	 * 在xml中查找节点
	 * 
	 * @param el
	 *            Element
	 * @param subName
	 *            String
	 * @return Element
	 */
	public List getElementsByName(Element el, String subName) {
		List lst = new ArrayList();
		Element e = null;
		if (el == null) {
			el = document.getRootElement();
		}
		if (el.getQName().getName().toString().equalsIgnoreCase(subName)) {
			lst.add(el);
			return lst;
		}
		Element sub = null;
		Iterator it = el.elementIterator();
		while (it.hasNext()) {
			sub = (Element) it.next();
			// System.out.println(sub.getQName().getName().toString());
			if (sub.getQName().getName().toString().equalsIgnoreCase(subName)) {
				lst.add(sub);
			} else {
				List subs = getElementsByName(sub, subName);
				if (subs != null && subs.size() > 0) {
					lst.addAll(subs);
				}
			}
		}
		return lst;
	}
}

