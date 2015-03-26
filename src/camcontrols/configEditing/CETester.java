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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Parser pars = new Parser();

        ConfigEditor cfge = new ConfigEditor();
        cfge.loadDefaultConfigFile("C://test/default.conf", pars);
        MotionCamera1.getInstance().setConfigPath("C://test/cam1");
        MotionCamera2.getInstance().setConfigPath("C://test/cam2");
        pars.createConfigFolders(MotionCamera1.getInstance().getConfigPath());
        pars.createConfigFolders(MotionCamera2.getInstance().getConfigPath());
        System.out.println(MotionCamera1.getInstance().getConfigPath());
        //TODO(Dominik):broken
        cfge.editConfigList("test", "test", "test", "test", "test", "test", "test", "test", "test", "test", pars);
        cfge.createConfig("cam1", pars);
        cfge.createConfig("cam2", pars);
      //  pars.destroyConfigFolders(MotionCamera1.getInstance().getConfigPath() + "/motion.conf");
        
    }

}
