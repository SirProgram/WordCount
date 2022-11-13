package com.sirprogram.wordcount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class RunWordCount {

    private static final Logger LOG = LoggerFactory.getLogger(RunWordCount.class);

    public static void main(String[] args) {
        Optional<String> optionalFirstArg = getFilePathArgument(args);
        if (optionalFirstArg.isPresent()) {
            String filePath = optionalFirstArg.get();
            List<WordCountOccurrence> wordCountOccurrences = countWordsFromFilePath(filePath);
            String wordOccurrenceMessage = generateConsolePrint(wordCountOccurrences);
            LOG.info(wordOccurrenceMessage);
        } else {
            LOG.info("Please provide filepath to word count as first parameter");
        }
    }

    private static Optional<String> getFilePathArgument(String[] args) {
        if (args == null || args.length == 0) {
            LOG.debug("args not provided");
            return Optional.empty();
        }

        String argFromCommandLine = args[0];
        LOG.debug("read args as: " + argFromCommandLine);
        return Optional.of(argFromCommandLine);
    }

    public static List<WordCountOccurrence> countWordsFromFilePath(String filePath) {
        WordCounter wordCounter = new WordCounter();
        List<String> readStrings = new WordCountFileReader().readFile(filePath);
        wordCounter.countWords(readStrings);
        return wordCounter.getCountedWordsByOccurance();
    }

    public static String generateConsolePrint(List<WordCountOccurrence> occurrences) {
        StringBuilder printedString = new StringBuilder();
        occurrences.forEach(o -> printedString.append(o.word()).append(":").append(o.occurrence()).append("\n"));
        return printedString.toString();
    }

}
