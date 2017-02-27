package Test;

import BackEnd.BackEnd;
import BackEnd.Word;

public class BackEndTester {

	public static void main(String[] args) {
		BackEnd test = new BackEnd();
		test.printWords();
		//test.addLevel();
		test.addWord(new Word(1001,"user",1));
		System.out.println("______________");
		test.printWords();
		
		test.saveLevels();
	}

}
