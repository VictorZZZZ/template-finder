package com.zvv.template;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateProcessorTest {

    private static Stream<Arguments> linesForTemplateConvert() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of("   ", false),
                Arguments.of("123", false),
                Arguments.of("ewqeqw", false),
                Arguments.of("e2wqeqw", false),
                Arguments.of("и2 в4", false),
                Arguments.of("a1", true),
                Arguments.of("м1ю2с4", true),
                Arguments.of("\"абв\"", true),
                Arguments.of("\"абв", false),
                Arguments.of("\"а бв\"", true),
                Arguments.of("\"а4бв\"", true),
                Arguments.of("11", true)
        );
    }

    @ParameterizedTest
    @MethodSource("linesForTemplateConvert")
    public void testParseTemplate(String input, boolean isValid) {
        Optional<Template> template = TemplateProcessor.parseTemplate(input);
        assertEquals(isValid, template.isPresent());
    }
}