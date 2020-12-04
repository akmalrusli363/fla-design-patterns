package ohmypatt.patt.behavioral.observer.tuube.observer;

import ohmypatt.patt.behavioral.observer.tuube.model.User;

public interface Observable {
	public void addSubscriber(User user);
	public void removeSubscriber(User user);
	public void notifyUser(String msg);
}
