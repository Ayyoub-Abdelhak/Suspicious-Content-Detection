package PreProcessing;

/**
 * @author Ayyoub Abdelhak
 */
import Main.Tweet;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class StopWords {

    private String processedTweet;
    private String originalTweet;
    private final String tab[] = {",", ".", "!", "?", "#", ";", ":", "[", "]", "{", "}", "*", "&", "%", "/", "\\", "|", "<", ">", "~",
        "(", ")", "^", "-", "_", "+", "'", "\""};

    Stemmer st = new Stemmer();
    Similarity sim = new Similarity();

    public StopWords() {
        processedTweet = "";
        originalTweet = "";
    }

    public void removeStream(String text, ArrayList<Tweet> tweets, HashMap<String, Integer> hashmap1, HashMap<String, Integer> hashmap2, ArrayList<Mapping> mapping) {
        processedTweet = "";
        String[] words = text.split("\\s+");
        for (String w : words) {
            while (begin(tab, w)) {
                w = w.substring(1, w.length());
            }
            while (end(tab, w)) {
                w = w.substring(0, w.length() - 1);
            }
            //we don't process numbers, urls and words existing in the names and stop words databases
            if (hashmap1.get(w) == null && hashmap2.get(w) == null && !w.matches("^-*[0-9,\\.]+$") && !w.startsWith("http")
                    && !w.startsWith("@")) {
                processedTweet = processedTweet + sim.getCorresponding(mapping, w) + " ";
            }
        }
        if (!processedTweet.equals("")) {
            tweets.add(new Tweet(null, text, processedTweet, null, "normal", null, null, null));
        }

    }

    public void removeTestSW(String file, ArrayList<Tweet> tweets, HashMap<String, Integer> hashmap1,
            HashMap<String, Integer> hashmap2, ArrayList<Mapping> mapping) throws IOException {
        Path path = Paths.get(file);
        Scanner sc = new Scanner(path, "UTF-8");
        while (sc.hasNextLine()) {
            processedTweet = "";
            originalTweet = sc.nextLine();
            Scanner rowReader = new Scanner(originalTweet);
            while (rowReader.hasNext()) {
                String word = rowReader.next().toLowerCase();
                while (begin(tab, word)) {
                    word = word.substring(1, word.length());
                }
                while (end(tab, word)) {
                    word = word.substring(0, word.length() - 1);
                }
                //we don't process numbers, urls and words existing in the names and stop words databases
                if (hashmap1.get(word) == null && hashmap2.get(word) == null && !word.matches("^-*[0-9,\\.]+$") && !word.startsWith("http")
                        && !word.startsWith("@")) {
                    processedTweet = processedTweet + sim.getCorresponding(mapping, word) + " ";
                }
            }
            rowReader.close();
            if (!processedTweet.equals("")) {
                String type;
                if (file.startsWith("res/normal") || file.startsWith("res/testNormal")) {
                    type = "normal";
                } else {
                    type = "suspicious";
                }
                tweets.add(new Tweet(null, originalTweet, processedTweet, null, type, null, null, null));
            }
        }
        sc.close();
    }

    public void removeTrainSW(String file, ArrayList<Tweet> tweets) throws IOException {
        ArrayList<String> wordVector = new ArrayList<>();
        String type;
        Path path = Paths.get(file);
        Scanner sc = new Scanner(path, "UTF-8");
        while (sc.hasNextLine()) {
            processedTweet = "";
            originalTweet = sc.nextLine();
            Scanner rowReader = new Scanner(originalTweet);
            while (rowReader.hasNext()) {
                String word = rowReader.next().toLowerCase();
                processedTweet = processedTweet + word + " ";
            }
            rowReader.close();
            if (!processedTweet.equals("")) {
                String[] words = processedTweet.split("\\s+");
                for (String w : words) {
                    String word = st.stem(w);
                    wordVector.add(word);
                }
            }
        }
        sc.close();
        if (file.startsWith("res/normal") || file.startsWith("res/testNormal")) {
            type = "normal";
        } else {
            type = "suspicious";
        }
        tweets.add(new Tweet(null, originalTweet, processedTweet, null, type, null, wordVector, null));
    }

    public void removeSemanticSW(String file, ArrayList<Tweet> tweets) throws IOException {
        Path path = Paths.get(file);
        Scanner sc = new Scanner(path, "UTF-8");
        String[] domains = {"Terrorism", "Domestic Security", "HAZMAT & Nuclear", "Health Concern", "Infrastructure Security",
            "Southwest Border Violence", "Weather/Disaster/Emergency", "Cyber Security"};
        int i = 0;
        while (sc.hasNextLine()) {
            processedTweet = "";
            originalTweet = sc.nextLine();
            Scanner rowReader = new Scanner(originalTweet);
            while (rowReader.hasNext()) {
                String word = rowReader.next().toLowerCase();
                processedTweet = processedTweet + word + " ";
            }
            rowReader.close();
            if (!processedTweet.equals("")) {
                tweets.add(new Tweet(null, originalTweet, processedTweet, null, domains[i], null, null, null));
                i++;
            }
        }
        sc.close();
    }

    private boolean begin(String[] tab, String word) {
        return word.length() > 0 && Arrays.toString(tab).contains(word.substring(0, 1));

    }

    private boolean end(String[] tab, String word) {
        return word.length() > 0 && Arrays.toString(tab).contains(word.substring(word.length() - 1, word.length()));
    }
}
