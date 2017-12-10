package search;

import java.util.ArrayList;
import java.util.Collections;

class Query {
    private PageDB db;

    Query(PageDB db) {
        this.db = db;
    }

    private double getCountFrequencyScore(Page page, String query) {
        double score = 0.0;
        String[] queries = query.split(" ");
        for (String word : queries) {
            for (int id : page.words) {
                if (id == db.getIdForWord(word)) {
                    score++;
                }
            }
        }
        return score;
    }

    private double getCountLocationScore(Page page, String query) {
        double score = 0.0;
        String[] queries = query.split(" ");
        for (String word : queries) {
            for (int i = 0; i < page.words.size(); i++) {
                if (page.words.get(i) == db.getIdForWord(word)) {
                    score += i;
                } else {
                    score += 100000;
                }
            }
        }
        return score;
    }

    ArrayList<SearchResult> query(String query) {
        ArrayList<SearchResult> result = new ArrayList<>();

        double[] content = new double[db.getPages().size()];
        double[] location = new double[db.getPages().size()];

        for (int i = 0; i < db.getPages().size(); i++) {
            Page page = db.getPages().get(i);
            content[i] = getCountFrequencyScore(page, query);
            location[i] = getCountLocationScore(page, query);
        }
        content = normalizeScores(content, false);
        location = normalizeScores(location, true);

        for (int i = 0; i < db.getPages().size(); i++) {
            Page p = db.getPages().get(i);
            double score = 1.0 * content[i] + 0.5 * location[i];
            result.add(new SearchResult(score, p));
        }
        Collections.sort(result);
        Collections.reverse(result);

        return result;
    }

    private double[] normalizeScores(double[] scores, boolean smallIsBetter) {
        if (smallIsBetter) {
            double min = Double.MAX_VALUE;
            for (double s : scores) {
                if (s < min) {
                    min = s;
                }
            }
            for (int i = 0; i < scores.length; i++) {
                scores[i] = min / Math.max(scores[i], 0.00001);
            }
            return scores;
        }
        else {
            double max = Double.MIN_VALUE;
            for (double s : scores) {
                if (s > max) {
                    max = s;
                }
            }
            if (max == 0.0) {
                max = 0.00001;
            }
            for (int i = 0; i < scores.length; i++) {
                scores[i] = scores[i] / max;
            }
            return scores;
        }
    }
}
