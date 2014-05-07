package com.nostalgi.math;

public interface ITransformation {
	public void setTranslation(Vector3f translation);
	public void setScale(Vector3f scale);
	public void setRotation(Quaternion rotation);
	
	public Vector3f getTranslation();
	public Vector3f getScale();
	public Quaternion getRotation();
}
