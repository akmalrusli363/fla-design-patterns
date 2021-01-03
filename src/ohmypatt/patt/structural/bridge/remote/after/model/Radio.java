package ohmypatt.patt.structural.bridge.remote.after.model;

public class Radio implements Device {
	private static final int minVolume = 0, maxVolume = 100;
	private static final float minFreq = 87.5f, maxFreq = 108.0f;
	private boolean turnedOn = false, muted = false;
	private int volume = 10;
	private float frequency = minFreq;

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
		if (frequency < maxFreq) {
			frequency += .1f;
		}
	}

	@Override
	public void previous() {
		if (frequency > minFreq) {
			frequency -= .1f;
		}
	}

	@Override
	public String toString() {
		return String.format("Radio: [Frequency: %.1f FM | Volume: %d/%d]", frequency, volume, maxVolume);
	}
}