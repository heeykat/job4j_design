package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.*;

import java.util.Arrays;

@XmlRootElement(name = "cat")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cat {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private boolean sex;
    private int age;
    private ContactXml ownersContact;
    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] statuses;

    public Cat(String name, boolean sex, int age, ContactXml ownersContact, String[] statuses) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.ownersContact = ownersContact;
        this.statuses = statuses;
    }

    public Cat() {
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
