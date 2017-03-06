package Test;

import BackEnd.BackEnd;
import BackEnd.User;
import BackEnd.Word;

public class BackEndTester {

	public static void main(String[] args) {
		User testUser = new User("Joe",10);
		BackEnd test = new BackEnd(testUser);
		//testUser.addWord(new Word("character",3));
		//testUser.addWord(new Word("porcupine",3));
		//testUser.addWord(new Word("flamingo",3));
		test.exit();
		test.printAllWords();
		System.out.println("____________________________");
		test.printUserWords();
	}

}
