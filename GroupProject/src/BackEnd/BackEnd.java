package BackEnd;
import java.util.ArrayList;
import java.util.TreeMap;
import XMLFileEditor.XMLParser;

public class BackEnd 
{
	
/////////////////VARIABLES/////////////////
	
	private ArrayList<ArrayList<Word>> levels = new ArrayList<ArrayList<Word>>();
	private ArrayList<User> users = new ArrayList<User>();
	private User currentU = null;
	
//////////CONSTRUCTORS/IMPORTANT//////////
	
	//for the game
	public BackEnd(User u)
	{
		setWords();
		currentU = setUser(u);
	}
	
	//for the editor
	public BackEnd()
	{
		setWords();
		currentU = null;
	}
	
	//when the game is ended
	public void exit() {
		saveWords();
		saveUsers();
	}
	
///////////////////USERS///////////////////
	
	public User getUser(){
		return currentU;
	}
	//writes out new user info into UserInfo.xml
	private void saveUsers() 
	{
		
		User[] user = new User[users.size()];
		for(int i = 0; i < user.length;i++) 
		{
			user[i] = users.get(i);
		}
		
		String[] es = new String[] {"Username","age","words"};
		XMLParser.saveUsers(es, user, "res\\UserInfo.xml");
	}
	
	
	
	
	//sets current User and adds new users XMLParser
	private User setUser(User u) 
	{
		XMLParser.loadUsers("res\\UserInfo.xml");
		users = XMLParser.users;
		for(int i = 0; i < users.size();i++) 
		{
			if(u.getAge()==users.get(i).getAge() && u.getName().equalsIgnoreCase(users.get(i).getName())) 
			{
				User us = users.get(i);
				
				System.out.println(us.getCorrectlySpelt().size());
				//
				us.setLastLevel(us.getCorrectlySpelt().get(us.getCorrectlySpelt().size()-1).getLevel());
				
				return us;
			}
		}
		users.add(u);
		if(u.getAge()<=5)
			u.setLastLevel(1);
		else if(u.getAge()<=6)
			u.setLastLevel(2);
		else if(u.getAge()<=7)
			u.setLastLevel(3);
		else if(u.getAge()<=8)
			u.setLastLevel(4);
		else if(u.getAge()<=8)
			u.setLastLevel(5);
		else if(u.getAge()<=9)
			u.setLastLevel(6);
		else
			u.setLastLevel(7);
		return u;
	}
	
	//prints user's correctly spelt words
	public void printUserWords() 
	{
		if(currentU!=null) 
		{
			ArrayList<Word> temp = currentU.getCorrectlySpelt();
			for(int i = 0; i < temp.size();i++) 
			{
				System.out.println(temp.get(i).getLevel() + " " + temp.get(i).getSpelling());
			}
		}
	}
	
///////////////////WORDS///////////////////
	
	//checks the user input against the correct spelling of the word
	public ArrayList<Boolean> checkSpelling(Word correct, String input) 
	{
		int min = Math.min(correct.getSpelling().length(), input.length());
		ArrayList<Boolean> red = new ArrayList<Boolean>();
		//sets red for incorrect parts of word
		for(int i = 0; i < min;i++) 
		{
			if(correct.getSpelling().substring(i,i+1).equalsIgnoreCase(input.substring(i,i+1)))
				red.add(false);
			else
				red.add(true);
		}
		//if there are remaining parts of the word (input is bigger than word)
		//sets remaining parts to red
		if(red.size()<input.length()) 
		{
			for(int i = red.size(); i < input.length();i++) 
			{
				red.add(true);
			}
		}
		
		return red;
	}
	
	//prints out all words
	public void printAllWords() 
	{
		for(int i = 0; i < levels.size();i++) 
		{
			System.out.println("LEVEL " + (i+1) + "\n---------");
			for(int c = 0; c < levels.get(i).size();c++) 
			{
				System.out.println(levels.get(i).get(c));
			}
			System.out.println();
		}
	}
	
	//writes new words back into xml file
	private void saveWords() 
	{
		//make word list
		ArrayList<Word> words = new ArrayList<Word>();
		for(int i = 0; i < levels.size();i++) 
		{
			for(int c = 0; c < levels.get(i).size();c++) 
			{
				words.add(levels.get(i).get(c));
			}
		}
		//paste word list into array
		Word[] wordsArray = new Word[words.size()];
		for(int i = 0; i < wordsArray.length;i++) 
		{
			wordsArray[i] = words.get(i);
		}
		//save
		String[] es = {"spelling", "level"};
		XMLParser.save(es, wordsArray, "res\\WordList.xml");
	}
	
