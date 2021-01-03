package ohmypatt.patt.behavioral.mediator.model;

import ohmypatt.patt.behavioral.mediator.mediator.MessageMediator;

public class DatabaseAdmin extends Employee {
    public DatabaseAdmin(String name, MessageMediator mediator) {
        super(name, mediator);
        this.role = "Database Admin";
    }
}

