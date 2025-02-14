package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "contact")
public class ContactXml {

    @XmlAttribute
    private String phone;

    public ContactXml() {
    }

    public ContactXml(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }
}