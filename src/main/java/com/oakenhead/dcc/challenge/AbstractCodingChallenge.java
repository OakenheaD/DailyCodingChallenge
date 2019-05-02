package com.oakenhead.dcc.challenge;

import java.util.List;
import java.util.function.Function;

public abstract class AbstractCodingChallenge<R, T> implements CodingChallenge<R, T> {



    @Override
    public boolean doRunTestsAndCheckIfPass() {

        final List<TripleValue<R, Function<T, R>, T>> testCases = getTestCases();

        return testCases.stream()
                .mapToInt(testCase -> testCase.middle.apply(testCase.right).equals(testCase.left) ? 1 : 0)
                .sum() == testCases.size();

    }

}
