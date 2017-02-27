package BackEnd;
import java.util.ArrayList;

import XMLFileEditor.XMLParser;


public class BackEnd {
	private ArrayList<ArrayList<Word>> levels = new ArrayList<ArrayList<Word>>();
	
	public BackEnd(){
		setLevels();
	}
	//prints out all words
	public void printWords() {
		for(int i = 0; i < levels.size();i++) {
			System.out.println("LEVEL " + (i+1) + "\n---------");
			for(int c = 0; c < levels.get(i).size();c++) {
				System.out.println(levels.get(i).get(c));
			}
		}
	}
	//writes new words back into xml file
	public void saveLevels() {
		//make word list
		ArrayList<Word> words = new ArrayList<Word>();
		for(int i = 0; i < levels.size();i++) {
			for(int c = 0; c < levels.get(i).size();c++) {
				words.add(levels.get(i).get(c));
			}
		}
		//paste word list into array
		Word[] wordsArray = new Word[words.size()];
		for(int i = 0; i < wordsArray.length;i++) {
			wordsArray[i] = words.get(i);
		}
		//save
		String[] es = {"spelling", "level"};
		XMLParser.save(es, wordsArray, "res\\WordList.xml");
	}
	//sets the levels and words
	private void setLevels() {
		XMLParser.load("res\\WordList.xml");
		int maxLevel = 0;
		ArrayList<Word> wordList = XMLParser.wordList;
		//find maximum level
		for(int i = 0; i < wordList.size();i++) {
			if(wordList.get(i).getLevel()>maxLevel)
				maxLevel = wordList.get(i).getLevel();
		}
		//create all levels
		for(int i = 0; i < maxLevel; i++) {
			levels.add(new ArrayList<Word>());
		}
		//add all words
		for(int i = 0; i < wordList.size();i++) {
			addWord(wordList.get(i));
		}
	}
	//adds desired word
	public void addWord(Word w) {
		levels.get(w.getLevel()-1).add(w);
	}
	//removes desired word
	public void removeWord(Word w,int level){
		for(int i = 0;i<levels.get(level-1).size();i++){
			if(w.getSpelling().equals(levels.get(level-1).get(i).getSpelling())){
				levels.get(level-1).remove(i);
			}
		}
	}
	//adds highest level
	public void addLevel() {
		levels.add(new ArrayList<Word>());
	}
	//adds desired level
	public void addLevel(int level) {
		levels.add(level-1,new ArrayList<Word>());
	}
	//removes desired level
	public void removeLevel(int level) {
		levels.remove(level-1);
	}
	//returns next word
	public Word nextWord(Word word) {
		
		int c = word.getLevel()-1;
		int index = 0;
		
		for(int i = 0; i < levels.get(c).size();i++) {
			
			if(levels.get(c).get(i).getSpelling().equals(word.getSpelling())) {
				
				if(i+1==levels.get(c).size()) {
					//if at highest word of highest level ends game
					if(c == levels.size()-1) 
						return null;
					//if at highest word returns first word of next level
					c++;
					index = 0;
					return levels.get(c).get(index);
				} else {
					//next word in list
					index = i+1;
					return levels.get(c).get(index);
				}
				
			}	
		}
		//if desired word is not in the lists
		return null;
	}
	
}
