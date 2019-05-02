package com.oakenhead.dcc.challenge;

import org.springframework.data.util.Pair;

import java.util.List;
import java.util.function.Function;

public interface CodingChallenge<R, T> {

    String dateString();

    String shortName();

    String getChallengeTask();

    R runChallengeCase(final T input);

    List<TripleValue<R, Function<T, R> ,T>> getTestCases();

    boolean doRunTestsAndCheckIfPass();

}
