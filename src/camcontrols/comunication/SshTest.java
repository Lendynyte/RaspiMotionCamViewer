package camcontrols.comunication;

import camcontrols.dependencies.MotionCamera1;

/**
 *
 * @deprecated
 */
//TODO(Dominik): remove
public class SshTest
{

    public static void main(String[] args)
    {
        System.out.println("sendCommand");

        String login = "pi";
        String password = "raspberry";
        String ip = "picamera1";
        int port = 22;
        int timeout = 10000;
        String command1 = "sudo motion";
        String command2 = "sudo reboot";

        /*shComunication ssh = new SshComunication(login, password, ip, port, timeout);

         ssh.runCommand(command2, timeout);
         ssh.sshDisconnect();*/
        SshCamerahandler ssh = new SshCamerahandler();
        MotionCamera1.getInstance().setCamLogin(login);
        MotionCamera1.getInstance().setCamPassword(password);
        MotionCamera1.getInstance().setURL(ip);

        //TODO(Dominik):when sending add /motion.conf to file or it prob wont work .. test it
        
        //ssh.runMotion(MotionCamera1.getInstance());
        ssh.turnOffRaspberry(MotionCamera1.getInstance(), 10000);

    }
}
