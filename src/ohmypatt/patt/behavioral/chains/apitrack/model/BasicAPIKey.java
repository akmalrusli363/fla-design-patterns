package ohmypatt.patt.behavioral.chains.apitrack.model;

public class BasicAPIKey extends APIKey {
	public BasicAPIKey(String apiKey) {
		super(apiKey);
	}

	public BasicAPIKey(String apiKey, int quota) {
		super(apiKey, quota);
	}

	@Override
	protected int getMaxQuota() {
		return 50;
	}
}
