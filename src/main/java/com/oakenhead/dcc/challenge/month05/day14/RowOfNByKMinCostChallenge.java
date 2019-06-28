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
        final int houses = input.length;
        final int colours = input[0].length;

        final Integer[][] weightsToPoint = new Integer[houses][colours];
        PairValue<Integer, Integer> minCostInPrevRow = new PairValue<>(0, -1);
        PairValue<Integer, Integer> minCostInPrevRowSecond = new PairValue<>(0, -1);

        for (int iHouses = 0; iHouses < houses; iHouses++) {

            minCostInPrevRow = iHouses == 0 ?
                    new PairValue<>(0, -1) :
                    findMinimumValue(weightsToPoint[iHouses - 1], minCostInPrevRow.right);

            minCostInPrevRowSecond = iHouses == 0 ?
                    new PairValue<>(0, -1) :
                    findMinimumValue(weightsToPoint[iHouses - 1], minCostInPrevRow.right);

            for (int iColours = 0; iColours < colours; iColours++) {

                final int costOfThisHouseAndColor = input[iHouses][iColours];


                weightsToPoint[iHouses][iColours] =
            }
        }

        PairValue<Integer, Integer> minWeightFinal = findMinimumValue(weightsToPoint[houses - 1], -1);

        return minWeightFinal.left;
    }

    private PairValue<Integer, Integer> findMinimumValue(final Integer[] column, int indexToSkip) {

        int currentMinimum = Integer.MIN_VALUE;
        int currentMinIndex = -1;

        for (int i = 0; i < column.length; i++) {

            if (i == indexToSkip) {
                continue;
            }

            if (currentMinimum > column[i]) {
                currentMinimum = column[i];
                currentMinIndex = i;
            }
        }

        return new PairValue<>(currentMinimum, currentMinIndex);

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
