package com.nostalgi.engine.gl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class Shader implements IShader {
	
	private int shaderId;
	
	
	public Shader(int shaderId) {
		this.shaderId = shaderId;
	}


	@Override
	public int getShaderId() {
		return this.shaderId;
	}
	
	@Override
	public boolean compileSource(String shaderSource) {
		GL20.glShaderSource(this.shaderId, shaderSource);
		GL20.glCompileShader(this.shaderId);
		
		return (GL20.glGetShaderi(this.shaderId, GL20.GL_COMPILE_STATUS) != GL11.GL_FALSE);
	}
}
