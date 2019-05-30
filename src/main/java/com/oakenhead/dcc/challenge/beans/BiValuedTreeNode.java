package com.oakenhead.dcc.challenge.beans;

public class BiValuedTreeNode<T, AT> extends TreeNode<T> {

    public final AT anotherValue;

    public BiValuedTreeNode(final T nodeValue, final AT anotherNodeValue) {

        super(nodeValue);
        this.anotherValue = anotherNodeValue;

    }


}
