package ohmypatt.patt.behavioral.observer.tuube.model;

import java.util.Vector;

import ohmypatt.patt.behavioral.observer.tuube.observer.Observable;

public class Channel implements Observable {
	private String name;
	private Vector<User> subscibers;
	
	public Channel(String name) {
		this.name = name;
		this.subscibers = new Vector<User>();
	}

	@Override
	public void addSubscriber(User user) {
		subscibers.add(user);
	}

	@Override
	public void removeSubscriber(User user) {
		subscibers.remove(user);
	}

	@Override
	public void notifyUser(String msg) {
		System.out.println(name + " posted a new video!");
		String constructedMessage = String.format("%s: %s", name, msg);
		for(User user : subscibers) {
			user.receiveNotification(constructedMessage);
		}
	}
}
