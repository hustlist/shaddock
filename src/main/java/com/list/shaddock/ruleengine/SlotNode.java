package com.list.shaddock.ruleengine;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SlotNode {

    public String value;
    public Map<String,SlotNode> children;
    public Set<String> exist;
    public int level;

    public SlotNode() {
        this.children = new HashMap<>();
        this.value="";
    }

    @Override
    public String toString() {
        return "SlotNode{" +
                "value='" + value + '\'' +
                ", children=" + children +
                ", exist=" + exist +
                ", level=" + level +
                '}';
    }
}
