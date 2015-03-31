package camcontrols.saving;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Dominik Pauli
 * @version 0.1
 */
public class XMLIo
{

    ////http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
    public void LoadXMLFile(String filePath)
    {
        try
        {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document dc = dBuilder.parse(xmlFile);
            System.out.println("Root element :" + dc.getDocumentElement().getNodeName());
            NodeList nodeList = dc.getElementsByTagName("camera");

            for (int temp = 0; temp < nodeList.getLength(); temp++)
            {

                Node nNode = nodeList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;
                    //System.out.println("First Name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
                    //TODO(Dominik):put nodes into config file gui
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e)
        {
            System.err.println("Loading XML file failed");
        }

    }

    public void createXMLFile(String savePath, String camName, String camHandle,
                              String configPath, String camURL, String camRotation, String camWidth,
                              String camHeight, String camFramerate, String camAutoBrightness,
                              String camBrightness, String camContrast, String camHue,
                              String camSaturation, String camQuality)
    {
        try
        {
            //TODO(Dominik):Hardcoded structure for now make better later
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document document = docBuilder.newDocument();
            Element root = document.createElement("camera");
            document.appendChild(root);

            // name element
            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(camName));
            root.appendChild(name);

            // handle element
            Element handle = document.createElement("handle");
            handle.appendChild(document.createTextNode(camHandle));
            root.appendChild(handle);

            // configuration file path element
            Element cPath = document.createElement("confPath");
            cPath.appendChild(document.createTextNode(configPath));
            root.appendChild(cPath);

            // URL element
            Element URL = document.createElement("URL");
            URL.appendChild(document.createTextNode(camURL));
            root.appendChild(URL);
            
            // rotation element
            Element rotation = document.createElement("rotation");
            rotation.appendChild(document.createTextNode(camRotation));
            root.appendChild(rotation);

            // width element
            Element width = document.createElement("width");
            width.appendChild(document.createTextNode(camWidth));
            root.appendChild(width);
            
            // height element
            Element height = document.createElement("height");
            height.appendChild(document.createTextNode(camHeight));
            root.appendChild(height);
            
            // framerate element
            Element framerate = document.createElement("framerate");
            framerate.appendChild(document.createTextNode(camFramerate));
            root.appendChild(framerate);

            // autoBrightness element
            Element autoBrightness = document.createElement("autoBrightness");
            autoBrightness.appendChild(document.createTextNode(camAutoBrightness));
            root.appendChild(autoBrightness);

            // brightness element
            Element brightness = document.createElement("brightness");
            brightness.appendChild(document.createTextNode(camBrightness));
            root.appendChild(brightness);
            
            // contrast element
            Element contrast = document.createElement("contrast");
            contrast.appendChild(document.createTextNode(camContrast));
            root.appendChild(contrast);
            
            // hue element
            Element hue = document.createElement("hue");
            hue.appendChild(document.createTextNode(camHue));
            root.appendChild(hue);

            // saturation element
            Element saturation = document.createElement("saturation");
            saturation.appendChild(document.createTextNode(camSaturation));
            root.appendChild(saturation);
            
            // quality element
            Element quality = document.createElement("quality");
            quality.appendChild(document.createTextNode(camQuality));
            root.appendChild(quality);
            
            //TODO(Dominik):add rest of options to save

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource dsource = new DOMSource(document);
            StreamResult result = new StreamResult(new File(savePath));
            transformer.transform(dsource, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException | DOMException | TransformerException e)
        {
            System.err.println("Creating XML file failed");
        }
    }
}
