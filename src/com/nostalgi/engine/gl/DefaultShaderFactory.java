package com.nostalgi.engine.gl;

import java.io.File;

import org.lwjgl.opengl.GL20;

import com.nostalgi.engine.gl.exceptions.ShaderFactoryIsNotSetException;

public class DefaultShaderFactory implements IShaderFactory {
	
	public DefaultShaderFactory() {
		
	}


	@Override
	public IShader createShader(int type, String source) {
		
		// Create a new shader.
		IShader s = new Shader(GL20.glCreateShader(type));
		
		// Upload a source to our shader.
		s.compileSource(source);
		
		System.err.println(GL20.glGetShaderInfoLog(s.getShaderId(), 1024));
		
		return s;
	}
	
	@Override
	public IShaderProgram createShaderProgram() {
		
		IShaderProgram sp = new ShaderProgram(GL20.glCreateProgram());
		
		return sp;
		
	}

}
