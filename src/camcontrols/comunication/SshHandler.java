package camcontrols.comunication;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author World
 *
 */
public class SshHandler
{

    // http://stackoverflow.com/questions/3071760/ssh-connection-with-java
    public Session session;

    public SshHandler(String user, String password, String host, int port)
    {
        ssh_start(user, password, host, port);
    }

    public void ssh_start(String user, String password, String host, int port)
    {
        try
        {
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
        }
        catch (JSchException ex)
        {
            System.err.println(ex);
        }
    }

    public void ssh_stop()
    {
        session.disconnect();
    }

    public void runCommand(String Command, String name)
    {
        try
        {
            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            InputStream exec = channelExec.getInputStream();
            //channelExec.setCommand(Cluster.cmd);
            channelExec.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(exec));
            String line;

            while ((line = reader.readLine()) != null)
            {
                //   Cluster.vystup[Integer.parseInt(name)] += line;
                //System.out.println(name + " : " + line);
            }

            channelExec.disconnect();
        }
        catch (JSchException | IOException ex)
        {
         //   Cluster.vystup[Integer.parseInt(name)] = ex.getMessage();
            //  Cluster.stavVlaken[Integer.parseInt(name)] = true;
        }
    }
}
