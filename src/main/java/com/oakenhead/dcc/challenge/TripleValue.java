package com.oakenhead.dcc.challenge;

public class TripleValue<L, M, R> extends PairValue<L, R> {

    public final M middle;

    public TripleValue(final L left, final M middle, final R right) {

        super(left, right);
        this.middle = middle;

    }
}
