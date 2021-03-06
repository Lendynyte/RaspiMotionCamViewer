package camcontrols.saving;

import camcontrols.dependencies.ApplicationVariables;
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
 * @version 0.4
 */
public class XMLCameraHandler
{

    /**
     * This method chcek path for XML save for application
     *
     * @param path expected save path
     * @return true if save exists false if it does not exist
     */
    public boolean checkForXMLSave(String path)
    {
        File file = new File(path);
        if (file.length() > 0)
        {
            return file.exists();
        }
        else
        {
            return false;
        }
    }

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
                    MotionCamera.setCamWidth(Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()));
                    MotionCamera.setCamHeight(Integer.parseInt(eElement.getElementsByTagName("height").item(0).getTextContent()));
                    MotionCamera.setCamFramerate(Integer.parseInt(eElement.getElementsByTagName("framerate").item(0).getTextContent()));
                    MotionCamera.setCamAutoBrightness(Boolean.parseBoolean(eElement.getElementsByTagName("autoBrightness").item(0).getTextContent()));
                    MotionCamera.setCamBrightness(Integer.parseInt(eElement.getElementsByTagName("brightness").item(0).getTextContent()));
                    MotionCamera.setCamConstrast(Integer.parseInt(eElement.getElementsByTagName("contrast").item(0).getTextContent()));
                    MotionCamera.setCamHue(Integer.parseInt(eElement.getElementsByTagName("hue").item(0).getTextContent()));
                    MotionCamera.setCamSaturation(Integer.parseInt(eElement.getElementsByTagName("saturation").item(0).getTextContent()));
                    MotionCamera.setCamQuality(Integer.parseInt(eElement.getElementsByTagName("quality").item(0).getTextContent()));

                    //NOT CURRENTLY USED PARAMETER
                    //MotionCamera.setCamRotation(Integer.parseInt(eElement.getElementsByTagName("rotatinon").item(0).getTextContent()));
                }
            }
            System.out.println("File loaded ...");
        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            System.err.println("Loading XML file failed");
        }

    }

    /**
     *
     */
    public void loadApplicationSave()
    {
        new Thread()
        {

            @Override
            public void run()
            {
                try
                {
                    File xmlFile;
                    System.out.println("Loading file ...");
                    // WINDOWS
                    if (ApplicationVariables.getInstance().getOperatingSystem() == 1)
                    {
                        xmlFile = new File("C://CamControls/src/appSave.xml");
                    }

                    //LINUX MAINLY MADE FOR RASPBERRY PI USER PI
                    else if (ApplicationVariables.getInstance().getOperatingSystem() == 2)
                    {
                        xmlFile = new File("/home/pi/CamControls/src/appSave.xml");
                    }

                    //UNKNOWN OPERATING SYSTEM
                    else
                    {
                        System.err.println("Cannot find install directory ...");
                        return;
                    }

                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document dc = dBuilder.parse(xmlFile);

                    NodeList nodeList = dc.getElementsByTagName("application");

                    for (int i = 0; i < nodeList.getLength(); i++)
                    {
                        Node nNode = nodeList.item(i);

                        if (nNode.getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element eElement = (Element) nNode;

                            ApplicationVariables.getInstance().setXmlSaveDirectoryPath(eElement.getElementsByTagName("savePath").item(0).getTextContent());

                        }
                    }
                }
                catch (ParserConfigurationException | SAXException | IOException e)
                {
                    System.err.println("Loading XML file failed");
                }
            }
        }.start();
    }

    /**
     * This method is used to create xml save of application variables manily
     * for future use
     */
    public void createApplicationSave()
    {
        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    System.out.println("Creating XML file ...");

                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                    Document document = docBuilder.newDocument();
                    Element root = document.createElement("application");
                    document.appendChild(root);

                    // savePath element
                    Element savePath = document.createElement("savePath");
                    savePath.appendChild(document.createTextNode(ApplicationVariables.getInstance().getXmlSaveDirectoryPath()));
                    root.appendChild(savePath);

                    System.out.println("Elements added ...");

                    System.out.println("Creating file ...");

                    // write the content into xml file
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource dsource = new DOMSource(document);

                    StreamResult result;

                    //WINDOWS
                    if (ApplicationVariables.getInstance().getOperatingSystem() == 1)
                    {
                        result = new StreamResult(new File("C://CamControls/src/appSave.xml"));
                    }

                    //LINUX MAINLY MADE FOR RASPBERRY PI USER PI
                    else if (ApplicationVariables.getInstance().getOperatingSystem() == 2)
                    {
                        result = new StreamResult(new File("/home/pi/CamControls/src/appSave.xml"));
                    }

                    //UNKNOWN OPERATING SYSTEM
                    else
                    {
                        System.err.println("Cannot find install directory ...");
                        return;
                    }

                    transformer.transform(dsource, result);

                    System.out.println("File saved! ...");

                }
                catch (ParserConfigurationException | DOMException | TransformerException e)
                {
                    System.err.println("Creating XML file failed! ...");
                }
            }
        }.start();
    }

    /**
     *
     * @param MotionCamera
     * @param savePath
     */
    public void createCamSave(MotionCameraInterface MotionCamera, String savePath)
    {
        //TODO(Dominik): rewrite handle url config path autobrightness
        createXMLFile(savePath, MotionCamera.getName(),
                "handle", MotionCamera.getConfigPath(),
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
        new Thread()
        {

            @Override
            public void run()
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

                }
                catch (ParserConfigurationException | DOMException | TransformerException e)
                {
                    System.err.println("Creating XML file failed! ...");
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //TODO(Dominik): rewrite with right save names
    /**
     *
     * @param motionCamera
     * @param camId
     */
    public void loadCameraURLs(MotionCameraInterface motionCamera, int camId)
    {
        try
        {
            File xmlFile = null;
            System.out.println("Loading file ...");

            // WINDOWS
            if (ApplicationVariables.getInstance().getOperatingSystem() == 1)
            {
                switch (camId)
                {
                    case 1:
                    {
                        xmlFile = new File("C://CamControls/src/cam1Save.xml");
                        break;
                    }
                    case 2:
                    {
                        xmlFile = new File("C://CamControls/src/cam2Save.xml");
                        break;
                    }
                }
            }

            //LINUX MAINLY MADE FOR RASPBERRY PI USER PI
            else if (ApplicationVariables.getInstance().getOperatingSystem() == 2)
            {
                switch (camId)
                {
                    case 1:
                    {
                        xmlFile = new File("/home/pi/CamControls/src/cam1Save.xml");
                        break;
                    }
                    case 2:
                    {
                        xmlFile = new File("/home/pi/CamControls/src/cam2Save.xml");
                        break;
                    }
                }
            }

            //UNKNOWN OPERATING SYSTEM
            else
            {
                System.err.println("Cannot find install directory ...");
                return;
            }

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

                    motionCamera.setURL(eElement.getElementsByTagName("URL").item(0).getTextContent());
                }
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            System.err.println("Loading XML file failed");
        }
    }
}
