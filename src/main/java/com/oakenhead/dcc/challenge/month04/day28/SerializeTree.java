package com.oakenhead.dcc.challenge.month04.day28;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.PairValue;
import com.oakenhead.dcc.challenge.beans.BinaryNode;

import java.util.List;

public class SerializeTree extends AbstractCodingChallenge<Boolean, BinaryNode> {

    @Override
    public String dateString() {
        return "2019 April 28";
    }

    @Override
    public String shortName() {
        return "serialise binary tree";
    }

    @Override
    public String getChallengeTask() {
        return "" +
                "Given the root to a binary tree, implement serialize(root), which serializes the tree into a string, and deserialize(s), " +
                "which deserializes the string back into the tree.\n" +
                "\n" +
                "For example, given the following Node class\n" +
                "\n" +
                "class Node:\n" +
                "    def __init__(self, val, left=None, right=None):\n" +
                "        self.val = val\n" +
                "        self.left = left\n" +
                "        self.right = right\n" +
                "\n" +
                "The following test should pass:\n" +
                "\n" +
                "node = Node('root', Node('left', Node('left.left')), Node('right'))\n" +
                "assert deserialize(serialize(node)).left.left.val == 'left.left'\n";
    }

    @Override
    public Boolean runChallengeCase(final BinaryNode input) {
        return null;
    }

    @Override
    public List<PairValue<Boolean, BinaryNode>> getTestCases() {
        return null;
    }
}
