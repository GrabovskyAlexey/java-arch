package ru.grabovsky.composite;

public abstract class Employee {
    protected String name;
    protected Double salary;

    public Employee(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }

    public abstract String getName();
    public abstract void setSalary(Double salary);
    public abstract Double getSalary();
}
