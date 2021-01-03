package ohmypatt.patt.structural.composite.filesystem.model;

import java.util.Vector;

public class Folder extends FileSystemComponent {
	private Vector<FileSystemComponent> components;

	public Folder(String name) {
		super(name);
		components = new Vector<FileSystemComponent>();
	}

	public void add(FileSystemComponent c) {
		components.add(c);
	}

	public void remove(FileSystemComponent c) {
		components.remove(c);
	}

	@Override
	public void open() {
		System.out.println("opening folder...");
	}

	@Override
	public void rename(String name) {
		this.name = name;
		System.out.println("renaming folder...");
	}

	@Override
	protected void printUsingIndentation(int indentation) {
		printIndent(indentation);
		System.out.println(name);

		for (FileSystemComponent c : components) {
			c.printUsingIndentation(indentation + 2);
		}
	}
}