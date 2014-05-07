package com.nostalgi.engine.gl;

public interface IShader {
	public int getShaderId();
	boolean compileSource(String shaderSource);
}
