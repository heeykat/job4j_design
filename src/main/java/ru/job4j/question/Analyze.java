package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analyze {
    public static Info diff(Set<User> previous, Set<User> current) {
        Info rst = new Info(0, 0, 0);
        Map<Integer, User> mapPrev = previous.stream().collect(Collectors.toMap(User::getId, u -> u));
        Map<Integer, User> mapCurr = current.stream().collect(Collectors.toMap(User::getId, u -> u));
        for (Map.Entry<Integer, User> entry : mapPrev.entrySet()) {
            if (!mapCurr.containsKey(entry.getKey())) {
                rst.setDeleted(rst.getDeleted() + 1);
            } else if (!entry.getValue().equals(mapCurr.get(entry.getKey()))) {
                rst.setChanged(rst.getChanged() + 1);
            }
        }
        for (Map.Entry<Integer, User> entry : mapCurr.entrySet()) {
            if (!mapPrev.containsKey(entry.getKey())) {
                rst.setAdded(rst.getAdded() + 1);
            }
        }
        return rst;
    }
}