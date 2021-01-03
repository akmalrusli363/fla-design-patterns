package ohmypatt.patt.behavioral.mediator.model;

import ohmypatt.patt.behavioral.mediator.mediator.MessageMediator;

public class Research extends Employee {
    public Research(String name, MessageMediator mediator) {
        super(name, mediator);
        this.role = "Research";
    }
}

