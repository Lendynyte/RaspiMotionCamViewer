package camcontrols.saving;

import camcontrols.dependencies.MotionCameraInterface;
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
 * @version 0.3
 */
public class XMLCameraHandler
{

    /**
     *
     * http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
     *
     * @param filePath
     * @param MotionCamera
     */
    public void LoadXMLFile(String filePath, MotionCameraInterface MotionCamera)
    {
        try
        {
            System.out.println("Loading file ...");
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document dc = dBuilder.parse(xmlFile);

            NodeList nodeList = dc.getElementsByTagName("camera");

            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node nNode = nodeList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;

                    MotionCamera.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    MotionCamera.setHandle(eElement.getElementsByTagName("handle").item(0).getTextContent());
                    MotionCamera.setConfigPath(eElement.getElementsByTagName("confPath").item(0).getTextContent());
                    MotionCamera.setURL(eElement.getElementsByTagName("URL").item(0).getTextContent());

                    //TODO(Dominik):uncoment after i put right values into save
                   /* MotionCamera.setCamRotation(Integer.parseInt(eElement.getElementsByTagName("rotatinon").item(0).getTextContent()));
                     MotionCamera.setCamWidth(Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()));
                     MotionCamera.setCamHeight(Integer.parseInt(eElement.getElementsByTagName("height").item(0).getTextContent()));
                     MotionCamera.setCamFramerate(Integer.parseInt(eElement.getElementsByTagName("framerate").item(0).getTextContent()));
                     MotionCamera.setCamAutoBrightness(Boolean.parseBoolean(eElement.getElementsByTagName("autoBrightness").item(0).getTextContent()));
                     MotionCamera.setCamBrightness(Integer.parseInt(eElement.getElementsByTagName("brightness").item(0).getTextContent()));
                     MotionCamera.setCamConstrast(Integer.parseInt(eElement.getElementsByTagName("contrast").item(0).getTextContent()));
                     MotionCamera.setCamHue(Integer.parseInt(eElement.getElementsByTagName("hue").item(0).getTextContent()));
                     MotionCamera.setCamSaturation(Integer.parseInt(eElement.getElementsByTagName("saturation").item(0).getTextContent()));
                     MotionCamera.setCamQuality(Integer.parseInt(eElement.getElementsByTagName("quality").item(0).getTextContent()));*/
                    //TODO(Dominik):add rest of options later
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e)
        {
            System.err.println("Loading XML file failed");
        }

    }

    //TODO(Dominik):maybe merge these two together later
    public void createCamSave(MotionCameraInterface MotionCamera)
    {
        createXMLFile(MotionCamera.getXMLSavePath(), MotionCamera.getName(),
                MotionCamera.getHandle(), MotionCamera.getConfigPath(),
                MotionCamera.getURL(), MotionCamera.getCamRotation() + "",
                MotionCamera.getCamWidth() + "", MotionCamera.getCamHeight() + "",
                MotionCamera.getCamFramerate() + "", MotionCamera.isCamAutoBrightness() + "",
                MotionCamera.getCamBrightness() + "", MotionCamera.getCamConstrast() + "",
                MotionCamera.getCamHue() + "", MotionCamera.getCamSaturation() + "",
                MotionCamera.getCamQuality() + "");
    }

    /**
     *
     * @param savePath
     * @param camName
     * @param camHandle
     * @param configPath
     * @param camURL
     * @param camRotation
     * @param camWidth
     * @param camHeight
     * @param camFramerate
     * @param camAutoBrightness
     * @param camBrightness
     * @param camContrast
     * @param camHue
     * @param camSaturation
     * @param camQuality
     */
    public void createXMLFile(String savePath, String camName, String camHandle,
                              String configPath, String camURL, String camRotation, String camWidth,
                              String camHeight, String camFramerate, String camAutoBrightness,
                              String camBrightness, String camContrast, String camHue,
                              String camSaturation, String camQuality)
    {
        try
        {
            System.out.println("Creating XML file ...");

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

            System.out.println("Elements added ...");

            //TODO(Dominik):add rest of options to save
            System.out.println("Creating file ...");

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource dsource = new DOMSource(document);
            StreamResult result = new StreamResult(new File(savePath));
            transformer.transform(dsource, result);

            System.out.println("File saved! ...");

        } catch (ParserConfigurationException | DOMException | TransformerException e)
        {
            System.err.println("Creating XML file failed! ...");
        }
    }
}
