package com.oakenhead.dcc.challenge.beans;

public class BinaryStringNode {

    public static final String SUFFIX_ROOT = "root";
    public static final String SUFFIX_LEFT = "left";
    public static final String SUFFIX_RIGHT = "right";

    public final String value;
    public final BinaryStringNode left;
    public final BinaryStringNode right;

    public BinaryStringNode(final String value, final BinaryStringNode left, final BinaryStringNode right) {

        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BinaryStringNode(final String value, final BinaryStringNode aNode) {

        this.value = value;

        final boolean isValueNull = aNode == null;

        final boolean isValueLeft =  !isValueNull && aNode.value.endsWith(SUFFIX_LEFT);
        final boolean isValueRight = !isValueNull && aNode.value.endsWith(SUFFIX_RIGHT);

        this.left =  isValueLeft  ? aNode : null;
        this.right = isValueRight ? aNode : null;

    }

    public BinaryStringNode(final String value) {

        this.value = value;

        this.left =  null;
        this.right = null;

    }


}
