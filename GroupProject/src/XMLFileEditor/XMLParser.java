package XMLFileEditor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
	public static ArrayList<Word> wordList = new ArrayList<Word>();
	public void load(String path, String elements[])	{
		File xml = new File(path);

		DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuild = dbFact.newDocumentBuilder();
			Document doc = (Document) dBuild.parse(xml);
			NodeList nList = ((Document) doc).getElementsByTagName("Word");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = (Node) nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement =  (Element) nNode;
					
					String spelling;
					int lvl;
					int id = Integer.parseInt(eElement.getAttribute("id"));
					
					spelling = eElement.getElementsByTagName(elements[0]).item(0).getTextContent();
					lvl = Integer.parseInt(eElement.getElementsByTagName(elements[1]).item(0).getTextContent());
					
					wordList.add(new Word(id, spelling, lvl));
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
