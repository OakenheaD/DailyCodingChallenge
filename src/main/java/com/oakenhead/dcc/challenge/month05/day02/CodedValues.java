package com.oakenhead.dcc.challenge.month05.day02;

public enum CodedValues {
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H,
    I,
    J,
    K,
    L,
    M,
    N,
    O,
    P,
    Q,
    R,
    S,
    T,
    U,
    V,
    W,
    X,
    Y,
    Z,
    ;

    public String toLetter() {
        return this.name();
    }

    //assume cached in robust version
    public String toNumberStr() {
        return Integer.toString(this.ordinal() + 1);
    }

}
