package ohmypatt.patt.structural.facade.room.model;

abstract class BaseTV implements TV {
	private String tvMode = "Television";

	@Override
	public void tvMode() {
		System.out.println("Set TV mode to Television");
		tvMode = "Television";
	}

	@Override
	public void consoleMode() {
		System.out.println("Set TV mode to Game Console");
		tvMode = "Game Console";
	}
	
	void getTvStatus() {
		System.out.println("Current TV Mode: " + tvMode);
	}
	
	@Override
	public void setChannel(String channel) {
		System.out.println("Set TV channel to " + channel);
	}
}
