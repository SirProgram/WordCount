package com.sirprogram.wordcount;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WordCounterTest {

    @Test
    void canCountSingleWords() {
        WordCounter wordCounter = new WordCounter();
        wordCounter.countWords(List.of("One Two Three"));

        assertThat(wordCounter.getCountedWords().get("One")).isEqualTo(1);
        assertThat(wordCounter.getCountedWords().get("Two")).isEqualTo(1);
        assertThat(wordCounter.getCountedWords().get("Three")).isEqualTo(1);
    }

    @Test
    void canCountMultipleStrings() {
        WordCounter wordCounter = new WordCounter();
        wordCounter.countWords(List.of("Three", "Three Two", "Three Two One"));

        assertThat(wordCounter.getCountedWords().get("One")).isEqualTo(1);
        assertThat(wordCounter.getCountedWords().get("Two")).isEqualTo(2);
        assertThat(wordCounter.getCountedWords().get("Three")).isEqualTo(3);
    }

    @Test
    void wordsReturnedInOccurrenceOrder() {
        WordCounter wordCounter = new WordCounter();
        wordCounter.countWords(List.of("Three", "Three Two", "Three Two One"));

        assertThat(wordCounter.getCountedWordsByOccurance()).hasSize(3);
        assertThat(wordCounter.getCountedWordsByOccurance()).extracting("word", "occurrence")
                .containsExactly(
                        new Tuple("Three", 3),
                        new Tuple("Two", 2),
                        new Tuple("One", 1));
    }

    @Test
    void sameOccurringWordsReturnedAlphabetically() {
        WordCounter wordCounter = new WordCounter();
        wordCounter.countWords(List.of("Alpha", "Charlie Beta", "Charlie Beta Alpha"));

        List<WordCountOccurrence> countedWordsByOccurrence = wordCounter.getCountedWordsByOccurance();
        assertThat(countedWordsByOccurrence).hasSize(3);
        assertThat(countedWordsByOccurrence).extracting("word", "occurrence")
                .containsExactly(
                        new Tuple("Alpha", 2),
                        new Tuple("Beta", 2),
                        new Tuple("Charlie", 2));
    }
}