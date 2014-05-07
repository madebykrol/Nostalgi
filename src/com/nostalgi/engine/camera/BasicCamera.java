package com.nostalgi.engine.camera;

import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.opengl.Display;

import com.nostalgi.engine.utils.TempVars;
import com.nostalgi.math.Matrix4f;
import com.nostalgi.math.Quaternion;
import com.nostalgi.math.Vector3f;

public abstract class BasicCamera implements ICamera{
	private Vector3f position;
	private Vector3f up;
	
	private Quaternion look;
	
	
	private float fLeft = -1;
	private float fRight = 1;
	private float fTop = -1;
	private float fBottom = 1;
	private float fNear = 0.1f;
	private float fFar = -1000f;
	
	private int width = 0;
	private int height = 0;
	
	private float fov = 90f;
	
	public BasicCamera () {
		this.look = new Quaternion();
	}
	
	@Override
	public void setFov(float fov) {
		this.fov = fov;
	}
	
	@Override
	public float getFov() {
		return this.fov;
	}
	
	public void setPosition(Vector3f pos) {
		// TODO Auto-generated method stub
		this.position = pos;
	}

	public Vector3f getPosition() {
		return this.position;
	}
	

	public abstract void setViewAndFrustum();

	
	public void setFrustumLeft(float left) {
		this.fLeft = left;
	}
	
	public float getFrustumLeft() {
		return this.fLeft;
	}
	
	public void setFrustumRight(float right) {
		this.fRight = right;
	}
	
	public float getFrustumRight() {
		return this.fRight;
	}
	
	public void setFrustumTop(float top) {
		this.fTop = top;
	}
	
	public float getFrustumTop() {
		return this.fTop;
	}
	
	public void setFrustumBottom(float bottom) {
		this.fBottom = bottom;
	}
	public float getFrustumBottom() {
		return this.fBottom;
	}
	
	public void setFrustumNear(float near) {
		this.fNear = near;
	}
	public float getFrustumNear() {
		return this.fNear;
	}
	
	public void setFrustumFar(float far) {
		this.fFar = far;
	}
	public float getFrustumFar() {
		return this.fFar;
	}
	
	@Override
	public void setRotation(Quaternion look) {
		// TODO Auto-generated method stub
		this.look = look;
	}

	@Override
	public Quaternion getRotation() {
		// TODO Auto-generated method stub
		return this.look;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	@Override
	public void setWidth(int width) {
		// TODO Auto-generated method stub
		this.width = width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}

	@Override
	public void setHeight(int height) {
		// TODO Auto-generated method stub
		this.height = height;
	}
	
	@Override
	public void move(Vector3f translation, float speed, float tD) {
		Vector3f t = this.look.mult(this.position);
		
	    this.position = this.position.add(t);
	    
	    System.out.println(this.position);
	}
	
	@Override
	public void lookAt(Vector3f location, Vector3f up) {
		assert TempVars.get().lock();
        Vector3f compVecA = TempVars.get().vect1;
        compVecA.set(location).subtractLocal(this.position);
        this.look.lookAt(compVecA, up);
        assert TempVars.get().unlock();
	}
}