	//sets the levels and words
	private void setWords() 
	{
		XMLParser.load("res\\WordList.xml");
		int maxLevel = 0;
		//TreeMap<String, Word> wordList = XMLParser.list;
		ArrayList<Word> wordList = new ArrayList<Word>(XMLParser.list.values());
		//find maximum level
		for(int i = 0; i < wordList.size();i++) 
		{
			//ArrayList<Word> words = (ArrayList<Word>) wordList.values();
			if(wordList.get(i).getLevel()>maxLevel)
				maxLevel = wordList.get(i).getLevel();
		}
		//create all levels
		for(int i = 0; i < maxLevel; i++) 
		{
			levels.add(new ArrayList<Word>());
		}
		//add all words
		for(int i = 0; i < wordList.size();i++) 
		{
			addWord(wordList.get(i));
		}
	}
	
	//changes a word's spelling
	public void changeWordSpelling(Word w, String newSpelling)
	{
		int level = w.getLevel();
		for(int i = 0;i<levels.get(level-1).size();i++)
		{
			if(w.getSpelling().equalsIgnoreCase(levels.get(level-1).get(i).getSpelling()))
			{
				levels.get(level-1).get(i).setSpelling(newSpelling);
			}
		}
		//changes the word's spelling for all users
		for(int i = 0; i < users.size();i++) 
		{
			users.get(i).changeSpelling(w, newSpelling);
		}
	}
	
	//changes a word's level
	public void changeWordLevel(Word w,int newLevel) 
	{
		int level = w.getLevel();
		for(int i = 0;i<levels.get(level-1).size();i++)
		{
			if(w.getSpelling().equalsIgnoreCase(levels.get(level-1).get(i).getSpelling()))
			{
				levels.get(level-1).get(i).setLevel(newLevel);
				levels.get(newLevel-1).add(new Word(w.getSpelling(),newLevel));
				levels.get(level-1).remove(i);
			}
		}
	}
	
	//adds desired word
	public void addWord(Word w) 
	{
		levels.get(w.getLevel()-1).add(w);
	}
	
	//removes desired word
	public void removeWord(Word w)
	{
		for(int i = 0;i<levels.get(w.getLevel()-1).size();i++)
		{
			if(w.getSpelling().equalsIgnoreCase(levels.get(w.getLevel()-1).get(i).getSpelling())){
				levels.get(w.getLevel()-1).remove(i);
				
			}
		}
		//removes the word for all users
		for(int i = 0; i < users.size();i++) 
		{
			users.get(i).removeWord(w);
		}
	}
	
	//adds highest level
	public void addLevel() 
	{
		levels.add(new ArrayList<Word>());
	}
	
	//adds desired level
	public void addLevel(int level) 
	{
		levels.add(level-1, new ArrayList<Word>());
		for(int i = level;i<levels.size();i++) 
		{
			for(int c = 0; c < levels.get(i).size();c++) 
			{
				Word w = levels.get(i).get(c);
				w.setLevel(w.getLevel()+1);
			}
		}	
	}
	
	//removes desired level
	public void removeLevel(int level) 
	{
		levels.remove(level-1);
		for(int i = level-1;i<levels.size();i++) 
		{
			for(int c = 0; c < levels.get(i).size();c++)
			{
				Word word = levels.get(i).get(c);
				word.setLevel(word.getLevel()-1);
			}
		}
	}
	
	//checks if current user has already spelled the desired word
	private boolean alreadySpelt(Word word) 
	{
		for(int i = 0; i < currentU.getCorrectlySpelt().size();i++) 
		{
			if(word.getSpelling().equalsIgnoreCase(currentU.getCorrectlySpelt().get(i).getSpelling()))
				return true;
		}
		return false;
	}
	
	public ArrayList<ArrayList<Word>> getWordList() {
		return levels;
	}
	
	//returns next word
	public Word nextWord(Word word, boolean spelledRight) 
	{
		if(word == null){
			return levels.get(currentU.getLastLevel()-1).get(0);
		}
		if(spelledRight)
		{
			currentU.addWord(word);
		}
		
		int c = word.getLevel()-1;
		int index = 0;
		
		for(int i = 0; i < levels.get(c).size();i++) 
		{
			if(levels.get(c).get(i).getSpelling().equalsIgnoreCase(word.getSpelling())) 
			{
				if(i+1>=levels.get(c).size()) 
				{
					//if at highest word of highest level ends game
					if(c == levels.size()-1) 
						return null;
					
					//if at highest word returns first word of next level
					c++;
					index = 0;
					currentU.setLastLevel(getUser().getLastLevel()+1);
					if(!alreadySpelt(levels.get(c).get(index)))
						return levels.get(c).get(index);
					else
						return nextWord(levels.get(c).get(index),false);
				} else 
				{
					//next word in list
					index = i+1;
					if(!alreadySpelt(levels.get(c).get(index)))
						return levels.get(c).get(index);
					else
						return nextWord(levels.get(c).get(index),false);
				}
			}	
		}
		//if desired word is not in the lists
		return null;
	}
	
}
/*
frame.addWindowListener(new java.awt.event.WindowAdapter() {
	public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		
	}
});
*/