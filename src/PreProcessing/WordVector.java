package PreProcessing;

/**
 * @author Ayyoub Abdelhak
 */
import Main.Tweet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class WordVector {

    ArrayList<String> vectorialSpace = new ArrayList<>();

    public void word2vec(ArrayList<Tweet> tweets, HashMap<String, Integer> vocabulary) {
        for (Tweet tw : tweets) {
            ArrayList<Integer> intWordVector = new ArrayList<>();
            ArrayList<String> wordVector = tw.getWordVector();
            for (String key : vocabulary.keySet()) {
                intWordVector.add(Collections.frequency(wordVector, key));
            }
            tw.setIntWordVector(intWordVector);
        }
    }

    public void word2vec(ArrayList<Tweet> tweets) {
        for (Tweet tw : tweets) {
            ArrayList<String> wordVector = tw.getWordVector();
            for (String word : wordVector) {
                if (!vectorialSpace.contains(word)) {
                    vectorialSpace.add(word);
                }
            }
        }
        for (Tweet tw : tweets) {
            ArrayList<Integer> intWordVector = new ArrayList<>();
            ArrayList<String> wordVector = tw.getWordVector();
            for (String v : vectorialSpace) {
                intWordVector.add(Collections.frequency(wordVector, v));
            }
            tw.setIntWordVector(intWordVector);
        }
    }

    public WordVector() {
    }
}
