package PreProcessing;
/**
 * @author Ayyoub Abdelhak
 */
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Mapping {

    private String english;
    private String darija;
    private String arabic;
    private final ArrayList<Mapping> mapping = new ArrayList<>();
    Stemmer stem = new Stemmer();

    public String getEnglish() {
        return english;
    }

    public ArrayList<Mapping> getMapping() {
        return mapping;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getDarija() {
        return darija;
    }

    public void setDarija(String darija) {
        this.darija = darija;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public Mapping(String english, String darija, String arabic) {
        this.english = english;
        this.darija = darija;
        this.arabic = arabic;
    }

    public Mapping() throws IOException {
        final Path path = Paths.get("res/mapping.txt");
        Scanner sc = new Scanner(path, "UTF-8");
        String e = null, d = null, a = null;
        while (sc.hasNextLine()) {
            Scanner rowReader = new Scanner(sc.nextLine());
            int i = 0;
            while (rowReader.hasNext()) {
                if (i == 0) {
                    e = stem.stem(rowReader.next().toLowerCase());
                    i++;
                } else if (i == 1) {
                    d = rowReader.next();
                    i++;
                } else {
                    a = rowReader.next();
                    i++;
                }
            }
            mapping.add(new Mapping(e, d, a));
        }
        sc.close();
    }
    

}
