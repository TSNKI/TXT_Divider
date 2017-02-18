import java.io.*;

/**
 * Created by tsnk on 2017/2/17.
 */
public class Reader {

    private static String currentChapter = "";
    private static String title = "";
    private static String content = "";

    public static void read() {
        File file = new File("src.txt");
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
        }

        while (tmpStr != null) {
            // remove all the spaces.
//            tmpStr = tmpStr.replace(" ", "");
//            tmpStr = tmpStr.replace("　", "");

            if (tmpStr.length() < 3) {

                try {
                    tmpStr = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                continue;
            }

            // line starts with '♂'
            if (tmpStr.charAt(0) == '♂') {

                System.out.println(title);
                Writer.write(title, content);

                tmpStr = tmpStr.substring(1, tmpStr.length() - 1);

                if (!tmpStr.equals(currentChapter)) {
                    currentChapter = tmpStr;
                    System.out.println(currentChapter);
                    Writer.write(currentChapter, "");
                }

                title = "";
                content = "";

                try {
                    tmpStr = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                continue;

            }

            if (tmpStr.charAt(0) == '→') {
                title = tmpStr.substring(1, tmpStr.length() - 1);

                try {
                    tmpStr = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                continue;
            }

            content += "<p>" + tmpStr + "</p>\n";

            try {
                tmpStr = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
