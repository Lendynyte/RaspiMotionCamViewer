package camcontrols.comunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Dominik Pauli
 * @version 0.2
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
        String command = "ping " + ipAddress;
        String inputLine;

        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try
        {
            process = runtime.exec(command);
        } catch (IOException e)
        {
            System.err.println("Unable to get exec acces to remote ...");
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
            System.err.println("Failed to read input ...");
        }
        try
        {
            bufferReader.close();
        } catch (IOException e)
        {
            System.err.println("Unable to close buffered reader ...");
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
