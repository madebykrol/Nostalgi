package com.nostalgi.engine.input;

import org.lwjgl.input.Keyboard;

public class LWJGLKeyboardThread extends Thread {
	private volatile IInputHandler inputHandler;
	
	public LWJGLKeyboardThread(IInputHandler handler) {
		this.inputHandler = handler;
	}
	
	public void run() {
		while(Keyboard.next()) {
			
		}
	}
}
