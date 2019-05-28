package com.oakenhead.dcc.challenge.month05.day03;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.TripleValue;
import com.oakenhead.dcc.challenge.beans.PureBinaryNode;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UnivTreeSubtreeCounting extends AbstractCodingChallenge<Integer, PureBinaryNode<Integer>> {

    @Override
    public String dateString() {
        return "2019 May 03";
    }

    @Override
    public String shortName() {
        return "Counting universal subtrees in binary tree";
    }

    @Override
    public String getChallengeTask() {
        return "" +
                "Good morning! Here's your coding interview problem for today.\n" +
                "This problem was asked by Google.\n" +
                "A unival tree (which stands for \"universal value\") is a tree where all nodes under it have the same value.\n" +
                "Given the root to a binary tree, count the number of unival subtrees.\n" +
                "For example, the following tree has 5 unival subtrees:\n" +
                "   0\n" +
                "  / \\\n" +
                " 1   0\n" +
                "    / \\\n" +
                "   1   0\n" +
                "  / \\\n" +
                " 1   1\n";
    }

    @Override
    public Integer runChallengeCase(final PureBinaryNode<Integer> input) {
        return countNodeUnivSubtrees(input);
    }

    @Override
    public List<TripleValue<Integer, Function<PureBinaryNode<Integer>, Integer>, PureBinaryNode<Integer>>> getTestCases() {

        final Function<PureBinaryNode<Integer>, Integer> testFunction = this::runChallengeCase;

        final PureBinaryNode sampleTree =
                new PureBinaryNode(0,
                    new PureBinaryNode(1, null, null),
                    new PureBinaryNode(0,
                            new PureBinaryNode(1,
                                    new PureBinaryNode(1, null, null),
                                    new PureBinaryNode(1, null, null)),
                            new PureBinaryNode(0, null, null)));

        return Arrays.asList(
                new TripleValue<>(5, testFunction, sampleTree)
        );
    }


    private int countNodeUnivSubtrees(final PureBinaryNode<Integer> node) {
        if (nodeHasAUnivSubtree(node)) {
            return 1;
        }

        return countNodeUnivSubtrees(node.rightChild) + countNodeUnivSubtrees(node.leftChild);
    }

    private boolean nodeHasAUnivSubtree(final PureBinaryNode<Integer> node) {

        final boolean noChildren = node.leftChild == null && node.rightChild == null;

        if (noChildren) {
            return true;
        }

        return allChildrenHaveSameValue(node, node.value);
    }

    private boolean allChildrenHaveSameValue(final PureBinaryNode<Integer> node, final Integer value) {

        return Objects.equals(node.value, value)
                && (node.leftChild == null || allChildrenHaveSameValue(node.leftChild, value))
                && (node.rightChild == null || allChildrenHaveSameValue(node.rightChild, value));

    }
}
