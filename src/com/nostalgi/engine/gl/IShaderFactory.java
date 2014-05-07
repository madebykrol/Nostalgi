package com.nostalgi.engine.gl;

public interface IShaderFactory {
	public IShader createShader(int type, String source);
	IShaderProgram createShaderProgram();
}
