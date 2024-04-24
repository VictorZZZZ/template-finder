package com.zvv.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static List<String> splitInWords(String line) {
        Pattern pattern = Pattern.compile("[a-zA-ZА-я0-9]+");
        Matcher matcher = pattern.matcher(line);
        List<String> words = new ArrayList<>();
        while (matcher.find()) {
            words.add(matcher.group());
        }
        return words;
    }

    public static Map<Character, Integer> getLetterMap(String string) {
        Map<Character, Integer> letterMap = new HashMap<>();
        for (Character c : string.toCharArray()) {
            if (letterMap.containsKey(c)) {
                Integer i = letterMap.get(c);
                i++;
                letterMap.put(c, i);
                continue;
            }
            letterMap.put(c, 1);
        }
        return letterMap;
    }
}
