package com.nostalgi.engine.scene.geometry.shapes;

import com.nostalgi.engine.scene.buffer.VertexBuffer;
import com.nostalgi.engine.scene.geometry.IBoundVolume;
import com.nostalgi.engine.scene.geometry.Mesh;
import com.nostalgi.engine.utils.BufferUtils;
import com.nostalgi.math.Vector3f;

public class Cube extends Mesh{

	public Cube() {
		super();
		
		this.setBuffer(VertexBuffer.BUFFER_TYPE_POSITION, 3, BufferUtils.createFloatBuffer(
				new Vector3f[] {
						
						new Vector3f (-0.1f, -0.1f, 0.1f), 
						new Vector3f (0.1f, -0.1f, 0.1f), 
						new Vector3f (0.1f, 0.1f, 0.1f), 
						new Vector3f (-0.1f, 0.1f, 0.1f), 
						new Vector3f (-0.1f, -0.1f, -0.1f), 
						new Vector3f (0.1f, -0.1f, -0.1f), 
						new Vector3f (0.1f, 0.1f, -0.1f), 
						new Vector3f (-0.1f, 0.1f, -0.1f)
				}));
		
		this.setBuffer(VertexBuffer.BUFFER_TYPE_INDEX, BufferUtils.createByteBuffer(new byte[] {
				0, 1, 2, 2, 3, 0, 
				3, 2, 6, 6, 7, 3, 
				7, 6, 5, 5, 4, 7, 
				4, 0, 3, 3, 7, 4, 
				0, 1, 5, 5, 4, 0,
				1, 5, 6, 6, 2, 1 
		}));
	}

	@Override
	public IBoundVolume getBoundingVolume() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
