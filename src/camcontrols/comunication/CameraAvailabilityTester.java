package camcontrols.comunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dominik Pauli
 * @version 0.1
 */
public class CameraAvailabilityTester
{

    /**
     * This method is used for pinging an ip adress with timeout of 5000
     *
     * @param ipAddress adress to check if reachable
     * @return returns true if reachable false if not reachable
     *
     */
    public boolean isReachable(String ipAddress)
    {
        //TODO(Dominik):old version may work test if does but dont think it will
        String command = "ping " + ipAddress;
        String inputLine;

        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try
        {
            process = runtime.exec(command);
        } catch (IOException ex)
        {
            Logger.getLogger(CameraAvailabilityTester.class.getName()).log(Level.SEVERE, null, ex);
        }

        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        try
        {
            while ((inputLine = bufferReader.readLine()) != null)
            {
                //TODO(Dominik):test on linux
                //TODO(Dominik):check  failed and working command
                //TODO(Dominik):iplement loguc to return right value
                System.out.println(inputLine);
            }
        } catch (IOException e)
        {
            System.out.println("Failed to read input ...");
        }
        try
        {
            bufferReader.close();
        } catch (IOException e)
        {
            System.out.println("Unable to close buffered reader ...");
        }
        return false;
    }

    /**
     * This method pings ipaddress and prints out results
     *
     * @param ipAddress adress to ping
     */
    public void pingCamera(String ipAddress)
    {
        System.out.println("Sending ping request to: " + ipAddress + " ...");

        if (isReachable(ipAddress))
        {
            System.out.println("Camera is reachable ...");
        }
        else
        {
            System.err.println("Unable to reach camera ...");
        }
    }
}
