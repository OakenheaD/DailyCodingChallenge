package com.oakenhead.dcc.challenge.month05.day01;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.TripleValue;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

@Service
public class XORLinkedList extends AbstractCodingChallenge<String, XORLinkedList.XorList<String>> {

    @Override
    public String dateString() {
        return "2019 May 01";
    }

    @Override
    public String shortName() {
        return "XOR list";
    }

    @Override
    public String getChallengeTask() {
        return "This problem was asked by Google.\n" +
                "\n" +
                "An XOR linked list is a more memory efficient doubly linked list. " +
                "Instead of each node holding next and prev fields, " +
                "it holds a field named both, which is an XOR of the next node and the previous node. Implement an XOR linked list; " +
                "it has an add(element) which adds the element to the end, and a get(index) which returns the node at index.\n" +
                "\n" +
                "If using a language that has no pointers (such as Python), you can assume you have access to get_pointer " +
                "and dereference_pointer functions that converts between nodes and memory addresses.";
    }

    @Override
    public String runChallengeCase(final XorList<String> input) {
        return null;
    }

    @Override
    public List<TripleValue<String, Function<XorList<String>, String>, XorList<String>>> getTestCases() {

        final XorList<String> testList = new XorList<>();
        testList.addElement("zero");
        testList.addElement("one");
        testList.addElement("two");
        testList.addElement("three");
        testList.addElement("four");
        testList.addElement("five");
        testList.addElement("six");
        testList.addElement("seven");
        testList.addElement("eight");
        testList.addElement("nine");
        ;

        return Arrays.asList(
                new TripleValue<>("zero",  (list) -> list.get(0), testList),
                new TripleValue<>("one",   (list) -> list.get(1), testList),
                new TripleValue<>("two",   (list) -> list.get(2), testList),
                new TripleValue<>("three", (list) -> list.get(3), testList),
                new TripleValue<>("eight", (list) -> list.get(8), testList),
                new TripleValue<>("nine",  (list) -> list.get(9), testList)
        );
    }

    public class XorList<T> {

        private XorListElement<T> firstElement = null;
        private XorListElement<T> lastElement = null;
        private int size = 0;

        //memory
        private final HashMap<XorListElement<T>, Long> ELEMENT_TO_REF = new HashMap<>();
        private final HashMap<Long, XorListElement<T>> REF_TO_ELEMENT = new HashMap<>();

        public synchronized T addElement(final T newElement) {

            final XorListElement<T> wrapper = new XorListElement<>(newElement);

            if (size == 0) {

                firstElement = wrapper;

            }
            final long newElementAddress = generateRandomAddress(this.size);
            storeElement(newElementAddress, wrapper);
            link(wrapper, lastElement);
            lastElement = wrapper;

            this.size++;

            return wrapper.value;

        }

        public T get(final int atIndex) {

            return getElement(atIndex).value;

        }

        private XorListElement<T> getElement(final int atIndex) {

            if (atIndex >= this.size) {

                throw new IndexOutOfBoundsException(String.format("cant get element %s from list length %s", atIndex, this.size));

            }

            XorListElement<T> currentElement = firstElement;
            XorListElement<T> previousElement = firstElement;
            long nextElemPointer = 0 ^ firstElement.xorPreviousNextAddress; //since this is first element && (A xor 0 == A)

            for (int i = 1; i <= atIndex; i++) {

                previousElement = currentElement;
                currentElement = deReference(nextElemPointer);
                nextElemPointer = getPointer(previousElement) ^ currentElement.xorPreviousNextAddress;

            }

            return currentElement;

        }

        private void storeElement(final long address, final XorListElement<T> element) {

            ELEMENT_TO_REF.put(element, address);
            REF_TO_ELEMENT.put(address, element);

        }

        private synchronized void link(XorListElement<T> newElement, XorListElement<T> previous) {

            final long newElementAddress = getPointer(newElement);
            final long previousLastElemPtr = previous == null ? 0L : getPointer(previous);

            final long afterNewElementAddress = 0L;

            newElement.xorPreviousNextAddress = previousLastElemPtr ^ afterNewElementAddress;

            if (previous != null) {

                previous.xorPreviousNextAddress = previous.xorPreviousNextAddress ^ newElementAddress;

            }

        }

        private long getPointer(final XorListElement<T> element) {
            return ELEMENT_TO_REF.get(element);
        }

        private XorListElement<T> deReference(final Long pointer) {
            return REF_TO_ELEMENT.get(pointer);
        }

        private long generateRandomAddress(final int listSize) {

            final long randomPart = ((long) (Math.random() * (double)Integer.MAX_VALUE)) * 100000;
            return randomPart + listSize;

        }

        class XorListElement<T> {

            public T value;
            public long xorPreviousNextAddress;

            public XorListElement(final T value) {

                this.value = value;

            }
        }

    }

}
