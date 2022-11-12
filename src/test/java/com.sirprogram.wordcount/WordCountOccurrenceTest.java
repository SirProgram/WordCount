package com.sirprogram.wordcount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WordCountOccurrenceTest {

    @Test
    void wordCannotBeNull() {
        Assertions.assertThrows(NullPointerException.class, () -> new WordCountOccurrence(null, 0));
    }
}