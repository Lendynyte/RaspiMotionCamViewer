package camcontrols;

import java.util.ArrayList;

/**
 *
 * @author Dominik Pauli
 * @version 0.2
 */
public class ConfParser
{

    //TODO: udelat metodu pro width/height a framerate //sudo done 
    //TODO: udelat motodu ktera vse zmeni v arraylistu // working kinda
    //TODO: udelat metodu na nastaveni path a slozky podle kamery
    //TODO: udealt gui
    //TODO: newtworking
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ArrayList<String> radky = new ArrayList<>();
        Parser pars = new Parser();
        CamControl camc = new CamControl();

        String adr = "C:\\\\Users\\Dominik\\Desktop\\bcbcbc\\config-editor\\motion.conf";

        String path = "C:\\\\Users\\Dominik\\Desktop\\bcbcbc\\config-editor\\motiontested.conf";

        //C:\Users\Dominik\Desktop\bcbcbc\config-editor
        pars.nacistRadky(radky, adr);

        for (String rdk : radky)
        {
            System.out.println(rdk);
        }

        pars.prepisRadek(radky, CamControl.startDaemon.OFF.getStatus(), 1);
        System.out.println("-------------------------------------");
        for (String rdk : radky)
        {
            System.out.println(rdk);
        }

        //pars.vytvorSoubor(radky, path);
        // System.out.println(startDaemon.ON.status);
    }

}
