package camcontrols.dependencies;

import java.util.ArrayList;

/**
 *
 * @author Dominik Pauli
 * @version 0.3
 */
public interface MotionCameraInterface
{

    /**
     *
     * @return
     */
    public ArrayList<String> getParsedConfig();

    /**
     *
     * @return
     */
    public String getHandle();

    /**
     *
     * @param handle
     */
    public void setHandle(String handle);

    /**
     *
     * @return
     */
    public String getName();

    /**
     *
     * @param name
     */
    public void setName(String name);

    /**
     *
     * @return
     */
    public String getURL();

    /**
     *
     * @param URL
     */
    public void setURL(String URL);

    /**
     *
     * @param configPath
     */
    public void setConfigPath(String configPath);

    /**
     *
     * @return
     */
    public String getConfigPath();

    /**
     *
     * @return
     */
    public int getCamRotation();

    /**
     *
     * @param camRotation
     */
    public void setCamRotation(int camRotation);

    /**
     *
     * @return
     */
    public int getCamWidth();

    /**
     *
     * @param camWidth
     */
    public void setCamWidth(int camWidth);

    /**
     *
     * @return
     */
    public int getCamHeight();

    /**
     *
     * @param camHeight
     */
    public void setCamHeight(int camHeight);

    /**
     *
     * @return
     */
    public int getCamFramerate();

    /**
     *
     * @param camFramerate
     */
    public void setCamFramerate(int camFramerate);

    /**
     *
     * @return
     */
    public boolean isCamAutoBrightness();

    /**
     *
     * @param camAutoBrightness
     */
    public void setCamAutoBrightness(boolean camAutoBrightness);

    /**
     *
     * @return
     */
    public int getCamBrightness();

    /**
     *
     * @param camBrightness
     */
    public void setCamBrightness(int camBrightness);

    /**
     *
     * @return
     */
    public int getCamConstrast();

    /**
     *
     * @param camConstrast
     */
    public void setCamConstrast(int camConstrast);

    /**
     *
     * @return
     */
    public int getCamHue();

    /**
     *
     * @param camHue
     */
    public void setCamHue(int camHue);

    /**
     *
     * @return
     */
    public int getCamSaturation();

    /**
     *
     * @param camSaturation
     */
    public void setCamSaturation(int camSaturation);

    /**
     *
     * @return
     */
    public int getCamQuality();

    /**
     *
     * @param camQuality
     */
    public void setCamQuality(int camQuality);

}
