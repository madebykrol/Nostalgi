package com.nostalgi.engine.scene.geometry.shapes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import com.nostalgi.engine.scene.buffer.VertexBuffer;
import com.nostalgi.engine.scene.geometry.IBoundVolume;
import com.nostalgi.engine.scene.geometry.Mesh;
import com.nostalgi.engine.utils.BufferUtils;
import com.nostalgi.math.Vector3f;

public class Triangle extends Mesh {
	
	
	public Triangle() {
		super();
		
		this.setBuffer(VertexBuffer.BUFFER_TYPE_POSITION, 3, BufferUtils.createFloatBuffer(
				new Vector3f[] {
						
						new Vector3f(-0.5f, -0.5f, -0.5f),
						new Vector3f(0.5f, -0.5f, -0.5f),
						new Vector3f(0.5f, 0.5f, -0.5f),

				}));
		

		this.setBuffer(VertexBuffer.BUFFER_TYPE_INDEX, BufferUtils.createByteBuffer(new byte[] {
				0, 1, 2,
				}));
	}

	@Override
	public IBoundVolume getBoundingVolume() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	
}
