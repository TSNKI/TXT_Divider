import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by tsnk on 2017/2/17.
 */
public class Writer {

    private static int count = -1;

    public static void write(String title, String content) {

        String fileContent = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?>\n" +
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\"\n" +
                "  \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\n" +
                "\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "  <link href=\"../Styles/style.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                "\n" +
                "  <title>" + title + "</title><!--填入章节名-->\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<h3 style=\"text-align: center; \">" + title + "<br/></h3>\n" +
                content +
                "</body>\n" +
                "</html>\n";

        String fileNum = new DecimalFormat("000").format(++count);

        File file = new File("xhtml/Section" + fileNum + ".xhtml");

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(fileContent);
            bw.close();
        } catch (IOException e) {
            count--;
            e.printStackTrace();
        }
    }

}
