package com.nostalgi.engine.input;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.input.Keyboard;

public class LWJGLInputHandler implements IInputHandler {

	private HashMap<String, ArrayList<IEventTrigger>> mappings;
	
	public LWJGLInputHandler () {
		this.mappings = new HashMap<String, ArrayList<IEventTrigger>>();
	}
	
	@Override
	public void bindMapping(String event, IEventTrigger trigger) {
		
	}
	
	@Override
	public void addListener(String event, IActionListener listner) {
		
	}
	
	@Override
	public void addListener(String event, IAnalogListener listner) {
		
	}
	
	@Override
	public ArrayList<IEventTrigger> getBindings(String event) {
		return mappings.get(event);
	}
	
	@Override
	public void notifyListeners(float t) {
		for(ArrayList<IEventTrigger> triggers : this.mappings.values()) {
			for(IEventTrigger trigger : triggers) {
				
			}
		}
	}
	
}
