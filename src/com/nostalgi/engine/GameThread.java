package com.nostalgi.engine;

public class GameThread extends Thread {
	private volatile IGameEngine engine;
	public GameThread(IGameEngine engine) {
		this.engine = engine;
	}
	
	public void run() {
		this.engine.run();
		this.engine.cleanUp();
	}
}
