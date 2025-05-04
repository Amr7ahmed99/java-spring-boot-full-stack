package com.AmrSaleh.learning_spring_framework_01;

import com.AmrSaleh.learning_spring_framework_01.game.GameRunner;
import com.AmrSaleh.learning_spring_framework_01.game.SuperContraGame;

public class App01GamingBasicJava {
	public static void main(String[] args) {
		//var marioGame= new MarioGame();
		//var pacManGame= new PacManGame();
		var superContraGame= new SuperContraGame(); // 1: Object Creation
		var game= new GameRunner(superContraGame); // 2: Object Creation + Wiring of Dependencies ==> Game dependency of GameRunner
		game.run();
	}
}
