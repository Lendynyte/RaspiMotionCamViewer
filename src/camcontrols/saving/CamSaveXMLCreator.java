package camcontrols.saving;

import java.io.File;
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

/**
 *
 * @author Dominik Pauli
 * @version 0.1 from
 * http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
 */
public class CamSaveXMLCreator
{

    public void createXMLSaveFile(String savePath)
    {
        try
        {
            //TODO(Dominik):Hardcoded structure for now make better later
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document document = docBuilder.newDocument();
            Element root = document.createElement("camSystem");
            document.appendChild(root);

            // cam 1 element
            Element cam1 = document.createElement("cam");
            root.appendChild(cam1);
            cam1.setAttribute("id", "1");

            //cam 2 element
            Element cam2 = document.createElement("cam");
            root.appendChild(cam2);
            cam2.setAttribute("id", "2");

            // name elements
            Element name1 = document.createElement("name");
            name1.appendChild(document.createTextNode("cam1name"));//TODO(Dominik):add from singleton
            cam1.appendChild(name1);

            // name elements
            Element name2 = document.createElement("name");
            name2.appendChild(document.createTextNode("cam2name"));//TODO(Dominik):add from singleton
            cam2.appendChild(name2);
            
            // handle elements
            Element handle1 = document.createElement("handle");
            handle1.appendChild(document.createTextNode("CAM1"));
            cam1.appendChild(handle1);

            Element handle2 = document.createElement("handle");
            handle2.appendChild(document.createTextNode("CAM2"));
            cam2.appendChild(handle2);

            // configuration file path elements
            Element cPath1 = document.createElement("confPath");
            cPath1.appendChild(document.createTextNode("confPath1"));
            cam1.appendChild(cPath1);

            Element cPath2 = document.createElement("confPath");
            cPath2.appendChild(document.createTextNode("confPath2"));
            cam2.appendChild(cPath2);
            
            // URL elements
            Element URL1 = document.createElement("URL");
            URL1.appendChild(document.createTextNode("URL1"));
            cam1.appendChild(URL1);

            Element URL2 = document.createElement("URL");
            URL2.appendChild(document.createTextNode("URL2"));
            cam2.appendChild(URL2);
            
            // rotation elements
            Element rotation1 = document.createElement("rotation");
            rotation1.appendChild(document.createTextNode("rotation1"));
            cam1.appendChild(rotation1);

            Element rotation2 = document.createElement("rotation");
            rotation2.appendChild(document.createTextNode("rotation2"));
            cam2.appendChild(rotation2);
            
            // width elements
            Element width1 = document.createElement("width");
            width1.appendChild(document.createTextNode("width1"));
            cam1.appendChild(width1);

            Element width2 = document.createElement("width");
            width2.appendChild(document.createTextNode("width2"));
            cam2.appendChild(width2);
            
            // height elements
            Element height1 = document.createElement("height");
            height1.appendChild(document.createTextNode("height1"));
            cam1.appendChild(height1);

            Element height2 = document.createElement("height");
            handle2.appendChild(document.createTextNode("height2"));
            cam2.appendChild(height2);
            
            // framerate elements
            Element framerate1 = document.createElement("framerate");
            framerate1.appendChild(document.createTextNode("framerate1"));
            cam1.appendChild(framerate1);

            Element framerate2 = document.createElement("framerate");
            framerate2.appendChild(document.createTextNode("framerate2"));
            cam2.appendChild(framerate2);
            
            // autoBrightness elements
            Element autoBrightness1 = document.createElement("autoBrightness");
            autoBrightness1.appendChild(document.createTextNode("AutoBrightness1"));
            cam1.appendChild(autoBrightness1);

            Element autoBrightness2 = document.createElement("autoBrightness");
            autoBrightness2.appendChild(document.createTextNode("AutoBrightness2"));
            cam2.appendChild(autoBrightness2);
            
            // brightness elements
            Element brightness1 = document.createElement("brightness");
            brightness1.appendChild(document.createTextNode("brightness1"));
            cam1.appendChild(brightness1);

            Element brightness2 = document.createElement("brightness");
            brightness2.appendChild(document.createTextNode("brightness2"));
            cam2.appendChild(brightness2);
            
            // contrast elements
            Element contrast1 = document.createElement("contrast");
            contrast1.appendChild(document.createTextNode("contrast1"));
            cam1.appendChild(contrast1);

            Element contrast2 = document.createElement("contrast");
            contrast2.appendChild(document.createTextNode("contrast2"));
            cam2.appendChild(contrast2);
            
            // hue elements
            Element hue1 = document.createElement("hue");
            hue1.appendChild(document.createTextNode("hue1"));
            cam1.appendChild(hue1);

            Element hue2 = document.createElement("hue");
            hue2.appendChild(document.createTextNode("hue2"));
            cam2.appendChild(hue2);
            
            // saturation elements
            Element saturation1 = document.createElement("saturation");
            saturation1.appendChild(document.createTextNode("saturation1"));
            cam1.appendChild(saturation1);

            Element saturation2 = document.createElement("saturation");
            saturation2.appendChild(document.createTextNode("saturation2"));
            cam2.appendChild(saturation2);
            
            // quality elements
            Element quality1 = document.createElement("quality");
            quality1.appendChild(document.createTextNode("quality1"));
            cam1.appendChild(quality1);

            Element quality2 = document.createElement("quality");
            quality2.appendChild(document.createTextNode("quality2"));
            cam2.appendChild(quality2);
            
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource dsource = new DOMSource(document);
            StreamResult result = new StreamResult(new File(savePath));
            transformer.transform(dsource, result);

            System.out.println("File saved!");//TODO(Dominik):notify user in gui

        } catch (ParserConfigurationException | DOMException | TransformerException e)
        {
            e.printStackTrace();
            System.err.println("Creating XML file failed");
        }
    }
}
