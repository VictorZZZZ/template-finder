package com.zvv.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {

    @Test
    void splitInWords() {
        String line = "Отец мой Андрей Петрович Гринев; в молодости своей служил при. графе Минихе и вышел в отставку премьер-майором в 17.. году.";
        List<String> words = StringUtil.splitInWords(line);
        for (String word : words) {
            System.out.println(word);
        }
        assertEquals(21, words.size());
    }
}