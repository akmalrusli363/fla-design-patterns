package ohmypatt.patt.creational.factorymethod.milktea.model;

public abstract class MilkTea {
	protected String leaf, milk;
	
	public abstract void prepare();
	
	public void make() {
		System.out.println("Blend the leaf and milk");
	}
	
	public void serve() {
		System.out.println("Serve to customer");
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getBeverageName());
		if (leaf != null || milk != null || !leaf.isEmpty() || !milk.isEmpty()) {
			sb.append(" created from ");
			if (leaf != null && !leaf.isEmpty())
				sb.append(leaf);
			if (leaf != null && milk != null && !leaf.isEmpty() && !milk.isEmpty())
				sb.append(" and ");
			if (milk != null && !milk.isEmpty())
				sb.append(milk);
		}
		return getBeverageName();
		
	}
	
	protected abstract String getBeverageName();
	
}
