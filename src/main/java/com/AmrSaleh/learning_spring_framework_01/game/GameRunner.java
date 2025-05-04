package com.AmrSaleh.learning_spring_framework_01.game;

public class GameRunner {
	private GameConsole game;
	public GameRunner(GameConsole game) {
		this.game= game;
	}
	
	public void run() {
		System.out.println("Running game: " + this.game);
		this.game.down();
		this.game.up();
		this.game.left();
		this.game.right();
	}
}
