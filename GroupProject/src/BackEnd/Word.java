package BackEnd;

public class Word {
	private String spelling;
	private int level;
	private int id;
	public static Word search(String w)
	{
		
		return 
	}
	public Word(int id, String s, int l){
		spelling = s;
		level = l;
		this.id = id;
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
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
}
