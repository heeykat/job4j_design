package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int index = getIndex(key);
        boolean rst = table[index] == null;
        if (rst) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
        }
        return rst;
    }

    @Override
    public V get(K key) {
        V rst = null;
        int index = getIndex(key);
        boolean notEmpty = table[index] != null;
        if (isKeysEquals(key, notEmpty, index)) {
            rst = table[index].value;
        }
        return rst;
    }

    @Override
    public boolean remove(K key) {
        boolean rst = false;
        int index = getIndex(key);
        boolean notEmpty = table[index] != null;
        if (isKeysEquals(key, notEmpty, index)) {
            table[index] = null;
            rst = true;
            count--;
            modCount++;
        }
        return rst;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private int index = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        while (hash >= capacity) {
            hash = hash % capacity;
        }
        return hash;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry: table) {
            if (entry != null) {
                newTable[getIndex(entry.key)] = entry;
            }
        }
        table = newTable;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int getIndex(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private boolean isKeysEquals(K key, boolean notEmpty, int index) {
        return notEmpty && Objects.hashCode(table[index].key) == Objects.hashCode(key) && Objects.equals(table[index].key, key);
    }
}
