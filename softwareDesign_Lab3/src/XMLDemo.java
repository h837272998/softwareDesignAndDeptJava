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

	// ��ò���xml�ļ��Ķ���
	private static Document getDocument() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();// �õ����� DOM �������Ĺ�����
		DocumentBuilder builder = null;// �õ� DOM ����������
		Document document = null;// �õ����������ĵ��� Document ����
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

	// ���ڴ��е����ݱ��浽XML�ļ���
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
