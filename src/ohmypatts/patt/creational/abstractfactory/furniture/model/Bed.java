package ohmypatts.patt.creational.abstractfactory.furniture.model;

import ohmypatts.patt.creational.abstractfactory.furniture.parts.FurnitureMaterial;

public abstract class Bed implements Furniture {
	private String cover, pillowFoam;
	private FurnitureMaterial material;
	
	public Bed() {
		assemble();
		System.out.println("Building bed...");
	}

	public void setMaterial(FurnitureMaterial material) {
		this.material = material;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public FurnitureMaterial getMaterial() {
		return material;
	}

	public String getCover() {
		return cover;
	}

	public String getPillowFoam() {
		return pillowFoam;
	}

	public void setPillowFoam(String pillowFoam) {
		this.pillowFoam = pillowFoam;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(describe());
		sb.append("\nMaterial: ").append(getMaterial());
		sb.append("\nCover: ").append(getCover());
		sb.append("\nPillow Foam: ").append(getPillowFoam());
		return sb.toString();
	}
}
