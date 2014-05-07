package com.nostalgi.engine.scene;

import java.util.ArrayList;
import java.util.Stack;

import com.nostalgi.math.Matrix4f;

public class MatrixStack implements IMatrixStack {
	private Stack<Matrix4f> stack;
	private int currentMatrixDepth = 0;
	
	public MatrixStack() {
		
		this.stack = new Stack<Matrix4f>();
		
		Matrix4f m = new Matrix4f();
		m.loadIdentity();
		
		this.stack.add(m);
	}
	
	@Override
	public Matrix4f push() {
		
		Matrix4f m = this.stack.lastElement().clone();
		

		this.stack.push(m);
		
		return m;
	}

	@Override
	public Matrix4f pop() {
		Matrix4f m = this.stack.pop();
		return m;
	}

	@Override
	public int getCurrentMatrixDepth() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Matrix4f getCurrentMatrix() {
		return stack.lastElement();
	}
}
