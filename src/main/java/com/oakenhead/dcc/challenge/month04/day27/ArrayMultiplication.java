package com.oakenhead.dcc.challenge.month04.day27;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.CodingChallenge;
import com.oakenhead.dcc.challenge.TripleValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ArrayMultiplication extends AbstractCodingChallenge<List<Long>, List<Long>> {
    @Override
    public String dateString() {
        return "2019 April 27";
    }

    @Override
    public String shortName() {
        return "Array Multiplication";
    }

    @Override
    public String getChallengeTask() {
        return "" +
                "This problem was asked by Uber.\n" +
                "\n" +
                "Given an array of integers, return a new array such that each element at index i of the new array " +
                "is the product of all the numbers in the original array except the one at i.\n" +
                "\n" +
                "For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. " +
                "If our input was [3, 2, 1], the expected output would be [2, 3, 6].\n" +
                "\n" +
                "Follow-up: what if you can't use division?";
    }

    @Override
    public List<Long> runChallengeCase(final List<Long> input) {

        final long product = input.stream().mapToLong(i -> i).reduce(1, (a, b) -> a * b);
        return input.stream().map(num -> product / num).collect(Collectors.toList());

    }
    public List<Long> runChallengeCaseNonStream(final List<Long> input) {

        long product = 1;

        for (int i = 0; i < input.size(); i++) {

            product *= input.get(i);

        }

        final List<Long> result = new ArrayList<>(input.size());

        for (int i = 0; i < input.size(); i++) {

            result.add(i, product / input.get(i));

        }

        return result;
    }

    public List<Long> runChallengeCaseNonDivision(final List<Long> input) {

        final long[] forwardProducts = new long[input.size()];
        final long[] reverseProducts = new long[input.size()];

        final int maxUsableIndex = input.size() - 1;

        forwardProducts[0] = input.get(0);
        reverseProducts[maxUsableIndex] = input.get(maxUsableIndex);

        for (int i = 1; i < input.size(); i++) {

            forwardProducts[i] = input.get(i) * forwardProducts[i - 1];
            reverseProducts[maxUsableIndex - i] = input.get(maxUsableIndex - i) * reverseProducts[maxUsableIndex - i + 1];

        }

        final List<Long> result = new ArrayList<>(input.size());

        result.add(0, reverseProducts[1]);

        for (int i = 1; i < maxUsableIndex; i++) {

            result.add(i, forwardProducts[i - 1] * reverseProducts[i + 1]);

        }

        result.add(maxUsableIndex, forwardProducts[maxUsableIndex - 1]);

        return result;
    }

    @Override
    public List<TripleValue<List<Long>, Function<List<Long>, List<Long>>, List<Long>>> getTestCases() {

        //final Function<List<Long>, List<Long>> testFunction = this::runChallengeCase;
        //final Function<List<Long>, List<Long>> testFunction = this::runChallengeCaseNonStream;
        final Function<List<Long>, List<Long>> testFunction = this::runChallengeCaseNonDivision;

        return Arrays.asList(
                new TripleValue<>(Arrays.asList(120L, 60L, 40L, 30L, 24L), testFunction, Arrays.asList(1L, 2L, 3L, 4L, 5L)),
                new TripleValue<>(Arrays.asList(2L, 3L, 6L),               testFunction, Arrays.asList(3L, 2L, 1L))
        );
    }
}
