package com.oakenhead.dcc.challenge;

import java.util.List;
import java.util.function.Function;

public abstract class AbstractCodingChallenge<R, T> implements CodingChallenge<R, T> {



    @Override
    public TripleValue<Long, Long, Boolean> doRunTestsAndCheckIfPass() {

        final List<TripleValue<R, Function<T, R>, T>> testCases = getTestCases();

        final long challengeBegin = System.nanoTime();

        final Boolean result =  testCases.stream()
                .mapToInt(testCase -> testCase.middle.apply(testCase.right).equals(testCase.left) ? 1 : 0)
                .sum() == testCases.size();

        final long challengeEnd = System.nanoTime();

        return new TripleValue<>(challengeBegin, challengeEnd, result);

    }

}
