package com.nostalgi.engine;

import com.nostalgi.display.IDisplay;
import com.nostalgi.engine.camera.ICamera;
import com.nostalgi.engine.gl.renderer.IGLRenderer;
import com.nostalgi.engine.scene.IGameScene;
import com.nostalgi.engine.settings.ISettingsRepository;

public interface IGameEngine {
	
	public void run();
	public void update(float dT);
	public void render();
	
	public void init();
	public void start();
	public void cleanUp();
	
	public void setDisplay(IDisplay display);
	public IDisplay getDisplay();

	public void setCurrentScene(IGameScene startScene);
	public IGameScene getCurrentScene();

	public void setSettingsRepository(ISettingsRepository gameSettings);
	public ISettingsRepository getSettingsRepository();

	public void setCurrentCamera(ICamera cam);
	
	public GameState getGameState();

	public void setGameState(GameState state);
	
	public void setRenderer(IGLRenderer renderer);
	
	public IGLRenderer getRenderer();
	
	public int getTimeDelta();
}
