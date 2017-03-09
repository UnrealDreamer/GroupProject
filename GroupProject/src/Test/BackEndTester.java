package Test;

import BackEnd.BackEnd;
import BackEnd.User;
import BackEnd.Word;

public class BackEndTester {

	public static void main(String[] args) {
		User testUser = new User("Joe",10);
		BackEnd test = new BackEnd(testUser);
		
		test.printAllWords();
		System.out.println("_________________");
		test.printUserWords();
		System.out.println("_________________");
		
		test.changeWordLevel(new Word("porcupine",3), 2);
		
		test.printAllWords();
		System.out.println("_________________");
		test.printUserWords();
		System.out.println("_________________");
		
		test.exit();
	}

}
