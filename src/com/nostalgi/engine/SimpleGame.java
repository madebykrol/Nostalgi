package com.nostalgi.engine;

import java.lang.reflect.Method;

import org.lwjgl.opengl.Display;

import com.nostalgi.display.IDisplay;
import com.nostalgi.display.LWJGLDisplay;
import com.nostalgi.engine.camera.ICamera;
import com.nostalgi.engine.camera.PerspectiveCamera;
import com.nostalgi.engine.gl.DefaultShaderFactory;
import com.nostalgi.engine.gl.IShaderFactory;
import com.nostalgi.engine.gl.renderer.IGLRenderer;
import com.nostalgi.engine.gl.renderer.LWJGLSceneGraphRenderer;
import com.nostalgi.engine.input.IEventTrigger;
import com.nostalgi.engine.input.IInputHandler;
import com.nostalgi.engine.scene.IGameScene;
import com.nostalgi.engine.scene.ISpatial;
import com.nostalgi.engine.settings.ISettingsRepository;
import com.nostalgi.math.Quaternion;
import com.nostalgi.math.Vector3f;

public abstract class SimpleGame implements IGameEngine{

	private GameState state;
	private IGameScene currentScene;
	private ICamera currentCam;
	private IGLRenderer renderer;
	private IDisplay display;
	
	private IGameScene startScene;
	private ISettingsRepository gameSettings;
	private IInputHandler inputHandler;
	private long lastTime;
	
	private int fps;
	
	private long lastFPS;
	
	public SimpleGame() {
		
	}
	
	public void init() {
		this.initDisplay();
		this.initStartScene();
		this.initEngine();
	}	
	

	private void initDisplay() {
		this.setDisplay(new LWJGLDisplay(null));
	}
	
	private void initStartScene() {
		//new TestScene();
	}
	
	private void initEngine() {	
		
		ICamera cam = new PerspectiveCamera();
		
		IShaderFactory sf = new DefaultShaderFactory();
		IGLRenderer renderer = new LWJGLSceneGraphRenderer(sf);
		
		
		this.setRenderer(renderer);
		
		cam.setFrustumNear(0.1f);
		cam.setFrustumFar(100.0f);
		cam.setWidth(1024);
		cam.setHeight(768);
		cam.setFov(45);
		
		cam.setPosition(new Vector3f(0f,0f,-6f));
		
		this.setCurrentCamera(cam);
	}
	
	public void cleanUp() {
		this.display.destroy();
	}
	
	public void isRunning() {}
	
	public GameState getGameState() {
		return this.state;
	}
	
	public void setGameState(GameState state) {
		this.state = state;
	}
	
	public void render() {
		
		this.renderer.clear();
		
		if(this.currentScene != null) {
			ISpatial root = this.currentScene.getRootNode();
			for(ISpatial node : root.getChildren()) {
				this.renderer.renderSpatial(node);
			}
		}
		
		this.display.update();
		//this.display.sync(80);
	}
	
	public void run() {
		
		this.display.initDisplay();
		
		
		this.renderer.setCamera(this.currentCam);
		this.renderer.begin();
		
		lastFPS = getTime();
		getTimeDelta();
		// Game loop.
		while(this.getGameState() != GameState.STOPPED)
		{
			// Once per frame.
			getFPS();
			float delta = (float)getTimeDelta()/1000;
			
			if(this.inputHandler != null) {
				this.inputHandler.notifyListeners(delta);
			}
			
			this.update(delta);
			this.render();
			updateFPS();
				
			if(Display.isCloseRequested()) {
				this.setGameState(GameState.STOPPED);
			}
			
			
		}
		
		this.renderer.cleanUp();
	}
	
	public void setCurrentScene(IGameScene scene) {
		this.currentScene = scene;
		this.currentScene.buildScene();
	}
	
	public IGameScene getCurrentScene() {
		return this.currentScene;
	}
	
	public void setSettingsRepository(ISettingsRepository gameSettings) {
		this.gameSettings = gameSettings;
	}
	
	public ISettingsRepository getSettingsRepository() {
		return this.gameSettings;
	}
	
	public void setCurrentCamera(ICamera cam) {
		this.currentCam = cam;
	}
	
	public ICamera getCurrentCamera() {
		return this.currentCam;
	}
	
	public void setRenderer(IGLRenderer renderer) {
		this.renderer = renderer;
	}
	
	public IGLRenderer getRenderer() {
		return this.renderer;
	}

	public void start() {
		this.init();
		this.setSettingsRepository(this.gameSettings);
		this.setGameState(GameState.RUNNING);
		GameThread thread = new GameThread(this);
		thread.start();
	}

	public void setDisplay(IDisplay display) {
		this.display = display;
	}

	public IDisplay getDisplay() {
		return this.display;
	}
	
	public int getTimeDelta() {
		long time = getTime();
		int delta = (int)(time - lastTime);
		
		lastTime = time;
		return delta;
	}
	
	public long getTime() {
		return System.nanoTime()/100000;
	}
	
	public void updateFPS() {
		
		if (getTime() - lastFPS > 10000) {
			fps = 0;
			lastFPS = getTime();
		}
		fps++;
	}
	
	public int getFPS() {
		return fps;
	}
	
}
