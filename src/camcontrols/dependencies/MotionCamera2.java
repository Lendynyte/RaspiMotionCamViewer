package camcontrols.dependencies;

import java.util.ArrayList;

/**
 *
 * @author Dominik Pauli
 * @version v 0.3
 */
public class MotionCamera2 implements MotionCameraInterface
{

    private static MotionCamera2 instance = null;

    protected MotionCamera2()
    {
        //to stop instantatiaon
        this.parsedConfig = new ArrayList<>();
        this.name = "Camera2";
    }

    public static MotionCamera2 getInstance()
    {
        if (instance == null)
        {
            instance = new MotionCamera2();
        }
        return instance;
    }

    //Handle for camera used for GUI
    private String handle;
    //Name of camera for folders
    private String name;
    //URL to camera stream
    private String URL;
    //path to motion configuration file
    private String configPath;
    //
    private final ArrayList<String> parsedConfig;

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

    private String camLogin;

    private String camPassword;

    private String camImageFolderPath;

    @Override
    public ArrayList<String> getParsedConfig()
    {
        return this.parsedConfig;
    }

    @Override
    public String getHandle()
    {
        return handle;
    }

    @Override
    public void setHandle(String handle)
    {
        this.handle = handle;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getURL()
    {
        return URL;
    }

    @Override
    public void setURL(String URL)
    {
        this.URL = URL;
    }

    @Override
    public String getConfigPath()
    {
        return configPath;
    }

    @Override
    public void setConfigPath(String configPath)
    {
        this.configPath = configPath;
    }

    @Override
    public int getCamRotation()
    {
        return camRotation;
    }

    @Override
    public void setCamRotation(int camRotation)
    {
        this.camRotation = camRotation;
    }

    @Override
    public int getCamWidth()
    {
        return camWidth;
    }

    @Override
    public void setCamWidth(int camWidth)
    {
        this.camWidth = camWidth;
    }

    @Override
    public int getCamHeight()
    {
        return camHeight;
    }

    @Override
    public void setCamHeight(int camHeight)
    {
        this.camHeight = camHeight;
    }

    @Override
    public int getCamFramerate()
    {
        return camFramerate;
    }

    @Override
    public void setCamFramerate(int camFramerate)
    {
        this.camFramerate = camFramerate;
    }

    @Override
    public boolean isCamAutoBrightness()
    {
        return camAutoBrightness;
    }

    @Override
    public void setCamAutoBrightness(boolean camAutoBrightness)
    {
        this.camAutoBrightness = camAutoBrightness;
    }

    @Override
    public int getCamBrightness()
    {
        return camBrightness;
    }

    @Override
    public void setCamBrightness(int camBrightness)
    {
        this.camBrightness = camBrightness;
    }

    @Override
    public int getCamConstrast()
    {
        return camConstrast;
    }

    @Override
    public void setCamConstrast(int camConstrast)
    {
        this.camConstrast = camConstrast;
    }

    @Override
    public int getCamHue()
    {
        return camHue;
    }

    @Override
    public void setCamHue(int camHue)
    {
        this.camHue = camHue;
    }

    @Override
    public int getCamSaturation()
    {
        return camSaturation;
    }

    @Override
    public void setCamSaturation(int camSaturation)
    {
        this.camSaturation = camSaturation;
    }

    @Override
    public int getCamQuality()
    {
        return camQuality;
    }

    @Override
    public void setCamQuality(int camQuality)
    {
        this.camQuality = camQuality;
    }

    @Override
    public String getCamLogin()
    {
        return camLogin;
    }

    @Override
    public String getCamPassword()
    {
        return camPassword;
    }

    @Override
    public void setCamLogin(String camLogin)
    {
        this.camLogin = camLogin;
    }

    @Override
    public void setCamPassword(String camPassword)
    {
        this.camPassword = camPassword;
    }

    @Override
    public String getImageFolderPath()
    {
        return camImageFolderPath;
    }

    @Override
    public void setImageFolderPath(String camImageFolderPath)
    {
        this.camImageFolderPath = camImageFolderPath;
    }
}
