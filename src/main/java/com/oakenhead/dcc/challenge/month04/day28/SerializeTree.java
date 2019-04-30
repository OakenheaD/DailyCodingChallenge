package com.oakenhead.dcc.challenge.month04.day28;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.PairValue;
import com.oakenhead.dcc.challenge.beans.BinaryNode;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SerializeTree extends AbstractCodingChallenge<Boolean, PairValue<BinaryNode, String>> {

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
    public Boolean runChallengeCase(final PairValue<BinaryNode, String> input) {

        return deserializeNode(serializeNode(input.left)).left.left.value.equalsIgnoreCase(input.right);

    }

    @Override
    public List<PairValue<Boolean, PairValue<BinaryNode, String>>> getTestCases() {

        return Arrays.asList(
                new PairValue<>(true,
                        new PairValue<>(new BinaryNode("root", new BinaryNode("left", new BinaryNode("left.left")), new BinaryNode("right")), "left.left"))
        );

    }

    //trivial case - use new Gson();
    //non trivial case:

    private String serializeNode(final BinaryNode node) {

        return serializeNodeRecursive(node);

    }

    private BinaryNode deserializeNode(final String nodeStr) {

        return deserializeNodeRecursive(nodeStr);

    }

    private String serializeNodeRecursive(final BinaryNode node) {

        final String leftValue = node.left == null ? "null" : serializeNodeRecursive(node.left);
        final String rightValue = node.right == null ? "null" : serializeNodeRecursive(node.right);
        final String mainValueSanitized = !node.value.contains("#") ?
                node.value :
                node.value.replaceAll("&", "&ampersand_san;").replaceAll("#", "&hash_sanitized;");

        return String.format("{%s#%s#%s}", mainValueSanitized, leftValue, rightValue);

    }

    private BinaryNode deserializeNodeRecursive(final String nodeStr) {

        if ("null".equalsIgnoreCase(nodeStr)) {
            return null;
        }

        final String withoutBraces = nodeStr.substring(1, nodeStr.length() - 1);
        final String[] parts = withoutBraces.split("#");

        final String mainValueUnSanitized = !parts[0].contains("&hash_sanitized;") ?
                parts[0] :
                parts[0].replaceAll("&hash_sanitized;", "#").replaceAll("&ampersand_san;","&");

        final BinaryNode leftValue = deserializeNode(parts[1]);
        final BinaryNode rightValue = deserializeNode(parts[2]);

        return new BinaryNode(mainValueUnSanitized, leftValue, rightValue);

    }
}
