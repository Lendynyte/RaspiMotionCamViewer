package camcontrols.dependencies;

import java.util.ArrayList;

/**
 *
 * @author Dominik Pauli
 * @version v 0.2
 */
public class MotionCamera1 implements MotionCameraInterface
{

    private static MotionCamera1 instance = null;

    protected MotionCamera1()
    {
        //to stop instantatiaon
    }

    public static MotionCamera1 getInstance()
    {
        if (instance == null)
        {
            instance = new MotionCamera1();
        }
        return instance;
    }

    //CAMERA VARIABLES FOR GUI
    //Handle for camera used for GUI
    private String handle;
    //Name of camera for folders
    private String name = "Camera1";
    //URL to camera stream
    private String URL;
    //path to motion configuration file
    private String configPath;
    //
    private ArrayList<String> parsedConfig = new ArrayList<String>();

    //CAMERA VARIABLES FOR CONFIGURATION FILE
    //camera rotation -- maybe remove
    private int camRotation;
    //camera resolution width
    private int camWidth;
    //camera resolution height
    private int camHeight;
    //camera framerate -- maybe remove
    private int camFramerate;
    //camera auto brightness(on/off)
    private boolean camAutoBrightness;
    //camera brightnewss level(0-255)
    private int camBrightness;
    //camera contrast level(0-255)
    private int camConstrast;
    //camera hue level(0-255)
    private int camHue;
    //cam saturation level(0-255)
    private int camSaturation;
    //camera quality (50/75/100)
    private int camQuality;

    @Override
    public ArrayList<String> getParsedConfig()
    {
        return this.parsedConfig;
    }
    
    public String getHandle()
    {
        return handle;
    }

    public void setHandle(String handle)
    {
        this.handle = handle;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getURL()
    {
        return URL;
    }

    public void setURL(String URL)
    {
        this.URL = URL;
    }

    @Override
    public String getConfigPath()
    {
        return configPath;
    }

    public void setConfigPath(String configPath)
    {
        this.configPath = configPath;
    }

    public int getCamRotation()
    {
        return camRotation;
    }

    public void setCamRotation(int camRotation)
    {
        this.camRotation = camRotation;
    }

    public int getCamWidth()
    {
        return camWidth;
    }

    public void setCamWidth(int camWidth)
    {
        this.camWidth = camWidth;
    }

    public int getCamHeight()
    {
        return camHeight;
    }

    public void setCamHeight(int camHeight)
    {
        this.camHeight = camHeight;
    }

    public int getCamFramerate()
    {
        return camFramerate;
    }

    public void setCamFramerate(int camFramerate)
    {
        this.camFramerate = camFramerate;
    }

    public boolean isCamAutoBrightness()
    {
        return camAutoBrightness;
    }

    public void setCamAutoBrightness(boolean camAutoBrightness)
    {
        this.camAutoBrightness = camAutoBrightness;
    }

    public int getCamBrightness()
    {
        return camBrightness;
    }

    public void setCamBrightness(int camBrightness)
    {
        this.camBrightness = camBrightness;
    }

    public int getCamConstrast()
    {
        return camConstrast;
    }

    public void setCamConstrast(int camConstrast)
    {
        this.camConstrast = camConstrast;
    }

    public int getCamHue()
    {
        return camHue;
    }

    public void setCamHue(int camHue)
    {
        this.camHue = camHue;
    }

    public int getCamSaturation()
    {
        return camSaturation;
    }

    public void setCamSaturation(int camSaturation)
    {
        this.camSaturation = camSaturation;
    }

    public int getCamQuality()
    {
        return camQuality;
    }

    public void setCamQuality(int camQuality)
    {
        this.camQuality = camQuality;
    }

}
