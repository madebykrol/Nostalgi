package com.nostalgi.math;

public class Vector2f {
	float x;
	float y;
	
	public static Vector2f ZERO_VECTOR_2F = new Vector2f(0f,0f);
	
	
	public Vector2f (float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return this.x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getY() {
		return this.y;
	}
	
	public static Vector2f lerp(Vector2f from, Vector2f to, float t) {
		
		return new Vector2f(
			from.getX() + ((to.getX() - from.getX()) * t),
			from.getY() + ((to.getY() - from.getY()) * t) 
		);
	}
	
}
