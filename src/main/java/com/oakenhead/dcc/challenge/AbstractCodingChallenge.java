package com.oakenhead.dcc.challenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public abstract class AbstractCodingChallenge<R, T> implements CodingChallenge<R, T> {

    protected static final Logger ABSTRACT_LOGGER = LoggerFactory.getLogger(AbstractCodingChallenge.class);



    @Override
    public QuadValue<Long, Long, Integer, Boolean> doRunTestsAndCheckIfPass() {

        final List<TripleValue<R, Function<T, R>, T>> testCases = getTestCases();

        final long challengeBegin = System.nanoTime();

        final Boolean result =  testCases.stream()
                .mapToInt(testCase -> compateSupposedAndActualCase(testCase.middle.apply(testCase.right),testCase.left))
                .sum() == testCases.size();

        final long challengeEnd = System.nanoTime();

        return new QuadValue<>(challengeBegin, challengeEnd, testCases.size(), result);

    }

    private int compateSupposedAndActualCase(final R testCase, final R supposedCase) {

        final boolean isArray = supposedCase.getClass().isArray();

        if (isArray) {
            return Arrays.deepEquals((Object[])testCase, (Object[]) supposedCase) ? 1 : 0;
        }

        return Objects.equals(testCase, supposedCase)? 1 : 0;

    }

}
