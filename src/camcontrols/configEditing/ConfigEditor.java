package camcontrols.configEditing;

import camcontrols.dependencies.ApplicationVariables;
import camcontrols.dependencies.MotionCameraInterface;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author Dominik Pauli
 * @version 0.5
 */
public class ConfigEditor
{

    /**
     *
     * @param camHandle handle from gui to select camera fir editing
     */
    private void fillConfig(Parser parser, MotionCameraInterface MotionCamera)
    {
        MotionCamera.getParsedConfig().clear();

        //TODO(Dominik): check if matters if i load deffault config file here
        InputStream in = getClass().getResourceAsStream("/cleanConfig.conf");

        parser.loadConfLines(MotionCamera.getParsedConfig(), in);
    }

    /**
     *
     * @param parser
     * @param MotionCamera
     */
    public void loadDefaultConfigFile(Parser parser, MotionCameraInterface MotionCamera)
    {
        MotionCamera.getParsedConfig().clear();

        InputStream in = getClass().getResourceAsStream("/cleanConfig.conf");

        parser.loadConfLines(MotionCamera.getParsedConfig(), in);
    }

    /**
     *
     * @param parser
     * @param MotionCamera
     * @param camId 1 for cam 1, 2 for cam 2
     */
    public void createConfig(Parser parser, MotionCameraInterface MotionCamera, String camId)
    {
        //WINDOWS
        if (ApplicationVariables.getInstance().getOperatingSystem() == 1)
        {
            parser.createConfigFolders("C://CamControls/src/save/cam" + camId);
            parser.createConfFile(MotionCamera.getParsedConfig(), "C://CamControls/src/save/cam" + camId);
            System.out.println("File created ...");
        }

        //LINUX MAINLY MADE FOR RASPBERRY PI USER PI
        else if (ApplicationVariables.getInstance().getOperatingSystem() == 2)
        {
            parser.createConfigFolders("/home/pi/CamControls/src/save/cam" + camId);
            parser.createConfFile(MotionCamera.getParsedConfig(), "/home/pi/CamControls/src/save/cam" + camId);
            System.out.println("File created ...");
        }

        //UNKNOWN OPERATING SYSTEM
        else
        {
            System.err.println("Cannot find install directory ...");
        }
    }

    //TODO(Dominik): still needs rework
    /**
     *
     * @param parser
     * @param MotionCamera
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
    public void editConfigList(Parser parser, MotionCameraInterface MotionCamera,
            String targetWidth, String targetHeight, String targetRotation, String targetFramerate,
            String targetAutoBright, String targetBrightness, String targetContrast, String targetHue,
            String targetSaturation, String targetQuality)
    {
        //TODO(Dominik): HAVE TO FING WORKING RESOLUTIONS THESE CRASH MOTION
       /* if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "width", targetWidth))
         {
         loadDefaultConfigFile(parser, MotionCamera);
         }

         if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "height", targetHeight))
         {
         loadDefaultConfigFile(parser, MotionCamera);
         }*/

        InputStream in = getClass().getResourceAsStream("/cleanConfig.conf");

        if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "rotate", targetRotation))
        {
            loadDefaultConfigFile(parser, MotionCamera);
        }

        if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "framerate", targetFramerate))
        {
            loadDefaultConfigFile(parser, MotionCamera);
        }

        if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "auto_brightness", targetAutoBright))
        {
            loadDefaultConfigFile(parser, MotionCamera);
        }

        if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "brightness", "auto_brightness", targetBrightness))
        {
            loadDefaultConfigFile(parser, MotionCamera);
        }

        if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "contrast", targetContrast))
        {
            loadDefaultConfigFile(parser, MotionCamera);
        }

        if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "hue", targetHue))
        {
            loadDefaultConfigFile(parser, MotionCamera);
        }

        if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "saturation", targetSaturation))
        {
            loadDefaultConfigFile(parser, MotionCamera);
        }

        if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "quality", "stream_quality", targetQuality))
        {
            loadDefaultConfigFile(parser, MotionCamera);
        }
    }

    /**
     *
     * @param MotionCamera
     * @param targetWidth
     */
    private boolean findChangeConfValue(Parser parser, ArrayList<String> parsedConfig, String valueToChange, String unvantedValuetoChange, String targetValue)
    {
        int lineNumber = -1;

        for (String line : parsedConfig)
        {
            if (line.contains(valueToChange) && !line.contains(unvantedValuetoChange))
            {
                lineNumber = parsedConfig.indexOf(line);
            }
        }

        if (lineNumber == -1)
        {
            System.err.println("Config File is broken loading default config...");
            return false;
        }

        parsedConfig.set(lineNumber, valueToChange + " " + targetValue);
        return true;
    }

    /**
     *
     * @param MotionCamera
     * @param targetWidth
     */
    private boolean findChangeConfValue(Parser parser, ArrayList<String> parsedConfig, String valueToChange, String targetValue)
    {
        int lineNumber = -1;

        for (String line : parsedConfig)
        {
            if (line.contains(valueToChange))
            {
                lineNumber = parsedConfig.indexOf(line);
            }
        }

        if (lineNumber == -1)
        {
            //System.err.println("Config File is broken loading default config...");
            return false;
        }

        parsedConfig.set(lineNumber, valueToChange + " " + targetValue);
        return true;
    }
}
