package BackEnd;
import java.util.ArrayList;

import XMLFileEditor.XMLParser;


public class BackEnd {
	private ArrayList<ArrayList<Word>> levels = new ArrayList<ArrayList<Word>>();
	
	BackEnd(){
		
		setLevels();
		
	}
	//
	private void saveLevels() {
		
	}
	//sets the levels and words
	private void setLevels() {
		
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
			addWord(wordList.get(i),wordList.get(i).getLevel());
		}
	}
	//adds desired word
	public void addWord(Word w, int level) {
		levels.get(level-1).add(w);
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
