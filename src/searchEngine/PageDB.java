package searchEngine;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PageDB {
    HashMap wordToId;
    ArrayList pages;
    PageDB() {
        wordToId = new HashMap<String, Integer>();
        pages = new ArrayList<Page>();
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

    void generatePage(String url, File wordsFile) throws IOException {
        try {
            ArrayList<Integer> words = new ArrayList<>();
            BufferedReader win = new BufferedReader(new FileReader(wordsFile));
            String line;
            while ((line = win.readLine()) != null) {
                String[] wlist = line.split(" ");
                for (String w : wlist) {
                    int id = db.getIdForWord(w);
                    words.add(id);
                }
            }
            win.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
