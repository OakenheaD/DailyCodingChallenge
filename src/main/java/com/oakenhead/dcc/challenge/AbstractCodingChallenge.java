package com.oakenhead.dcc.challenge;

import java.util.List;

public abstract class AbstractCodingChallenge<R, T> implements CodingChallenge<R, T> {



    @Override
    public boolean doRunTestsAndCheckIfPass() {

        final List<PairValue<R, T>> testCases = getTestCases();

        return testCases.stream()
                .mapToInt(testCase -> runChallengeCase(testCase.right).equals(testCase.left) ? 1 : 0)
                .sum() == testCases.size();

    }

}
