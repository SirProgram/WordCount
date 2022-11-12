package com.sirprogram.wordcount;

import java.util.Objects;

public record WordCountOccurrence(String word, int occurrence) {

    public WordCountOccurrence {
        Objects.requireNonNull(word, "word in WordCountOccurrence cannot be null");
    }
}
