package com.nostalgi.engine.camera;

import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.opengl.Display;

import com.nostalgi.math.Matrix4f;
import com.nostalgi.math.Vector3f;

public class OrthogonalCamera extends BasicCamera implements ICamera {
	
	@Override
	public void setViewAndFrustum() {
		
	}

	@Override
	public Matrix4f getProjectionMatrix() {
		// TODO Auto-generated method stub
		Matrix4f projection = new Matrix4f();
		projection.loadIdentity();
		
		//projection.fromFrustum(this.getFrustumNear(), this.getFrustumFar(), this.getFrustumLeft(), this.getFrustumRight(), this.getFrustumTop(), this.getFrustumBottom(), true);
		
		
		float fov = this.getFov();
		
		float near = this.getFrustumNear();
		float far = this.getFrustumFar();
		float width = this.getWidth();
		float height = this.getHeight();
		float aspect = width / height;
		float right = this.getFrustumRight();
		float left = this.getFrustumLeft();
		float top = this.getFrustumTop();
		float bottom = this.getFrustumBottom();
		float f = (float)Math.tan((double)fov/2);
		
		projection.m00 = 2.0f / (right-left);
		projection.m01 = 0;
		projection.m02 = 0;
		projection.m03 = 0;
		
		projection.m10 = 0;
		projection.m11 = 2.0f / (top - bottom);
		projection.m12 = 0;
		projection.m13 = 0;
		
		projection.m20 = 0;
		projection.m21 = 0;
		projection.m22 = -2.0f / (far-near);
		projection.m23 = -0;
		
		projection.m30 = -(right+left) / (right-left);
		projection.m31 = -(top+bottom) / (top-bottom);
		projection.m32 = -(far+near) / (far-near);
		projection.m33 = 1;
		
		return projection;
	}

	

}
