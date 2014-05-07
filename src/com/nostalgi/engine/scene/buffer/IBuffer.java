package com.nostalgi.engine.scene.buffer;

import java.nio.Buffer;

public interface IBuffer {
	
	public Buffer getBuffer();
	public int getComponents();
	public void setComponents(int components);
	
	
}
