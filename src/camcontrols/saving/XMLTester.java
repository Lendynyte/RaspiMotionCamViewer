package camcontrols.saving;

import camcontrols.dependencies.MotionCamera1;

/**
 *
 * @author Dominik
 * @deprecated
 */
public class XMLTester
{
    //TODO(Dominik): REMOVE

    public static void main(String[] args)
    {
        XMLCameraHandler xml = new XMLCameraHandler();

        xml.createXMLFile("c://test/test.xml", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test");

        xml.LoadXMLFile("c://test/test.xml", MotionCamera1.getInstance());
        
        System.out.println(MotionCamera1.getInstance().getName());
        System.out.println(MotionCamera1.getInstance().getHandle());
        System.out.println(MotionCamera1.getInstance().getConfigPath());
        System.out.println(MotionCamera1.getInstance().getURL());
    }
}
