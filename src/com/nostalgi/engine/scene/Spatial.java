package com.nostalgi.engine.scene;

import java.util.ArrayList;
import java.util.HashMap;

import com.nostalgi.engine.utils.TempVars;
import com.nostalgi.math.ITransformation;
import com.nostalgi.math.Quaternion;
import com.nostalgi.math.Transformation;
import com.nostalgi.math.Vector3f;

public class Spatial implements ISpatial {

	private ArrayList<ISpatial> children;
	private ISpatial parent;
	
	private ITransformation worldTransformation;
	private ITransformation localTransformation;
	
	private boolean hasParent = false;
	
	private String id = null;
	
	private byte refreshStatus = 1;
	
	public Spatial (String id) {
		this.id = id;
		this.children = new ArrayList<ISpatial>();
		this.worldTransformation = new Transformation();
		this.localTransformation = new Transformation();
	}
	
	@Override
	public ArrayList<ISpatial> getChildren() {
		return this.children;
	}
	
	@Override
	public ISpatial getChild(String name) {
		for(ISpatial child : this.children) {
			if(child.getName().equals(name)) {
				return child;
			}
		}
		return null;
	}
	
	
	@Override
	public ISpatial addChildren(ISpatial child) {
		this.children.add(child);
		child.hasParent(true);
		child.setParent(this);
		return this;
	}

	
	@Override
	public boolean hasParent() {
		return this.hasParent;
	}
	
	@Override
	public boolean hasParent(boolean bool) {
		this.hasParent = bool;
		return this.hasParent();
	}

	@Override
	public ITransformation getWorldTransformation() {
		this.doTransformationUpdate();
		return this.worldTransformation;
	}

	@Override
	public void setLocalTransformation(ITransformation transformation) {
		this.localTransformation = transformation;
	}

	@Override
	public ITransformation getLocalTransformation() {
		return this.localTransformation;
	}

	@Override
	public void setLocalTranslation(Vector3f translation) {
		this.localTransformation.setTranslation(translation);
	}

	@Override
	public Vector3f getLocalTranslation() {
		// TODO Auto-generated method stub
		return this.localTransformation.getTranslation();
	}

	@Override
	public Vector3f getWorldTranslation() {
		return this.worldTransformation.getTranslation();
	}
	
	@Override
	public void setLocalScale(Vector3f scale) {
		// TODO Auto-generated method stub
		this.localTransformation.setScale(scale);
	}
	
	@Override
	public Vector3f getLocalScale() {
		return this.localTransformation.getScale();
	}
	
	public Quaternion getLocalRotation() {
		return this.localTransformation.getRotation();
	}
	
	public void setLocalRotation(Quaternion rotation) {
		this.localTransformation.setRotation(rotation);
	}
	
	public Quaternion getWorldRotation() {
		return this.worldTransformation.getRotation();
	}
	
	@Override
	public void lookAt(Vector3f location, Vector3f up) {
		assert TempVars.get().lock();
        Vector3f compVecA = TempVars.get().vect1;
        compVecA.set(location).subtractLocal(getWorldTranslation());
        getLocalRotation().lookAt(compVecA, up);
        assert TempVars.get().unlock();
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return this.id;
	}

	@Override
	public void setParent(ISpatial parent) {
		this.parent = parent;
	}

	@Override
	public ISpatial getParent() {
		return this.parent;
	}
	
	private void doTransformationUpdate() {
		
		if(this.refreshStatus != 0) {
			if(this.hasParent) {
				// Look upwards for transformations.
				ITransformation pT = this.parent.getWorldTransformation();
				ITransformation lT = this.localTransformation;
				
				Quaternion wRQ = pT.getRotation();
				Quaternion lRQ = lT.getRotation();
				
				Quaternion nWRQ = lRQ.mult(wRQ);
				
				Vector3f wTV = pT.getTranslation();
				Vector3f lTV = lT.getTranslation();
				
				Vector3f rWT = wTV.add(wRQ.mult(lTV));
				
				Vector3f scale = lT.getScale().multLocal(pT.getScale());
				this.worldTransformation.setRotation(nWRQ);
				this.worldTransformation.setTranslation(rWT);
				this.worldTransformation.setScale(scale);
				
				
			}
			else {
				this.worldTransformation = this.localTransformation;
			}
		}
	}
}
