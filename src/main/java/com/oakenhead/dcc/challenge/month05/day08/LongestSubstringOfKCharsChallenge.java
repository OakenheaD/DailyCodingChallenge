package com.oakenhead.dcc.challenge.month05.day08;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.PairValue;
import com.oakenhead.dcc.challenge.TripleValue;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Component
public class LongestSubstringOfKCharsChallenge extends AbstractCodingChallenge<String, PairValue<String, Integer>> {
    @Override
    public String dateString() {
        return "2019 May 08";
    }

    @Override
    public String shortName() {
        return "longest substring that contains at most k distinct characters";
    }

    @Override
    public String getChallengeTask() {
        return "" +
                "Good morning! Here's your coding interview problem for today.\n" +
                "\n" +
                "This problem was asked by Amazon.\n" +
                "\n" +
                "Given an integer k and a string s, find the length of the longest substring that contains at most k distinct characters.\n" +
                "\n" +
                "For example, given s = \"abcba\" and k = 2, the longest substring with k distinct characters is \"bcb\".\n";
    }

    @Override
    public String runChallengeCase(final PairValue<String, Integer> input) {
        return null;
    }

    @Override
    public List<TripleValue<String, Function<PairValue<String, Integer>, String>, PairValue<String, Integer>>> getTestCases() {


        final Function<PairValue<String, Integer>, String> testFunction = this::runChallengeCase;

        return Arrays.asList(
                new TripleValue<>("bcb", testFunction, new PairValue<>("abcba", 2))
        );
    }
}
