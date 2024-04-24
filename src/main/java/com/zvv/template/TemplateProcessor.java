package com.zvv.template;

import com.zvv.utils.StringUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TemplateProcessor {
    private List<Template> templates = new ArrayList<>();

    public TemplateProcessor(File templateFile) {
        readTemplates(templateFile);
    }

    private void readTemplates(File templateFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(templateFile));
            String line = reader.readLine();

            while (line != null) {
                parseTemplate(line).ifPresent(template -> templates.add(template));
                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Optional<Template> parseTemplate(String line) {
        if (line == null || line.isBlank()) {
           return Optional.empty();
        }
        if (line.startsWith("\"") && line.endsWith("\"")) {
            return Optional.of(new Template(line.replace("\"", "")));
        }
        return parseLetterMapTemplate(line);
    }

    private static Optional<Template> parseLetterMapTemplate(String line) {
        if (line.length() % 2 != 0) {
            System.err.println("Template '" + line + "' is not valid. Ignoring.");
            return Optional.empty();
        }
        try {
            boolean isLetterNowGoing = true;
            Map<Character, Integer> letterMap = new HashMap<>();
            for (int i = 0; i < line.length() - 1; i++) {
                char c = line.charAt(i);
                if ((isLetterNowGoing && !Character.isLetter(c) && !Character.isDigit(c))
                        || (!isLetterNowGoing && Character.isLetter(c))) {
                    System.err.println("Template '" + line + "' is not valid. Ignoring.");
                    return Optional.empty();
                }
                if (isLetterNowGoing) {
                    letterMap.put(c, Character.getNumericValue(line.charAt(i + 1)));
                }
                isLetterNowGoing = !isLetterNowGoing;
            }
            return Optional.of(new Template(letterMap));
        } catch (Exception e) {
            System.err.println("Template '" + line + "' is not valid. Ignoring.");
            return Optional.empty();
        }
    }

    public void processFile(File textFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(textFile));
            String line = reader.readLine();
            reader.read();
            while (line != null) {
                recountTemplateEntries(line);
                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void recountTemplateEntries(String line) {
        List<String> words = StringUtil.splitInWords(line);
        for (String word : words) {
            Map<Character, Integer> wordLetterMap = StringUtil.getLetterMap(word);
            templates.forEach(template -> {
                if (template.getLetterMap() != null) {
                    template.increaseIfMatch(wordLetterMap);
                } else {
                    template.increaseIfMatch(word);
                }
            });
        }
    }

    public void printOutput(File outputFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, false));
        templates.forEach(template -> {
            try {
                writer.append(template.printResult() + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        writer.close();
    }
}
