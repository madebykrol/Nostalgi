package com.nostalgi.display;

public interface IDisplay {
	public void initDisplay();
	public void setDisplaySettings(IDisplaySettingsRepository displaySettings);
	public IDisplaySettingsRepository getDisplaySettings();
	public void destroy();
	public void update();
	public void sync(int freq);
}
