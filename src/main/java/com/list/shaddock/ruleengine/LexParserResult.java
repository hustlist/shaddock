package com.list.shaddock.ruleengine;

import java.util.List;

public class LexParserResult {

    public String intent;

    public List<SlotType> slots;

    public LexParserResult() {
    }


    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public List<SlotType> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotType> slots) {
        this.slots = slots;
    }

    @Override
    public String toString() {
        return "LexParserResult{" +
                "intent='" + intent + '\'' +
                ", slots=" + slots +
                '}';
    }
}
