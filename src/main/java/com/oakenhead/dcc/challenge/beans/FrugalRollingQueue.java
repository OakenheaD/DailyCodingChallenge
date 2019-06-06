package com.oakenhead.dcc.challenge.beans;

import com.oakenhead.dcc.challenge.PairValue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FrugalRollingQueue<T> {

    private T lastElement = null;
    private final List<T[]> data; //array list is quite efficient in Java
    private final int perLayer = 10;  //arbitrary value
    private final AtomicInteger fetchingSize = new AtomicInteger(0);
    private final AtomicInteger insertingSize = new AtomicInteger(0);

    public FrugalRollingQueue() {
        data = new ArrayList<>();
    }

    public FrugalRollingQueue(final int expectedSize) {
        data = new ArrayList<>(expectedSize / perLayer + 1);
    }

    public T push(final T newTopValue) {

        lastElement = newTopValue;
        insert(newTopValue);
        return newTopValue;

    }

    public void validateIndex(final int indexFromLast) {

        if (lastElement == null || indexFromLast >= fetchingSize.get()) {
            throw new IndexOutOfBoundsException();
        }

    }

    public T get(final int indexFromLast) {

        validateIndex(indexFromLast);

        final int dataIndex = fetchingSize.get() - indexFromLast - 1;
        final PairValue<Integer, Integer> expectedIndex = computeIndexLayering(dataIndex);

        return data.get(expectedIndex.left)[expectedIndex.right];

    }

    @SuppressWarnings("unchecked")
    private synchronized void insert(final T value) {

        final PairValue<Integer, Integer> insertionIndex = computeIndexLayering(insertingSize.getAndIncrement());

        final int topLayerIndex = insertionIndex.left;
        final int dataArrayIndex = insertionIndex.right;

        final T[] dataLayer;

        if (topLayerIndex >= data.size()) {
            dataLayer = (T[]) new Object[perLayer];
            data.add(dataLayer);
        } else {
            dataLayer = data.get(topLayerIndex);
        }

        dataLayer[dataArrayIndex] = value;

        fetchingSize.incrementAndGet();  //we may miss a value, but we'll never run into NPEs and mis indexing.

    }

    private synchronized PairValue<Integer, Integer> computeIndexLayering(final int elementIndex) {
        return new PairValue<>(elementIndex / perLayer, elementIndex % perLayer);
    }
}
