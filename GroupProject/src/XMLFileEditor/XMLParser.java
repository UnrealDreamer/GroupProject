package XMLFileEditor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import BackEnd.User;
import BackEnd.Word;

public class XMLParser {
	
	public static ArrayList<Word> wordList = new ArrayList<Word>();
	public static ArrayList<User> users = new ArrayList<User>();
	public static TreeMap<String, Word> list = new TreeMap<String, Word>();//change to wordList
	
	public static void load(String path)	{
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

					spelling = eElement.getElementsByTagName("spelling").item(0).getTextContent();
					lvl = Integer.parseInt(eElement.getElementsByTagName("level").item(0).getTextContent());

					wordList.add(new Word(id, spelling, lvl));
					list.put(spelling, new Word(id, spelling, lvl));
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		} 
	}
	//TODO instead of always sending the words make the function refer to wordList.
	public static void save(String[] elements, Word[] words, String path)
	{
		int ind = 0;
		File xml = new File(path);
		BufferedWriter construct = null;
		try{
			construct = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(xml.getAbsolutePath()), "utf-8"));
			construct.write("<?xml version=\"1.0\"?>");
			construct.write("\n<Wordbank>");
			while(ind < words.length)
			{
				construct.write("\n\t<Word id=\"" + words[ind].getID() + "\">");
				construct.write("\n\t\t<" + elements[0] + '>' + words[ind].getSpelling() + "</" + elements[0] + '>');
				construct.write("\n\t\t<" + elements[1] + '>' + words[ind].getLevel() + "</" + elements[1] + '>');
				construct.write("\n\t</Word>");
				ind ++;
			}
			construct.write("\n</Wordbank>");
		} catch (IOException ex) {
			System.err.println(ex.getStackTrace());
		} finally {
			try {construct.close();} catch (Exception ex) {}
		}
	}
	public static void loadUsers(String path)	{
		File xml = new File(path);

		DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuild = dbFact.newDocumentBuilder();
			Document doc = (Document) dBuild.parse(xml);
			NodeList nList = ((Document) doc).getElementsByTagName("User");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = (Node) nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement =  (Element) nNode;

					String username;
					int age;

					User u = new User();
					username = eElement.getElementsByTagName("Username").item(0).getTextContent();
					age = Integer.parseInt(eElement.getElementsByTagName("age").item(0).getTextContent());
					String word = (eElement.getElementsByTagName("words").item(0).getTextContent());
					String[] wordList = word.split("\\s");
					for(String s : wordList)
					{
						u.wronglySpelt.add(list.get(s));
					}
					u.setName(username);
					u.setAge(age);
					users.add(u);
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		} 
	}
	public static void saveUsers(String[] elements, User[] user, String path)
	{
		int ind = 0;
		File xml = new File(path);
		BufferedWriter construct = null;
		try{
			construct = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(xml.getAbsolutePath()), "utf-8"));
			construct.write("<?xml version=\"1.0\"?>");
			construct.write("\n<UserInfo>");
			while(ind < user.length)
			{
				construct.write("\n\t\t<" + elements[0] + '>' + user[ind].getName() + "</" + elements[0] + '>');
				construct.write("\n\t\t<" + elements[1] + '>' + user[ind].getAge() + "</" + elements[1] + '>');
				construct.write("\n\t</User>\n\t\t<" + elements[2] + '>');
				for(Word w : user[ind].wronglySpelt)
				{
					construct.write(w.getSpelling());
				}
				construct.write( "</" + elements[2] + '>');
				ind ++;
			}
			construct.write("\n</UserInfo>");
		} catch (IOException ex) {
			System.err.println(ex.getStackTrace());
		} finally {
			try {construct.close();} catch (Exception ex) {}
		}
	}
}
