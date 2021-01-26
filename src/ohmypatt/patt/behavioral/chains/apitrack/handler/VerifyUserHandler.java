package ohmypatt.patt.behavioral.chains.apitrack.handler;

import ohmypatt.patt.behavioral.chains.apitrack.model.User;

public class VerifyUserHandler extends BaseHandler {
	private User user;

	public VerifyUserHandler(User user, Handler nextHandler) {
		this.user = user;
		this.nextHandler = nextHandler;
	}

	@Override
	public void handle() {
		if (user == null) {
			System.err.println("Not Logged in!");
			return;
		}
		super.handle();
	}
}
