package camcontrols.configEditing;

import camcontrols.dependencies.MotionCamera1;
import camcontrols.dependencies.MotionCamera2;
import java.util.ArrayList;

/**
 *
 * @author Dominik Pauli
 * @version 0.3
 */
public class ConfigEditor
{

    //create parser instance
    private Parser parser;

    private final ArrayList<String> parsedConfig = new ArrayList<String>();

    //TODO(Dominik):maybe load a default config file?
    //TODO(Dominik):create defaul config file
    /**
     *
     * @param camHandle handle from gui to select camera fir editing
     */
    private void fillConfig(String camHandle)
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
     */
    private void loadDefaultConfigFile()
    {
        this.parsedConfig.clear();

        parser.loadConfLines(parsedConfig, "defaultconfigpath");
    }

    /**
     *
     * @param camHandle
     */
    private void createConfig(String camHandle)
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

    /**
     *
     * @param parsedConfig
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
     */
    private void editConfigList(ArrayList<String> parsedConfig, String targetWidth, String targetHeight,
                                String targetRotation, String targetFramerate, String targetAutoBright,
                                String targetBrightness, String targetContrast, String targetHue,
                                String targetSaturation, String targetQuality)
    {
        for (String line : parsedConfig)
        {
            if (line.contains("width"))
            {
                parser.rewriteLine(parsedConfig, targetWidth, parsedConfig.indexOf(line));
            }
            if (line.contains("height"))
            {
                parser.rewriteLine(parsedConfig, targetHeight, parsedConfig.indexOf(line));
            }
            if (line.contains("rotate"))
            {
                parser.rewriteLine(parsedConfig, targetRotation, parsedConfig.indexOf(line));
            }
            if (line.contains("framerate"))
            {
                parser.rewriteLine(parsedConfig, targetFramerate, parsedConfig.indexOf(line));
            }
            if (line.contains("auto_brightness"))
            {
                parser.rewriteLine(parsedConfig, targetAutoBright, parsedConfig.indexOf(line));
            }
            if (line.contains("brightness"))
            {
                parser.rewriteLine(parsedConfig, targetBrightness, parsedConfig.indexOf(line));
            }
            if (line.contains("contrast"))
            {
                parser.rewriteLine(parsedConfig, targetContrast, parsedConfig.indexOf(line));
            }
            if (line.contains("hue"))
            {
                parser.rewriteLine(parsedConfig, targetHue, parsedConfig.indexOf(line));
            }
            if (line.contains("saturation"))
            {
                parser.rewriteLine(parsedConfig, targetSaturation, parsedConfig.indexOf(line));
            }
            if (line.contains("quality"))
            {
                parser.rewriteLine(parsedConfig, targetQuality, parsedConfig.indexOf(line));
            }
        }
    }
}
