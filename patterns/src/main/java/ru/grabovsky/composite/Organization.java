package ru.grabovsky.composite;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private final List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public Double getNetSalaries() {
        return employees.stream().mapToDouble(Employee::getSalary).sum();
    }
}
