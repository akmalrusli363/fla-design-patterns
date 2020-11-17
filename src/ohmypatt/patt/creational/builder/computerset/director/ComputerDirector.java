package ohmypatt.patt.creational.builder.computerset.director;

import ohmypatt.patt.creational.builder.computerset.builder.ComputerBuilder;
import ohmypatt.patt.creational.builder.computerset.model.Computer;

public class ComputerDirector {
	
	// Declare Singleton
	private static ComputerDirector director = null;
	
	private ComputerDirector() {}
	
	public static synchronized ComputerDirector getInstance() {
		if (director == null) {
			director = new ComputerDirector();
		}
		return director;
	}
	
	public Computer officeComputer() {
		ComputerBuilder builder = new ComputerBuilder();
		builder.processor("AMD A9 2320-E Lite Processor");
		builder.ram("VGen Rescue Memory 4GB");
		builder.storage("Samsung 1TB HDD Storage");
		return builder.getResult();
	}
	
	public Computer programmerComputer() {
		ComputerBuilder builder = new ComputerBuilder();
		builder.processor("AMD Ryzen 5-3030");
		builder.ram("Samsung 8GB RAM");
		builder.storage("Seagate Hybirdist 1TB SSHD Storage");
		builder.gpu("NVIDIA GT 1050");
		return builder.getResult();
	}
	
	public Computer casualGamerComputer() {
		ComputerBuilder builder = new ComputerBuilder();
		builder.processor("Intel Core i5-1145U");
		builder.ram("Kingston HyperX 16GB RAM");
		builder.storage("Samsung SSD 320GB + WD Barracuda 1TB Storage");
		builder.gpu("NVIDIA RTX 2060");
		return builder.getResult();
	}
	
	public Computer masterRaceComputer() {
		ComputerBuilder builder = new ComputerBuilder();
		builder.processor("Intel Core i9-11980X");
		builder.ram("Corsair 64GB RAM");
		builder.gpu("RTX 3090 OC Founder Edition");
		builder.storage("Corsair M2 SATA 4TB SSD");
		builder.cooling("Aquahook NiCo Turbo Intercooler");
		return builder.getResult();
	}

}
