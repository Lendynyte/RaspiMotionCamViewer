package camcontrols.saving;

import camcontrols.dependencies.MotionCameraInterface;

/**
 *
 * @author Dominik Pauli
 * @version 0.1
 */
public class XMLCameraHandler
{
    //TODO(Dominik):this class is used for calling xmlio and putting stuff from motioncameras to xml files

    private final XMLIo xml = new XMLIo();

    /**
     *
     * @param MotionCamera
     */
    public void createCamSave(MotionCameraInterface MotionCamera)
    {
        xml.createXMLFile(MotionCamera.getXMLSavePath(), MotionCamera.getName(),
                MotionCamera.getHandle(), MotionCamera.getConfigPath(),
                MotionCamera.getURL(), MotionCamera.getCamRotation() + "",
                MotionCamera.getCamWidth() + "", MotionCamera.getCamHeight() + "",
                MotionCamera.getCamFramerate() + "", MotionCamera.isCamAutoBrightness() + "",
                MotionCamera.getCamBrightness() + "", MotionCamera.getCamConstrast() + "",
                MotionCamera.getCamHue() + "", MotionCamera.getCamSaturation() + "",
                MotionCamera.getCamQuality() + "");
    }
}
