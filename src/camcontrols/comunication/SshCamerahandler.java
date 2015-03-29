package camcontrols.comunication;

import camcontrols.dependencies.MotionCameraInterface;

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
     */
    public void turnOffRaspberry(MotionCameraInterface MotionCamera)
    {
        SshComunication sshComunication = new SshComunication(MotionCamera.getCamLogin(), MotionCamera.getCamPassword(), MotionCamera.getURL(), 22, 10000);

        sshComunication.runCommand("sudo shutdown -h now", 10000);

        sshComunication.sshDisconnect();
    }

    //TODO(Dominik): remove tesating for now
    /**
     *
     * @param MotionCamera
     */
    public void runMotion(MotionCameraInterface MotionCamera)
    {
        SshComunication sshComunication = new SshComunication(MotionCamera.getCamLogin(), MotionCamera.getCamPassword(), MotionCamera.getURL(), 22, 10000);

        sshComunication.runCommand("sudo motion", 10000);

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
        SshComunication sshComunication = new SshComunication(MotionCamera.getCamLogin(), MotionCamera.getCamPassword(), MotionCamera.getURL(), 22, 10000);

        sshComunication.sendFile(null, sshTimeout);

        sshComunication.sshDisconnect();
    }

}
