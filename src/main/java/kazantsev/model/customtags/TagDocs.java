package kazantsev.model.customtags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

public class TagDocs extends SimpleTagSupport {

    StringWriter sw = new StringWriter();

    public void doTag() throws JspException, IOException {
        getJspBody().invoke(sw);
        String locale = sw.toString() == "" ? "ru" : sw.toString();
        FileReader fileReader = new FileReader("src/main/resources/docs/library_rules_" + locale + ".txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line + "<br>");
        }
        getJspContext().getOut().println(stringBuilder.toString());
        fileReader.close();
        bufferedReader.close();
    }

}
