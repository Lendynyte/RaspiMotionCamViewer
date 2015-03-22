package camcontrols.configEditing;

import camcontrols.dependencies.MotionCamera1;

/**
 *
 * @author Dominik Pauli
 * @version 0.2
 * @deprecated
 */
public class CETester
{
    //TODO(Dominik): REMOVE

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Parser pars = new Parser();

        ConfigEditor cfge = new ConfigEditor();
        cfge.loadDefaultConfigFile("J://test/default.conf", pars);
        MotionCamera1.getInstance().setConfigPath("J://test");
        System.out.println(MotionCamera1.getInstance().getConfigPath());
        cfge.createConfig("cam1", pars);

    }

}
