package ohmypatt.patt.behavioral.mediator.mediator;

import ohmypatt.patt.behavioral.mediator.model.Employee;

public interface MessageMediator {
    public void sendMessage(String message, Employee from, Employee to, String division);
    public void addEmployee(Employee employee);
    public void removeEmployee(Employee employee);
}
