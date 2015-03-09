package camcontrols.saving;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document dc = dBuilder.parse(xmlFile);
            //System.out.println("Root element :" + dc.getDocumentElement().getNodeName());
            NodeList nodeList = dc.getElementsByTagName("camera");

            for (int temp = 0; temp < nodeList.getLength(); temp++)
            {

                Node nNode = nodeList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;
                    //System.out.println("First Name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
                    //TODO(Dominik):put nodes into config file gui
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println("Loading XML file failed");
        }

    }
}
