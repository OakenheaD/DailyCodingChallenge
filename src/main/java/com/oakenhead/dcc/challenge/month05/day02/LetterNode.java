package com.oakenhead.dcc.challenge.month05.day02;

import com.oakenhead.dcc.challenge.beans.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LetterNode extends TreeNode<String> {

    public final CodedValues letterPossibility;

    public LetterNode(final CodedValues letterPossibility, final String remainderOfWord) {

        super(remainderOfWord);
        this.letterPossibility = letterPossibility;

    }

    public boolean isALeaf() {

        return  this.children.size() == 0;

    }

    public boolean noSymbolsToDecodeLeft() {

        return this.nodeValue == null || this.nodeValue.length() == 0;

    }

    public boolean terminatesAValidWord() {

        return this.isALeaf() && this.noSymbolsToDecodeLeft();

    }

    public void spawnLeaves() {

        final List<LetterNode> possibilities = Arrays.stream(CodedValues.values())
                .filter(letter -> this.nodeValue.startsWith(letter.toNumberStr()))
                .map(chance -> new LetterNode(chance, this.nodeValue.substring(chance.toNumberStr().length())))
                .collect(Collectors.toList());

        possibilities.forEach(this::addChild);
        possibilities.forEach(LetterNode::spawnLeaves);

    }

    public int countValidLeavesBelow() {

        if (this.terminatesAValidWord()) {
            return 1;
        }

        return children.stream().mapToInt(child -> ((LetterNode) child).countValidLeavesBelow()).sum();

    }


}
