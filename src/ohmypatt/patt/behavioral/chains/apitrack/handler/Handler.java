package ohmypatt.patt.behavioral.chains.apitrack.handler;

public interface Handler {
	void handle();
	void setHandler(Handler nextHandler);
}
