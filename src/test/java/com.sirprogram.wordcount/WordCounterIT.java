package com.sirprogram.wordcount;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WordCounterIT {

    @Test
    @SuppressWarnings("ConstantConditions")
    void filesWithSameContentsDifferentOrderMatch() {
        WordCountFileReader reader = new WordCountFileReader();
        ClassLoader classLoader = this.getClass().getClassLoader();
        WordCounter lorem1counter = new WordCounter();
        WordCounter lorem2counter = new WordCounter();

        String lorem1filePath = classLoader.getResource("loremipsum1.txt").getFile();
        lorem1counter.countWords(reader.readFile(lorem1filePath));
        String lorem2filePath = classLoader.getResource("loremipsum2.txt").getFile();
        lorem2counter.countWords(reader.readFile(lorem2filePath));

        assertThat(lorem1counter.getCountedWordsByOccurance())
                .containsExactlyElementsOf(lorem2counter.getCountedWordsByOccurance());
    }
}
