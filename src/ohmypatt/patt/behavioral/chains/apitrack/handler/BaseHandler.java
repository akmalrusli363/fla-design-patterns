package ohmypatt.patt.behavioral.chains.apitrack.handler;

public abstract class BaseHandler implements Handler {
	protected Handler nextHandler;

	@Override
	public void handle() {
		if (nextHandler != null)
			nextHandler.handle();
	}

	@Override
	public void setHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}
}
