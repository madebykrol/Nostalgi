package com.nostalgi.engine.scene.geometry;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import com.nostalgi.engine.scene.buffer.IBuffer;
import com.nostalgi.engine.scene.buffer.IndexBuffer;
import com.nostalgi.engine.scene.buffer.VertexBuffer;

public class Mesh implements IMesh {
	
	protected int vertexSize;
	protected IBuffer[] buffers;
	
	public static int COLOR_BYTE_OFFSET = 0;
	public static int TEXTURE_BYTE_OFFSET = 0;
	public static int NORMALS_BYTE_OFFSET = 0;
	
	public boolean isStatic;
	
	
	public static int FLOAT_BYTE_SIZE = 4;
	
	public Mesh () {
		
		if(this.buffers == null) {
			this.buffers = new IBuffer[3];
		}
	}
	
	public void setBuffer(int type, Buffer buffer) {
		this.setBuffer(type, 1, buffer);
	}
	
	public void setBuffer(int type, int components, Buffer buffer) {
		
		IBuffer b = null;
		
		switch(type) {
			case VertexBuffer.BUFFER_TYPE_POSITION :
				
				b = new VertexBuffer((FloatBuffer)buffer,
						components, 0);
				
				break;
				
			case VertexBuffer.BUFFER_TYPE_INDEX :
				
				b = new IndexBuffer((ByteBuffer)buffer);
				break;
			
			case VertexBuffer.BUFFER_TYPE_TEXTURE : 
				
				break;
				
			case VertexBuffer.BUFFER_TYPE_NORMAL : 
				
				break;
		}
		
		
		this.buffers[type] = b;
	}
	
	public IBuffer getBuffer(int type) {
		IBuffer buffer = null;
		try {
			buffer = buffers[type];
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		return buffer;
	}
	
	@Override
	public IBoundVolume getBoundingVolume() {
		return null;
	}

	@Override
	public boolean isStatic() {
		return this.isStatic;
	}

	@Override
	public boolean isStatic(boolean isStatic) {
		this.isStatic = isStatic;
		return this.isStatic;
	}
	
}
