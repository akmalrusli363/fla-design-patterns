package ohmypatt.patt.behavioral.chains.apitrack.handler;

import ohmypatt.patt.behavioral.chains.apitrack.model.Session;

public class HelloMessage extends BaseHandler {
	private Session session;

	public HelloMessage(Session session) {
		this.session = session;
	}

	@Override
	public void handle() {
		System.out.println("Hello, " + session + "! Here is your API corner!");
	}
}
