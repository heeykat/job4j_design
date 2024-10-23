package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean rst = !contains(value);
        if (rst) {
            set.add(value);
        }
        return rst;
    }

    @Override
    public boolean contains(T value) {
        boolean rst = false;
        for (T el : set) {
            if (Objects.equals(el, value)) {
                rst = true;
                break;
            }
        }
        return rst;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}