package ohmypatts.patt.creational.builder.computerset.builder;

import ohmypatts.patt.creational.builder.computerset.model.Computer;

public class ComputerBuilder {

	private Computer computer;
	
	public ComputerBuilder() {
		this(new Computer());
	}
	
	public ComputerBuilder(Computer computer) {
		this.computer = computer;
	}

	public void processor(String processor) {
		computer.setProcessor(processor);
	}
	
	public void ram(String ram) {
		computer.setRam(ram);
	}
	
	public void storage(String storage) {
		computer.setStorage(storage);
	}
	
	public void gpu(String gpu) {
		computer.setGpu(gpu);
	}
	
	public void cooling(String cooling) {
		computer.setCooling(cooling);
	}
	
	public Computer getResult() {
		return computer;
	}
	
	public void resetBuilder() {
		computer = new Computer();
	}
	
}
