package com.list.shaddock.ruleengine;

public class NumberNode {

    public static final String POSITIVE_TYPE="p";
    public static final String FLOAT_TYPE="f";
    public static final String RANGE_TYPE="r";

    public String type;
    public int lower;
    public int upper;
    public String text;

    public NumberNode(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public NumberNode(String type, int lower, int upper, String text) {
        this.type = type;
        this.lower = lower;
        this.upper = upper;
        this.text = text;
    }
}
