package com.list.shaddock.ruleengine;

import java.util.HashMap;
import java.util.Map;

public class PatternNode {

    public String type;
    public int lower;
    public int upper;
    public String text;
    public String intent;
    public Map<String,PatternNode> children;
    public int level;

    public PatternNode() {
        this.children = new HashMap<>();
        this.text= "";
        this.type="";
        this.intent="";
    }

    @Override
    public String toString() {
        return "PatternNode{" +
                "type='" + type + '\'' +
                ", lower=" + lower +
                ", upper=" + upper +
                ", text='" + text + '\'' +
                ", intent='" + intent + '\'' +
                ", children=" + children +
                ", level=" + level +
                '}';
    }
}
