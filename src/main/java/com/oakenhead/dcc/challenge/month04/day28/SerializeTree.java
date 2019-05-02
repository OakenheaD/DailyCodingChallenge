package com.oakenhead.dcc.challenge.month04.day28;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.PairValue;
import com.oakenhead.dcc.challenge.TripleValue;
import com.oakenhead.dcc.challenge.beans.BinaryNode;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

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
    public List<TripleValue<Boolean, Function<PairValue<BinaryNode, String>, Boolean>, PairValue<BinaryNode, String>>> getTestCases() {

        final BinaryNode testNode = new BinaryNode("root", new BinaryNode("left", new BinaryNode("left.left")), new BinaryNode("right"));
        final Function<PairValue<BinaryNode, String>, Boolean> testFunction = this::runChallengeCase;

        return Arrays.asList(
                new TripleValue<>(true, testFunction, new PairValue<>(testNode, "left.left"))
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

    private static final String INNER_SEPARATOR = "#";
    private static final String INNER_SEPARATOR_SAN = "&hash_sanitized;";
    private static final String BRACE_UP = "{";
    private static final String BRACE_UP_SAN = "&brace_up;";
    private static final String BRACE_DOWN = "}";
    private static final String BRACE_DOWN_SAN = "&brace_down;";
    private static final String AMPERSAND = "&";
    private static final String AMPERSAND_SAN = "&ampersand_san;";

    private String serializeNodeRecursive(final BinaryNode node) {

        final String leftValue = node.left == null ? "null" : serializeNodeRecursive(node.left);
        final String rightValue = node.right == null ? "null" : serializeNodeRecursive(node.right);

        final String mainValueSanitized = node.value
                .replaceAll("\\" + AMPERSAND, AMPERSAND_SAN)
                .replaceAll("\\" + BRACE_UP, BRACE_UP_SAN)
                .replaceAll("\\" + BRACE_DOWN, BRACE_DOWN_SAN)
                .replaceAll("\\" + INNER_SEPARATOR, INNER_SEPARATOR_SAN);

        return BRACE_UP + mainValueSanitized + INNER_SEPARATOR + leftValue + INNER_SEPARATOR + rightValue + BRACE_DOWN;

    }

    private BinaryNode deserializeNodeRecursive(final String nodeStr) {

        if ("null".equalsIgnoreCase(nodeStr)) {
            return null;
        }

        final String withoutBraces = nodeStr.substring(1, nodeStr.length() - 1);
        final String[] parts = splitToParts(withoutBraces);

        final String mainValueUnSanitized =  parts[0]
                .replaceAll("\\" + BRACE_UP_SAN, BRACE_UP)
                .replaceAll("\\" + BRACE_DOWN_SAN, BRACE_DOWN)
                .replaceAll("\\" + INNER_SEPARATOR_SAN, INNER_SEPARATOR)
                .replaceAll("\\" + AMPERSAND_SAN,AMPERSAND);

        final BinaryNode leftValue = deserializeNode(parts[1]);
        final BinaryNode rightValue = deserializeNode(parts[2]);

        return new BinaryNode(mainValueUnSanitized, leftValue, rightValue);

    }

    private String[] splitToParts(final String input) {
        //assume input in the format "a#b#c" or "a#{b, c}#d"

        final String[] retParts = new String[3];

        int level = 0;
        int resultsIndex = 0;
        int stringBeginIndex = 0;

        for (int i = 0; i < input.length(); i++) {

            final char currentChar = input.charAt(i);

            if (currentChar == BRACE_UP.charAt(0)){

                level++;

            }

            if (currentChar == BRACE_DOWN.charAt(0)){

                level--;

            }

            if (currentChar == INNER_SEPARATOR.charAt(0) && level == 0) {

                retParts[resultsIndex++] = input.substring(stringBeginIndex, i);
                stringBeginIndex = i + 1;

            }

            if (i == input.length() - 1) {

                retParts[resultsIndex++] = input.substring(stringBeginIndex, i + 1);

            }

        }

        return retParts;

    }
}
