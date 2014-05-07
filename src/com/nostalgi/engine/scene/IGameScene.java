package com.nostalgi.engine.scene;

import com.nostalgi.engine.IGameEngine;

public interface IGameScene {
	public void buildScene();
	public void setGameEngine(IGameEngine engine);
	public void setTransition(ISceneTransition transition);
	public ISpatial getRootNode();
	public void setRootNode(ISpatial node);
	
	public void update(float t);
}
