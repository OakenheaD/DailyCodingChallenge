package com.oakenhead.dcc.challenge.month04.day30;

import com.oakenhead.dcc.challenge.AbstractCodingChallenge;
import com.oakenhead.dcc.challenge.TripleValue;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Component
public class ElementsOfPair extends AbstractCodingChallenge<Integer, ElementsOfPair.FunctionalPair<Integer>> {

    @Override
    public String dateString() {
        return "2019 April 30";
    }

    @Override
    public String shortName() {
        return "Elements of Pair, CAR, CDR";
    }

    @Override
    public String getChallengeTask() {
        return "" +
                //not much sense in Java technically?
                "This problem was asked by Jane Street.\n" +
                "\n" +
                "cons(a, b) constructs a pair, and car(pair) and cdr(pair) returns the first and last element of that pair. " +
                "For example, car(cons(3, 4)) returns 3, and cdr(cons(3, 4)) returns 4.\n" +
                "\n" +
                "Given this implementation of cons:\n" +
                "\n" +
                "def cons(a, b):\n" +
                "    def pair(f):\n" +
                "        return f(a, b)\n" +
                "    return pair\n" +
                "\n" +
                "Implement car and cdr.";
    }

    @Override
    public Integer runChallengeCase(final ElementsOfPair.FunctionalPair<Integer> input) {

        return 0;
    }

    public Integer cAR(final ElementsOfPair.FunctionalPair<Integer> pair) {
        return pair.left;
    }

    public Integer cDR(final ElementsOfPair.FunctionalPair<Integer> pair) {
        return pair.right;
    }

    @Override
    public List<TripleValue<Integer, Function<ElementsOfPair.FunctionalPair<Integer>, Integer>, ElementsOfPair.FunctionalPair<Integer>>> getTestCases() {

        final Function<FunctionalPair<Integer>, Integer> car = this::cAR;
        final Function<FunctionalPair<Integer>, Integer> cdr = this::cDR;

        final ElementsOfPair.FunctionalPair<Integer> testCase = new ElementsOfPair.FunctionalPair<>(3, 4);


        return Arrays.asList(
                new TripleValue<>(3, car, testCase),
                new TripleValue<>(4, cdr, testCase)
        );
    }

    public class FunctionalPair<T> {

        public final T left;
        public final T right;

        public FunctionalPair(final T left, final T right) {

            this.left = left;
            this.right = right;

        }

        public <R> FunctionalPair<R> apply(final Function<T, R> function) {

            return new FunctionalPair(
                    function.apply(this.left),
                    function.apply(this.right));

        }
    }

    public <T> FunctionalPair<T> cons(final T a, final T b) {
        return new FunctionalPair<T>(a, b);
    }
}
