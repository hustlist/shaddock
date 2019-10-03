package com.list.shaddock.ruleengine;

public class SlotType {

    private String type;
    private String originalWord;

    public static final String DEFAULT="default";
    public static final String NORMAL_TEXT="NormalText";
    public static final String WILD_WORD="WILD_WORD";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOriginalWord() {
        return originalWord;
    }

    public void setOriginalWord(String originalWord) {
        this.originalWord = originalWord;
    }

    public SlotType(String type, String originalWord) {
        this.type = type;
        this.originalWord = originalWord;
    }
}
