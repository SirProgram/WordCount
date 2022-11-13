package com.sirprogram.wordcount;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounter {

    private final Map<String, Integer> wordCount = new HashMap<>();

    public void countWords(List<String> inputStrings) {
        inputStrings.stream()
                .map(s -> s.split(" "))
                .flatMap(Stream::of)
                .map(this::removePunctuation)
                .map(String::toLowerCase)
                .forEach(this::countSingleWord);
    }

    private String removePunctuation(String word) {
        return word.replaceAll("[.\":;,!?(){}]", "");
    }

    private void countSingleWord(String word) {
        wordCount.merge(word, 1, Integer::sum);
    }

    public Map<String, Integer> getCountedWords() {
        return wordCount;
    }

    public List<WordCountOccurrence> getCountedWordsByOccurance() {
        return wordCount.entrySet().stream()
                .sorted(Map.Entry.<String,Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .map(entryToWordCountOccurrence())
                .collect(Collectors.toList());
    }

    private static Function<Map.Entry<String, Integer>, WordCountOccurrence> entryToWordCountOccurrence() {
        return e -> new WordCountOccurrence(e.getKey(), e.getValue());
    }

}
