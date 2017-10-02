package Classification;
/**
 * @author Ayyoub Abdelhak
 */
import Main.Tweet;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Lemowsky
 */
public class Classify {

    public void learn(ArrayList<String> tweet, Classifier<String, String> bayes, String type) {
        bayes.learn(type, tweet);
        bayes.setMemoryCapacity(500);
    }

    public Collection<Classification<String, String>> getDetails(ArrayList<String> tweet, Classifier<String, String> bayes) {
        return ((BayesClassifier<String, String>) bayes).classifyDetailed(tweet);
    }

    public String getCategory(ArrayList<String> tweet, Classifier<String, String> bayes) {
        return bayes.classify(tweet).getCategory();
    }

    public float getNormalProb(ArrayList<String> tweet, Classifier<String, String> bayes) {
        float prob = 0,prob1=0;
        for (Classification<String, String> c : ((BayesClassifier<String, String>) bayes).classifyDetailed(tweet)) {
            if (c.getCategory().equals("normal")) {
                prob = c.getProbability();
            }
            else{
                prob1=c.getProbability();
            }
        }
        if(prob>prob1)
            return 1-prob-0.15f;
        return prob1+0.15f ;
    }

    public float getSucpicousProb(ArrayList<String> tweet, Classifier<String, String> bayes) {
        float prob = 0,prob1=0;
        for (Classification<String, String> c : ((BayesClassifier<String, String>) bayes).classifyDetailed(tweet)) {
            if (c.getCategory().equals("suspicious")) {
                prob = c.getProbability();
            }
            else{
                prob1=c.getProbability();
            }
        }if(prob>prob1)
            return 1-prob-0.15f;
        return prob1+0.15f;
    }

    public Collection<String> getFeatureSet(ArrayList<String> tweet, Classifier<String, String> bayes) {
        return bayes.classify(tweet).getFeatureset();
    }

    public float recall(ArrayList<Tweet> tweets) {
        int ts = 0, tn = 0, fs = 0, fn = 0;
        float suspiciousRecall, normalRecall;
        for (Tweet t : tweets) {
            if (t.getType().equals("suspicious")) {
                if (t.getDecision().equals(t.getType())) {
                    ts++;
                } else {
                    fs++;
                }
            } else {
                if (t.getDecision().equals(t.getType())) {
                    tn++;
                } else {
                    fn++;
                }
            }
        }
        suspiciousRecall = (float) ts / (ts + fn);
        normalRecall = (float) tn / (tn + fs);
        return (suspiciousRecall + normalRecall) / 2;
    }

    public float precision(ArrayList<Tweet> tweets) {
        int ts = 0, tn = 0, fs = 0, fn = 0;
        float suspiciousPrecision, normalPrecision;
        for (Tweet t : tweets) {
            if (t.getType().equals("suspicious")) {
                if (t.getDecision().equals(t.getType())) {
                    ts++;
                } else {
                    fs++;
                }
            } else {
                if (t.getDecision().equals(t.getType())) {
                    tn++;
                } else {
                    fn++;
                }
            }
        }
        suspiciousPrecision = (float) ts / (ts + fs);
        normalPrecision = (float) tn / (tn + fn);
        return (suspiciousPrecision + normalPrecision) / 2;
    }
    
    public float F_Measure(ArrayList<Tweet> tweets){
        float recall = recall(tweets);
        float precision = precision(tweets);
        return 2*(precision*recall)/(precision+recall);
    }
    
    public float accuracy(ArrayList<Tweet> tweets){
        int ts = 0, tn = 0, fs = 0, fn = 0;
        for (Tweet t : tweets) {
            if (t.getType().equals("suspicious")) {
                if (t.getDecision().equals(t.getType())) {
                    ts++;
                } else {
                    fs++;
                }
            } else {
                if (t.getDecision().equals(t.getType())) {
                    tn++;
                } else {
                    fn++;
                }
            }
        }
        return (float) (ts+tn)/(ts+fs+tn+fn);
    }
}


//public float getNormalProb(ArrayList<String> tweet, Classifier<String, String> bayes) {
//        float prob = 0;
//        for (Classification<String, String> c : ((BayesClassifier<String, String>) bayes).classifyDetailed(tweet)) {
//            if (c.getCategory().equals("normal")) {
//                prob = c.getProbability();
//            }
//        }
//        return prob;
//    }
//
//    public float getSucpicousProb(ArrayList<String> tweet, Classifier<String, String> bayes) {
//        float prob = 0;
//        for (Classification<String, String> c : ((BayesClassifier<String, String>) bayes).classifyDetailed(tweet)) {
//            if (c.getCategory().equals("suspicious")) {
//                prob = c.getProbability();
//            }
//        }
//        return prob;
//    }