package com.nostalgi.engine.camera;

import com.nostalgi.math.Matrix4f;
import com.nostalgi.math.Quaternion;
import com.nostalgi.math.Vector3f;

public interface ICamera {
	
	/**
	 * Set up the view frustum for the camera.
	 */
	public void setViewAndFrustum();	
	
	public Matrix4f getProjectionMatrix();
	
	/**
	 * Set the position of the camera
	 * @param pos
	 */
	public void setPosition(Vector3f pos);
	
	/**
	 * Get the position of the camera
	 * @return
	 */
	public Vector3f getPosition();
	
	/**
	 * Set the look at direction of the camera
	 * @param look
	 */
	public void setRotation(Quaternion look);
	
	public Quaternion getRotation();
	
	public void setFrustumLeft(float left);
	public float getFrustumLeft();
	
	public void setFrustumRight(float right);
	public float getFrustumRight();
	
	public void setFrustumTop(float top);
	public float getFrustumTop();
	
	public void setFrustumBottom(float bottom);
	public float getFrustumBottom();
	
	public void setFrustumNear(float near);
	public float getFrustumNear();
	
	public void setFrustumFar(float far);
	public float getFrustumFar();
	
	public int getWidth();
	public void setWidth(int width);
	
	public int getHeight();
	public void setHeight(int height);

	void setFov(float fov);

	float getFov();

	void move(Vector3f translation, float speed, float tD);

	void lookAt(Vector3f location, Vector3f up);
	
}
