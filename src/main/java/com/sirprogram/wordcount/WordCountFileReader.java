package com.sirprogram.wordcount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordCountFileReader {

    private static final Logger LOG = LoggerFactory.getLogger(WordCountFileReader.class);

    public List<String> readFile(String path) {
        File inputFile = new File(path);
        List<String> readLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                readLines.add(line);
            }
        } catch (Exception e) {
            LOG.error("Failed to read file: " + path, e);
            return Collections.emptyList();
        }

        return readLines;
    }
}
