import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLDemo {
	private static XMLDemo xmlDemo;

	private XMLDemo() {
	}

	public static XMLDemo getXMLDemo() {
		if (xmlDemo == null) {
			xmlDemo = new XMLDemo();
		}
		return xmlDemo;
	}

	public static void change(String name) {
		// System.out.println(vis.getClass().getName());
		Document document = getDocument();
		NodeList n = document.getElementsByTagName("className");
		n.item(0).setTextContent(name);
		writeXml(document);

	}

	public static String get() {
		Document document = getDocument();
		NodeList n = document.getElementsByTagName("className");

		return n.item(0).getTextContent();
	}

	// 获得操作xml文件的对象
	private static Document getDocument() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();// 得到创建 DOM 解析器的工厂。
		DocumentBuilder builder = null;// 得到 DOM 解析器对象。
		Document document = null;// 得到代表整个文档的 Document 对象
		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(new File("softwareDesign_Lab3/src/config.x" + "ml"));
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Element e = document.getDocumentElement();
		return document;
	}

	// 将内存中的数据保存到XML文件中
	private static void writeXml(Document document) {

		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File("softwareDesign_Lab3/src/config.xml"));

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer trans = null;
		try {
			trans = factory.newTransformer();
			trans.transform(source, result);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
