package com.list.shaddock.ruleengine;

import java.util.Set;

public class SlotSearchResult {

    public String prefix;
    public String next;
    public Set<String> exist;

    public SlotSearchResult(String prefix) {
        this.prefix = prefix;
    }

    public SlotSearchResult(String prefix, String next, Set<String> exist) {
        this.prefix = prefix;
        this.next = next;
        this.exist = exist;
    }

    @Override
    public String toString() {
        return "SlotSearchResult{" +
                "prefix='" + prefix + '\'' +
                ", next='" + next + '\'' +
                ", exist=" + exist +
                '}';
    }
}
