package com.examples.desktop;

import com.nostalgi.engine.IGameEngine;
import com.nostalgi.engine.scene.Geometry;
import com.nostalgi.engine.scene.IGameScene;
import com.nostalgi.engine.scene.IMaterial;
import com.nostalgi.engine.scene.ISpatial;
import com.nostalgi.engine.scene.ISceneTransition;
import com.nostalgi.engine.scene.Material;
import com.nostalgi.engine.scene.Spatial;
import com.nostalgi.engine.scene.geometry.shapes.Cube;
import com.nostalgi.engine.scene.geometry.shapes.Quad;
import com.nostalgi.math.ColorRGBA;
import com.nostalgi.math.Quaternion;
import com.nostalgi.math.Vector3f;

public class TestScene implements IGameScene {

	private IGameEngine engine;
	private ISpatial rootNode;
	
	@Override
	public void buildScene() {
		Geometry geom1 = new Geometry("Geom1", new Cube());
		geom1.setLocalTranslation(new Vector3f(0f, 0f, 0f));
		
		
		IMaterial mat = new Material(null, null);
		mat.setColor(ColorRGBA.BLUE);
		
		Quaternion q1 = new Quaternion();
		q1.fromAngleAxis((float)Math.toRadians(45), new Vector3f(0,0,1));
		geom1.setLocalRotation(q1);
		geom1.setLocalScale(new Vector3f(1,1,1));
		geom1.setMaterial(mat);
		
		Geometry geom2 = new Geometry("Geom2", new Cube());
		geom2.setMaterial(mat);
		geom2.setLocalTranslation(new Vector3f(-0.5f, 0f, 0f));
		
		Geometry geom3 = new Geometry("Geom3", new Cube());
		geom3.setLocalTranslation(new Vector3f(0.0f, 0f, -0.5f));
		
		Quaternion q2 = new Quaternion();
		q2.fromAngleAxis((float)Math.toRadians(90), new Vector3f(1,0,0));
		geom3.setLocalRotation(q2);
		geom3.setMaterial(mat);
		
		Quaternion q3 = new Quaternion();
		q3.fromAngleAxis((float)Math.toRadians(20), new Vector3f(0,0,1));
		//geom2.setLocalRotation(q3);
		
		ISpatial root = new Spatial("Universe");
		root.addChildren(geom1);
		geom1.addChildren(geom2);
		geom2.addChildren(geom3);
		
		this.setRootNode(root);
	}
	
	@Override
	public void setGameEngine(IGameEngine engine) {
		// TODO Auto-generated method stub
		this.engine = engine;
	}

	@Override
	public void setTransition(ISceneTransition transition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISpatial getRootNode() {
		// TODO Auto-generated method stub
		return this.rootNode;
	}

	@Override
	public void setRootNode(ISpatial node) {
		// TODO Auto-generated method stub
		this.rootNode = node;
	}

	@Override
	public void update(float t) {
		// TODO Auto-generated method stub
		
	}
	
}
