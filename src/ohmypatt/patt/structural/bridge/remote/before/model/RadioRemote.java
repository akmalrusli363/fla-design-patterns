package ohmypatt.patt.structural.bridge.remote.before.model;

public class RadioRemote implements Remote {
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
	public void increaseVolume() {
		if (volume < maxVolume) {
			volume++;
		}
	}

	@Override
	public void decreaseVolume() {
		if (volume > minVolume) {
			volume--;
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
}