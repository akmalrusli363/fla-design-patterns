package ohmypatt.patt.behavioral.chains.apitrack.model;

public class SuperAPIKey extends APIKey {
	public SuperAPIKey(String apiKey) {
		super(apiKey);
	}

	public SuperAPIKey(String apiKey, int quota) {
		super(apiKey, quota);
	}

	@Override
	protected int getMaxQuota() {
		return 1000;
	}
}
