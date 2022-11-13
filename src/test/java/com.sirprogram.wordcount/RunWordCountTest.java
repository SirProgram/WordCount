package com.sirprogram.wordcount;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

class RunWordCountTest {

    ListAppender<ILoggingEvent> listAppender;

    @BeforeEach
    void setUp() {
        Logger logger = (Logger) LoggerFactory.getLogger(RunWordCount.class);
        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
    }

    @AfterEach
    void tearDown() {
        listAppender.stop();
    }

    @Test
    void argsNullRequiresFilePath() {
        RunWordCount.main(null);

        Assertions.assertThat(listAppender.list)
                .extracting(ILoggingEvent::getMessage)
                .contains("Please provide filepath to word count as first parameter");
    }

    @Test
    void noArgsRequiresFilePath() {
        RunWordCount.main(new String[]{});

        Assertions.assertThat(listAppender.list)
                .extracting(ILoggingEvent::getMessage)
                .contains("Please provide filepath to word count as first parameter");
    }

    @Test
    void firstParameterAsFilePrintsWordCount() {
        RunWordCount.main(new String[] {"src/test/resources/shortText.txt"});

        String expectedLoggedResult = """
                line:3
                the:2
                And:1
                Here:1
                This:1
                a:1
                comes:1
                first:1
                is:1
                second:1
                there's:1
                third:1
                too:1
                """;

        Assertions.assertThat(listAppender.list)
                .extracting(ILoggingEvent::getMessage)
                .contains(expectedLoggedResult);
    }
}