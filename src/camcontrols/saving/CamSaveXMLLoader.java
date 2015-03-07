package camcontrols.saving;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

/**
 *
 * @author Dominik Pauli
 * @version 0.01
 */
public class CamSaveXMLLoader
{
//http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
    public void LoadXMLFile(String filePath)
    {
        try
        {
            File xmlFile = new File(filePath);
            //DocumentBuilderFactory dbfactory = new DocumentBuilderFactory.newInstance();
          //  DocumentBuilder dBuilder = dbfactory.newDocumentBuilder();
         //  Document dc = dBuilder.parse(xmlFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
