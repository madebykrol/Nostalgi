package com.nostalgi.engine.gl;

public interface IShaderProgram{
	
	public void setProgramId(int id);
	public int getProgramId();
	
	public void attachShader(int shaderId);
	void attachShader(IShader shader);
	
	
}
