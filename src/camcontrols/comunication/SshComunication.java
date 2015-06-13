package camcontrols.comunication;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Dominik Pauli
 * @version 0.4
 */
public class SshComunication
{

    /**
     *
     * @param login
     * @param password
     * @param ip
     * @param sshPort
     * @param command
     * @param sshTimeout
     */
    public void runCommand(String login, String password, String ip, int sshPort, String command, int sshTimeout)
    {
        /* new Thread()
         {
         @Override
         public void run()
         {*/
        try
        {
            //creating ssh session
            System.out.println("Creating new SSH session ...");
            Session sshSession = new JSch().getSession(login, ip, sshPort);
            sshSession.setPassword(password);
            sshSession.setConfig("StrictHostKeyChecking", "no");
            sshSession.connect(sshTimeout);
            System.out.println("SSH session created ...");

            //sending commands
            ChannelExec channelExec = (ChannelExec) sshSession.openChannel("exec");
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
            sshSession.disconnect();
        }
        catch (JSchException e)
        {
            System.err.println("Unable to run command exec ...");
        }
        catch (IOException e)
        {
            System.err.println("Unable to create input stream ...");
        }
        /*       }
         }.start();*/
    }

    /**
     *
     * @param login
     * @param remtoteConfigPath
     * @param ip
     * @param password
     * @param sshPort
     * @param fileToSend
     * @param sshTimeout
     */
    public void uploadFile(String login, String password, String ip, int sshPort, String remtoteConfigPath, File fileToSend, int sshTimeout)
    {
        /*new Thread()
         {
         @Override
         public void run()
         {*/

        try
        {
            //creating ssh session
            System.out.println("Creating new SSH session ...");
            Session sshSession = new JSch().getSession(login, ip, sshPort);
            sshSession.setPassword(password);
            sshSession.setConfig("StrictHostKeyChecking", "no");
            sshSession.connect();
            System.out.println("SSH session created ...");

            //sending files
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            ChannelSftp channelSFTP = (ChannelSftp) channel;
            channelSFTP.cd(remtoteConfigPath);
            System.out.println("Trying to send file ...");
            channelSFTP.put(new FileInputStream(fileToSend), fileToSend.getName());
            System.out.println("File succesfully send ...");
            channelSFTP.disconnect();
            sshSession.disconnect();
        }
        catch (JSchException e)
        {
            System.err.println("Unable to create sftp session ...");
        }
        catch (SftpException e)
        {
            System.err.println("Sending file failed ...");
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File to send does not exist ...");
        }
        /*  }
         }.start();*/
    }

    //TODO(Dominik):test how log it takes to download large amount of files if it is too slow add some sort of status checking and make user be able to stop download
    /**
     *
     * @param login
     * @param password
     * @param folderName
     * @param sshPort
     * @param ip
     * @param storageFolderPath
     * @param sshTimeout
     */
    public void downloadFiles(String login, String password, String ip, int sshPort, String folderName, String storageFolderPath, int sshTimeout)
    {
        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    //creating ssh session
                    System.out.println("Creating new SSH session ...");
                    Session sshSession = new JSch().getSession(login, ip, sshPort);
                    sshSession.setPassword(password);
                    sshSession.setConfig("StrictHostKeyChecking", "no");
                    sshSession.connect(sshTimeout);
                    System.out.println("SSH session created ...");

                    //downloading files
                    ChannelSftp channelSftp = (ChannelSftp) sshSession.openChannel("sftp");
                    channelSftp.connect(sshTimeout);

                    System.out.println("Trying to download files ...");
                    for (Object file : channelSftp.ls(folderName))
                    {
                        System.out.println("Saving file" + ((ChannelSftp.LsEntry) file).getFilename());
                        channelSftp.get(folderName + ((ChannelSftp.LsEntry) file).getFilename(), storageFolderPath);
                        System.out.println("File succesfully downloaded ...");
                    }
                    System.out.println("All files succesfully downloaded ...");

                    channelSftp.disconnect();
                    sshSession.disconnect();
                }
                catch (JSchException e)
                {
                    System.err.println("Unable to create sftp session ...");
                }
                catch (SftpException e)
                {
                    System.err.println("Downloading file failed ...");
                }
            }
        }.start();
    }
}
