package com.nostalgi.engine.utils;

import java.lang.ref.ReferenceQueue;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.nostalgi.math.Vector2f;
import com.nostalgi.math.Vector3f;

public class BufferUtils {
	
	public static FloatBuffer createFloatBuffer(Vector3f[] components) {
		int bufferSize = (4*3*components.length); // This is the size of the BYTE Buffer.
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(bufferSize);
		byteBuffer.order(ByteOrder.nativeOrder());
		FloatBuffer buffer = byteBuffer.asFloatBuffer();
		
		for(Vector3f component : components) {
			buffer.put(component.getX());
			buffer.put(component.getY());
			buffer.put(component.getZ());
		}
		
		buffer.flip();
		
		return buffer;
	}
	
	public static FloatBuffer createFloatBuffer(Vector2f[] components) {
		int bufferSize = (4*2*components.length); // This is the size of the BYTE Buffer.
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(bufferSize);
		byteBuffer.order(ByteOrder.nativeOrder());
		FloatBuffer buffer = byteBuffer.asFloatBuffer();
		
		for(Vector2f component : components) {
			buffer.put(component.getX());
			buffer.put(component.getY());
		}
		
		buffer.flip();
		
		return buffer;
	}
	
	public static FloatBuffer createFloatBuffer(int size) {
        FloatBuffer buf = ByteBuffer.allocateDirect(4 * size).order(ByteOrder.nativeOrder()).asFloatBuffer();
        buf.clear();
        //onBufferAllocated(buf);
        return buf;
    }
	
	public static FloatBuffer createVector3Buffer(int vertices) {
        FloatBuffer vBuff = createFloatBuffer(3 * vertices);
        return vBuff;
    }
	
	public IntBuffer createIntBuffer(int[] indexes) {
		return null;
	}
	
	public static Buffer createByteBuffer(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocateDirect(1*bytes.length).order(ByteOrder.nativeOrder());
		buffer.put(bytes);
		buffer.flip();
		
		return buffer;
	}
}
