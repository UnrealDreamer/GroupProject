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
	
///////////////CONSTRUCTORS///////////////	
	
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
	
	public void exit() {
		saveWords();
		saveUsers();
	}
	
///////////////////USERS///////////////////
	
	private void saveUsers() {
		
		User[] user = new User[users.size()];
		for(int i = 0; i < user.length;i++) {
			user[i] = users.get(i);
		}
		
		String[] es = new String[] {"Username","age","words"};
		XMLParser.saveUsers(es, user, "res\\UserInfo.xml");
	}
	
	private User setUser(User u) {
		XMLParser.loadUsers("res\\UserInfo.xml");
		users = XMLParser.users;
		for(int i = 0; i < users.size();i++) {
			if(u.getAge()==users.get(i).getAge() && u.getName().equalsIgnoreCase(users.get(i).getName())) {
				return users.get(i);
			}
		}
		users.add(u);
		return u;
	}
	
	public void printUserWords() {
		if(currentU!=null) {
			ArrayList<Word> temp = currentU.getCorrectlySpelt();
			for(int i = 0; i < temp.size();i++) {
				System.out.println(temp.get(i).getLevel() + " " + temp.get(i).getSpelling());
			}
		}
		
	}
	
///////////////////WORDS///////////////////
	
	//checks the user input against the correct spelling of the word
	public boolean[] checkSpelling(Word correct, Word input) {
		int min = Math.min(correct.getSpelling().length(), input.getSpelling().length());
		ArrayList<Boolean> red = new ArrayList<Boolean>();
		//sets red for incorrect parts of word
		for(int i = 0; i < min;i++) {
			if(correct.getSpelling().charAt(i)==input.getSpelling().charAt(i))
				red.add(false);
			else
				red.add(true);
		}
		//if there are remaining parts of the word (input is bigger than word)
		//sets remaining parts to red
		if(red.size()<input.getSpelling().length()) {
			for(int i = red.size(); i < input.getSpelling().length();i++) {
				red.add(false);
			}
		}
		//move red to array
		boolean[] redArray = new boolean[red.size()];
		for(int i = 0; i < red.size();i++) {
			redArray[i] = red.get(i);
		}
		
		return redArray;
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
	
	//adds desired word
	public void addWord(Word w) 
	{
		levels.get(w.getLevel()-1).add(w);
	}
	
	//removes desired word
	public void removeWord(Word w,int level)
	{
		for(int i = 0;i<levels.get(level-1).size();i++)
		{
			if(w.getSpelling().equalsIgnoreCase(levels.get(level-1).get(i).getSpelling())){
				levels.get(level-1).remove(i);
			}
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
	
	//returns next word
	public Word nextWord(Word word, boolean spelledRight) 
	{
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
