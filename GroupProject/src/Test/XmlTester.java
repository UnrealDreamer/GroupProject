package Test;
import XMLFileEditor.XMLParser;

public class XmlTester {
	public static void main(String[] args)
	{
		XMLParser xml = new XMLParser();
		String aee[] = {"spelling", "level"};
        xml.load("res\\thing.xml", aee);
        System.out.println("wait");
	}
}
