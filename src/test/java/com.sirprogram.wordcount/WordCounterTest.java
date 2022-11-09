package com.sirprogram.wordcount;

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

}