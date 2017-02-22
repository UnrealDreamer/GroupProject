package XMLFileEditor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import BackEnd.Word;

public class XMLParser {
	public static String[] wordList;
//	private final static String[] elementID = {"spellin", "Age"}; 
	private String[] contents;
	public void load(String path, String elements[])	{
		File xml = new File(path);

		DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuild = dbFact.newDocumentBuilder();
			Document doc = (Document) dBuild.parse(xml);
			NodeList nList = ((Document) doc).getElementsByTagName("Word");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = (Node) nList.item(temp);

				System.out.println("Current Element : " + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement =  (Element) nNode;

					System.out.println("Word id : " + eElement.getAttribute("id"));
					for(String e : elements)
					{
						System.out.println(e + " : " + eElement.getElementsByTagName(e).item(0).getTextContent());
					}
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		} 
	}
	public void save(Word[] lines)
	{
		int ind = 0;
		File xml = new File("res\\thing.xml");
		try {
			FileWriter writer = new FileWriter("");
			xml.setWritable(true);
			do{
				DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuild = dbFact.newDocumentBuilder();
				Document doc = (Document) dBuild.parse(xml);
				NodeList nList = ((Document) doc).getElementsByTagName("Word");
			}while(ind < lines.length);
		} catch (ParserConfigurationException  | SAXException | IOException e) 
		{	e.printStackTrace();	}

	}
}
