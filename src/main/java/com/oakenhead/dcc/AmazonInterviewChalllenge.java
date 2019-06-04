package com.oakenhead.dcc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AmazonInterviewChalllenge {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmazonInterviewChalllenge.class);

    public static void main(final String[] args) {

        System.out.println(countOnesInBinaryFormOfInteger(3, 7));

        final String taskTwoTest =
                "Sun 10:00-20:00\r\n" +
                        "Fri 05:00-10:00\r\n" +
                        "Fri 16:30-23:50\r\n" +
                        "Sat 10:00-24:00\r\n" +
                        "Sun 01:00-04:00\r\n" +
                        "Sat 02:00-06:00\r\n" +
                        "Tue 03:30-18:15\r\n" +
                        "Tue 19:00-20:00\r\n" +
                        "Wed 04:25-15:14\r\n" +
                        "Wed 15:14-22:40\r\n" +
                        "Thu 00:00-23:59\r\n" +
                        "Mon 05:00-13:00\r\n" +
                        "Mon 15:00-21:00";

        TaskTwo taskTwo = new TaskTwo();
        System.out.println(taskTwo.calcMaxBreakTime(taskTwoTest));
    }

    private static class TaskTwo {

        public int calcMaxBreakTime(String S) {
            // write your code in Java SE 8
            return computeLargestRestBreak(S);
        }


        private int computeLargestRestBreak(final String input) {

            final List<Meeting> ascendingMeetings = parseInputToMeetings(input);
            final List<Meeting> ascendingBreaks = computeRestBreaks(ascendingMeetings);

            return ascendingBreaks.stream()
                    .mapToInt(breaks -> breaks.duration)
                    .max().orElse(-1);

        }

        private List<Meeting> parseInputToMeetings(final String input) {

            final String[] unorderedParts = input.split(System.lineSeparator());

            final List<Meeting> sortedMeetings = Arrays.stream(unorderedParts)
                    .map(Meeting::new)
                    .sorted((meet1, meet2) -> Integer.compare(meet1.start.minutesSinceEpochStart, meet2.start.minutesSinceEpochStart))
                    .collect(Collectors.toList());

            return sortedMeetings;

        }

        private List<Meeting> computeRestBreaks(final List<Meeting> meetings) {

            final List<Meeting> meetingsWithBoundaries = new ArrayList<>(meetings);

            meetingsWithBoundaries.add(0, new Meeting("Mon 00:00-00:00"));
            meetingsWithBoundaries.add(new Meeting("Sun 24:00-24:00"));

            final List<Meeting> preMeetingBreaks = new ArrayList<>();

            for (int i = 1; i < meetingsWithBoundaries.size(); i++) {

                final Meeting previousMeeting = meetingsWithBoundaries.get(i - 1);
                final Meeting currentMeeting = meetingsWithBoundaries.get(i);

                preMeetingBreaks.add(new Meeting(previousMeeting.finish, currentMeeting.start));

            }

            return preMeetingBreaks;

        }

        private int minuteOfDay(final String hhmm) {

            // assume correct format passed in
            // assume someone else validated for 25:70 kinds of errors.
            final String[] ints = hhmm.split(":");
            final int hours = Integer.parseInt(ints[0]);
            final int minutes = Integer.parseInt(ints[1]);

            return hours * 60 + minutes;

        }

        private class Meeting {

            public final String sourceString;

            public final int startDayOrdinal;
            public final TimeMoment start;
            public final TimeMoment finish;

            public final int duration;

            //assume "Day hh:mm-hh:mm"
            public Meeting(final String sourceString) {

                this.sourceString = sourceString;

                final String[] dayTimeParts = sourceString.split("\\s");
                final String[] justTimeParts = dayTimeParts[1].split("-");

                final String day = dayTimeParts[0];
                final String startTime = justTimeParts[0];
                final String finishTime = justTimeParts[1];

                //NPE unsafe - assume input is pre-validated;
                this.startDayOrdinal = WeekDays.whichDayIsIt(day).ordinal();
                this.start = new TimeMoment(day + " " + startTime);
                this.finish = new TimeMoment(day + " " + finishTime);

                this.duration = minutesBetweenMoments(this.finish, this.start);

            }

            //assume start is bigger than finish
            public Meeting(final TimeMoment start, final TimeMoment finish) {

                this.sourceString = "";

                this.startDayOrdinal = start.dayOrdinal;

                this.start = start;
                this.finish = finish;

                this.duration = minutesBetweenMoments(this.finish, this.start);

            }
        }

        public int minutesBetweenMoments(final TimeMoment one, final  TimeMoment two) {

            final int oneMinutes = one.minutesSinceEpochStart;
            final int twoMinutes = two.minutesSinceEpochStart;

            final int interval = oneMinutes - twoMinutes;

            return interval < 0 ? -interval : interval;

        }

        private class TimeMoment {

            public static final int MINUTES_IN_A_DAY = 24 * 60;

            public final int dayOrdinal;
            public final int minuteInDay;

            public final int minutesSinceEpochStart;

            public TimeMoment(int dayOrdinal, int minuteInDay) {

                this.dayOrdinal = dayOrdinal;
                this.minuteInDay = minuteInDay;

                this.minutesSinceEpochStart = this.dayOrdinal * TimeMoment.MINUTES_IN_A_DAY + this.minuteInDay;

            }

            //Assume "DAY HH:MM"
            public TimeMoment(final String canonical) {

                final String[] dayTimeParts = canonical.split("\\s");

                //NPE unsafe, assume input is validated before
                this.dayOrdinal = WeekDays.whichDayIsIt(dayTimeParts[0]).ordinal();
                this.minuteInDay = minuteOfDay(dayTimeParts[1]);

                this.minutesSinceEpochStart = this.dayOrdinal * TimeMoment.MINUTES_IN_A_DAY + this.minuteInDay;

            }
        }

        private enum WeekDays {

            MONDAY("Mon"),
            TUESDAY("Tue"),
            WEDNESDAY("Wed"),
            THURSDAY("Thu"),
            FRIDAY("Fri"),
            SATURDAY("Sat"),
            SUNDAY("Sun")
            ;
            public final String threeLetterCode;

            WeekDays(String threeLetterCode) {
                this.threeLetterCode = threeLetterCode;
            }

            public static WeekDays whichDayIsIt(final String justDayStr) {

                if (justDayStr == null) {
                    return null;
                }

                return Arrays.stream(WeekDays.values())
                        .filter(weekDay -> justDayStr.toLowerCase().startsWith(weekDay.threeLetterCode.toLowerCase()))
                        .findAny().orElse(null);

            }

        }

    }


















    private static int countOnesInBinaryFormOfInteger(int A, int B)  {

        final long longA = (long) A;
        final long longB = (long) B;

        final long multAB = longA * longB;

        final String multABinBinary = Long.toBinaryString(multAB);

        return countOnesInStringStream(multABinBinary);
    }

    public static int countOnesInString(final String input) {

        if (input == null) {
            return 0;
        }

        final String justOnes = input.replaceAll("[^1]", "");

        return justOnes.length();
    }

    public static int countOnesInStringStream(final String input) {

        if (input == null) {
            return 0;
        }

        //streams are cool!
        return input.chars()
                .map(ch -> ch == ((int) '1') ? 1 : 0)
                .sum();

    }

    public static int countOnesInBinaryLong(final long binaryLong) {

        return Long.bitCount(binaryLong);

    }
}
