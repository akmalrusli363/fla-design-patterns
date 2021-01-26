package ohmypatt.patt.behavioral.chains.apitrack.handler;

import ohmypatt.patt.behavioral.chains.apitrack.model.Session;

public class VerifySessionHandler extends BaseHandler {
	private Session session;

	public VerifySessionHandler(Session session, Handler nextHandler) {
		this.session = session;
		this.nextHandler = nextHandler;
	}

	@Override
	public void handle() {
		if (session == null || session.isGuest()) {
			System.err.println("Not Logged in!");
			return;
		}
		super.handle();
	}
}
