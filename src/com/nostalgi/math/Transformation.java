package com.nostalgi.math;

public class Transformation implements ITransformation {

	private Vector3f translation;
	private Vector3f scale;
	private Quaternion rot;
	
	public Transformation() {
		this.translation = new Vector3f(0,0,0);
		this.scale = new Vector3f(1,1,1);
		this.rot = new Quaternion();
	}
	
	@Override
	public void setTranslation(Vector3f translation) {
		this.translation = translation;
	}

	@Override
	public void setScale(Vector3f scale) {
		this.scale = scale;
	}

	@Override
	public void setRotation(Quaternion rotation) {
		this.rot = rotation;
	}

	@Override
	public Vector3f getTranslation() {
		return this.translation;
	}

	@Override
	public Vector3f getScale() {
		return this.scale;
	}

	@Override
	public Quaternion getRotation() {
		return this.rot;
	}

}
