package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Cat cat = new Cat("Kartoshka", true, 7, new Contact(11, "111111"),
                new String[]{"Eats", "Vomits"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(cat));

        final String personJson =
                "{"
                        + "\"name\":\"Kartoshka\","
                        + "\"sex\":true,"
                        + "\"age\":7,"
                        + "\"ownersContact\":"
                        + "{\"zipCode\":11,\"phone\":\"111111\"},"
                        + "\"statuses\":[\"Eats\",\"Vomits\"]"
                        + "}";

        final Cat catMod = gson.fromJson(personJson, Cat.class);
        System.out.println(catMod);
    }
}
