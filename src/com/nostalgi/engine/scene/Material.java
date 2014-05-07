package com.nostalgi.engine.scene;

import java.nio.FloatBuffer;

import com.nostalgi.io.IAssetManager;
import com.nostalgi.math.ColorRGBA;

public class Material implements IMaterial {
	
	private ColorRGBA color;
	
	public Material(IAssetManager assetManager, String shader) {
		
	}

	@Override
	public ColorRGBA getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	@Override
	public void setColor(ColorRGBA color) {
		// TODO Auto-generated method stub
		this.color = color;
	}
	
}
