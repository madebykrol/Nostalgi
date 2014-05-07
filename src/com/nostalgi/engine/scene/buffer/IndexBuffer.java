package com.nostalgi.engine.scene.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class IndexBuffer implements IBuffer {
	
	private ByteBuffer data;
	
	
	public IndexBuffer(ByteBuffer data) {
		this.data = data;
	}

	@Override
	public Buffer getBuffer() {
		return this.data;
	}

	@Override
	public int getComponents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setComponents(int components) {
		// TODO Auto-generated method stub
		
	}
	
	public int getIndexCount() {
		return this.data.limit();
	}
	
}
