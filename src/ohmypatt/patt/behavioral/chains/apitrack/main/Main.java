package ohmypatt.patt.behavioral.chains.apitrack.main;

import ohmypatt.patt.behavioral.chains.apitrack.handler.Handler;
import ohmypatt.patt.behavioral.chains.apitrack.handler.HelloMessage;
import ohmypatt.patt.behavioral.chains.apitrack.handler.VerifyApiKeyHandler;
import ohmypatt.patt.behavioral.chains.apitrack.handler.VerifySessionHandler;
import ohmypatt.patt.behavioral.chains.apitrack.handler.VerifyUserHandler;
import ohmypatt.patt.behavioral.chains.apitrack.model.APIKey;
import ohmypatt.patt.behavioral.chains.apitrack.model.BasicAPIKey;
import ohmypatt.patt.behavioral.chains.apitrack.model.Session;
import ohmypatt.patt.behavioral.chains.apitrack.model.User;

public class Main {
	public Main() {
		APIKey key = new BasicAPIKey("asademar1a");
		User user = new User("Budi Setiawan", "budisetia01_", key);
		Session s = new Session(user);

		Handler hello = new HelloMessage(s);
		Handler middleware = getMiddlewareChains(s, hello);
		middleware.handle();
	}

	private Handler getMiddlewareChains(Session s, Handler targetHandler) {
		Handler apiKeyHandler = new VerifyApiKeyHandler(s.getUser().getApiKey(), targetHandler);
		Handler userHandler = new VerifyUserHandler(s.getUser(), apiKeyHandler);
		return new VerifySessionHandler(s, userHandler);
	}

	public static void main(String[] args) {
		new Main();
	}
}
