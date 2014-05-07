package com.nostalgi.math;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import com.nostalgi.engine.utils.BufferUtils;

/**
 * 
 * @author ksdkrol
 * 
 * ColorRGBA is a floating point value representation 
 * of a RGB + Alpha color
 * each of r,g,b and a is represented by a value between 0 and 1.
 * values larger or smaller will be clamped to the minimum and maximum values.
 */
public class ColorRGBA {
	
	public static final ColorRGBA BLACK 
		= new ColorRGBA(0f, 0f, 0f, 1f);
	public static final ColorRGBA BLACK_NO_ALPHA 
		= new ColorRGBA(0f,0f,0f,0f);
	
	public static final ColorRGBA WHITE
		= new ColorRGBA(1f,1f,1f,1f);
	
	public static final ColorRGBA BLUE
		= new ColorRGBA(0f,0f,1f,1f);
	
	public static final ColorRGBA RED 
		= new ColorRGBA(1f,0f,0f,1f);
	
	public static final float MAX = 1.0f;
	public static final float MIN = 0.0f;
	
	public float r,g,b,a;
	
	public ColorRGBA(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		
		clamp();
	}
	
	public ColorRGBA(ColorRGBA color) {
		this(color.r, color.g, color.b, color.a);
	}
	
	public ColorRGBA set(ColorRGBA rgba) {
		
		this.r = rgba.r;
		this.g = rgba.g;
		this.b = rgba.b;
		this.a = rgba.a;
		
		clamp();
		return this;
	}
	
	public ColorRGBA set(float r, float g, float b, float a) {
		
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		
		clamp();
		return this;
	}
	
	/**
	 * Clamp the values to be between 0 and 1.
	 * where 0 is the absolute MINIMUM
	 * and 1 is the absolute MAXIMUM
	 */
	public void clamp() {
		if (r > ColorRGBA.MAX) {
			r = ColorRGBA.MAX;
		} else if(r < ColorRGBA.MIN) {
			r = ColorRGBA.MIN;
		}
		
		if (g > ColorRGBA.MAX) {
			g = ColorRGBA.MAX;
		} else if(g < ColorRGBA.MIN) {
			g = ColorRGBA.MIN;
		}
		
		if (b > ColorRGBA.MAX) {
			b = ColorRGBA.MAX;
		} else if(b < ColorRGBA.MIN) {
			b = ColorRGBA.MIN;
		}
		
		if (a > ColorRGBA.MAX) {
			a = ColorRGBA.MAX;
		} else if(a < ColorRGBA.MIN) {
			a = ColorRGBA.MIN;
		}
	}
	
	
	public byte[] asBytesRGBA() {
		byte[] store = new byte[4];
		store[0] = (byte) ((int) (r * 255) & 0xFF);
		store[1] = (byte) ((int) (g * 255) & 0xFF);
		store[2] = (byte) ((int) (b * 255) & 0xFF);
		store[3] = (byte) ((int) (a * 255) & 0xFF);
		return store;
    }
	
	public ByteBuffer getBuffer() {
		ByteBuffer bb = (ByteBuffer)BufferUtils.createByteBuffer(this.asBytesRGBA());
		
		return bb;
	}
	
	public FloatBuffer getFloatBuffer() {
		float[] store = new float[4];
		store[0] = r;
		store[1] = g;
		store[2] = b;
		store[3] = a;
		FloatBuffer fb = (FloatBuffer)BufferUtils.createFloatBuffer(4);
		fb.put(store);
		fb.flip();
		return fb;
	}

	
}
