package search;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
public class SearchEngineController {
    @CrossOrigin(origins = "http://localhost:8082")
    @RequestMapping("/")
    public String search(@RequestParam(value="query", defaultValue="") String query) throws IOException, URISyntaxException {
        PageDB pdb = new PageDB();
        pdb.generate("src/main/resources/data/Words");
        Query q = new Query(pdb);
        ArrayList<SearchResult> list = q.query(query);
        if (list.size() <= 0) return "Nothing to show!";
        StringBuilder sb = new StringBuilder();
        sb.append("{ \"payload\": [");
        for (int i = 0; i <= 25; i++) {
            SearchResult sr = list.get(i);
            sb.append(sr.toString());
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append("]}");
        return sb.toString();
    }
}
