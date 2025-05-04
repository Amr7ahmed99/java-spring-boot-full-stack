package com.AmrSaleh.learning_spring_framework_02.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("PacMan")
public class PacManGame implements GameConsole{
	public PacManGame(){}
	
	public void up() {
		System.out.println("Up");
	}
	public void down() {
		System.out.println("Down");
	}
	public void left() {
		System.out.println("Left");
	}
	public void right() {
		System.out.println("Right");
	}
}

