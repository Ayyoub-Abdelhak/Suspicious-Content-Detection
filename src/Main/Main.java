package Main;
/**
 * @author Ayyoub Abdelhak
 */
import PreProcessing.Mapping;
import Classification.BayesClassifier;
import Classification.Classifier;
import Classification.Classify;
import FuzzyLogic.Fuzzy;
import PreProcessing.Stemmer;
import PreProcessing.StopWords;
import PreProcessing.WordVector;
import java.io.IOException;
import java.util.ArrayList;
import twitter4j.Status;
import twitter4j.TwitterException;

public class Main {

    public static void main(String[] args) throws IOException, TwitterException {

        StopWords sw = new StopWords();
        Database db = new Database();
        Tweet train = new Tweet();
        Tweet test = new Tweet();
        Tweet semantic = new Tweet();
        Stemmer st = new Stemmer();
        Mapping m = new Mapping();
        WordVector wv = new WordVector();
        Classify cl = new Classify();
        Classifier<String, String> bayes = new BayesClassifier<>();
        Classifier<String, String> bayesSemantic = new BayesClassifier<>();
        Fuzzy fuzzy = new Fuzzy();
        
        db.generateDB();
        
        //removing Stop Words
//        for(Status status : db.getTweets().getTweets()){
//            sw.removeStream(status.getText(), test.getTweets(), db.names, db.stopWords, m.getMapping());
//        }
        sw.removeTrainSW("res/normal.txt", train.getTweets());
        sw.removeTrainSW("res/suspicious.txt", train.getTweets());
        sw.removeSemanticSW("res/suspicious.txt", semantic.getTweets());
        sw.removeTestSW("res/testNormal.txt", test.getTweets(), db.names, db.stopWords, m.getMapping());
        sw.removeTestSW("res/testSuspicious.txt", test.getTweets(), db.names, db.stopWords, m.getMapping());

        //Stemming
        st.stem(test.getTweets());
        st.stem(semantic.getTweets());

        for (Tweet t : train.getTweets()) {
            cl.learn(t.getWordVector(), bayes, t.getType());
        }

        for (Tweet t : semantic.getTweets()) {
            cl.learn(t.getWordVector(), bayesSemantic, t.getType());
        }

        for (Tweet t : test.getTweets()) {
            ArrayList<String> wordVector = t.getWordVector();
            float normal_prob = cl.getNormalProb(wordVector, bayes);
            float suspicious_prob = cl.getSucpicousProb(wordVector, bayes);
            if (normal_prob == suspicious_prob) {
                t.setDecision("normal");
            } else {
                t.setDecision(cl.getCategory(wordVector, bayes));
            }
            if (t.getDecision().equals("normal")) {
                t.setDomain("normal");
            } else {
                t.setDomain(cl.getCategory(wordVector, bayesSemantic));
            }
            System.out.println(t.getOriginalTweet() + " ===> " + t.getDecision());
            System.out.println(t.getWordVector());
            System.out.println("Normal Probability = " + normal_prob);
            System.out.println("Suspicious Probability = " + suspicious_prob);
            if (t.getDecision().equals("suspicious")) {
            System.out.println("Fuzzy Decision : " + fuzzy.getDecision(normal_prob, suspicious_prob));                
            }
            System.out.println("Semantic Domain : " + t.getDomain());
            System.out.println();
        }
//        fuzzy.showSharts(0.2f,0.5f);
        System.out.println("Accuracy : " + cl.accuracy(test.getTweets()));
        System.out.println("Precision : " + cl.precision(test.getTweets()));
        System.out.println("Recall : " + cl.recall(test.getTweets()));
        System.out.println("F_Measure : " + cl.F_Measure(test.getTweets()));

//        for(Mapping map : m.getMapping()){
//            System.out.println(map.getEnglish()+" "+map.getDarija()+" "+map.getArabic());
//        }
        //Converting nominal data into numeric one
        //wv.word2vec(tw.getTweets(), db.vocabulary);
    }
}
