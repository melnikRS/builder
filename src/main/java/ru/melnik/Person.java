package ru.melnik;

import java.util.Objects;
import java.util.OptionalInt;

public class Person {
    private final String name;
    private final String surname;
    private int age;
    private String address;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        return OptionalInt.of(age);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void happyBirthday() {
        age++;
    }

    public boolean hasAge() {
        return age > 0;
    }

    public boolean hasAddress() {
        if (address == null) return false;
        return !address.isEmpty();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(age, person.age) && Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, address);
    }

    public static class PersonBuilder {

        private String name;
        private String surname;
        private int age;
        private String address;

        public PersonBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public PersonBuilder setAge(int age) {
            if (age < 0) throw new IllegalArgumentException("Не корректно указан возраст");
            this.age = age;
            return this;
        }

        public PersonBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Person build() {
            if ((name == null || name.isEmpty()) || (surname == null || surname.isEmpty())) throw new IllegalStateException("Не заполнены обязательные поля");
            Person person = new Person(name, surname, age);
            person.setAddress(this.address);
            return person;
        }
    }

    PersonBuilder newChildBuilder() {
        PersonBuilder personChild = new PersonBuilder();
        personChild.setSurname(getSurname());
        personChild.setAddress(getAddress());
        return personChild;
    }

}
