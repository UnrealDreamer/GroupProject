package Test;
import BackEnd.User;
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

/*		String[] s ={"spelling", "level"};
		Word[] es ={new Word(1001, "race", 1), new Word(2001, "racial", 3), new Word(3001, "flamingo", 3), new Word(3002, "character", 3), new Word(3003, "porcupine", 3)};
		XMLParser.save(s, es, "res\\WordList.xml");*/
	/*	XMLParser.load("res\\WordList.xml");
		XMLParser.loadUsers("res\\UserInfo.xml");
		for(User u : XMLParser.users)
		{	
			for(Word w : u.getCorrectlySpelt())
				System.out.println("Word: " + w.getSpelling());
			System.out.println();
		}*/
		User u = new User();
		u.addWord(new Word("bed", 1));
		u.addWord(new Word("boy", 1));
		u.addWord(new Word("age", 1));
		
		XMLParser.saveUsers(new String[]{"Username", "age", "words"}, new User[]{u}, "res/UserInfo.xml");
	}
}
