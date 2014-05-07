package com.nostalgi.engine.scene;

import com.nostalgi.math.Matrix4f;

public interface IMatrixStack {
	public Matrix4f push();
	public Matrix4f pop();
	public int getCurrentMatrixDepth();
}
