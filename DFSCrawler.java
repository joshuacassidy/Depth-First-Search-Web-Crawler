import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DFSCrawler {
        private List<String> visitedSites;
        private Vertex initialSite;

        public DFSCrawler(String initialSite) {
            this.initialSite = new Vertex(initialSite);
            this.visitedSites = new ArrayList();
        }

        public void addNeighbours(Vertex vertex) {
            System.out.println("Visiting URL: " + vertex);
            String html = getWebPageContents(vertex.getName());
            String exp = "https://[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_+.~#?&//=]*)";
            Pattern pattern = Pattern.compile(exp);
            Matcher matcher = pattern.matcher(html);

            while(matcher.find()) {
                String actualURL = matcher.group();
                if(!visitedSites.contains(actualURL)) {
                    visitedSites.add(actualURL);
                    vertex.addAdjacency(new Vertex(actualURL));
                    System.out.println("Website has been found: " + actualURL);
                }
            }
        }

        public void findSites() {
            this.findSites(this.initialSite);
        }

        public void findSites(Vertex root) {
            addNeighbours(root);
            for( Vertex v: root.getAdjacencyList()) {
                if (!v.isVisited()) {
                    v.setVisited(true);
                    findSites(v);
                }
            }

        }

        public String getWebPageContents(String v) {
            String rawHTML = "";

            try {
                URL url = new URL(v);
                BufferedReader line = new BufferedReader(new InputStreamReader(url.openStream()));
                for(String readLine; (readLine = line.readLine()) != null; rawHTML+= readLine);
                line.close();
            } catch (Exception var6) {
                System.out.println("Could not connect to website: " + var6);
            }
            return rawHTML;
        }
    }

