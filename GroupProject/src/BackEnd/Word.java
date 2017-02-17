package BackEnd;
import java.util.ArrayList;

import javax.sound.sampled.*;

public class Word {
	private AudioInputStream audio;
	private String spelling;
	private int level;
	
	public Word(AudioInputStream a, String s, int l){
		audio = a;
		spelling = s;
		level = l;
	}
	public String getSpelling(){
		return spelling;
	}
	public AudioInputStream getAudio(){
		return audio;
	}
	public int getLevel(){
		return level;
	}
	public void setSpelling(String sp){
		spelling = sp;
	}
	public void setAudio(AudioInputStream aud){
		audio = aud;
	}
	public void setLevel(int lvl){
		level = lvl;
	}

}
