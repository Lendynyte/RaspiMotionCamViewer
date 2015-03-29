package camcontrols.comunication;

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

        SshComunication ssh = new SshComunication(login, password, ip, port, timeout);

        ssh.runCommand(command2, timeout);
        ssh.sshDisconnect();
    }
}
