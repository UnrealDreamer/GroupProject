package Test;

import BackEnd.BackEnd;
import BackEnd.User;
import BackEnd.Word;

public class BackEndTester {

	public static void main(String[] args) {
		User testUser = new User("Joe",10);
		BackEnd test = new BackEnd(testUser);
		
		
		
		test.exit();
	}

}
