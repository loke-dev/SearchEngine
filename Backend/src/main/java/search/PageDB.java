package search;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;

class PageDB {
    private HashMap<String, Integer> wordToId;
    private ArrayList<Page> pages;

    PageDB() {
        wordToId = new HashMap<>();
        pages = new ArrayList<>();
    }

    ArrayList<Page> getPages() {
        return pages;
    }

    int getIdForWord(String word) {
        if (wordToId.containsKey(word)) {
            return wordToId.get(word);
        } else {
            int id = wordToId.size();
            wordToId.put(word, id);
            return id;
        }
    }

    private void generatePage(String pageName, File wordsFile) throws IOException {
        Page page = new Page("/wiki/" + pageName);

        ArrayList<String> lines = (ArrayList<String>) Files
            .readAllLines(Paths.get(wordsFile.toURI()), Charset.defaultCharset());
        lines
            .forEach(line -> Arrays
                .stream(line.split(" "))
                .forEach(word -> page.words.add(getIdForWord(word))));

        pages.add(page);
    }

    void generate(String url) throws IOException {
        Files.walk(Paths.get(url))
                .filter(Files::isRegularFile)
                .forEach(file -> {
                    try {
                        generatePage(file.getFileName().toString(), file.toFile());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
