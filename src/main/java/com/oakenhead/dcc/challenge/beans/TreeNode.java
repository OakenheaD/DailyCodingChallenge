package com.oakenhead.dcc.challenge.beans;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {

    protected List<TreeNode<T>> children = new ArrayList<>();
    protected final T nodeValue;
    protected TreeNode<T> previous;

    public TreeNode(final T nodeValue) {
        this.nodeValue = nodeValue;
    }

    public <D extends TreeNode<T>> void addChild(final D newChild) {

        children.add(newChild);
        newChild.previous = this;

    }

    public void removeAllChildren() {

        children.forEach(this::removeChild);

    }

    public void removeChild(final TreeNode<T> toPrune) {

        children.remove(toPrune);
        toPrune.previous = null;
        toPrune.removeAllChildren();

    }

    public TreeNode<T> getPrevious() {

        return previous;

    }

    public boolean isNonBranching() {

        return children.size() == 1;

    }

    public List<TreeNode<T>> getChildren() {

        return children;

    }

    public T getNodeValue() {

        return nodeValue;

    }
}
