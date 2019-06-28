package com.oakenhead.dcc.challenge.month05.day15;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.PairValue;
import com.oakenhead.dcc.challenge.TripleValue;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Service
public class IntersectingListsChallenge extends AbstractCodingChallenge<Integer, PairValue<LinkedList<Integer>, LinkedList<Integer>>> {


    @Override
    public String dateString() {
        return "2019 May 15";
    }

    @Override
    public String shortName() {
        return "Intersecting linked lists";
    }

    @Override
    public String getChallengeTask() {
        return "This problem was asked by Google.\n" +
                "\n" +
                "Given two singly linked lists that intersect at some point, find the intersecting node. The lists are non-cyclical.\n" +
                "\n" +
                "For example, given A = 3 -> 7 -> 8 -> 10 and B = 99 -> 1 -> 8 -> 10, return the node with value 8.\n" +
                "\n" +
                "In this example, assume nodes with the same value are the exact same node objects.\n" +
                "\n" +
                "Do this in O(M + N) time (where M and N are the lengths of the lists) and constant space.";
    }

    @Override
    public Integer runChallengeCase(final PairValue<LinkedList<Integer>, LinkedList<Integer>> input) {

        final HashMap<Integer, Integer> listOneMap = input.left.stream().collect(HashMap::new, (map, entry) -> map.put(entry, entry), HashMap::putAll);

        Iterator<Integer> listTwoInteger = input.right.iterator();

        while(listTwoInteger.hasNext()) {

            final Integer next = listTwoInteger.next();
            if (listOneMap.containsKey(next)) {
                return next;
            }

        }

        return Integer.MIN_VALUE;
    }

    @Override
    public List<TripleValue<Integer, Function<PairValue<LinkedList<Integer>, LinkedList<Integer>>, Integer>, PairValue<LinkedList<Integer>, LinkedList<Integer>>>> getTestCases() {

        final Function<PairValue<LinkedList<Integer>, LinkedList<Integer>>, Integer> testFunction = this::runChallengeCase;

        final LinkedList<Integer> listOne = new LinkedList<>(Arrays.asList(3, 7, 8, 10));
        final LinkedList<Integer> listTwo = new LinkedList<>(Arrays.asList(99, 1, 8, 10));

        return Arrays.asList(
                new TripleValue<>(8, testFunction, new PairValue<>(listOne, listTwo))
        );
    }
}
