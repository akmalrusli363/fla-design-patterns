package ohmypatt.patt.structural.composite.filesystem.main;

import ohmypatt.patt.structural.composite.filesystem.model.File;
import ohmypatt.patt.structural.composite.filesystem.model.Folder;

public class Main {

	public Main() {
		Folder root = new Folder("GoF");
		Folder folder1 = new Folder("creational");
		Folder folder2 = new Folder("structural");
		Folder folder3 = new Folder("behavioral");

		root.add(folder1);
		root.add(folder2);
		root.add(folder3);

		folder1.add(new File("prototype.txt"));
		folder1.add(new File("singleton.txt"));

		folder2.add(new File("adapter.txt"));
		folder2.add(new File("composite.txt"));
		folder2.add(new File("decorator.txt"));

		folder3.add(new File("observer.txt"));
		folder3.add(new File("strategy.txt"));

		root.add(new File("cover.jpg"));

		root.print();
	}

	public static void main(String[] args) {
		new Main();
	}

}
