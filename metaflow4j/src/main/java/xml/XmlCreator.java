package xml;

import com.jamesmurty.utils.XMLBuilder;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class XmlCreator {

    public void create() throws ParserConfigurationException, TransformerException {
        XMLBuilder builder = XMLBuilder.create("Projects")
                .e("java-xmlbuilder")
                .a("language", "Java")
                .a("scm","SVN")
                .e("Location")
                .a("type", "URL")
                .t("http://code.google.com/p/java-xmlbuilder/")
                .up()
                .up()
                .e("JetS3t")
                .a("language", "Java")
                .a("scm","CVS")
                .e("Location")
                .a("type", "URL")
                .t("http://jets3t.s3.amazonaws.com/index.html");
        System.out.println( builder.asString());
    }
}
