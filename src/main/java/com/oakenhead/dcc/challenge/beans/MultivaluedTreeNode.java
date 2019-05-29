package com.oakenhead.dcc.challenge.beans;

public class MultivaluedTreeNode<T, AT> extends TreeNode<T> {

    public final AT anotherValue;

    public MultivaluedTreeNode(final T nodeValue, final AT anotherNodeValue) {

        super(nodeValue);
        this.anotherValue = anotherNodeValue;

    }


}
