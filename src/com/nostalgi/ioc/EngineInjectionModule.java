package com.nostalgi.ioc;

import com.google.inject.AbstractModule;
import com.nostalgi.engine.IGameEngine;
import com.nostalgi.engine.SimpleGame;
import com.nostalgi.engine.camera.PerspectiveCamera;
import com.nostalgi.engine.camera.ICamera;
import com.nostalgi.engine.camera.OrthogonalCamera;

public class EngineInjectionModule extends AbstractModule {
	public void configure() {
		bind(IGameEngine.class).to(SimpleGame.class);
		bind(ICamera.class).to(PerspectiveCamera.class);
	}
}
