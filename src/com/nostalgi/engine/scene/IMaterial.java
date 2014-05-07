package com.nostalgi.engine.scene;

import java.nio.FloatBuffer;

import com.nostalgi.math.ColorRGBA;

public interface IMaterial {

	public ColorRGBA getColor();
	public void setColor(ColorRGBA color);
}
