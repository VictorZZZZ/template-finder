package com.zvv.template;

import java.util.Map;

public class Template {
    private Map<Character, Integer> letterMap;
    private String charSeq;
    private int count;

    public Template(Map<Character, Integer> letterMap) {
        this.letterMap = letterMap;
    }

    public Template(String charSeq) {
        this.charSeq = charSeq;
    }

    public Map<Character, Integer> getLetterMap() {
        return letterMap;
    }

    public String getCharSeq() {
        return charSeq;
    }

    public int getCount() {
        return count;
    }

    public void increaseIfMatch(Map<Character, Integer> wordMap) {
        if (this.letterMap != null) {
            for (Map.Entry<Character, Integer> entry : letterMap.entrySet()) {
                if (!wordMap.containsKey(entry.getKey())) {
                    return;
                }
                if (!wordMap.get(entry.getKey()).equals(entry.getValue())) {
                    return;
                }
            }
            this.count++;
        }
    }

    public void increaseIfMatch(String word) {
        if (this.charSeq != null && word.contains(charSeq)) {
            this.count++;
        }
    }

    public String printResult() {
        if (letterMap != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<Character, Integer> entry : letterMap.entrySet()) {
                stringBuilder.append(entry.getKey().toString() + entry.getValue());
            }
            return stringBuilder.append(" " + count).toString();
        }
        return "\""+charSeq+"\"" + " " + count;
     }
}
