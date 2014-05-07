package com.nostalgi.engine.gl;

import org.lwjgl.opengl.GL20;

public class ShaderProgram implements IShaderProgram {

	private int programId;
	
	public ShaderProgram(int id) {
		this.programId = id;
	}
	
	@Override
	public void setProgramId(int id) {
		this.programId = id;
	}

	@Override
	public int getProgramId() {
		return this.programId;
	}

	@Override
	public void attachShader(IShader shader) {
		GL20.glAttachShader(this.programId, shader.getShaderId());
	}

	@Override
	public void attachShader(int shaderId) {
		GL20.glAttachShader(this.programId, shaderId);
	}
	
}
