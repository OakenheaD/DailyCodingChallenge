package com.oakenhead.dcc.challenge.month05.day16;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.PairValue;
import com.oakenhead.dcc.challenge.TripleValue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Service
public class SchedulingRoomsForIntervalsChallenge extends AbstractCodingChallenge<Integer, List<PairValue<Integer, Integer>>> {

    @Override
    public String dateString() {
        return "2019 May 16";
    }

    @Override
    public String shortName() {

        return "Overlapping Intervals detection";
    }

    @Override
    public String getChallengeTask() {

        return "Good morning! Here's your coding interview problem for today.\n"+
                "\n"+
                "This problem was asked by Snapchat.\n"+
                "\n"+
                "Given an array of time intervals (start, end) for classroom lectures (possibly overlapping), find the minimum number of rooms required.\n"+
                "\n"+
                "For example, given [(30, 75), (0, 50), (60, 150)], you should return 2.";
    }

    @Override
    public Integer runChallengeCase(final List<PairValue<Integer, Integer>> input) {

        final List<List<PairValue<Integer, Integer>>> listOfPerRoomSchedules = new ArrayList<>();

        input.stream().forEach(schedule -> addToSchedule(schedule, listOfPerRoomSchedules));
        return listOfPerRoomSchedules.size();

    }

    private synchronized void addToSchedule(final PairValue<Integer, Integer> newSchedule, final List<List<PairValue<Integer, Integer>>> listOfPerRoomSchedules) {

        for (List<PairValue<Integer, Integer>> scheduleForRoom : listOfPerRoomSchedules) {

            if (scheduleForRoom.size() > 0 && !overlapsWithList(newSchedule, scheduleForRoom)) {

                scheduleForRoom.add(newSchedule);
                return;
            }
        }

        final List<PairValue<Integer, Integer>> newRoomRequired = new ArrayList<>();
        newRoomRequired.add(newSchedule);
        listOfPerRoomSchedules.add(newRoomRequired);

    }

    private boolean overlapsWithList(final PairValue<Integer, Integer> newSchedule, final List<PairValue<Integer, Integer>> schedulesForRoom) {

        return schedulesForRoom.stream().anyMatch(oldSchedule -> overlapsWithSingle(newSchedule, oldSchedule));
    }

    private boolean overlapsWithSingle(final PairValue<Integer, Integer> newSchedule, final PairValue<Integer, Integer> existing) {

        final boolean newBeginsWithinOld = existing.left <= newSchedule.left && newSchedule.left <= existing.right;
        final boolean newFinishesWithinOld = existing.left <= newSchedule.right && newSchedule.right <= existing.right;

        final boolean newIsWithinOld = existing.left <= newSchedule.left && newSchedule.right <= existing.right;
        final boolean oldIsWithinNew = newSchedule.left <= existing.left && existing.right <= newSchedule.right;

        return newBeginsWithinOld || newFinishesWithinOld || newIsWithinOld || oldIsWithinNew;
    }

    @Override
    public List<TripleValue<Integer, Function<List<PairValue<Integer, Integer>>, Integer>, List<PairValue<Integer, Integer>>>> getTestCases() {

        final Function<List<PairValue<Integer, Integer>>, Integer> testFunction = this::runChallengeCase;

        final List<PairValue<Integer, Integer>> intervals = Arrays.asList(
                new PairValue<>(30, 75),
                new PairValue<>(0, 50),
                new PairValue<>(60, 150)
        );

        final List<PairValue<Integer, Integer>> intervals10 = Arrays.asList(
                new PairValue<>(30, 75),
                new PairValue<>(0, 50),
                new PairValue<>(60, 150),
                new PairValue<>(31, 76),
                new PairValue<>(1, 51),
                new PairValue<>(61, 151),
                new PairValue<>(32, 77),
                new PairValue<>(2, 52),
                new PairValue<>(62, 152),
                new PairValue<>(33, 78),
                new PairValue<>(3, 53),
                new PairValue<>(63, 153),
                new PairValue<>(34, 79),
                new PairValue<>(4, 54),
                new PairValue<>(64, 154)
        );

        return Arrays.asList(
                new TripleValue<>(2, testFunction, intervals),
                new TripleValue<>(10, testFunction, intervals10)
        );
    }
}
