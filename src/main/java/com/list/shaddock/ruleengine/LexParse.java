package com.list.shaddock.ruleengine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class LexParse {

    private PatternTree patternTree;
    private SlotTree slotTree;

    public LexParse() {
    }

    public LexParse(String patternFilePath, String slotFilePath) {
        this.patternTree = new PatternTree();
        this.slotTree = new SlotTree();
        load(patternFilePath, slotFilePath);
    }

    /**
     * 加载通配符文件和插槽文件
     *
     * @param patternFilePath 通配符文件路径
     * @param slotFilePath    插槽文件路径
     */
    private void load(String patternFilePath, String slotFilePath) {
        InputStream is = LexParse.class.getResourceAsStream(patternFilePath);
        InputStreamReader inputReader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(inputReader);
        try {
            String line = "";
            Pattern re = Pattern.compile("\\[@(\\w+)\\]");
            String intentName = "";
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                Matcher matcher = re.matcher(line);
                if (matcher.find()) {
                    intentName = matcher.group();
                } else {
                    List<String> normalText = patternTree.addTree(line, intentName);
                    if (normalText != null) {
                        for (String t : normalText) {
                            this.slotTree.addTree(t, SlotType.NORMAL_TEXT);
                        }
                    }
                }
            }
            bufferedReader.close();
            is = LexParse.class.getResourceAsStream(slotFilePath);
            inputReader = new InputStreamReader(is);
            bufferedReader = new BufferedReader(inputReader);
            String slotName = "";
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                int slotIndex = patternTree.getSlotIndex(line);
                if (slotIndex > 0) {
                    slotName = line.substring(0, slotIndex);
                } else {
                    slotTree.addTree(line, slotName);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputReader != null) {
                    inputReader.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * @param obj
     * @return
     */
    private String matchPattern(LexParseNode obj) {
        PatternTree ptree = obj.patternTree;
        String text = obj.nextWord;
        if (text != null && text.length() > 0) {
            return "";
        }
        if (Utils.isEmpty(ptree.root.intent)) {
            return "";
        }
        return ptree.root.intent;
    }


    public List<LexParserResult> parse(String text) {
        List<LexParserResult> resCandidates = new ArrayList<>();
        if (Utils.isEmpty(text)) {
            return resCandidates;
        }
        LexParseNode root = new LexParseNode();
        root.nextWord = text;
        root.patternTree = patternTree;
        List<LexParseNode> candidates = new ArrayList<>();
        candidates.add(root);
        List<LexParseNode> results = new ArrayList<>();
        while (!candidates.isEmpty()) {
            LexParseNode cand = candidates.get(0);
            text = cand.nextWord;
            int tlen = text.length();
            PatternTree ptree = cand.patternTree;
            if (ptree.root.children.containsKey(PatternType.PATTERN_WILDTREE)) {
                PatternNode node = ptree.root.children.get(PatternType.PATTERN_WILDTREE);
                for (Map.Entry<String, PatternNode> entry : node.children.entrySet()) {
                    PatternNode wtree = entry.getValue();
                    int lower = wtree.lower;
                    int upper = wtree.upper;
                    while (lower <= upper && lower <= tlen) {
                        LexParseNode lexParseNode = new LexParseNode();
                        lexParseNode.currentWord = text.substring(0, lower);
                        lexParseNode.slot = SlotType.WILD_WORD;
                        lexParseNode.nextWord = text.substring(lower);
                        lexParseNode.preNode = candidates.get(0);
                        lexParseNode.patternTree = new PatternTree(wtree);
                        lexParseNode.intentName = wtree.intent;
                        if (lexParseNode.nextWord.length() > 0 || Utils.isEmpty(wtree.intent)) {
                            candidates.add(lexParseNode);
                        } else {
                            results.add(lexParseNode);
                        }
                        lower++;
                    }
                }
            }
            handleQuestionMarkNode(text, ptree, candidates, results);
            searchNumberNode(text, ptree, candidates, results);
            doOneStepSearch(text, ptree, candidates, results);
            candidates.remove(0);
        }

        for (LexParseNode result : results) {
            LexParserResult res = new LexParserResult();
            res.setIntent(result.intentName);
            List<SlotType> slots = new ArrayList<>();
            LexParseNode r = result;
            while (r.preNode != null) {
                if (!Utils.isEmpty(r.slot)) {
                    slots.add(0, new SlotType(r.slot, r.currentWord));
                }
                r = r.preNode;
            }
            res.setSlots(slots);
            System.out.printf(res.toString());
            resCandidates.add(res);
        }
        return resCandidates;
    }

    private void doOneStepSearch(String text, PatternTree ptree, List<LexParseNode> candidates, List<LexParseNode> results) {
        List<SlotSearchResult> slotResults = slotTree.prefixSearch(text);
        if (slotResults == null || slotResults.isEmpty()) {
            return;
        }
        for (int j = 0; j < slotResults.size(); j++) {
            String currWord = slotResults.get(j).prefix;
            String nextWord = slotResults.get(j).next;
            Set<String> slots = slotResults.get(j).exist;
            for (String slot : slots) {
                LexParseNode lexParseNode = new LexParseNode();
                lexParseNode.patternTree = ptree;
                lexParseNode.preNode = candidates.get(0);
                lexParseNode.slot = slot;
                lexParseNode.currentWord = currWord;
                lexParseNode.nextWord = nextWord;
                PatternTree nextPt = null;
                if (Utils.equals(lexParseNode.slot, SlotType.NORMAL_TEXT)) {
                    nextPt = ptree.searchOneSlot(lexParseNode.currentWord);
                } else if (lexParseNode.slot != null) {
                    nextPt = ptree.searchOneSlot(lexParseNode.slot);
                }
                if (nextPt != null) {
                    lexParseNode.patternTree = nextPt;
                    lexParseNode.intentName = nextPt.root.intent;
                    if (!Utils.isEmpty(matchPattern(lexParseNode))) {
                        results.add(lexParseNode);
                    } else {
                        candidates.add(lexParseNode);
                    }
                }
            }
        }
    }


    /**
     * @param text
     * @param ptree
     * @param candidates
     * @param results
     */
    private void searchNumberNode(String text, PatternTree ptree, List<LexParseNode> candidates, List<LexParseNode> results) {
        String number = ptree.getNumber(text);
        if (Utils.isEmpty(number)) {
            return;
        }

        String key = PatternType.PATTERN_NONNEGATIVE_INT;
        if (number.contains(".")) {
            key = PatternType.PATTERN_NONNEGATIVE_FLOAT;
        }

        if (!ptree.root.children.containsKey(key)) {
            return;
        }
        PatternNode node = ptree.root.children.get(key);
        String nextText = text.substring(number.length());
        LexParseNode lexParseNode = new LexParseNode();
        lexParseNode.currentWord = number;
        lexParseNode.slot = PatternType.PATTERN_NUMBER;
        lexParseNode.nextWord = nextText;
        lexParseNode.preNode = candidates.get(0);
        lexParseNode.patternTree = new PatternTree();
        lexParseNode.intentName = node.intent;
        if (nextText.length() > 0 || Utils.isEmpty(node.intent)) {
            candidates.add(lexParseNode);
        } else {
            results.add(lexParseNode);
        }
    }

    private void handleQuestionMarkNode(String text, PatternTree ptree, List<LexParseNode> candidates, List<LexParseNode> results) {
        if (ptree.root.children.containsKey(PatternType.PATTERN_QUESTION_MARK)) {
            PatternNode node = ptree.root.children.get(PatternType.PATTERN_QUESTION_MARK);
            for (Map.Entry<String, PatternNode> entry : node.children.entrySet()) {
                PatternNode qtree = entry.getValue();
                String rawText = qtree.text.substring(3, qtree.text.length() - 1);
                if (text.startsWith(rawText)) {
                    LexParseNode lexParseNode = new LexParseNode();
                    lexParseNode.currentWord = rawText;
                    lexParseNode.slot = PatternType.PATTERN_QUESTION_MARK;
                    lexParseNode.nextWord = text.substring(rawText.length());
                    lexParseNode.preNode = candidates.get(0);
                    lexParseNode.patternTree = new PatternTree();
                    lexParseNode.intentName = qtree.intent;
                    if (lexParseNode.nextWord.length() > 0 || Utils.isEmpty(qtree.intent)) {
                        candidates.add(lexParseNode);
                    } else {
                        results.add(lexParseNode);
                    }
                }
            }
        }
    }


}
