package com.oakenhead.dcc.challenge.month05.day08;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.PairValue;
import com.oakenhead.dcc.challenge.TripleValue;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        return
                allTrailingSubstrings(input.left).stream()
                .map(substring -> allRearSubstrings(substring))
                .collect(ArrayList<String>::new, List::addAll, List::addAll).stream()
                .filter(substring -> distinctCharsInString(substring) <= input.right)
                .max(Comparator.comparing(String::length))
                .orElse(input.left);

    }

    private List<String> allTrailingSubstrings(final String input) {

        return IntStream.range(1, input.length() + 1).boxed()
                .map(i -> input.substring(0, i))
                .collect(Collectors.toList());

    }

    private List<String> allRearSubstrings(final String input) {

        return IntStream.range(0, input.length()).boxed()
                .map(i -> input.substring(i, input.length()))
                .collect(Collectors.toList());

    }

    private int distinctCharsInString(final String input) {

        return input.chars().boxed().collect(HashSet::new, HashSet::add, HashSet::addAll).size();

    }

    @Override
    public List<TripleValue<String, Function<PairValue<String, Integer>, String>, PairValue<String, Integer>>> getTestCases() {

        final Function<PairValue<String, Integer>, String> testFunction = this::runChallengeCase;

        return Arrays.asList(
                new TripleValue<>("bcb", testFunction, new PairValue<>("abcba", 2)),
                new TripleValue<>("czzzzzzzzzzzzzc", testFunction, new PairValue<>("abcabcabczzzzzzzzzzzzzcabcabcabcabc", 2))
        );
    }
}
