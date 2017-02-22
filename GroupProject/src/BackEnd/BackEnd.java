package BackEnd;
import java.util.ArrayList;


public class BackEnd {
	private ArrayList<ArrayList<Word>> levels = new ArrayList<ArrayList<Word>>();
	
	BackEnd(){
		//Loads all Words int levels
		
	}
	public void removeWord(Word w,int level){
		for(int i = 0;i<levels.get(level-1).size();i++){
			if(w.getSpelling().equals(levels.get(level-1).get(i).getSpelling())){
				levels.get(level-1).remove(i);
			}
		}
	}
}
