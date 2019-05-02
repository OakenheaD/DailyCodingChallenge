package com.oakenhead.dcc.challenge;

public class QuadValue<L, ML, MR, R> extends PairValue<L, R> {

    public final ML middleLeft;
    public final MR middleRight;

    public QuadValue(final L left, final ML middleLeft, final MR middleRight, final R right) {

        super(left, right);
        this.middleLeft = middleLeft;
        this.middleRight = middleRight;

    }
}
