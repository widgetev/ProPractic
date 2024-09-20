package org.example;

public class Employee {
    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getFunction() {
        return function;
    }

    Integer age;
    String name;
    String function;

    public Employee(int age, String name, String function) {
        this.age = age;
        this.name = name;
        this.function = function;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", function='" + function + '\'' +
                '}';
    }
}
