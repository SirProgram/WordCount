package com.sirprogram.wordcount;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class WordCounter {

    private final Map<String, Integer> wordCount = new HashMap<>();

    public void countWords(List<String> inputStrings) {
        inputStrings.stream()
                .map(s -> s.split(" "))
                .flatMap(Stream::of)
                .forEach(this::countSingleWord);
    }

    private void countSingleWord(String word) {
        wordCount.merge(word, 1, Integer::sum);
    }

    public Map<String, Integer> getCountedWords() {
        return wordCount;
    }

    public List<WordCountOccurrence> getCountedWordsByOccurance() {
        return Collections.emptyList();
    }

}
