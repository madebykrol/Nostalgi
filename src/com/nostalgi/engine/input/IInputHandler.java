package com.nostalgi.engine.input;

import java.lang.reflect.Method;
import java.util.ArrayList;

public interface IInputHandler {
	public void bindMapping(String event, IEventTrigger trigger);
	public ArrayList<IEventTrigger> getBindings(String event);
	void notifyListeners(float t);
	void addListener(String event, IAnalogListener listner);
	void addListener(String event, IActionListener listner);
}
