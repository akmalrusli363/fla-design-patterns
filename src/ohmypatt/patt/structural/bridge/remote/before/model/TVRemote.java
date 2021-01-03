package ohmypatt.patt.structural.bridge.remote.before.model;

public class TVRemote implements Remote {
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
		if (channel > maxChannel) {
			channel = minChannel;
		}
		channel++;
	}

	@Override
	public void previous() {
		if (channel < 0) {
			channel = maxChannel;
		}
		channel--;
	}
}
