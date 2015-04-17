package camcontrols.comunication;

import camcontrols.dependencies.ApplicationVariables;
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

    private boolean isReachable;

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

        Thread thread = new Thread()
        {
            public void run()
            {
                String commandLinux = "ping -c 1 -w " + timeOut + " " + ipAddress;
                //TODO(Dominik):delete later
                String commandWindows = "ping -n 1 -w " + timeOut + " " + ipAddress;
                String inputLine;

                Runtime runtime = Runtime.getRuntime();
                Process process = null;
                try
                {
                    if (ApplicationVariables.getInstance().getOperatingSystem() == 1)
                    {
                        process = runtime.exec(commandWindows);
                    }
                    else if (ApplicationVariables.getInstance().getOperatingSystem() == 2)
                    {
                        process = runtime.exec(commandLinux);
                    }
                    else
                    {
                        System.err.println("Unable to ping ...");
                    }
                }
                catch (IOException e)
                {
                    System.err.println("Unable to get exec acces to remote ...");
                }

                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                try
                {
                    while ((inputLine = bufferReader.readLine()) != null)
                    {
                        System.out.println(inputLine);
                        if (inputLine.contains("P�ijat� = 1") || (inputLine.contains("1 received")) || (inputLine.contains("Received = 1")))
                        {
                            // return true;
                            isReachable = true;
                        }
                    }
                }
                catch (IOException e)
                {
                    System.err.println("Failed to read input ...");
                }
                try
                {
                    bufferReader.close();
                }
                catch (IOException e)
                {
                    System.err.println("Unable to close buffered reader ...");
                }
                // return false;
                isReachable = false;
            }
        };
        thread.start();
        return isReachable;
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
