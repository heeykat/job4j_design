package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.Contact;
import ru.job4j.serialization.Cat;
import java.util.ArrayList;
import java.util.List;

public class MainPojo {
    public static void main(String[] args) {

        /* JSONObject из json-строки */
        JSONObject jsonContact = new JSONObject("phone", "+7 (111) 111-11-11");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Eats");
        list.add("Vomits");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Cat cat = new Cat("Kartoshka", true, 7, new Contact(11, "111111"),
                new String[]{"Eats", "Vomits"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", cat.getName());
        jsonObject.put("sex", cat.isSex());
        jsonObject.put("age", cat.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject);

        /* Преобразуем объект cat в json-строку */
        System.out.println(new JSONObject(cat));
    }
}
