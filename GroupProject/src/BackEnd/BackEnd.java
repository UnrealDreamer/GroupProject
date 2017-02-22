package BackEnd;
import java.util.ArrayList;


public class BackEnd {
	private ArrayList<ArrayList<Word>> levels = new ArrayList<ArrayList<Word>>();
	
	BackEnd(){

		
		
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
					if(c == levels.size()-1) 
						return null;
					c++;
					index = 0;
					return levels.get(c).get(index);
				} else {
					index = i+1;
					return levels.get(c).get(index);
				}
				
			}	
		}
		return null;
	}
	
}
