package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    int counterInput = 0;
    int counterOutput = 0;

    public T poll() {
        if (counterInput + counterOutput == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (counterOutput == 0) {
            while (counterInput > 0) {
                output.push(input.pop());
                counterInput--;
                counterOutput++;
            }
        }
        counterOutput--;
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        counterInput++;
    }
}