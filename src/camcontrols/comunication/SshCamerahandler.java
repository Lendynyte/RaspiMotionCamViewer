package camcontrols.comunication;

import camcontrols.dependencies.MotionCameraInterface;
import java.io.File;

/**
 *
 * @author Dominik Pauli
 * @version 0.1
 */
public class SshCamerahandler
{
    //TODO(Dominik): implement
    //TODO(Dominik): this classs is called when i need to restart and such
    //TODO(Dominik): add login password to MotionCamera1/2
    //TODO(Dominik): make password handling secure

    /**
     *
     * @param MotionCamera
     */
    public void restartCamera(MotionCameraInterface MotionCamera)
    {
        SshComunication sshComunication = new SshComunication(MotionCamera.getCamLogin(), MotionCamera.getCamPassword(), MotionCamera.getURL(), 22, 10000);

        sshComunication.runCommand("sudo reboot", 10000);

        sshComunication.sshDisconnect();
    }

    /**
     *
     * @param MotionCamera
     * @param sshTimeout
     */
    public void turnOffRaspberry(MotionCameraInterface MotionCamera, int sshTimeout)
    {
        SshComunication sshComunication = new SshComunication(MotionCamera.getCamLogin(), MotionCamera.getCamPassword(), MotionCamera.getURL(), 22, 10000);

        sshComunication.runCommand("sudo shutdown -h now", sshTimeout);

        sshComunication.sshDisconnect();
    }

    //TODO(Dominik): remove tesating for now
    /**
     *
     * @param MotionCamera
     * @param sshTimeout
     */
    public void runMotion(MotionCameraInterface MotionCamera, int sshTimeout)
    {
        SshComunication sshComunication = new SshComunication(MotionCamera.getCamLogin(), MotionCamera.getCamPassword(), MotionCamera.getURL(), 22, sshTimeout);

        sshComunication.runCommand("sudo motion", sshTimeout);

        sshComunication.sshDisconnect();
    }

    /**
     *
     * @param MotionCamera
     * @param confFileLocation
     * @param sshTimeout
     */
    public void sendConfFile(MotionCameraInterface MotionCamera, String confFileLocation, int sshTimeout)
    {
        SshComunication sshComunication = new SshComunication(MotionCamera.getCamLogin(), MotionCamera.getCamPassword(), MotionCamera.getURL(), 22, sshTimeout);

        sshComunication.uploadFile(MotionCamera.getConfigPath(), new File(confFileLocation), sshTimeout);

        sshComunication.sshDisconnect();
    }

    /**
     *
     * @param MotionCamera
     * @param storagePath
     * @param sshTimeout
     */
    public void downLoadImageFiles(MotionCameraInterface MotionCamera, String storagePath, int sshTimeout)
    {
        SshComunication sshComunication = new SshComunication(MotionCamera.getCamLogin(), MotionCamera.getCamPassword(), MotionCamera.getURL(), 22, sshTimeout);

       //TODO(Dominik):add ImageFOlderPath to motioncameras
        //sshComunication.downloadFiles(MotionCamera.getImageFolderPath(), storagePath, sshTimeout);
        
        sshComunication.sshDisconnect();
    }

}
