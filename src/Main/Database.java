package Main;
/**
 * @author Ayyoub Abdelhak
 */
import PreProcessing.Stemmer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Database {

    public Database() {
    }
    Stemmer s = new Stemmer();

    HashMap<String, Integer> names = new HashMap<>();
    HashMap<String, Integer> stopWords = new HashMap<>();
    HashMap<String, Integer> vocabulary = new HashMap<>();

    public void generateDB() throws IOException {
        importData("res/allnames.txt", names);
        importData("res/worldsurnames.txt", names);
        importData("res/stopwords.txt", stopWords);
        importStemedData("res/suspicious.txt", vocabulary);
    }

    public void importData(String file, HashMap<String, Integer> hashmap) throws FileNotFoundException, IOException {
        final Path path = Paths.get(file);
        Scanner sc = new Scanner(path, "UTF-8");
        String word;
        while (sc.hasNextLine()) {
            Scanner rowReader = new Scanner(sc.nextLine());
            while (rowReader.hasNext()) {
                word = rowReader.next().toLowerCase();
                hashmap.put(word, 0);
            }
        }
        sc.close();
    }

    public void importStemedData(String file, HashMap<String, Integer> hashmap) throws FileNotFoundException, IOException {
        final Path path = Paths.get(file);
        Scanner sc = new Scanner(path, "UTF-8");
        String word;
        while (sc.hasNextLine()) {
            Scanner rowReader = new Scanner(sc.nextLine());
            while (rowReader.hasNext()) {
                word = s.stem(rowReader.next().toLowerCase());
                hashmap.put(word, 0);
            }
        }
        sc.close();
    }

    QueryResult getTweets() throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("cwUFEut5HGttwhxo3pAuPsFv7")
                .setOAuthConsumerSecret("Dl9pjk4HkFpAEqtDggt7jWQCIzGU32o4CAOp2shUlKtz0Yfpmw")
                .setOAuthAccessToken("3013580153-6rQRnCSleZ7QbEdxsSWSfVmi7esHP0VZpk1h3vl")
                .setOAuthAccessTokenSecret("II8oBDejHPPO0oYtvp1G0FWS6F9NqLreJbatOZ8kboMTK");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Query q = new Query("attack");
        QueryResult result = twitter.search(q);
        return result;
    }
}
