package com.AmrSaleh.learning_spring_framework_02.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
	private GameConsole game;
	public GameRunner(@Qualifier("PacMan") GameConsole game) {
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
