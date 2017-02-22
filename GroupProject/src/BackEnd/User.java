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
	
	public ArrayList<ArrayList<String>> test = new ArrayList<ArrayList<String>>();
	
	
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
	
	public void setWordHistory(XMLParser wh){
		history = wh;
	}
	
	public static void main(String[] args){
		User test1 = new User();
		ArrayList<String> a1 = new ArrayList<String>();
		ArrayList<String> a2 = new ArrayList<String>();
		ArrayList<String> a3 = new ArrayList<String>();
		ArrayList<String> a4 = new ArrayList<String>();
		ArrayList<String> a5 = new ArrayList<String>();
		
		a1.add("A");
		a1.add("B");
		a1.add("C");
		a1.add("D");
		a1.add("E");
		
		a2.add("F");
		a2.add("G");
		a2.add("H");
		a2.add("I");
		a2.add("J");
		
		a3.add("K");
		a3.add("L");
		a3.add("M");
		a3.add("N");
		a3.add("O");
		
		a4.add("P");
		a4.add("Q");
		a4.add("R");
		a4.add("S");
		a4.add("T");
		
		a5.add("U");
		a5.add("V");
		a5.add("W");
		a5.add("X");
		a5.add("Y");
		
		
		test1.test.add(a1);
		test1.test.add(a2);
		test1.test.add(a3);
		test1.test.add(a4);
		test1.test.add(a5);
		
		System.out.println(test1.test.get(0).get(4));
		System.out.println(test1.test.get(1).get(2));
		System.out.println(test1.test.get(4).get(0));
		
	}


}
