package com.AmrSaleh.learning_spring_framework_01.game;

public class SuperContraGame implements GameConsole{
	
	public SuperContraGame() {}
	
	public void up() {
		System.out.println("Jump");
	}
	public void down() {
		System.out.println("Sit");
	}
	public void left() {
		System.out.println("Go back");
	}
	public void right() {
		System.out.println("Shoot a bullet");
	}
}
