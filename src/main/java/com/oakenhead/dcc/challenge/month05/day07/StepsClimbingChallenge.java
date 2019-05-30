package com.oakenhead.dcc.challenge.month05.day07;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.PairValue;
import com.oakenhead.dcc.challenge.TripleValue;
import com.oakenhead.dcc.challenge.beans.BiValuedTreeNode;
import com.oakenhead.dcc.challenge.beans.TreeNode;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Service
public class StepsClimbingChallenge extends AbstractCodingChallenge<Integer, Integer> {

    @Override
    public String dateString() {
        return "2019 May 07";
    }

    @Override
    public String shortName() {
        return "Count ways you can climb X steps";
    }

    @Override
    public String getChallengeTask() {
        return "" +
                "This problem was asked by Amazon.\n" +
                "\n" +
                "There exists a staircase with N steps, and you can climb up either 1 or 2 steps at a time. " +
                "Given N, write a function that returns the number of unique ways you can climb the staircase. The order of the steps matters.\n" +
                "\n" +
                "For example, if N is 4, then there are 5 unique ways:\n" +
                "\n" +
                "    1, 1, 1, 1\n" +
                "    2, 1, 1\n" +
                "    1, 2, 1\n" +
                "    1, 1, 2\n" +
                "    2, 2\n" +
                "\n" +
                "What if, instead of being able to climb 1 or 2 steps at a time, you could climb any number from a set of " +
                "positive integers X? For example, if X = {1, 3, 5}, you could climb 1, 3, or 5 steps at a time.";
    }

    @Override
    public Integer runChallengeCase(final Integer input) {
        return null;
    }

    @Override
    public List<TripleValue<Integer, Function<Integer, Integer>, Integer>> getTestCases() {

        final List<Integer> possible1or2Steps = Arrays.asList(1, 2);

        final Function<Integer, Integer> testFunction = (steps) -> computeStairCase(possible1or2Steps, steps);

        return Arrays.asList(
                new TripleValue<>(5, testFunction, 4)
        );
    }

    private int computeStairCase(final List<Integer> possibleSteps , final int totalSteps) {

        final BiValuedTreeNode<Integer, Integer> rootNode = new BiValuedTreeNode<>(0, totalSteps);
        populateTree(possibleSteps, rootNode);
        return countNode(rootNode);
    }

    private void populateTree(final List<Integer> possibleSteps, final BiValuedTreeNode<Integer, Integer> startNode) {

        possibleSteps.stream()
                .map(step -> new PairValue<Integer, Integer>(step, startNode.anotherValue - step))
                .filter(pair -> pair.right >= 0)
                .forEach(pair -> startNode.addChild(new BiValuedTreeNode<Integer, Integer>(pair.left, pair.right)))
        ;

        startNode.getChildren().forEach(node -> populateTree(possibleSteps, (BiValuedTreeNode<Integer, Integer>) node));

    }

    private int countNode(final BiValuedTreeNode<Integer, Integer> startNode) {
        if (startNode.getChildren().size() == 0) {

            final int count = startNode.anotherValue == 0 ? 1 : 0;
            //ABSTRACT_LOGGER.info(" node chain " + printNode(startNode) + " count " + count);
            return count;

        }

        return startNode.getChildren().stream().mapToInt(child -> countNode((BiValuedTreeNode<Integer, Integer>) child)).sum();
    }

    private String printNode(final BiValuedTreeNode<Integer, Integer> endNode) {

        if (endNode.getPrevious() == null) {
            return " " + endNode.getNodeValue();
        }

        return printNode((BiValuedTreeNode<Integer, Integer>) endNode.getPrevious()) + ", " + endNode.getNodeValue();
    }


}
