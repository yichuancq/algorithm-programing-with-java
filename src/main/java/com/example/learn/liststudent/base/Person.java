package com.example.learn.liststudent.base;

import java.util.Objects;

/**
 *
 */
public class Person {

    /**
     * 编号
     */
    private String number;

    /**
     * 姓名
     */
    private String name;


    public String getName() {
        return name;
    }

    public Person() {
    }

    public Person(String number, String name) {
        this.name = name;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(number, person.number) && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
