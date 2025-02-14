package ru.job4j.serialization;

import java.util.Arrays;

public class Cat {

    private final String name;
    private final boolean sex;
    private final int age;
    private final Contact ownersContact;
    private final String[] statuses;

    public Cat(String name, boolean sex, int age, Contact ownersContact, String[] statuses) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.ownersContact = ownersContact;
        this.statuses = statuses;
    }

    public String getName() {
        return name;
    }

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public Contact getOwnersContact() {
        return ownersContact;
    }

    public String[] getStatuses() {
        return statuses;
    }

    @Override
    public String toString() {
        return "Cat{"
                + "name='" + name + '\''
                + ", sex=" + sex
                + ", age=" + age
                + ", ownersContact=" + ownersContact
                + ", statuses=" + Arrays.toString(statuses)
                +
                '}';
    }
}
