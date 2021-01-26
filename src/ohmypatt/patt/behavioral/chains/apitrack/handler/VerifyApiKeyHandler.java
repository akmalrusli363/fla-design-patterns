package ohmypatt.patt.behavioral.chains.apitrack.handler;

import ohmypatt.patt.behavioral.chains.apitrack.model.APIKey;

public class VerifyApiKeyHandler extends BaseHandler {
	private APIKey apiKey;

	public VerifyApiKeyHandler(APIKey apiKey, Handler nextHandler) {
		this.apiKey = apiKey;
		this.nextHandler = nextHandler;
	}

	@Override
	public void handle() {
		if (apiKey == null) {
			System.err.println("Please generate API Key first!");
		} else if (APIKey.validateAPIKey(apiKey)) {
			System.err.println("API Key must be alphanumeric, non-whitespaces, >= 6 chars!");
		} else if (!apiKey.isFetchable()) {
			System.err.println("API Key was expired or out of quota! Please reset or generate new API key!");
		} else {
			apiKey.takeQuota();
			super.handle();
		}
	}
}
