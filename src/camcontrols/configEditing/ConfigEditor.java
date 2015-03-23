package camcontrols.configEditing;

import camcontrols.dependencies.MotionCamera1;
import camcontrols.dependencies.MotionCamera2;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Dominik Pauli
 * @version 0.3
 */
public class ConfigEditor
{

    private final ArrayList<String> parsedConfig = new ArrayList<String>();

    //TODO(Dominik):maybe load a default config file?
    //TODO(Dominik):create defaul config file
    /**
     *
     * @param camHandle handle from gui to select camera fir editing
     */
    private void fillConfig(String camHandle, Parser parser)
    {
        this.parsedConfig.clear();

        switch (camHandle)
        {
            case "cam1":
                parser.loadConfLines(parsedConfig, MotionCamera1.getInstance().getConfigPath());
                break;
            case "cam2":
                parser.loadConfLines(parsedConfig, MotionCamera2.getInstance().getConfigPath());
                break;
            default:
                System.err.println("Wrong cam handle detected");
                break;
        }
    }

    /**
     *
     * @param defaultConfPath
     * @param parser
     */
    public void loadDefaultConfigFile(String defaultConfPath, Parser parser)
    {
        this.parsedConfig.clear();

        parser.loadConfLines(this.parsedConfig, defaultConfPath);
        System.out.println("loaded");
    }

    /**
     *
     * @param camHandle
     * @param parser
     */
    public void createConfig(String camHandle, Parser parser)
    {
        switch (camHandle)
        {
            case "cam1":
                //TODO(Dominik):maybe save to temp location after that send using ssh send
                parser.createConfFile(parsedConfig, MotionCamera1.getInstance().getConfigPath());
                break;
            case "cam2":
                parser.createConfFile(parsedConfig, MotionCamera2.getInstance().getConfigPath());
                break;
            default:
                System.err.println("Wrong cam handle detected");
                break;
        }
    }

    private void sendConfig()
    {
        //TODO(Dominik):ssh magic
    }

    //TODO(Dominik): maybe prepare the strings somewhere else or save them like this in camera in the first place
    //TODO(Dominik): or jsut create new variables called String parameters ... think about
    /**
     * This method takes parsed configuration file, seeks lines with the same name as parameters 
     * and replaces them with premade replacement target strings from config String factory
     * 
     * @param targetWidth
     * @param targetHeight
     * @param targetRotation
     * @param targetFramerate
     * @param targetAutoBright
     * @param targetBrightness
     * @param targetContrast
     * @param targetHue
     * @param targetSaturation
     * @param targetQuality
     * @param parser
     */
    public void editConfigList(String targetWidth, String targetHeight,
            String targetRotation, String targetFramerate, String targetAutoBright,
            String targetBrightness, String targetContrast, String targetHue,
            String targetSaturation, String targetQuality, Parser parser)
    {
        Iterator<String> confIterator = this.parsedConfig.iterator();
        
        //TODO(Dominik):rewrite foreach have to use iterator
        //TODO(Dominik): http://stackoverflow.com/questions/3184883/concurrentmodificationexception-for-arraylist
        for (String line : parsedConfig)
        {
            if (line.contains("width"))
            {
                parser.rewriteLine(parsedConfig, "width " + targetWidth, parsedConfig.indexOf(line));
            }
            if (line.contains("height"))
            {
                parser.rewriteLine(parsedConfig, "height " + targetHeight, parsedConfig.indexOf(line));
            }
            if (line.contains("rotate"))
            {
                parser.rewriteLine(parsedConfig, "rotate " + targetRotation, parsedConfig.indexOf(line));
            }
            if (line.contains("framerate"))
            {
                parser.rewriteLine(parsedConfig, "framerate " + targetFramerate, parsedConfig.indexOf(line));
            }
            if (line.contains("auto_brightness"))
            {
                parser.rewriteLine(parsedConfig, "auto_brightness " + targetAutoBright, parsedConfig.indexOf(line));
            }
            if (line.contains("brightness") && !line.contains("auto_brightness"))
            {
                parser.rewriteLine(parsedConfig, "brightness " + targetBrightness, parsedConfig.indexOf(line));
            }
            if (line.contains("contrast"))
            {
                parser.rewriteLine(parsedConfig, "contrast " + targetContrast, parsedConfig.indexOf(line));
            }
            if (line.contains("hue"))
            {
                parser.rewriteLine(parsedConfig, "hue " + targetHue, parsedConfig.indexOf(line));
            }
            if (line.contains("saturation"))
            {
                parser.rewriteLine(parsedConfig, "saturation " + targetSaturation, parsedConfig.indexOf(line));
            }
            if (line.contains("quality") && !line.contains("stream_quality"))
            {
                parser.rewriteLine(parsedConfig, "quality " + targetQuality, parsedConfig.indexOf(line));
            }
        }
    }
}
