package com.sirprogram.wordcount;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WordCounterTest {

    @Test
    void canCountSingleWords() {
        WordCounter wordCounter = new WordCounter();
        wordCounter.countWords(List.of("one two three"));

        assertThat(wordCounter.getCountedWords().get("one")).isEqualTo(1);
        assertThat(wordCounter.getCountedWords().get("two")).isEqualTo(1);
        assertThat(wordCounter.getCountedWords().get("three")).isEqualTo(1);
    }

    @Test
    void canCountMultipleStrings() {
        WordCounter wordCounter = new WordCounter();
        wordCounter.countWords(List.of("three", "three two", "three two one"));

        assertThat(wordCounter.getCountedWords().get("one")).isEqualTo(1);
        assertThat(wordCounter.getCountedWords().get("two")).isEqualTo(2);
        assertThat(wordCounter.getCountedWords().get("three")).isEqualTo(3);
    }

    @Test
    void wordsReturnedInOccurrenceOrder() {
        WordCounter wordCounter = new WordCounter();
        wordCounter.countWords(List.of("three", "three two", "three two one"));

        assertThat(wordCounter.getCountedWordsByOccurance()).hasSize(3);
        assertThat(wordCounter.getCountedWordsByOccurance()).extracting("word", "occurrence")
                .containsExactly(
                        new Tuple("three", 3),
                        new Tuple("two", 2),
                        new Tuple("one", 1));
    }

    @Test
    void sameOccurringWordsReturnedAlphabetically() {
        WordCounter wordCounter = new WordCounter();
        wordCounter.countWords(List.of("alpha", "charlie beta", "charlie beta alpha"));

        List<WordCountOccurrence> countedWordsByOccurrence = wordCounter.getCountedWordsByOccurance();
        assertThat(countedWordsByOccurrence).hasSize(3);
        assertThat(countedWordsByOccurrence).extracting("word", "occurrence")
                .containsExactly(
                        new Tuple("alpha", 2),
                        new Tuple("beta", 2),
                        new Tuple("charlie", 2));
    }

    @Test
    void removesPunctuation() {
        WordCounter wordCounter = new WordCounter();
        wordCounter.countWords(List.of("this \"is\" a sentence!", "(and) this: is {another} sentence.", "Is it?", "and another;"));

        List<WordCountOccurrence> countedWordsByOccurrence = wordCounter.getCountedWordsByOccurance();
        assertThat(countedWordsByOccurrence).hasSize(7);
        assertThat(countedWordsByOccurrence).extracting("word", "occurrence")
                .containsExactly(
                        new Tuple("is", 3),
                        new Tuple("and", 2),
                        new Tuple("another", 2),
                        new Tuple("sentence", 2),
                        new Tuple("this", 2),
                        new Tuple("a", 1),
                        new Tuple("it", 1));
    }

    @Test
    void makesLowerCase() {
        WordCounter wordCounter = new WordCounter();
        wordCounter.countWords(List.of("This is a sentence.", "And THIS is another sentence."));

        List<WordCountOccurrence> countedWordsByOccurrence = wordCounter.getCountedWordsByOccurance();
        assertThat(countedWordsByOccurrence).hasSize(6);
        assertThat(countedWordsByOccurrence).extracting("word", "occurrence")
                .containsExactly(
                        new Tuple("is", 2),
                        new Tuple("sentence", 2),
                        new Tuple("this", 2),
                        new Tuple("a", 1),
                        new Tuple("and", 1),
                        new Tuple("another", 1));
    }
}