package com.examples.desktop;


import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.nostalgi.engine.SimpleGame;
import com.nostalgi.engine.camera.ICamera;
import com.nostalgi.engine.camera.PerspectiveCamera;
import com.nostalgi.engine.gl.DefaultShaderFactory;
import com.nostalgi.engine.gl.IShaderFactory;
import com.nostalgi.engine.gl.renderer.IGLRenderer;
import com.nostalgi.engine.gl.renderer.LWJGLSceneGraphRenderer;
import com.nostalgi.engine.scene.IGameScene;
import com.nostalgi.math.Quaternion;
import com.nostalgi.math.Vector3f;

public class Main extends SimpleGame {
	
	public Main() {
		super();
	}
	
	public static void main(String args[]) {
		IGameScene scene = new TestScene();
		Main main = new Main();
		try {
			main.setCurrentScene(scene);
			main.start();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init() {
		super.init();
		
		
	}
	
	@Override
	public void update(float t) {
		IGameScene s = this.getCurrentScene();
		ICamera camera = this.getCurrentCamera();
		
		//System.out.println(this.getFPS());
		
		
	}
	
	
	public void moveForward() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			
			Vector3f cP = this.getCurrentCamera().getPosition().clone();
			
			cP.z += 0.01;
			
			this.getCurrentCamera().setPosition(cP);
		}

	}
}
