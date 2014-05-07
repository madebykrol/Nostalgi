package com.nostalgi.engine.camera;

import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.opengl.Display;

import com.nostalgi.math.Matrix4f;
import com.nostalgi.math.Quaternion;
import com.nostalgi.math.Vector3f;

public class PerspectiveCamera extends BasicCamera implements ICamera {

	@Override
	public void setViewAndFrustum() {
	}

	@Override
	public Matrix4f getProjectionMatrix() {
		// TODO Auto-generated method stub
		Matrix4f projection = new Matrix4f();
		projection.loadIdentity();
		
		float f = 1.0f/(float) (Math.tan(Math.toRadians((double)this.getFov()/2)));
		float aspect = this.getWidth()/this.getHeight();
		float near = this.getFrustumNear();
		float far = this.getFrustumFar();
		
		float clip = far-near;
		
		projection.m00 = f/aspect;
		projection.m01 = 0;
		projection.m02 = 0;
		projection.m03 = 0;
		
		projection.m10 = 0;
		projection.m11 = f;
		projection.m12 = 0;
		projection.m13 = 0;
		
		projection.m20 = 0;
		projection.m21 = 0;
		projection.m22 = -(near+far)/clip;
		projection.m23 = -(2*near*far)/clip;
		
		projection.m30 = 0;
		projection.m31 = 0;
		projection.m32 = -1;
		projection.m33 = 0;
		
		return projection;
	}
	
	

}
