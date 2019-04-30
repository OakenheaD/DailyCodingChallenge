package com.oakenhead.dcc.challenge;

import org.springframework.data.util.Pair;

import java.util.List;

public interface CodingChallenge<R, T> {

    String dateString();

    String shortName();

    String getChallengeTask();

    R runChallengeCase(final T input);

    List<PairValue<R, T>> getTestCases();

    boolean doRunTestsAndCheckIfPass();

}
