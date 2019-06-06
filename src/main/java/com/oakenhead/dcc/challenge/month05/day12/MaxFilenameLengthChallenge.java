package com.oakenhead.dcc.challenge.month05.day12;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.TripleValue;
import com.oakenhead.dcc.challenge.beans.BiValuedTreeNode;
import com.oakenhead.dcc.challenge.beans.TreeNode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class MaxFilenameLengthChallenge extends AbstractCodingChallenge<Integer, String> {
    @Override
    public String dateString() {
        return "2019 May 12";
    }

    @Override
    public String shortName() {
        return "Max filename in an FS";
    }

    @Override
    public String getChallengeTask() {
        return "Good morning! Here's your coding interview problem for today.\n" +
                "\n" +
                "This problem was asked by Google.\n" +
                "\n" +
                "Suppose we represent our file system by a string in the following manner:\n" +
                "\n" +
                "The string \"dir\\n\\tsubdir1\\n\\tsubdir2\\n\\t\\tfile.ext\" represents:\n" +
                "\n" +
                "dir\n" +
                "    subdir1\n" +
                "    subdir2\n" +
                "        file.ext\n" +
                "\n" +
                "The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.\n" +
                "\n" +
                "The string \"dir\\n\\tsubdir1\\n\\t\\tfile1.ext\\n\\t\\tsubsubdir1\\n\\tsubdir2\\n\\t\\tsubsubdir2\\n\\t\\t\\tfile2.ext\" represents:\n" +
                "\n" +
                "dir\n" +
                "    subdir1\n" +
                "        file1.ext\n" +
                "        subsubdir1\n" +
                "    subdir2\n" +
                "        subsubdir2\n" +
                "            file2.ext\n" +
                "\n" +
                "The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.\n" +
                "\n" +
                "We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is \"dir/subdir2/subsubdir2/file2.ext\", and its length is 32 (not including the double quotes).\n" +
                "\n" +
                "Given a string representing the file system in the above format, return the length of the longest absolute path to a file in the abstracted file system. If there is no file in the system, return 0.\n" +
                "\n" +
                "Note:\n" +
                "\n" +
                "The name of a file contains at least a period and an extension.\n" +
                "\n" +
                "The name of a directory or sub-directory will not contain a period.";
    }

    @Override
    public Integer runChallengeCase(final String input) {

        final BiValuedTreeNode<String, String> rootNode = new BiValuedTreeNode<>("", input);

        populateNode(rootNode);

        return computeNode(rootNode);
    }

    private String join(final List<String> strings) {

        return strings.stream().collect(Collectors.joining("\n"));
    }

    private int getObjectLevel(final String object) {

        if (object.equals("")) {
            return 0;
        }

        final boolean isTopLevel = !object.startsWith("\t");

        if (isTopLevel) {
            return 1;
        }

        return (object.lastIndexOf("\t") + "\t".length()) / "\t".length() + 1;
    }

    private void populateNode(final BiValuedTreeNode<String, String> startNode) {

        final String[] levels = startNode.anotherValue.split("\n");
        final int nodeLevel = getObjectLevel(startNode.getNodeValue());

        String currentChildObject = "";
        List<String> currentChildContent = new ArrayList<>();

        for (int i = 0; i < levels.length; i++) {

            final String currentObject = levels[i];
            final int currentObjectLevel = getObjectLevel(currentObject);
            final boolean isAChild = (currentObjectLevel - nodeLevel) > 0;
            final boolean isDirectChild = isAChild && (currentObjectLevel - nodeLevel) == 1;

            if (isDirectChild) {
                if (!StringUtils.isEmpty(currentChildObject)) {
                    startNode.addChild(new BiValuedTreeNode<>(currentChildObject, join(currentChildContent)));
                }
                currentChildContent = new ArrayList<>();
                currentChildObject = currentObject;
            } else if (currentObjectLevel > nodeLevel) {
                currentChildContent.add(currentObject);
            }
        }

        if (!StringUtils.isEmpty(currentChildObject)) {
            startNode.addChild(new BiValuedTreeNode<>(currentChildObject, join(currentChildContent)));
        }

        startNode.getChildren().forEach(child -> populateNode((BiValuedTreeNode<String, String>) child));
    }

    private int computeNode(final TreeNode<String> startNode) {

        if (isNodeFile((BiValuedTreeNode<String, String>) startNode)) {

            return /*getObjectLevel(startNode.getNodeValue())  + */startNode.getNodeValue().length();
        }

        if (isNodeEmptyDir((BiValuedTreeNode<String, String>) startNode)) {

            return -100500;
        }

        return startNode.getChildren().stream().mapToInt(node -> computeNode((BiValuedTreeNode<String, String>) node)).max().orElse(-1);

    }

    private boolean isNodeFile(final BiValuedTreeNode<String, String> startNode) {
        return startNode.getChildren().size() == 0 && startNode.getNodeValue().contains(".");
    }

    private boolean isNodeEmptyDir(final BiValuedTreeNode<String, String> startNode) {
        return startNode.getChildren().size() == 0 && !startNode.getNodeValue().contains(".");
    }

    @Override
    public List<TripleValue<Integer, Function<String, Integer>, String>> getTestCases() {
        final Function<String, Integer> testFunction = this::runChallengeCase;

        return Arrays.asList(
                new TripleValue<>( 32, testFunction, "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext")
        );
    }
}
