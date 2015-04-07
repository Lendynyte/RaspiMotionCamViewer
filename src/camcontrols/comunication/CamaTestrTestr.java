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

        camtestr.pingCamera("8.8.8.8");
    }
}
