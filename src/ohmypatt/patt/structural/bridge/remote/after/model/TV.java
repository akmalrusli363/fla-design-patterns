package ohmypatt.patt.structural.bridge.remote.after.model;

public class TV implements Device {
	private static final int minVolume = 0, maxVolume = 100;
	private static final int minChannel = 0, maxChannel = 20;
	private boolean turnedOn = false, muted = false;
	private int volume = 10, channel = minChannel;

	@Override
	public void turnOn() {
		turnedOn = true;
	}

	@Override
	public void turnOff() {
		turnedOn = false;
	}

	@Override
	public boolean isTurnedOn() {
		return turnedOn;
	}

	@Override
	public int getVolume() {
		return volume;
	}

	@Override
	public void setVolume(int volume) {
		if (volume >= minVolume && volume <= maxVolume) {
			this.volume = volume;
		}
	}

	@Override
	public void toggleMute() {
		muted = !muted;
	}

	@Override
	public void next() {
		if (channel > maxChannel) {
			channel = minChannel;
		}
		channel++;
	}

	@Override
	public void previous() {
		if (channel < minChannel) {
			channel = maxChannel;
		}
		channel--;
	}

	@Override
	public String toString() {
		if (!turnedOn) {
			return "TV is off";
		}
		return String.format("TV: [Channel: %d | Volume: %d/%d]", channel, volume, maxVolume);
	}
}
