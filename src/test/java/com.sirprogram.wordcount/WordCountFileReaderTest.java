package com.sirprogram.wordcount;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.List;

class WordCountFileReaderTest {

    ListAppender<ILoggingEvent> listAppender;

    @BeforeEach
    void setUp() {
        Logger logger = (Logger) LoggerFactory.getLogger(WordCountFileReader.class);
        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
    }

    @AfterEach
    void tearDown() {
        listAppender.stop();
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void hasCorrectNumberOfLines() {
        WordCountFileReader reader = new WordCountFileReader();

        ClassLoader classLoader = this.getClass().getClassLoader();
        String filePath = classLoader.getResource("shortText.txt").getFile();
        List<String> strings = reader.readFile(filePath);

        Assertions.assertThat(strings).hasSize(3);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void hasTheCorrectText() {
        WordCountFileReader reader = new WordCountFileReader();

        ClassLoader classLoader = this.getClass().getClassLoader();
        String filePath = classLoader.getResource("shortText.txt").getFile();
        List<String> strings = reader.readFile(filePath);

        Assertions.assertThat(strings.get(0)).isEqualTo("Here comes the first line");
        Assertions.assertThat(strings.get(1)).isEqualTo("This is the second line", strings.get(1));
        Assertions.assertThat(strings.get(2)).isEqualTo("And there's a third line too", strings.get(2));
    }

    @Test
    void filesNotFoundReturnsEmptyList() {
        WordCountFileReader reader = new WordCountFileReader();
        List<String> strings = reader.readFile("filenotfound.txt");

        Assertions.assertThat(strings).isEmpty();
    }

    @Test
    void filesNotFoundLogError() {
        WordCountFileReader reader = new WordCountFileReader();
        reader.readFile("filenotfound.txt");

        Assertions.assertThat(listAppender.list)
                .extracting(ILoggingEvent::getMessage)
                .contains("Failed to read file: filenotfound.txt");
    }

}