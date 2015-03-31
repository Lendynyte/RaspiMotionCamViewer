package camcontrols.saving;

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
        XMLIo xml = new XMLIo();

        xml.createXMLFile("c://test/test.xml", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test");

        xml.LoadXMLFile("c://test/test.xml");
    }
}
