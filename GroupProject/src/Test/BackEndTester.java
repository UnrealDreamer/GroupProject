package Test;

import BackEnd.BackEnd;
import BackEnd.User;

public class BackEndTester {

	public static void main(String[] args) {
		User joe = new User("Joe",21);
		BackEnd test = new BackEnd(joe);
		//test.printAllWords();
		test.printUserWords();
		//XMLParser.list.remove("porcupine");
	}

}
