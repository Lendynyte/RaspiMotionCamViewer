package camcontrols.configEditing;

import camcontrols.dependencies.MotionCamera1;
import camcontrols.dependencies.MotionCamera2;

/**
 *
 * @author Dominik Pauli
 * @version 0.2
 * @deprecated
 */
public class CETester
{
    //TODO(Dominik): REMOVE

    //TODO(Dominik): check all possible rewrites
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Parser pars = new Parser();

        ConfigEditor cfge = new ConfigEditor();
        cfge.loadDefaultConfigFile("J://test/uncommented.conf", pars, MotionCamera1.getInstance());
        cfge.loadDefaultConfigFile("J://test/default.conf", pars, MotionCamera2.getInstance());
        MotionCamera1.getInstance().setConfigPath("J://test/cam1");
        MotionCamera2.getInstance().setConfigPath("J://test/cam2");
        pars.createConfigFolders(MotionCamera1.getInstance().getConfigPath());
        pars.createConfigFolders(MotionCamera2.getInstance().getConfigPath());
        System.out.println(MotionCamera1.getInstance().getConfigPath());

        
        cfge.editConfigList(pars, "J://test/uncommented.conf", MotionCamera1.getInstance(), "test", "test", "test", "test", "test", "brightnesstest", "test", "test", "test", "test");

        cfge.createConfig(pars, MotionCamera1.getInstance());
        cfge.createConfig(pars, MotionCamera2.getInstance());
      //  pars.destroyConfigFolders(MotionCamera1.getInstance().getConfigPath() + "/motion.conf");

    }

}
