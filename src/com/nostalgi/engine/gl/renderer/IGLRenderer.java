package com.nostalgi.engine.gl.renderer;

import java.io.File;
import java.io.FileNotFoundException;

import com.nostalgi.engine.camera.ICamera;
import com.nostalgi.engine.gl.IShader;
import com.nostalgi.engine.gl.IShaderFactory;
import com.nostalgi.engine.gl.exceptions.ShaderFactoryIsNotSetException;
import com.nostalgi.engine.scene.IGameScene;
import com.nostalgi.engine.scene.ISpatial;

public interface IGLRenderer {

	public void renderSpatial(ISpatial spatial);
	public void setShaderFactory(IShaderFactory factory);
	public IShader createShader(int type, String source)
			throws ShaderFactoryIsNotSetException;
	public IShader createShader(int type, File source) 
			throws ShaderFactoryIsNotSetException, FileNotFoundException;
	void setCamera(ICamera camera);
	ICamera getCamera();
	public void begin();
	public void cleanUp();
	void renderScene(IGameScene scene);
	void clear();
	
	
	
}
