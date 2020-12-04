package ohmypatt.patt.behavioral.observer.tuube.main;

import ohmypatt.patt.behavioral.observer.tuube.model.Channel;
import ohmypatt.patt.behavioral.observer.tuube.model.User;

public class Main {
	public static void main(String[] args) {
		Channel ch = new Channel("Budi Setiawan Channel");
		User user1 = new User("Wika Wika");
		User user2 = new User("Jobot TV");
		User user3 = new User("Jinjit Karunia");
		
		ch.addSubscriber(user1);
		ch.addSubscriber(user2);
		ch.notifyUser("Ada video baru buat anda! Jangan lupa di subscribe ya!");
		
		user3.subscribe(ch);
		ch.notifyUser("Video baru!");
	}
}
