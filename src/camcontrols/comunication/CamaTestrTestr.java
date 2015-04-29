package camcontrols.comunication;

/**
 *
 * @author Dominik
 * @deprecated
 */
public class CamaTestrTestr
{

    //TODO(Dominik):remove
    //http://www.rgagnon.com/javadetails/java-0093.html
    public static void main(String[] args)
    {
        CameraAvailabilityTester camtestr = new CameraAvailabilityTester();

       // camtestr.pingCamera("8.8.8.8", 1);
        
        camtestr.isReachable("192.168.1.242", 10);
        //windows ping -n 1 -w timeout 8.8.8.8
        //linux ping -c 1 -w timeout 8.8.8.8
    }
}
