package Main;

/**
 * @author Ayyoub Abdelhak
 */
import java.util.ArrayList;

public class Tweet {

    private String author;
    private String originalTweet;
    private String processedTweet;
    private String decision;
    private String type;
    private String domain;

    private ArrayList<String> wordVector = new ArrayList<>();
    private ArrayList<Integer> intWordVector = new ArrayList<>();

    private final ArrayList<Tweet> tweets = new ArrayList<>();

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    public Tweet(String author, String originalTweet, String processedTweet, String decision, String type, String domain, ArrayList<String> wordVector, ArrayList<Integer> intWordVector) {
        this.author = author;
        this.originalTweet = originalTweet;
        this.processedTweet = processedTweet;
        this.decision = decision;
        this.type = type;
        this.domain = domain;
        this.wordVector = wordVector;
        this.intWordVector = intWordVector;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Integer> getIntWordVector() {
        return intWordVector;
    }

    public void setIntWordVector(ArrayList<Integer> intWordVector) {
        this.intWordVector = intWordVector;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOriginalTweet() {
        return originalTweet;
    }

    public void setOriginalTweet(String originalTweet) {
        this.originalTweet = originalTweet;
    }

    public String getProcessedTweet() {
        return processedTweet;
    }

    public void setProcessedTweet(String processedTweet) {
        this.processedTweet = processedTweet;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public ArrayList<String> getWordVector() {
        return wordVector;
    }

    public void setWordVector(ArrayList<String> wordVector) {
        this.wordVector = wordVector;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Tweet() {
    }
}
