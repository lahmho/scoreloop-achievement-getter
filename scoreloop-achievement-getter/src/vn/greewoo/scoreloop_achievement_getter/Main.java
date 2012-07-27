/**
 *   (C) Smart Physics
 *  www.smartphysics.net
 *  
 * @author: Lam Ho
 * @since: Jul 25, 2012
 */
package vn.greewoo.scoreloop_achievement_getter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * 
 */
public class Main {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public static void main(String argv[]) {

		try {

			File fXmlFile = new File(
					"D:\\git_home\\chinese-chess\\chinese-chess-android\\assets\\SLAwards.bundle\\Info.plist");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("dict");
			System.out.println("-----------------------");

			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File("AchievementIds.java")),
					"UTF8"));
			out.write("/*Automatic generated file*/\npublic class AchievementIds {\n ");
			for (int temp = 1; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					String value = getTagValue("string", eElement);
					if (getTagValue("string", eElement) != null) {
						out.write("public static final String _"
								+ value.toUpperCase().replace(".", "_") + " = "
								+ "\"" + value + "\";\n");
					}
				}
			}

			out.write("\n}");

			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	private static String getTagValue(String sTag, Element eElement) {
		if (eElement.getElementsByTagName(sTag).getLength() > 1) {
			NodeList nlList = eElement.getElementsByTagName(sTag).item(1)
					.getChildNodes();

			Node nValue = (Node) nlList.item(0);

			return nValue.getNodeValue();
		}
		return null;
	}
}
