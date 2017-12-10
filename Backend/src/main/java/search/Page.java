package search;

import java.util.ArrayList;

class Page {
    String url;
    ArrayList<Integer> words;

    Page(String url) {
        this.url = url;
        this.words = new ArrayList<>();
    }
}
