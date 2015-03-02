package camcontrols;

/**
 *
 * @author Dominik Pauli
 * @version v 0.2
 */
public class MotionCamera2SingletonTest
{

    private static MotionCamera2SingletonTest instance = null;

    protected MotionCamera2SingletonTest()
    {
        //to stop instantatiaon
    }

    public static MotionCamera2SingletonTest getInstance()
    {
        if (instance == null)
        {
            instance = new MotionCamera2SingletonTest();
        }
        return instance;
    }

    //Handle for camera used for GUI
    private String handle;
    //Name of camera for folders
    private String name;
    //URL to camera stream
    private String URL;
    //if the camera stream has increased size
    private boolean isFocused;
    //if the camera has highlighting overlay
    private boolean isHighlighted;

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

    public boolean isIsFocused()
    {
        return isFocused;
    }

    public void setIsFocused(boolean isFocused)
    {
        this.isFocused = isFocused;
    }

    public boolean isIsHighlighted()
    {
        return isHighlighted;
    }

    public void setIsHighlighted(boolean isHighlighted)
    {
        this.isHighlighted = isHighlighted;
    }

    public String getURL()
    {
        return URL;
    }

    public void setURL(String URL)
    {
        this.URL = URL;
    }
}
