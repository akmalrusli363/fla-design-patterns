package ohmypatt.patt.behavioral.templatemethod.warmindo.model;

public class NoodleSoup extends Noodle {
	public NoodleSoup() {
		super();
	}

	public NoodleSoup(String name) {
		super(name);
	}

	@Override
	protected void prepareIngredients() {
		System.out.println("Put seasoning to pan..");
	}

	@Override
	protected void serve() {
		System.out.println("Serving noodle together with soup to bowl");
	}

	@Override
	public String getName() {
		return "Mie Kuah " + name;
	}
}