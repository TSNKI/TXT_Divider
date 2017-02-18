import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tsnk on 2017/2/17.
 */
public class Reader {

    private static String title = "";
    private static String content = "";

    private static Pattern pattern = Pattern.compile("第\\d{3}章");

    public static void read() {
        File file = new File("../csj.txt");
        FileReader fr = null;

        try {
            fr = new FileReader(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(fr);

        String tmpStr = null;

        try {
            tmpStr = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errors in reading file!");
        }

        while (tmpStr != null) {
            // remove all the spaces.
            tmpStr = tmpStr.replace("　", "");

            if (tmpStr.length() < 2) {

                try {
                    tmpStr = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                continue;
            }

            // new chapter
            Matcher matcher = pattern.matcher(tmpStr);
            if (matcher.find()) {
                System.out.println(tmpStr);
                Writer.write(title, content);
                title = tmpStr;
                content = "";
            } else {
                content += "<p>" + tmpStr + "</p>\n";
            }

            try {
                tmpStr = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
