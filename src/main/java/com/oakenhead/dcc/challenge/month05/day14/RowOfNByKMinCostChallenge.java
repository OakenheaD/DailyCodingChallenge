package com.oakenhead.dcc.challenge.month05.day14;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.PairValue;
import com.oakenhead.dcc.challenge.TripleValue;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class RowOfNByKMinCostChallenge extends AbstractCodingChallenge<Integer, Integer[][]> {


    @Override
    public String dateString() {

        return "2019 May 14";
    }

    @Override
    public String shortName() {

        return "row of N houses that can be of K different colors";
    }

    @Override
    public String getChallengeTask() {

        return "This problem was asked by Facebook.\n" +
                "\n" +
                "A builder is looking to build a row of N houses that can be of K different colors. He has a goal of " +
                "minimizing cost while ensuring that no two neighboring houses are of the same color.\n" +
                "Given an N by K matrix where the nth row and kth column represents the cost to build the nth house with " +
                "kth color, return the minimum cost which achieves this goal.";
    }

    @Override
    public Integer runChallengeCase(final Integer[][] input) {
        return null;
    }

    @Override
    public List<TripleValue<Integer, Function<Integer[][], Integer>, Integer[][]>> getTestCases() {


        final Function<Integer[][], Integer> testFunction = this::runChallengeCase;

        final Integer[][] testInput0 = new Integer[][] { //N = 4, K = 3
                {0, 1, 2},
                {1, 0, 2},
                {0, 1, 3},
                {1, 0, 2}
        };

        final Integer testResult0 = 0;

        final Integer[][] testInput1 = new Integer[][] { //N = 5, K = 4
                {0, 1, 2, 3},
                {1, 0, 2, 3},
                {0, 1, 3, 4},
                {1, 1, 3, 5},
                {0, 1, 3, 4}
        };

        final Integer testResult1 = 1;

        return Arrays.asList(
                new TripleValue<>(testResult0, testFunction, testInput0),
                new TripleValue<>(testResult1, testFunction, testInput1)
        );
    }
}
