package Test;
import BackEnd.Word;
import XMLFileEditor.XMLParser;

public class XmlTester {
	public static void main(String[] args)
	{
	/*	XMLParser.load("res\\thing.xml");
		for(Word w: XMLParser.wordList)
		{
			System.out.println("Id: " +w.getID());		
			System.out.println("Word: " +w.getSpelling());		
			System.out.println("Level: " +w.getLevel());		
			
		}*/

		String[] s ={"spelling", "level"};
		Word[] es ={new Word(1, "race", 1), new Word(2, "racial", 3)};
		XMLParser.save(s, es, "res\\thing2.xml");
	}
}
