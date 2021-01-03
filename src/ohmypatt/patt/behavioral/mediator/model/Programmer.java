package ohmypatt.patt.behavioral.mediator.model;

import ohmypatt.patt.behavioral.mediator.mediator.MessageMediator;

public class Programmer extends Employee {
    public Programmer(String name, MessageMediator mediator) {
        super(name, mediator);
        this.role = "Programmer";
    }
}