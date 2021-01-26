package ohmypatt.patt.behavioral.chains.apitrack.model;

public class Session {
	private User user;

	public Session(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isGuest() {
		return user == null;
	}

	@Override
	public String toString() {
		return isGuest() ? "Guest" : getUser().getUsername();
	}
}
