package ohmypatt.patt.behavioral.chains.apitrack.model;

public abstract class APIKey {
	private final String key;
	private int quota;

	public APIKey(String key) {
		this.key = key;
		this.quota = getMaxQuota();
	}

	protected APIKey(String key, int quota) {
		this.key = key;
		this.quota = quota;
	}

	public static boolean validateAPIKey(String key) {
		return (key != null && key.matches("[A-Za-z0-9]+") && key.length() < 6);
	}

	public static boolean validateAPIKey(APIKey apiKey) {
		return validateAPIKey(apiKey.key);
	}

	protected abstract int getMaxQuota();

	public String getKey() {
		return key;
	}

	public int getRemainingQuota() {
		return getMaxQuota() - quota;
	}

	public boolean isFetchable() {
		return getMaxQuota() > 0;
	}

	public void reset() {
		this.quota = getMaxQuota();
	}

	public void takeQuota() {
		quota--;
	}
}
