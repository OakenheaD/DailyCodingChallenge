package com.oakenhead.dcc.challenge.month05.day17;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.PairValue;
import com.oakenhead.dcc.challenge.TripleValue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Service
public class DecodingStringsIntoSubstringsChallenge extends AbstractCodingChallenge<List<String>, PairValue<String, List<String>>> {

    @Override
    public String dateString() {

        return "2019 May 17";
    }

    @Override
    public String shortName() {

        return "Dictionary of words and a string into sentence";
    }

    @Override
    public String getChallengeTask() {

        return "This problem was asked by Microsoft.\n" +
                "\n" +
                "Given a dictionary of words and a string made up of those words (no spaces), return the original sentence " +
                "in a list. If there is more than one possible reconstruction, return any of them. If there is no possible reconstruction, then return null.\n" +
                "\n" +
                "For example, given the set of words 'quick', 'brown', 'the', 'fox', and the string \"thequickbrownfox\", " +
                "you should return ['the', 'quick', 'brown', 'fox'].\n" +
                "\n" +
                "Given the set of words 'bed', 'bath', 'bedbath', 'and', 'beyond', and the string \"bedbathandbeyond\", " +
                "return either ['bed', 'bath', 'and', 'beyond] or ['bedbath', 'and', 'beyond'].";
    }

    @Override
    public List<String> runChallengeCase(final PairValue<String, List<String>> input) {
        return null;
    }

    @Override
    public List<TripleValue<List<String>, Function<PairValue<String, List<String>>, List<String>>, PairValue<String, List<String>>>> getTestCases() {

        final Function<PairValue<String, List<String>>, List<String>> testFunction = this::runChallengeCase;

        final List<String> bedBathResult = Arrays.asList("bed", "bath", "and", "beyond");
        final List<String> bedBathDictionary = Arrays.asList("bed", "bath", "bedbath", "and", "beyond");
        final String bedBathString = "bedbathandbeyond";

        final List<String> quickFoxResult = Arrays.asList("the", "quick", "brown", "fox");
        final List<String> quickFoxDictionary = Arrays.asList("quick", "brown", "the", "fox");
        final String quickFoxString = "thequickbrownfox";

        return Arrays.asList(
                new TripleValue<>(bedBathResult, testFunction, new PairValue<>(bedBathString, bedBathDictionary)),
                new TripleValue<>(quickFoxResult, testFunction, new PairValue<>(quickFoxString, quickFoxDictionary))
        );
    }
}
