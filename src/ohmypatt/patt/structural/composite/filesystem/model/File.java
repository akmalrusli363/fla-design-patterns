package ohmypatt.patt.structural.composite.filesystem.model;

public class File extends FileSystemComponent {
	public File(String name) {
		super(name);
	}

	@Override
	public void open() {
		System.out.println("opening file...");
	}

	@Override
	public void rename(String name) {
		this.name = name;
		System.out.println("renaming file...");
	}

	@Override
	protected void printUsingIndentation(int indentation) {
		printIndent(indentation);
		System.out.println(name);
	}
}