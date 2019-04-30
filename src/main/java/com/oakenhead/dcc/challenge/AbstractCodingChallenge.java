package com.oakenhead.dcc.challenge;

import com.oakenhead.dcc.challenge.month04.day26.IntArrayAndInt;

import javax.annotation.PostConstruct;
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
