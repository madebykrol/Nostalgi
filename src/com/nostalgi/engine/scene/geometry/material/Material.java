package com.nostalgi.engine.scene.geometry.material;

import java.util.ArrayList;

import com.nostalgi.math.ColorRGBA;

public class Material implements IMaterial {

	private ColorRGBA color;
	private ArrayList<IShader> shaders;
	private ArrayList<ITexture> textures;
	@Override
	public void setColor(ColorRGBA color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ColorRGBA getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTexture(ITexture texture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ITexture getTexture() {
		// TODO Auto-generated method stub
		return null;
	}

}
