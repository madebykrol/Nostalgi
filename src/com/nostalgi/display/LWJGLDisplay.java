package com.nostalgi.display;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class LWJGLDisplay implements IDisplay {

	private IDisplaySettingsRepository displaySettings;
	private DisplayMode displayMode;
	private boolean vsync = false;
	
	public LWJGLDisplay(IDisplaySettingsRepository displaySettings) {
		this.displaySettings = displaySettings;
	}
	
	@Override
	public void initDisplay() {
		try {
			
			this.displayMode = new DisplayMode(1024,768);
			PixelFormat pixelFormat = new PixelFormat();
			ContextAttribs contextAtrributes = new ContextAttribs(3, 2)
						.withForwardCompatible(true)
						.withProfileCore(true);
			Display.setDisplayMode(this.displayMode);
			Display.setTitle(" Nostalgi Demo ");
			Display.create(pixelFormat, contextAtrributes);
			Display.setFullscreen(true);
			Keyboard.create();
			
			Display.setVSyncEnabled(this.vsync);
			
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setDisplaySettings(IDisplaySettingsRepository displaySettings) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IDisplaySettingsRepository getDisplaySettings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		Display.destroy();
	}

	@Override
	public void update() {
		Display.update();
	}
	
	@Override
	public void sync(int fps) {
		Display.sync(fps);
	}
}
