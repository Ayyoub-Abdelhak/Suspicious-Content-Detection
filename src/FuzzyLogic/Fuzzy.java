package FuzzyLogic;
/**
 * @author Ayyoub Abdelhak
 */
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Fuzzy {

    FIS fis = new FIS();
    Variable tip;

    public Fuzzy() {
        // Load from 'FCL' file
        String fileName = "res/fuzzy.fcl";
        fis = FIS.load(fileName, true);

        // Error while loading?
        if (fis == null) {
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }
    }

    public void showSharts(float normal_prob, float suspicious_prob) {
        // Set inputs
        fis.setVariable("normal_prob", normal_prob);
        fis.setVariable("suspicious_prob", suspicious_prob);

        // Evaluate
        fis.evaluate();

        tip = fis.getVariable("decision");
        // Show 
        JFuzzyChart.get().chart(fis);
        JFuzzyChart.get().chart(tip, tip.getDefuzzifier(), true);
    }

    public String getDecision(float normal_prob, float suspicious_prob) {
        // Set inputs
        fis.setVariable("normal_prob", normal_prob);
        fis.setVariable("suspicious_prob", suspicious_prob);

        // Evaluate
        fis.evaluate();
        tip = fis.getVariable("decision");
        String[] tab = {"normal", "degree1", "degree2", "degree3"};
        for (String decision : tab) {
            if (tip.getMembership(decision) != 0) {
                return decision;
            }
        }
        return null;
    }
}
