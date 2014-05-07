package com.nostalgi.engine.scene.geometry.material;

import com.nostalgi.math.ColorRGBA;

public interface IMaterial {
	// TextureCoordinates.
	
	public void setColor(ColorRGBA color);
	public ColorRGBA getColor();
	
	public void setTexture(ITexture texture);
	public ITexture getTexture();
	
}
