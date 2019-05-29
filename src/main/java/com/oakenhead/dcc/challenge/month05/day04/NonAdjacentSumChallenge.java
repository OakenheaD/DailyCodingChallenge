package com.oakenhead.dcc.challenge.month05.day04;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.TripleValue;
import com.oakenhead.dcc.challenge.beans.MultivaluedTreeNode;
import com.oakenhead.dcc.challenge.beans.PureBinaryNode;
import com.oakenhead.dcc.challenge.beans.TreeNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Service
public class NonAdjacentSumChallenge extends AbstractCodingChallenge<Integer, List<Integer>> {
    @Override
    public String dateString() {
        return "2019 may 04";
    }

    @Override
    public String shortName() {
        return "largest sum of non adjacent integers";
    }

    @Override
    public String getChallengeTask() {
        return "" +
                "Good morning! Here's your coding interview problem for today.\n" +
                "\n" +
                "This problem was asked by Airbnb.\n" +
                "\n" +
                "Given a list of integers, write a function that returns the largest sum of non-adjacent numbers. Numbers can be 0 or negative.\n" +
                "\n" +
                "For example, [2, 4, 6, 2, 5] should return 13, since we pick 2, 6, and 5. [5, 1, 1, 5] should return 10, since we pick 5 and 5.\n" +
                "\n" +
                "Follow-up: Can you do this in O(N) time and constant space?";
    }

    @Override
    public Integer runChallengeCase(final List<Integer> input) {

        final MultivaluedTreeNode<Integer, List<Integer>> startNode = new MultivaluedTreeNode<>(0, input);

        populateNode(startNode);
        final int answer = largestSumOfSubtree(startNode);

        return answer;

    }

    private void populateNode(final MultivaluedTreeNode<Integer, List<Integer>> startNode) {

        final List<Integer> allowedForwardValues = startNode.anotherValue;

        final boolean hasNext = allowedForwardValues.size() >= 1;
        final boolean hasNextNext = allowedForwardValues.size() >= 2;

        if (hasNext) {
            final boolean hasNextAllowed = allowedForwardValues.size() >= 3;
            final List<Integer> nextForward = hasNextAllowed ? allowedForwardValues.subList(2, allowedForwardValues.size()) : new ArrayList<>();

            final MultivaluedTreeNode<Integer, List<Integer>> newChild = new MultivaluedTreeNode<>(allowedForwardValues.get(0), nextForward);
            startNode.addChild(newChild);
            populateNode(newChild);

        }

        if (hasNextNext) {
            final boolean hasNextAllowed = allowedForwardValues.size() >= 4;
            final List<Integer> nextForward = hasNextAllowed ? allowedForwardValues.subList(3, allowedForwardValues.size()) : new ArrayList<>();

            final MultivaluedTreeNode<Integer, List<Integer>> newChild = new MultivaluedTreeNode<>(allowedForwardValues.get(1), nextForward);
            startNode.addChild(newChild);
            populateNode(newChild);

        }
    }

    private int largestSumOfSubtree(final MultivaluedTreeNode<Integer, List<Integer>> startNode) {

        if (startNode.getChildren().size() > 0) {

            return startNode.getNodeValue() + startNode.getChildren().stream()
                    .mapToInt(child -> largestSumOfSubtree((MultivaluedTreeNode<Integer, List<Integer>>) child))
                    .reduce((a, b) -> a > b ? a : b).getAsInt();

        }

        return startNode.getNodeValue();


    }

    @Override
    public List<TripleValue<Integer, Function<List<Integer>, Integer>, List<Integer>>> getTestCases() {

        final Function<List<Integer>, Integer> testFunction = this::runChallengeCase;

        return Arrays.asList(
                new TripleValue<>(13, testFunction, Arrays.asList(2, 4, 6, 2, 5)),
                new TripleValue<>(10, testFunction, Arrays.asList(5, 1, 1, 5))
        );
    }
}
