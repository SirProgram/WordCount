package com.sirprogram.wordcount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

public class WordCountFileReader {

    private static final Logger LOG = LoggerFactory.getLogger(WordCountFileReader.class);

    public List<String> readFile(String path) {
        File inputFile = new File(path);
        List<String> readLines;

        try {
            readLines = Files.readAllLines(inputFile.toPath());
        } catch (Exception e) {
            LOG.error("Failed to read file: " + path, e);
            return Collections.emptyList();
        }

        return readLines;
    }
}
