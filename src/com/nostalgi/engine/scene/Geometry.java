package com.nostalgi.engine.scene;

import com.nostalgi.engine.scene.geometry.IMesh;
import com.nostalgi.math.Vector3f;

public class Geometry extends Spatial {

	private IMesh mesh;
	private IMaterial material;
	
	public Geometry(String id, IMesh mesh) {
		super(id);
		this.mesh = mesh;
	}
	
	public IMesh getMesh() {
		return this.mesh;
	}
	
	public void setMaterial(IMaterial material) 
	{
		this.material = material;
	}

	public IMaterial getMaterial() {
		return this.material;
	}
	
}
