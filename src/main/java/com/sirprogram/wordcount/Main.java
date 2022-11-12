package com.sirprogram.wordcount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String filePath = getFilePathArgument(args);
        List<WordCountOccurrence> wordCountOccurrences = countWordsFromFilePath(filePath);
        String printedText = printToConsole(wordCountOccurrences);
        System.out.println(printedText);
        System.exit(0);
    }

    private static String getFilePathArgument(String[] args) {
        if (args.length == 0) {
            LOG.info("provide filepath to word count as first parameter");
            System.exit(1);
        }

        String argFromCommandLine = args[0];
        LOG.debug("read args as: " + argFromCommandLine);
        return argFromCommandLine;
    }

    public static List<WordCountOccurrence> countWordsFromFilePath(String filePath) {
        WordCounter wordCounter = new WordCounter();
        List<String> readStrings = new WordCountFileReader().readFile(filePath);
        wordCounter.countWords(readStrings);
        return wordCounter.getCountedWordsByOccurance();
    }

    public static String printToConsole(List<WordCountOccurrence> occurrences) {
        StringBuilder printedString = new StringBuilder();
        occurrences.forEach(o -> printedString.append(o.word()).append(":").append(o.occurrence()).append("\n"));
        String completeOccurrences = printedString.toString();
        LOG.info(completeOccurrences);
        return completeOccurrences;
    }

}
