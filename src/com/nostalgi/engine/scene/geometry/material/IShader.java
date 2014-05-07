package com.nostalgi.engine.scene.geometry.material;

import java.util.ArrayList;
import java.util.HashMap;

public interface IShader {
	
	public HashMap<String, byte[]> getUniforms();
	public byte[] getUniform(String name);
	public void setUniform(String name, byte[] bytes);
	public void removeUniform(String name);
	
}
