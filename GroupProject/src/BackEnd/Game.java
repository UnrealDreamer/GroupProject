package BackEnd;

import GUI.WelcomeScreen;
import GUI.spellingWindow;

public class Game {
	BackEnd back;
	WelcomeScreen welcome;
	spellingWindow spelling;
	Game(){
		welcome = new WelcomeScreen(this);
		
	}
	public static void main(String[] args){
		Game game = new Game();
		
	}
	public void moveToSpelling(User u){
		back = new BackEnd(u);
		spelling = new spellingWindow();
	}
}
