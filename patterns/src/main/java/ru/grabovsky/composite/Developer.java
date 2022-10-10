package ru.grabovsky.composite;

public class Developer extends Employee {
    public Developer(String name, Double salary) {
        super(name, salary);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public Double getSalary() {
        return salary;
    }
}
