package Test;

import XMLFileEditor.XMLParser;
import BackEnd.BackEnd;
import BackEnd.Word;

public class BackEndTester {

	public static void main(String[] args) {
		BackEnd test = new BackEnd();
		test.printAllWords();
		XMLParser.list.remove("porcupine");
	}

}
