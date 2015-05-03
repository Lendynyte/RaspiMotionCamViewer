package camcontrols.configEditing;

import camcontrols.dependencies.ApplicationVariables;
import camcontrols.dependencies.MotionCameraInterface;
import java.util.ArrayList;

/**
 *
 * @author Dominik Pauli
 * @version 0.4
 */
public class ConfigEditor
{

    //TODO(Dominik):create defaul config file
    //TODO(Dominik):rewrite this using application variables path
    /**
     *
     * @param camHandle handle from gui to select camera fir editing
     */
    private void fillConfig(Parser parser, MotionCameraInterface MotionCamera)
    {
        MotionCamera.getParsedConfig().clear();

        parser.loadConfLines(MotionCamera.getParsedConfig(), MotionCamera.getConfigPath());
    }

    /**
     *
     * @param defaultConfPath
     * @param parser
     * @param MotionCamera
     */
    public void loadDefaultConfigFile(String defaultConfPath, Parser parser, MotionCameraInterface MotionCamera)
    {
        MotionCamera.getParsedConfig().clear();

        parser.loadConfLines(MotionCamera.getParsedConfig(), defaultConfPath);
    }

    /**
     *
     * @param parser
     * @param MotionCamera
     */
    public void createConfig(Parser parser, MotionCameraInterface MotionCamera)
    {
        parser.createConfigFolders(MotionCamera.getConfigPath());
        //TODO(Dominik): FIX
        parser.createConfFile(MotionCamera.getParsedConfig(), ApplicationVariables.getInstance().getXmlSaveDirectoryPath() + "/cam1");
        System.out.println("File created ...");
    }

    //TODO(Dominik): still needs rework
    /**
     *
     * @param parser
     * @param defaultConfPath
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
    public void editConfigList(Parser parser, String defaultConfPath, MotionCameraInterface MotionCamera,
            String targetWidth, String targetHeight, String targetRotation, String targetFramerate,
            String targetAutoBright, String targetBrightness, String targetContrast, String targetHue,
            String targetSaturation, String targetQuality)
    {
        //TODO(Dominik): HAVE TO FING WORKING RESOLUTIONS THESE CRASH MOTION
       /* if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "width", targetWidth))
        {
            loadDefaultConfigFile(defaultConfPath, parser, MotionCamera);
        }

        if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "height", targetHeight))
        {
            loadDefaultConfigFile(defaultConfPath, parser, MotionCamera);
        }*/

        if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "rotate", targetRotation))
        {
            loadDefaultConfigFile(defaultConfPath, parser, MotionCamera);
        }

        if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "framerate", targetFramerate))
        {
            loadDefaultConfigFile(defaultConfPath, parser, MotionCamera);
        }

        if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "auto_brightness", targetAutoBright))
        {
            loadDefaultConfigFile(defaultConfPath, parser, MotionCamera);
        }

        if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "brightness", "auto_brightness", targetBrightness))
        {
            loadDefaultConfigFile(defaultConfPath, parser, MotionCamera);
        }

        if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "contrast", targetContrast))
        {
            loadDefaultConfigFile(defaultConfPath, parser, MotionCamera);
        }

        if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "hue", targetHue))
        {
            loadDefaultConfigFile(defaultConfPath, parser, MotionCamera);
        }

        if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "saturation", targetSaturation))
        {
            loadDefaultConfigFile(defaultConfPath, parser, MotionCamera);
        }

        if (!findChangeConfValue(parser, MotionCamera.getParsedConfig(), "quality", "stream_quality", targetQuality))
        {
            loadDefaultConfigFile(defaultConfPath, parser, MotionCamera);
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
