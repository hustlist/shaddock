package com.list.shaddock.ruleengine;

public class LexParseNode {

    public String currentWord;

    public String nextWord;

    public String slot;

    public PatternTree patternTree;

    public LexParseNode preNode;

    public String intentName;

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public String getNextWord() {
        return nextWord;
    }

    public void setNextWord(String nextWord) {
        this.nextWord = nextWord;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public PatternTree getPatternTree() {
        return patternTree;
    }

    public void setPatternTree(PatternTree patternTree) {
        this.patternTree = patternTree;
    }

    public LexParseNode getPreNode() {
        return preNode;
    }

    public void setPreNode(LexParseNode preNode) {
        this.preNode = preNode;
    }

    public String getIntentName() {
        return intentName;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    @Override
    public String toString() {
        return "LexParseNode{" +
                "currentWord='" + currentWord + '\'' +
                ", nextWord='" + nextWord + '\'' +
                ", slot='" + slot + '\'' +
                ", patternTree=" + patternTree +
                ", preNode=" + preNode +
                ", intentName='" + intentName + '\'' +
                '}';
    }
}
