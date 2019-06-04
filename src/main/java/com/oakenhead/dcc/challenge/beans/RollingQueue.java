package com.oakenhead.dcc.challenge.beans;

import java.util.ArrayList;
import java.util.List;

public class RollingQueue<T> {

    private T lastElement = null;
    private final List<T> data; //array list is quite efficient in Java

    public RollingQueue() {
        data = new ArrayList<>();
    }

    public RollingQueue(final int expectedSize) {
        data = new ArrayList<>(expectedSize);
    }

    public T push(final T newTopValue) {

        lastElement = newTopValue;
        data.add(newTopValue);
        return newTopValue;

    }

    public T get(final int indexFromLast) {

        if (lastElement == null || indexFromLast >= data.size()) {
            throw new IndexOutOfBoundsException();
        }

        return data.get(data.size() - indexFromLast - 1);

    }

}
