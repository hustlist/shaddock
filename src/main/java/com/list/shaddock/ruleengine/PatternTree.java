package com.list.shaddock.ruleengine;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTree {

    public PatternNode root;
    private Pattern wildcardRegex = Pattern.compile("^\\[W:(\\d+)-(\\d+)\\](.*)");
    private Pattern numberRegex = Pattern.compile("^(\\d+)(\\.\\d+)?(.*)");

    public PatternTree() {
        this.root = new PatternNode();
    }

    public PatternTree(PatternNode root) {
        this.root = root;
    }

    public List<String> addTree(String text) {
        return addTree(text, SlotType.DEFAULT);
    }

    public List<String> addTree(String text, String value) {
        List<String> normalText = new ArrayList<>();
        PatternNode node = root;
        int level = 1;
        while (text.length() > 0) {
            String key = "";
            WildcardRetDTO wrange = getWildcard(text);
            if (wrange.lower != -1 && wrange.upper != -1) {
                key = String.format("[W:%d-%d]", wrange.lower, wrange.upper);
                if (!node.children.containsKey(PatternType.PATTERN_WILDTREE)) {
                    PatternNode pn = new PatternNode();
                    pn.level = level++;
                    pn.text = PatternType.PATTERN_WILDTREE;
                    node.children.put(PatternType.PATTERN_WILDTREE, pn);
                }
                node = node.children.get(PatternType.PATTERN_WILDTREE);
                if (node.children.containsKey(key)) {
                    node = node.children.get(key);
                } else {
                    PatternNode pn = new PatternNode();
                    pn.type = PatternType.PATTERN_WILDTREE;
                    pn.text = key;
                    pn.lower = wrange.lower;
                    pn.upper = wrange.upper;
                    pn.level = level;
                    node.children.put(key, pn);
                    node = pn;
                }
                text = wrange.text;
            } else {
                int slotIndex = getSlotIndex(text);
                NumberNode numNode = getNumberNode(text);
                int questionMarkIndex = getQuestionMarkIndex(text);
                if (slotIndex > 0) {
                    key = text.substring(0, slotIndex);
                    text = text.substring(slotIndex);
                } else if (numNode != null) {
                    key = numNode.text;
                    text = text.substring(key.length());
                } else if (questionMarkIndex > 0) {
                    if (!node.children.containsKey(PatternType.PATTERN_QUESTION_MARK)) {
                        PatternNode pn = new PatternNode();
                        pn.level = level++;
                        pn.text = PatternType.PATTERN_QUESTION_MARK;
                        node.children.put(PatternType.PATTERN_QUESTION_MARK, pn);
                    }
                    node = node.children.get(PatternType.PATTERN_QUESTION_MARK);
                    key = text.substring(0, questionMarkIndex);
                    text = text.substring(questionMarkIndex);
                } else {
                    int normalIndex = getNormalTextIndex(text);
                    if (normalIndex <= 0) {
                        return normalText;
                    }
                    String ntext = text.substring(0, normalIndex);
                    normalText.add(ntext);
                    key = ntext;
                    text = text.substring(normalIndex);
                }

                if (node.children.containsKey(key)) {
                    node = node.children.get(key);
                } else {
                    PatternNode pn = new PatternNode();
                    if (slotIndex > 0) {
                        pn.type = PatternType.PATTERN_SLOT;
                    } else if (numNode != null) {
                        pn.type = PatternType.PATTERN_NUMBER;
                    } else if (questionMarkIndex > 0) {
                        pn.type = PatternType.PATTERN_QUESTION_MARK;
                    } else {
                        pn.type = PatternType.PATTERN_TEXT;
                    }
                    pn.text = key;
                    pn.level = level;
                    node.children.put(key, pn);
                    node = pn;
                }
            }
            level++;
        }
        node.intent = value;
        return normalText;
    }


    /**
     * @param pattern
     * @return
     */
    private int getNormalTextIndex(String pattern) {
        if (pattern == null) {
            return -1;
        }
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '[')
                return i;
        }
        return pattern.length();
    }


    /**
     * @param pattern
     * @return
     */
    private int getQuestionMarkIndex(String pattern) {
        if (pattern == null || pattern.length() < 4) {
            return -1;
        }
        if (pattern.charAt(0) == '[' && pattern.charAt(1) == 'Q' && pattern.charAt(2) == ':') {
            for (int i = 3; i < pattern.length(); i++) {
                return i + 1;
            }
        }
        return -1;
    }


    /**
     * @param pattern
     * @return
     */
    private NumberNode getNumberNode(String pattern) {
        if (pattern == null || pattern.length() < 5) {
            return null;
        }
        if (pattern.charAt(0) == '[' && pattern.charAt(1) == 'N' && pattern.charAt(2) == ':') {
            if (pattern.charAt(3) == 'p' && pattern.charAt(4) == ']') {
                return new NumberNode(NumberNode.POSITIVE_TYPE, pattern.substring(0, 5));
            }
            if (pattern.charAt(3) == 'f' && pattern.charAt(4) == ']') {
                return new NumberNode(NumberNode.FLOAT_TYPE, pattern.substring(0, 5));
            }
        }
        return null;
    }

    /**
     * 获取slot的索引值
     *
     * @param pattern
     * @return
     */
    public int getSlotIndex(String pattern) {
        if (pattern == null || pattern.length() < 4) {
            return -1;
        }
        if (pattern.charAt(0) == '[' && pattern.charAt(1) == 'D' && pattern.charAt(2) == ':') {
            for (int i = 3; i < pattern.length(); i++) {
                if (pattern.charAt(i) == ']')
                    return i + 1;
            }
        }
        return -1;
    }

    /**
     * @param pattern
     * @return
     */
    private WildcardRetDTO getWildcard(String pattern) {
        Matcher matcher = wildcardRegex.matcher(pattern);
        if (matcher.find()) {
            int lower = Integer.parseInt(matcher.group(1));
            int upper = Integer.parseInt(matcher.group(2));
            String nextText = matcher.group(3);
            if (lower < upper) {
                return new WildcardRetDTO(lower, upper, nextText);
            }

        }
        return new WildcardRetDTO(-1, -1, pattern);
    }


    /**
     *
     * @param text
     * @return
     */
    public String getNumber(String text) {
        Matcher matcher = numberRegex.matcher(text);
        if(matcher.find()){
            if(!Utils.isEmpty(matcher.group(2))){
                return matcher.group(1) + matcher.group(2);
            }
            return matcher.group(1);
        }
        return "";
    }


    /**
     *
     * @param slot
     * @return
     */
    public PatternTree searchOneSlot(String slot) {
        PatternNode node = root;
        if(node.children.containsKey(slot)){
            node = node.children.get(slot);
        }else{
            return null;
        }
        return new PatternTree(node);
    }
}
