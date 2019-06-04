package com.oakenhead.dcc.challenge.month05.day11;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.PairValue;
import com.oakenhead.dcc.challenge.TripleValue;
import com.oakenhead.dcc.challenge.beans.RollingQueue;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Service
public class RollingQueueChallenge extends AbstractCodingChallenge<Integer, PairValue<List<Integer>, Integer>> {

    @Override
    public String dateString() {
        return "2019 May 11";
    }

    @Override
    public String shortName() {
        return "Rolling Queue of numbers";
    }

    @Override
    public String getChallengeTask() {
        return "" +
                "Good morning! Here's your coding interview problem for today.\n" +
                "\n" +
                "This problem was asked by Twitter.\n" +
                "\n" +
                "You run an e-commerce website and want to record the last N order ids in a log. Implement a data structure to accomplish this, with the following API:\n" +
                "\n" +
                "    record(order_id): adds the order_id to the log\n" +
                "    get_last(i): gets the ith last element from the log. i is guaranteed to be smaller than or equal to N.\n" +
                "\n" +
                "You should be as efficient with time and space as possible.";
    }

    @Override
    public Integer runChallengeCase(PairValue<List<Integer>, Integer> input) {

        final RollingQueue<Integer> exampleQueue = new RollingQueue<>();
        input.left.stream().forEach(exampleQueue::push);

        return exampleQueue.get(input.right);
    }

    @Override
    public List<TripleValue<Integer, Function<PairValue<List<Integer>, Integer>, Integer>, PairValue<List<Integer>, Integer>>> getTestCases() {

        final Function<PairValue<List<Integer>, Integer>, Integer> testFunction = this::runChallengeCase;

        return Arrays.asList(
                new TripleValue<>( 15, testFunction, new PairValue<>(Arrays.asList(0,1,2,3,4,5,15,7,8,9,10,11), 5))
        );
    }
}
