package BackEnd;

import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;

import XMLFileEditor.XMLParser;

public class User {
	private String name;
	private int age;
	private int lastLevel;
	private int numOfWords;
	
	public ArrayList<Word> wronglySpelt = new ArrayList<Word>();
	
	
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
	public static void main(String[] args){
		
	/*	test1.test.add(a1);
		test1.test.add(a2);
		test1.test.add(a3);
		test1.test.add(a4);
		test1.test.add(a5);
		
		System.out.println(test1.test.get(0).get(4));
		System.out.println(test1.test.get(1).get(2));
		System.out.println(test1.test.get(4).get(0));
*/		
	}


}
