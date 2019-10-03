package com.list.shaddock.ruleengine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SlotTree {

    private SlotNode root;

    public SlotTree() {
        this.root = new SlotNode();
    }

    public SlotTree(SlotNode root) {
        this.root = root;
    }

    public void addTree(String word) {
        addTree(word,SlotType.DEFAULT);
    }

    public void addTree(String word, String value) {
        SlotNode node =root;
        String[] words = Utils.string2unicode(word);
        int level = 1;
        for(String ch:words){
            if(node.children.containsKey(ch)){
                node = node.children.get(ch);
            }else{
                SlotNode child = new SlotNode();
                child.value = ch;
                child.level = level;
                node.children.put(ch,child);
                node = child;
            }
            level++;
        }
        if(node.exist == null){
            node.exist = new HashSet<>();
        }
        node.exist.add(value);
    }

    public SlotNode getTree(){
        return root;
    }


    public List<SlotSearchResult> prefixSearch(String word){
        String[] words = Utils.string2unicode(word);
        SlotNode node = root;
        String pre = "";
        String next = "";
        List<SlotSearchResult> results = new ArrayList<>();
        for(int i = 0; i < words.length; i++){
            String ch = words[i];
            if(node.children.containsKey(ch)){
                node = node.children.get(ch);
                if(node.exist != null){
                    if(i == words.length -1){
                        pre = word;
                        next = "";
                    }else{
                        pre = word.substring(0,i+1);
                        next = word.substring(i + 1);
                    }
                    results.add(new SlotSearchResult(pre,next,node.exist));
                }
            }else{
                return results;
            }
        }
        return results;
    }

    @Override
    public String toString() {
        return "SlotTree{" +
                "root=" + root +
                '}';
    }
}
