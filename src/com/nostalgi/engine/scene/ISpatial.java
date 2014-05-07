package com.nostalgi.engine.scene;

import java.util.ArrayList;
import java.util.HashMap;

import com.nostalgi.math.ITransformation;
import com.nostalgi.math.Quaternion;
import com.nostalgi.math.Vector3f;

public interface ISpatial{
	
	public ITransformation getWorldTransformation();
	public void setLocalTransformation(ITransformation transformation);
	public ITransformation getLocalTransformation();
	
	
	public void setLocalTranslation(Vector3f translation);
	public Vector3f getLocalTranslation();
	public Vector3f getWorldTranslation();
	
	ArrayList<ISpatial> getChildren();
	public ISpatial getChild(String name);
	
	ISpatial addChildren(ISpatial child);
	
	public void setName(String name);
	public String getName();
	
	boolean hasParent();
	boolean hasParent(boolean bool);
	
	void setParent(ISpatial parent);
	ISpatial getParent();
	void setLocalScale(Vector3f scale);
	Vector3f getLocalScale();
	
	void setLocalRotation(Quaternion rotation);
	Quaternion getLocalRotation();
	void lookAt(Vector3f location, Vector3f up);
	
}
