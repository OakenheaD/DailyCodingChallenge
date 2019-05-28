package com.oakenhead.dcc.challenge.beans;

public class PureBinaryNode<T> {

    public final T value;
    public final PureBinaryNode<T> leftChild;
    public final PureBinaryNode<T> rightChild;

    public PureBinaryNode(final T value, final PureBinaryNode<T> leftChild, final PureBinaryNode<T> rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}
