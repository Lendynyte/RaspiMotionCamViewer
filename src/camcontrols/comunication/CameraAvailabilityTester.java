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
     * @param timeOut
     * @return returns true if reachable false if not reachable
     *
     */
    public boolean isReachable(String ipAddress, int timeOut)
    {
        String commandLinux = "ping -c 1 -w " + timeOut + " " + ipAddress;
        //TODO(Dominik):delete later
        String commandWindows = "ping -n 1 -w " + timeOut + " " + ipAddress;
        String inputLine;

        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try
        {
            //TODO(Dominik):check if linux or windows or create two versions
            process = runtime.exec(commandWindows);
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
                System.out.println(inputLine);
                //TODO(Dominik):change to linux 
                //TODO(Dominik):check something better
                //if(inputLine.contains("1 received"))
                if (inputLine.contains("P�ijat� = 1"))
                {
                    return true;
                }
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
     * @param timeOut time for ping respond (set to at least 100)
     */
    public void pingCamera(String ipAddress, int timeOut)
    {
        System.out.println("Sending ping request to: " + ipAddress + " ...");

        if (isReachable(ipAddress, timeOut))
        {
            System.out.println("Camera is reachable ...");
        }
        else
        {
            System.err.println("Unable to reach camera ...");
        }
    }
}
