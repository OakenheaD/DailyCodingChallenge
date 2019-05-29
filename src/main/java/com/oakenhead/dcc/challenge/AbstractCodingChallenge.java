package com.oakenhead.dcc.challenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;

public abstract class AbstractCodingChallenge<R, T> implements CodingChallenge<R, T> {

    protected static final Logger ABSTRACT_LOGGER = LoggerFactory.getLogger(AbstractCodingChallenge.class);



    @Override
    public QuadValue<Long, Long, Integer, Boolean> doRunTestsAndCheckIfPass() {

        final List<TripleValue<R, Function<T, R>, T>> testCases = getTestCases();

        final long challengeBegin = System.nanoTime();

        final Boolean result =  testCases.stream()
                .mapToInt(testCase -> testCase.middle.apply(testCase.right).equals(testCase.left) ? 1 : 0)
                .sum() == testCases.size();

        final long challengeEnd = System.nanoTime();

        return new QuadValue<>(challengeBegin, challengeEnd, testCases.size(), result);

    }

}
