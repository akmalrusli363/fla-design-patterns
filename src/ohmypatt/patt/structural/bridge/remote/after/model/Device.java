package ohmypatt.patt.structural.bridge.remote.after.model;

public interface Device {
	void turnOn();
	void turnOff();
	boolean isTurnedOn();
	int getVolume();
	void setVolume(int volume);
	void toggleMute();
	void next();
	void previous();
	String toString();
}
