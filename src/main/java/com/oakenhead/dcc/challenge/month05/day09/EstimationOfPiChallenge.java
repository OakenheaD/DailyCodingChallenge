package com.oakenhead.dcc.challenge.month05.day09;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.PairValue;
import com.oakenhead.dcc.challenge.TripleValue;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EstimationOfPiChallenge extends AbstractCodingChallenge<Boolean, Integer> {

    @Override
    public String dateString() {
        return "2019 May 09";
    }

    @Override
    public String shortName() {
        return "Estimate PI using Monte Carlo";
    }

    @Override
    public String getChallengeTask() {
        return "" +
                "Good morning! Here's your coding interview problem for today.\n"+
                "\n"+
                "This problem was asked by Google.\n"+
                "\n"+
                "The area of a circle is defined as πr^2. Estimate π to 3 decimal places using a Monte Carlo method.\n"+
                "\n"+
                "Hint: The basic equation of a circle is x2 + y2 = r2.";
    }

    @Override
    public Boolean runChallengeCase(final Integer input) {

        final Random random = new Random();

        final List<PairValue<Double, Double>> unitPoints =
                IntStream.range(0, input).boxed()
                .map(i -> new PairValue<Double, Double>(random.nextDouble(), random.nextDouble()))
                .collect(Collectors.toList());

        final List<PairValue<Double, Double>> fallingInsideTheCircle = unitPoints.stream()
                .filter(point -> isPointInUnitCircle(point.left, point.right))
                .collect(Collectors.toList());

        final double piEstimation = ((double) fallingInsideTheCircle.size() / (double) unitPoints.size());

        return Math.abs(piEstimation - Math.PI) < 0.001;
    }

    private boolean isPointInUnitCircle(final double x, final double y) {
        return (x*x + y*y) < 1;
    }

    @Override
    public List<TripleValue<Boolean, Function<Integer, Boolean>, Integer>> getTestCases() {
        final Function<Integer, Boolean> testFunction = this::runChallengeCase;

        return Arrays.asList(
                new TripleValue<>( true, testFunction, 1000)
        );
    }
}
