package ohmypatt.patt.behavioral.mediator.mediator;

import java.util.ArrayList;

import ohmypatt.patt.behavioral.mediator.model.Employee;

public class ChatMediator implements MessageMediator {
    private ArrayList<Employee> employeeList;

    public ChatMediator() {
        employeeList = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, Employee from, Employee to, String division) {
        if (to != null) {
            to.receiveMessage(message, from);
        } else {
            for (Employee employee : employeeList) {
                if (division == null && employee != from) {
                    employee.receiveMessage(message, from);
                } else if (employee.getRole().equals(division) && employee != from) {
                    employee.receiveMessage(message, from);
                }
            }
        }
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    @Override
    public void removeEmployee(Employee employee) {
        employeeList.remove(employee);
    }
}