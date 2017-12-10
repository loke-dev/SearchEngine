package search;

class SearchResult implements Comparable<SearchResult> {
    private double score;
    private Page page;

    SearchResult(double score, Page page) {
        this.score = score;
        this.page = page;
    }

    public int compareTo(SearchResult data) {
        return Double.compare(score, data.getScore());
    }

    double getScore() {
        return score;
    }

    public String toString() {
        return String.format("{\"score\": \"%s\", \"page\": \"%s\"}", String.format("%.4f", score), page.url);
    }
}
