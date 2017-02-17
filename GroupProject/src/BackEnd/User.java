package BackEnd;

import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;

import XMLFileEditor.XMLParser;

public class User {
	private String name;
	private int age;
	private int lastLevel;
	private XMLParser history;
	private int numOfWords;
	
	//public User(String name, int age){
		
	//}
	
	public String getName(){
		return name;
	}
	
	public void setName(String na){
		name = na;
	}
	
	public int getAge(){
		return age;
	}
	
	public void setAge(int ag){
		age = ag;
	}
	
	public int getLastLevel(){
		return lastLevel;
	}
	
	public void setLastLevel(int ll){
		lastLevel = ll;
	}
	
	public int getNumOfWords(){
		return numOfWords;
	}
	
	public void setNumOfWords(int now){
		numOfWords = now;
	}
	
	public XMLParser getWordHistory(){
		return history;
	}


}
