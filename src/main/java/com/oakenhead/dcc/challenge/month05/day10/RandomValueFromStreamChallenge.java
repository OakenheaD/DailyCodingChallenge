package com.oakenhead.dcc.challenge.month05.day10;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.TripleValue;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

@Service
public class RandomValueFromStreamChallenge extends AbstractCodingChallenge<Boolean, Double> {

    @Override
    public String dateString() {
        return "2019 May 10";
    }

    @Override
    public String shortName() {
        return "random element from the stream";
    }

    @Override
    public String getChallengeTask() {
        return "" +
                "Good morning! Here's your coding interview problem for today.\n" +
                "\n" +
                "This problem was asked by Facebook.\n" +
                "\n" +
                "Given a stream of elements too large to store in memory, pick a random element from the stream with uniform probability.";
    }

    @Override
    public Boolean runChallengeCase(final Double input) {
        //simulate an infinite stream by random termination;
        final Random random = new Random();
        final int terminationTries = 100_000_000;
        final double terminationProbability = 1d/1_000_000;

        double currentValue = random.nextDouble();
        double lastPickedValue = currentValue;

        int valuesGenerated = 1;
        int valuesPicked = 1;

        do {

            currentValue = random.nextDouble();
            valuesGenerated++;

            if (isAccept(random, input)) {
                lastPickedValue = currentValue;
                valuesPicked++;
            }
        } while (!isAccept(random, terminationProbability) && valuesGenerated < terminationTries);

        final double q = ((double) valuesPicked) / valuesGenerated;

        return q < 0.01;
    }

    private Boolean isAccept(final Random random, final double probability) {
        return random.nextDouble() < probability;
    }

    @Override
    public List<TripleValue<Boolean, Function<Double, Boolean>, Double>> getTestCases() {
        final Function<Double, Boolean> testFunction = this::runChallengeCase;

        return Arrays.asList(
                new TripleValue<>( true, testFunction, 1d/100d)
        );
    }
}
