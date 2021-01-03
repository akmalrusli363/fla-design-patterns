package ohmypatt.patt.behavioral.mediator.model;

import ohmypatt.patt.behavioral.mediator.mediator.MessageMediator;

public abstract class Employee {
    private String name;
    protected String role;
    protected MessageMediator mediator;

    public Employee(String name, MessageMediator mediator) {
        this.name = name;
        this.mediator = mediator;
        mediator.addEmployee(this);
    }

    public void sendMessage(String message, Employee to, String division) {
        mediator.sendMessage(message, this, to, division);
    }

    public void receiveMessage(String message, Employee from) {
        System.out.printf("%s sends message to %s: %s\n", from.toString(), this.toString(), message);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("[%s - %s]", getName(), getRole());
    }
}
