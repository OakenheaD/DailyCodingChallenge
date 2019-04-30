package com.oakenhead.dcc.challenge.beans;

public class BinaryNode {

    public static final String SUFFIX_ROOT = "root";
    public static final String SUFFIX_LEFT = "left";
    public static final String SUFFIX_RIGHT = "right";

    public final String value;
    public final BinaryNode left;
    public final BinaryNode right;

    public BinaryNode(final String value, final BinaryNode left, final BinaryNode right) {

        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BinaryNode(final String value, final BinaryNode aNode) {

        this.value = value;

        final boolean isValueNull = aNode == null;

        final boolean isValueLeft =  !isValueNull && aNode.value.endsWith(SUFFIX_LEFT);
        final boolean isValueRight = !isValueNull && aNode.value.endsWith(SUFFIX_RIGHT);

        this.left =  isValueLeft  ? aNode : null;
        this.right = isValueRight ? aNode : null;

    }

    public BinaryNode(final String value) {

        this.value = value;

        this.left =  null;
        this.right = null;

    }


}
