package com.nostalgi.engine.scene.buffer;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import com.nostalgi.math.Vector3f;

public class VertexBuffer implements IBuffer {
	
	private int components;
	private int stride;
	private int vertexCount;
	
	private FloatBuffer data;
	
	/**
	 * 
	 * components is how many points a vertex consists of, 1, 2 or 3 points.
	 * 
	 * @param buffer
	 * @param components
	 * @param stride
	 */
	public VertexBuffer(FloatBuffer buffer, int components) {
		this(buffer, components, components*Float.SIZE);
	}
	
	public VertexBuffer(FloatBuffer buffer, int components, int stride) {
		// TODO Auto-generated constructor stub
		this.data = buffer;
		this.components = components;
		this.stride = stride;
		
		this.vertexCount = buffer.limit()/this.getComponents();
	}

	/**
	 * 
	 */
	public FloatBuffer getBuffer() {
		return data;
	}
	
	/**
	 * 
	 * @param size
	 */
	public void setComponents(int size) {
		this.components = size;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getComponents() {
		return this.components;
	}
	
	/**
	 * 
	 * @param stride
	 */
	public void setStride(int stride) {
		this.stride = stride;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getStride() {
		return this.stride;
	}
	
	public int getVertexCount() {
		return this.vertexCount;
	}
	
	public static final int BUFFER_TYPE_POSITION = 0;
	public static final int BUFFER_TYPE_TEXTURE = 1;
	public static final int BUFFER_TYPE_INDEX = 2;
	public static final int BUFFER_TYPE_NORMAL = 3;
	
}
