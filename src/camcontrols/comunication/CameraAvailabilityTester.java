package camcontrols.comunication;

import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingResponse;
import org.icmp4j.IcmpPingUtil;

/**
 *
 * @author Dominik Pauli
 * @version 0.3
 */
public class CameraAvailabilityTester
{

    /**
     *
     * @param hostAdress
     * @param timeout
     * @return
     */
    public boolean isReachable(String hostAdress, int timeout)
    {
        try
        {
            final IcmpPingRequest request = IcmpPingUtil.createIcmpPingRequest();
            request.setHost(hostAdress);
            request.setTimeout(timeout);

            final IcmpPingResponse response = IcmpPingUtil.executePingRequest(request);

            final String formatResponse = IcmpPingUtil.formatResponse(response);

            return !formatResponse.contains("Error");
        }
        catch (Exception e)
        {
            System.err.println("Host does not exist ...");
            return false;
        }
    }

    /**
     * 
     * @param hostAdress
     * @param timeout
     * @param hostName
     * @return 
     */
    public String ping(String hostAdress, int timeout, String hostName)
    {
        if (isReachable(hostAdress, timeout))
        {
            return hostName + "is reachable ...";
        }
        else
        {
            return "Unable to reach " + hostName + " ...";
        }
    }
}
