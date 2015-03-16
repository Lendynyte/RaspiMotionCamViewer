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

    //TODO(Dominik):it is importatnt to call this AFTER i have items for setting so it is not filled with epty stuff
    //TODO(Dominik):add more options 
    //TODO(Dominik):change this from hardcoded to just be better than this
    public void createXMLSaveFile(String savePath, String cameraName1, String cameraName2, String cameraHandle1, String cameraHandle2,
                                  String configPathc1, String configPathc2, String URLc1, String URLc2, String rotationc1, String rotationc2,
                                  String widthc1, String widthc2, String heightc1, String heightc2, String frameratec1, String frameratec2,
                                  String autoBrightc1, String autoBrightc2, String brightc1, String brightc2, String contc1, String contc2,
                                  String huec1, String huec2, String saturationc1, String saturationc2, String qualityc1, String qualityc2)
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
            name1.appendChild(document.createTextNode(cameraName1));//TODO(Dominik):add from singleton
            cam1.appendChild(name1);

            // name elements
            Element name2 = document.createElement("name");
            name2.appendChild(document.createTextNode(cameraName2));//TODO(Dominik):add from singleton
            cam2.appendChild(name2);

            // handle elements
            Element handle1 = document.createElement("handle");
            handle1.appendChild(document.createTextNode(cameraHandle1));
            cam1.appendChild(handle1);

            Element handle2 = document.createElement("handle");
            handle2.appendChild(document.createTextNode(cameraHandle2));
            cam2.appendChild(handle2);

            // configuration file path elements
            Element cPath1 = document.createElement("confPath");
            cPath1.appendChild(document.createTextNode(configPathc1));
            cam1.appendChild(cPath1);

            Element cPath2 = document.createElement("confPath");
            cPath2.appendChild(document.createTextNode(configPathc2));
            cam2.appendChild(cPath2);

            // URL elements
            Element URL11 = document.createElement("URL");
            URL11.appendChild(document.createTextNode(URLc1));
            cam1.appendChild(URL11);

            Element URL22 = document.createElement("URL");
            URL22.appendChild(document.createTextNode(URLc2));
            cam2.appendChild(URL22);

            // rotation elements
            Element rotation1 = document.createElement("rotation");
            rotation1.appendChild(document.createTextNode(rotationc1));
            cam1.appendChild(rotation1);

            Element rotation2 = document.createElement("rotation");
            rotation2.appendChild(document.createTextNode(rotationc2));
            cam2.appendChild(rotation2);

            // width elements
            Element width1 = document.createElement("width");
            width1.appendChild(document.createTextNode(widthc1));
            cam1.appendChild(width1);

            Element width2 = document.createElement("width");
            width2.appendChild(document.createTextNode(widthc2));
            cam2.appendChild(width2);

            // height elements
            Element height1 = document.createElement("height");
            height1.appendChild(document.createTextNode(heightc1));
            cam1.appendChild(height1);

            Element height2 = document.createElement("height");
            handle2.appendChild(document.createTextNode(heightc2));
            cam2.appendChild(height2);

            // framerate elements
            Element framerate1 = document.createElement("framerate");
            framerate1.appendChild(document.createTextNode(frameratec1));
            cam1.appendChild(framerate1);

            Element framerate2 = document.createElement("framerate");
            framerate2.appendChild(document.createTextNode(frameratec2));
            cam2.appendChild(framerate2);

            // autoBrightness elements
            Element autoBrightness1 = document.createElement("autoBrightness");
            autoBrightness1.appendChild(document.createTextNode(autoBrightc1));
            cam1.appendChild(autoBrightness1);

            Element autoBrightness2 = document.createElement("autoBrightness");
            autoBrightness2.appendChild(document.createTextNode(autoBrightc2));
            cam2.appendChild(autoBrightness2);

            // brightness elements
            Element brightness1 = document.createElement("brightness");
            brightness1.appendChild(document.createTextNode(brightc1));
            cam1.appendChild(brightness1);

            Element brightness2 = document.createElement("brightness");
            brightness2.appendChild(document.createTextNode(brightc2));
            cam2.appendChild(brightness2);

            // contrast elements
            Element contrast1 = document.createElement("contrast");
            contrast1.appendChild(document.createTextNode(contc1));
            cam1.appendChild(contrast1);

            Element contrast2 = document.createElement("contrast");
            contrast2.appendChild(document.createTextNode(contc2));
            cam2.appendChild(contrast2);

            // hue elements
            Element hue1 = document.createElement("hue");
            hue1.appendChild(document.createTextNode(huec1));
            cam1.appendChild(hue1);

            Element hue2 = document.createElement("hue");
            hue2.appendChild(document.createTextNode(huec2));
            cam2.appendChild(hue2);

            // saturation elements
            Element saturation1 = document.createElement("saturation");
            saturation1.appendChild(document.createTextNode(saturationc1));
            cam1.appendChild(saturation1);

            Element saturation2 = document.createElement("saturation");
            saturation2.appendChild(document.createTextNode(saturationc2));
            cam2.appendChild(saturation2);

            // quality elements
            Element quality1 = document.createElement("quality");
            quality1.appendChild(document.createTextNode(qualityc1));
            cam1.appendChild(quality1);

            Element quality2 = document.createElement("quality");
            quality2.appendChild(document.createTextNode(qualityc2));
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
