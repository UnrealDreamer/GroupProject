package Test;
import XMLFileEditor.XMLParser;

public class XmlTester {
	public static void main(String[] args)
	{
		XMLParser xml = new XMLParser();
		String aee[] = {"firstname", "lastname", "nickname", "salary"};
        xml.load("res\\thing.xml", aee);
	}
}
