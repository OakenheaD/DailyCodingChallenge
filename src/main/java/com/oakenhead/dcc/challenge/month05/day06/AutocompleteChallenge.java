package com.oakenhead.dcc.challenge.month05.day06;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.PairValue;
import com.oakenhead.dcc.challenge.TripleValue;
import org.apache.commons.collections.MultiMap;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MultivaluedHashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Service
public class AutocompleteChallenge extends AbstractCodingChallenge<List<String>, PairValue<String, List<String>>> {

    @Override
    public String dateString() {
        return "2019 May 06";
    }

    @Override
    public String shortName() {
        return "Autocomplete System";
    }

    @Override
    public String getChallengeTask() {
        return "Good morning! Here's your coding interview problem for today.\n" +
                "This problem was asked by Twitter.\n" +
                "Implement an autocomplete system. That is, given a query string s and a set of all possible query strings, return all strings in the set that have s as a prefix.\n" +
                "For example, given the query string de and the set of strings [dog, deer, deal], return [deer, deal].\n" +
                "Hint: Try preprocessing the dictionary into a more efficient data structure to speed up queries.";
    }

    @Override
    public List<String> runChallengeCase(final PairValue<String, List<String>> input) {

        final MultivaluedHashMap<String, String> dictionary = new MultivaluedHashMap<>();

        input.right.stream().forEach(word -> getCachingKeysForWord(word).forEach(key -> dictionary.add(key, word)));

        return dictionary.get(input.left);
    }

    //this controls optimisation. For large dictionaries it makes sense to use previous word as key too, and start key generation from 3 letters, not from 1 like here.
    public List<String> getCachingKeysForWord(final String word) {

        final List<String> keys = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {

            keys.add(word.substring(0, i));

        }

        return keys;
    }

    @Override
    public List<TripleValue<List<String>, Function<PairValue<String, List<String>>, List<String>>, PairValue<String, List<String>>>> getTestCases() {

        final Function<PairValue<String, List<String>>, List<String>> testFunction = this::runChallengeCase;

        return Arrays.asList(
                new TripleValue<>(Arrays.asList("deer", "deal"), testFunction, new PairValue<>("de", Arrays.asList("dog", "deer", "deal")))
        );
    }
}
