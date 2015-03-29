package camcontrols.comunication;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Dominik Pauli
 * @version 0.1
 */
public class SshComunication
{

    private JSch jschSSH;
    private Session sshSession;

    /**
     * This method connects to remote machine trough ssh using Jsch.
     *
     * @param login
     * @param password
     * @param ip
     * @param sshPort
     * @param sshTimeout
     */
    public void sshConnect(String login, String password, String ip, int sshPort, int sshTimeout)
    {
        try
        {
            this.jschSSH = new JSch();
            this.sshSession = jschSSH.getSession(login, ip, sshPort);
            this.sshSession.setPassword(password);
            this.sshSession.setConfig("StrictHostKeyChecking", "no");
            this.sshSession.connect(sshTimeout);

            System.out.println("SSH session created ...");
        }
        catch (JSchException e)
        {
            System.err.println("Unable to create ssh session ...");
        }
    }

    /**
     * This method disconnects current ssh session by calling Jsch method for
     * disconnect.
     */
    public void sshDisconnect()
    {
        this.sshSession.disconnect();

        System.out.println("Disconnected ...");
    }

    /**
     *
     * @param command
     * @param sshTimeout
     */
    public void runCommand(String command, int sshTimeout)
    {
        try
        {
            ChannelExec channelExec = (ChannelExec) this.sshSession.openChannel("exec");
            InputStream exec = channelExec.getInputStream();
            channelExec.setCommand(command);
            channelExec.connect(sshTimeout);

            BufferedReader bReader = new BufferedReader(new InputStreamReader(exec));
            String line;

            while ((line = bReader.readLine()) != null)
            {
                System.out.println(line);
            }

            System.out.println("Command executed ...");

            channelExec.disconnect();
        }
        catch (JSchException e)
        {
            System.err.println("Unable to run command exec ...");
        }
        catch (IOException e)
        {
            System.err.println("Unable to create input stream ...");
        }
    }

    /**
     *
     * @param fileToSend
     * @param sshTimeout
     */
    public void sendFile(File fileToSend, int sshTimeout)
    {
        //TODO(Dominik): implement
    }

    /**
     *
     * @param login
     * @param password
     * @param ip
     * @param port
     * @param sshTimeout
     */
    public SshComunication(String login, String password, String ip, int port, int sshTimeout)
    {
        sshConnect(login, password, ip, port, sshTimeout);
    }

}
