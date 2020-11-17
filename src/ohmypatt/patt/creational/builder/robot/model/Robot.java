package ohmypatt.patt.creational.builder.robot.model;

public class Robot implements Cloneable {

	private String shield, sword, gun, brainchip;

	/**
	 * Declare empty constructor which able to create basic Robot parts without
	 * infer too long parameters to declare an object
	 */
	public Robot() {
	}

	public String getShield() {
		return shield;
	}

	public void setShield(String shield) {
		this.shield = shield;
	}

	public String getSword() {
		return sword;
	}

	public void setSword(String sword) {
		this.sword = sword;
	}

	public String getGun() {
		return gun;
	}

	public void setGun(String gun) {
		this.gun = gun;
	}

	public String getBrainchip() {
		return brainchip;
	}

	public void setBrainchip(String brainchip) {
		this.brainchip = brainchip;
	}

	@Override
	public String toString() {
		return "Robot Properties" 
				+ "\n-----------" 
				+ "\nShield: " + getShield() 
				+ "\nGun: " + getGun() 
				+ "\nSword: " + getSword() 
				+ "\nBrain chip: " + getBrainchip();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Robot nInstance = new Robot();
		nInstance.setBrainchip(brainchip);
		nInstance.setGun(gun);
		nInstance.setSword(sword);
		nInstance.setShield(shield);
		return nInstance;
	}

	public Robot[] duplicate(int size) throws CloneNotSupportedException {
		Robot robots[] = new Robot[size];
		for (int i = 0; i < size; i++) {
			robots[i] = (Robot) this.clone();
		}
		return robots;
	}

}
