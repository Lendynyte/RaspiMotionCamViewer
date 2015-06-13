package camcontrols.comunication;

import camcontrols.dependencies.MotionCameraInterface;
import java.io.File;

/**
 *
 * @author Dominik Pauli
 * @version 0.3
 */
public class SshCamerahandler
{

    /**
     *
     * @param MotionCamera
     */
    public void restartCamera(MotionCameraInterface MotionCamera)
    {
        new SshComunication().runCommand(MotionCamera.getCamLogin(), MotionCamera.getCamPassword(), MotionCamera.getURL(), 22, "sudo reboot", 10000);
    }

    /**
     *
     * @param MotionCamera
     * @param sshTimeout
     */
    public void turnOffRaspberry(MotionCameraInterface MotionCamera, int sshTimeout)
    {
        new SshComunication().runCommand(MotionCamera.getCamLogin(), MotionCamera.getCamPassword(), MotionCamera.getURL(), 22, "sudo shutdown -h now", sshTimeout);
    }

    /**
     *
     * @param MotionCamera
     * @return succes
     */
    public boolean runMotion(MotionCameraInterface MotionCamera)
    {
        return new SshComunication().runCommand(MotionCamera.getCamLogin(), MotionCamera.getCamPassword(), MotionCamera.getURL(), 22, "sudo motion", 1000000);
    }

    /**
     *
     * @param motionCamera
     * @return succes
     */
    public boolean restartMotion(MotionCameraInterface motionCamera)
    {
        return new SshComunication().runCommand(motionCamera.getCamLogin(), motionCamera.getCamPassword(), motionCamera.getURL(), 22, "sudo service motion restart", 1000000);
    }

    /**
     *
     * @param MotionCamera
     * @param confFileLocation
     * @param sshTimeout
     */
    public void sendConfFile(MotionCameraInterface MotionCamera, String confFileLocation, int sshTimeout)
    {
        new SshComunication().uploadFile(MotionCamera.getCamLogin(), MotionCamera.getCamPassword(), MotionCamera.getURL(), 22, MotionCamera.getConfigPath(), new File(confFileLocation), sshTimeout);
    }

    /**
     *
     * @param MotionCamera
     * @param storagePath
     * @param sshTimeout
     */
    public void downLoadImageFiles(MotionCameraInterface MotionCamera, String storagePath, int sshTimeout)
    {
        new SshComunication().downloadFiles(MotionCamera.getCamLogin(), MotionCamera.getCamPassword(), MotionCamera.getURL(), 22, MotionCamera.getImageFolderPath(), storagePath, sshTimeout);
    }
}
