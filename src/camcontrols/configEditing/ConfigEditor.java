package camcontrols.configEditing;

import camcontrols.dependencies.MotionCameraInterface;

/**
 *
 * @author Dominik Pauli
 * @version 0.4
 */
public class ConfigEditor
{

    //TODO(Dominik):maybe load a default config file?
    //TODO(Dominik):create defaul config file
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
        System.out.println("loaded");
    }

    //TODO(Dominik):maybe save to temp location after that send using ssh send
    /**
     *
     * @param parser
     * @param MotionCamera
     */
    public void createConfig(Parser parser, MotionCameraInterface MotionCamera)
    {
        parser.createConfFile(MotionCamera.getParsedConfig(), MotionCamera.getConfigPath());
    }

    private void sendConfig()
    {
        //TODO(Dominik):ssh magic
    }

    //TODO(Dominik): maybe prepare the strings somewhere else or save them like this in camera in the first place
    //TODO(Dominik): or jsut create new variables called String parameters ... think about
    /**
     * This method takes parsed configuration file, seeks lines with the same
     * name as parameters and replaces them with premade replacement target
     * strings from config String factory
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
        //Iterator<String> confIterator = this.parsedConfig.iterator();
        int position = 0;
        //TODO(Dominik):rewrite foreach have to use iterator
        //TODO(Dominik): http://stackoverflow.com/questions/3184883/concurrentmodificationexception-for-arraylist
        //while(confIterator.hasNext())
       /* {
         if (confIterator.next().contains("width"))
         {
         position++;
         parser.rewriteLine(parsedConfig, "width " + targetWidth, position);
         }
         if (confIterator.next().contains("height"))
         {
         position++;
         parser.rewriteLine(parsedConfig, "height " + targetHeight, position);
         }
         if (confIterator.next().contains("rotate"))
         {
         position++;
         parser.rewriteLine(parsedConfig, "rotate " + targetRotation, position);
         }
         if (confIterator.next().contains("framerate"))
         {
         position++;
         parser.rewriteLine(parsedConfig, "framerate " + targetFramerate, position);
         }
         if (confIterator.next().contains("auto_brightness"))
         {
         position++;
         parser.rewriteLine(parsedConfig, "auto_brightness " + targetAutoBright, position);
         }
         if (confIterator.next().contains("brightness") && !confIterator.next().contains("auto_brightness"))
         {
         position++;
         parser.rewriteLine(parsedConfig, "brightness " + targetBrightness, position);
         }
         if (confIterator.next().contains("contrast"))
         {
         position++;
         parser.rewriteLine(parsedConfig, "contrast " + targetContrast, position);
         }
         if (confIterator.next().contains("hue"))
         {
         position++;
         parser.rewriteLine(parsedConfig, "hue " + targetHue, position);
         }
         if (confIterator.next().contains("saturation"))
         {
         position++;
         parser.rewriteLine(parsedConfig, "saturation " + targetSaturation, position);
         }
         if (confIterator.next().contains("quality") && !confIterator.next().contains("stream_quality"))
         {
         position++;
         parser.rewriteLine(parsedConfig, "quality " + targetQuality, position);
         }
         }*/
    }
}
