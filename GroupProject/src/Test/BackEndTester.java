package Test;

import BackEnd.BackEnd;
import BackEnd.User;
import BackEnd.Word;

public class BackEndTester {

	public static void main(String[] args) {
		User testUser = new User("test",0);
		BackEnd test = new BackEnd(testUser);
		
		for(int i = 0; i < 10; i++) {
			test.addLevel();
		}
		
		//LEVEL 1
		test.addWord(new Word("age",1));
		test.addWord(new Word("bed",1));
		test.addWord(new Word("bird",1));
		test.addWord(new Word("blue",1));
		test.addWord(new Word("boy",1));
		
		test.addWord(new Word("bus",1));
		test.addWord(new Word("car",1));
		test.addWord(new Word("cat",1));
		test.addWord(new Word("cold",1));
		test.addWord(new Word("dad",1));
		
		test.addWord(new Word("dog",1));
		test.addWord(new Word("eight",1));
		test.addWord(new Word("four",1));
		test.addWord(new Word("five",1));
		test.addWord(new Word("girl",1));
		
		test.addWord(new Word("green",1));
		test.addWord(new Word("hay",1));
		test.addWord(new Word("hot",1));
		test.addWord(new Word("mom",1));
		test.addWord(new Word("nine",1));
		
		test.addWord(new Word("now",1));
		test.addWord(new Word("one",1));
		test.addWord(new Word("red",1));
		test.addWord(new Word("seven",1));
		test.addWord(new Word("sit",1));
		
		test.addWord(new Word("six",1));
		test.addWord(new Word("ten",1));
		test.addWord(new Word("test",1));
		test.addWord(new Word("three",1));
		test.addWord(new Word("two",1));
		
		//LEVEL 2
		
		test.addWord(new Word("bike",2));
		test.addWord(new Word("boat",2));
		test.addWord(new Word("bowl",2));
		test.addWord(new Word("cube",2));
		test.addWord(new Word("damp",2));
		
		test.addWord(new Word("dust",2));
		test.addWord(new Word("eat",2));
		test.addWord(new Word("fork",2));
		test.addWord(new Word("game",2));
		test.addWord(new Word("gate",2));
		
		test.addWord(new Word("gust",2));
		test.addWord(new Word("heat",2));
		test.addWord(new Word("hike",2));
		test.addWord(new Word("home",2));
		test.addWord(new Word("gift",2));
		
		test.addWord(new Word("lamp",2));
		test.addWord(new Word("neck",2));
		test.addWord(new Word("page",2));
		test.addWord(new Word("pie",2));
		test.addWord(new Word("plane",2));
		
		test.addWord(new Word("pond",2));
		test.addWord(new Word("rate",2));
		test.addWord(new Word("room",2));
		test.addWord(new Word("seat",2));
		test.addWord(new Word("spoon",2));
		
		test.addWord(new Word("stamp",2));
		test.addWord(new Word("tie",2));
		test.addWord(new Word("toy",2));
		test.addWord(new Word("train",2));
		test.addWord(new Word("tube",2));
		
		//LEVEL 3
		test.addWord(new Word("add",3));
		test.addWord(new Word("apple",3));
		test.addWord(new Word("arm",3));
		test.addWord(new Word("banana",3));
		test.addWord(new Word("brother",3));
		
		test.addWord(new Word("card",3));
		test.addWord(new Word("cart",3));
		test.addWord(new Word("case",3));
		test.addWord(new Word("chain",3));
		test.addWord(new Word("chair",3));
		
		test.addWord(new Word("chicken",3));
		test.addWord(new Word("children",3));
		test.addWord(new Word("cookie",3));
		test.addWord(new Word("cupcake",3));
		test.addWord(new Word("doctor",3));
		
		test.addWord(new Word("draw",3));
		test.addWord(new Word("dream",3));
		test.addWord(new Word("eleven",3));
		test.addWord(new Word("family",3));
		test.addWord(new Word("father",3));
		
		test.addWord(new Word("grape",3));
		test.addWord(new Word("joke",3));
		test.addWord(new Word("luck",3));
		test.addWord(new Word("mother",3));
		test.addWord(new Word("orange",3));
		
		test.addWord(new Word("sister",3));
		test.addWord(new Word("twelve",3));
		test.addWord(new Word("water",3));
		test.addWord(new Word("yellow",3));
		test.addWord(new Word("zero",3));
		
		
		//LEVEL 4
		test.addWord(new Word("country",4));
		test.addWord(new Word("discover",4));
		test.addWord(new Word("dinosaur",4));
		test.addWord(new Word("example",4));
		test.addWord(new Word("except",4));
		
		test.addWord(new Word("excuse",4));
		test.addWord(new Word("field",4));
		test.addWord(new Word("flower",4));
		test.addWord(new Word("fool",4));
		test.addWord(new Word("fort",4));
		
		test.addWord(new Word("happy",4));
		test.addWord(new Word("harm",4));
		test.addWord(new Word("globe",4));
		test.addWord(new Word("grass",4));
		test.addWord(new Word("juice",4));
		
		test.addWord(new Word("king",4));
		test.addWord(new Word("kitchen",4));
		test.addWord(new Word("laughter",4));
		test.addWord(new Word("lunchroom",4));
		test.addWord(new Word("meal",4));
		
		test.addWord(new Word("milk",4));
		test.addWord(new Word("prince",4));
		test.addWord(new Word("princess",4));
		test.addWord(new Word("queen",4));
		test.addWord(new Word("quotient",4));
		
		test.addWord(new Word("reason",4));
		test.addWord(new Word("relationship",4));
		test.addWord(new Word("remember",4));
		test.addWord(new Word("trouble",4));
		test.addWord(new Word("tomorrow",4));
		
		
		//LEVEL 5
		test.addWord(new Word("aciton",5));
		test.addWord(new Word("allowed",5));
		test.addWord(new Word("annual",5));
		test.addWord(new Word("attention",5));
		test.addWord(new Word("beginning",5));
		
		test.addWord(new Word("capitol",5));
		test.addWord(new Word("celebrate",5));
		test.addWord(new Word("climate",5));
		test.addWord(new Word("condition",5));
		test.addWord(new Word("continued",5));
		
		test.addWord(new Word("curtain",5));
		test.addWord(new Word("delicious",5));
		test.addWord(new Word("dictionary",5));
		test.addWord(new Word("disappoint",5));
		test.addWord(new Word("energy",5));
		
		test.addWord(new Word("exact",5));
		test.addWord(new Word("express",5));
		test.addWord(new Word("finishsed",5));
		test.addWord(new Word("future",5));
		test.addWord(new Word("grasp",5));
		
		test.addWord(new Word("happened",5));
		test.addWord(new Word("hoarse",5));
		test.addWord(new Word("increase",5));
		test.addWord(new Word("interesting",5));
		test.addWord(new Word("jungle",5));
		
		test.addWord(new Word("lenght",5));
		test.addWord(new Word("mammal",5));
		test.addWord(new Word("melody",5));
		test.addWord(new Word("million",5));
		test.addWord(new Word("natural",5));
		
		
		//LEVEL 6
		test.addWord(new Word("arithmetic",6));
		test.addWord(new Word("ancient",6));
		test.addWord(new Word("audience",6));
		test.addWord(new Word("blown",6));
		test.addWord(new Word("canyon",6));
		
		test.addWord(new Word("ceiling",6));
		test.addWord(new Word("combination",6));
		test.addWord(new Word("concentration",6));
		test.addWord(new Word("contagious",6));
		test.addWord(new Word("coupon",6));
		
		test.addWord(new Word("culture",6));
		test.addWord(new Word("demonstrate",6));
		test.addWord(new Word("depth",6));
		test.addWord(new Word("discussion",6));
		test.addWord(new Word("echoes",6));
		
		test.addWord(new Word("element",6));
		test.addWord(new Word("emptiness",6));
		test.addWord(new Word("entrance",6));
		test.addWord(new Word("establish",6));
		test.addWord(new Word("exercise",6));
		
		test.addWord(new Word("faucet",6));
		test.addWord(new Word("forgetting",6));
		test.addWord(new Word("frighten",6));
		test.addWord(new Word("gaze",6));
		test.addWord(new Word("grateful",6));
		
		test.addWord(new Word("hangar",6));
		test.addWord(new Word("heroes",6));
		test.addWord(new Word("hunger",6));
		test.addWord(new Word("immediate",6));
		test.addWord(new Word("ingredient",6));
		
		
		
		//LEVEL 7
		test.addWord(new Word("abound",7));
		test.addWord(new Word("beacon",7));
		test.addWord(new Word("berserk",7));
		test.addWord(new Word("cache",7));
		test.addWord(new Word("celestrial",7));
		
		test.addWord(new Word("clarification",7));
		test.addWord(new Word("confiscate",7));
		test.addWord(new Word("data",7));
		test.addWord(new Word("detract",7));
		test.addWord(new Word("embezzle",7));
		
		test.addWord(new Word("encounter",7));
		test.addWord(new Word("leisurely",7));
		test.addWord(new Word("lethargic",7));
		test.addWord(new Word("mellow",7));
		test.addWord(new Word("nomadic",7));
		
		test.addWord(new Word("pantomine",7));
		test.addWord(new Word("pessimist",7));
		test.addWord(new Word("precaution",7));
		test.addWord(new Word("prosecute",7));
		test.addWord(new Word("puncture",7));
		
		test.addWord(new Word("quest",7));
		test.addWord(new Word("retaliate",7));
		test.addWord(new Word("rant",7));
		test.addWord(new Word("reinforce",7));
		test.addWord(new Word("seclusion",7));
		
		test.addWord(new Word("sham",7));
		test.addWord(new Word("status",7));
		test.addWord(new Word("turmoil",7));
		test.addWord(new Word("uncouth",7));
		test.addWord(new Word("xenophobic",7));
		
		//LEVEL 8
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		test.addWord(new Word("",8));
		
		//LEVEL 9
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		test.addWord(new Word("",9));
		
		//LEVEL 10
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		test.addWord(new Word("",10));
		
		test.exit();
	}

}
