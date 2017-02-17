package BackEnd;
import java.util.ArrayList;

import javax.sound.sampled.*;

public class Word {
	private AudioInputStream audio;
	private ArrayList<Character> spelling;
	private int level;
	
	public Word(AudioInputStream a, ArrayList<Character> s, int l){
		audio = a;
		spelling = s;
		level = l;
	}
	public ArrayList<Character> getSpelling(){
		return spelling;
	}
	public AudioInputStream getAudio(){
		return audio;
	}
	public int getLevel(){
		return level;
	}
	public void setSpelling(ArrayList<Character> sp){
		spelling = sp;
	}
	public void setAudio(AudioInputStream aud){
		audio = aud;
	}
	public void setLevel(int lvl){
		level = lvl;
	}

}
