package com.oakenhead.dcc.challenge.month05.day02;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.TripleValue;
import com.oakenhead.dcc.challenge.beans.TreeNode;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Service
public class MessageDecoding extends AbstractCodingChallenge<Integer, String> {

    @Override
    public String dateString() {
        return "2019 May 02";
    }

    @Override
    public String shortName() {
        return "Find number of ways to decode";
    }

    @Override
    public String getChallengeTask() {
        return "" +
                "Good morning! Here's your coding interview problem for today.\n" +
                "\n" +
                "This problem was asked by Facebook.\n" +
                "\n" +
                "Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.\n" +
                "\n" +
                "For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.\n" +
                "\n" +
                "You can assume that the messages are decodable. For example, '001' is not allowed.";
    }

    @Override
    public Integer runChallengeCase(final String input) {

        final LetterNode rootNode = new LetterNode(null, input);
        rootNode.spawnLeaves();
        return rootNode.countValidLeavesBelow();
    }

    @Override
    public List<TripleValue<Integer, Function<String, Integer>, String>> getTestCases() {
        final Function<String, Integer> testFunction = this::runChallengeCase;

        return Arrays.asList(
                new TripleValue<>(3, testFunction, "111")
        );
    }
}
