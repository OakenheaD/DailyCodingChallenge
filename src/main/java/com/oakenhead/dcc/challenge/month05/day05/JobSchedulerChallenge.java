package com.oakenhead.dcc.challenge.month05.day05;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.PairValue;
import com.oakenhead.dcc.challenge.TripleValue;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class JobSchedulerChallenge extends AbstractCodingChallenge<Boolean, PairValue<Function, Long>> {

    @Override
    public String dateString() {
        return "2019 May 05";
    }

    @Override
    public String shortName() {
        return "Simple Job Scheduler";
    }

    @Override
    public String getChallengeTask() {
        return "" +
                "Good morning! Here's your coding interview problem for today.\n" +
                "\n" +
                "This problem was asked by Apple.\n" +
                "\n" +
                "Implement a job scheduler which takes in a function f and an integer n, and calls f after n milliseconds.";
    }

    @Override
    public Boolean runChallengeCase(final PairValue<Function, Long> input) {

        final Object testObject = new Object();

        final Long duration;

        final Supplier<Long> scheduled = () -> {

            final Long begin = System.nanoTime();

            try {

                Thread.sleep(input.right);
                input.left.apply(testObject);
                final Long end = System.nanoTime();
                final Long itTookNs = (end - begin);

                return itTookNs / 1000000;

            } catch (final Exception e) {
                //ignore
            }

            return 0L;

        };

        duration = scheduled.get();

        ABSTRACT_LOGGER.info(" test run took ms " + duration);
        return ((input.right - duration) / input.right) < 0.1;
    }

    @Override
    public List<TripleValue<Boolean, Function<PairValue<Function, Long>, Boolean>, PairValue<Function, Long>>> getTestCases() {


        final Function printTestValue = (obj) ->  {
            ABSTRACT_LOGGER.info("test case run");
            return obj;
        };

        final Function<PairValue<Function, Long>, Boolean> testFunction = this::runChallengeCase;

        return Arrays.asList(
                new TripleValue<>(true, testFunction, new PairValue<>(printTestValue, 1000L)),
                new TripleValue<>(true, testFunction, new PairValue<>(printTestValue, 2500L))
        );

    }
}
