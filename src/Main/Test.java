package Main;

/**
 * @author Ayyoub Abdelhak
 */
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import PreProcessing.Similarity;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/**
 *
 * @author Lemowsky
 */
public class Test {

    public static void main(String[] args) throws GeneralSecurityException, IOException {
//        Path path = Paths.get("res/x.txt");
//        Database db = new Database();
//        db.generateDB();
//        Scanner sc = new Scanner(path, "UTF-8");
//        String text="";
//        while (sc.hasNextLine()) {
//            Scanner rowReader = new Scanner(sc.nextLine());
//            while (rowReader.hasNext()) {
//                String word = rowReader.next().toLowerCase();
//                if(db.vocabulary.get(word)==null){
//                text=text+word+" ";}
//            }
//            rowReader.close();
//            text=text+"\n";
//        }
//        sc.close();
//        PrintStream outfile = new PrintStream("res/xx.txt", "UTF-8");
//        outfile.println(text);
//        outfile.close();

//        Similarity s = new Similarity();
//        double sim = s.similarity("abc", "abcd");
//        System.out.println(sim);
//        if (sim >= 0.75) {
//            System.out.println("Similar");
//        } else {
//            System.out.println("Not Similar");
//        }
//        Translate t = new Translate.Builder(
//                com.google.api.client.googleapis.javanet.GoogleNetHttpTransport.newTrustedTransport(), com.google.api.client.json.gson.GsonFactory.getDefaultInstance(), null)
//                //Need to update this to your App-Name
//                .setApplicationName("My Project")
//                .build();
//        Translate.Translations.List list = t.new Translations().list(
//                Arrays.asList(
//                        //Pass in list of strings to be translated
//                        "Hello World",
//                        "How to use Google Translate from Java"),
//                //Target language   
//                "ES");
//        //Set your API-Key from https://console.developers.google.com/
//        list.setKey("AIzaSyCY65utJT7RfHLgTCxSlMytAIMHH3JjPm0");
//        TranslationsListResponse response = list.execute();
//        for (TranslationsResource tr : response.getTranslations()) {
//            System.out.println(tr.getTranslatedText());
//        }

    }
}
