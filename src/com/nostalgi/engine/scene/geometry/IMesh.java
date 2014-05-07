package com.nostalgi.engine.scene.geometry;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import com.nostalgi.engine.scene.buffer.IBuffer;
import com.nostalgi.engine.scene.buffer.VertexBuffer;

public interface IMesh {
	
	public IBoundVolume getBoundingVolume();

	public IBuffer getBuffer(int type);
	
	public void setBuffer(int type, int components, Buffer buffer);
	
	public boolean isStatic();
	public boolean isStatic(boolean isStatic);
}
