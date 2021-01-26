package ohmypatt.patt.behavioral.chains.apitrack.model;

public class User {
	private final String username, password;
	private APIKey apiKey;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, APIKey apiKey) {
		this.username = username;
		this.password = password;
		this.apiKey = apiKey;
	}

	public boolean hasApiKey() {
		return apiKey != null;
	}

	public APIKey getApiKey() {
		return apiKey;
	}

	public void setApiKey(APIKey apiKey) {
		this.apiKey = apiKey;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
