package BackEnd;

public class Word {
	private String spelling;
	private int level;
	
	public Word(String s, int l){
		spelling = s;
		level = l;
	}
	public String toString() {
		return spelling;
	}
	public String getSpelling(){
		return spelling;
	}
	public int getLevel(){
		return level;
	}
	public void setSpelling(String sp){
		spelling = sp;
	}
	public void setLevel(int lvl){
		level = lvl;
	}

}
