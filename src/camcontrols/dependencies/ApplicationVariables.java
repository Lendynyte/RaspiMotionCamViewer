package camcontrols.dependencies;

/**
 *
 * @author Dominik Pauli
 * @version 0.1
 */
public class ApplicationVariables
{

    private static ApplicationVariables instance = null;

    protected ApplicationVariables()
    {
        //to stop instantatiaon
        this.isHelpOpen = false;
        this.isOptionsOpen = false;
    }

    public static ApplicationVariables getInstance()
    {
        if (instance == null)
        {
            instance = new ApplicationVariables();
        }
        return instance;
    }

    //variable determining if options menu is open
    private boolean isOptionsOpen;

    //variable determining of help menu is open
    private boolean isHelpOpen;

    public boolean isIsOptionsOpen()
    {
        return isOptionsOpen;
    }

    public void setIsOptionsOpen(boolean isOptionsOpen)
    {
        this.isOptionsOpen = isOptionsOpen;
    }

    public boolean isIsHelpOpen()
    {
        return isHelpOpen;
    }

    public void setIsHelpOpen(boolean isHelpOpen)
    {
        this.isHelpOpen = isHelpOpen;
    }

    /**
     * This method checks operating system and returns 1 for windows 2 for linux
     * 0 for another unknown operating system.
     *
     * @return 1 for windows, 2 for linux
     */
    public int getOperatingSystem()
    {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win"))
        {
            return 1;
        }
        else if (os.contains("nix") || os.contains("nux") || os.contains("aix"))
        {
            return 2;
        }
        else
        {
            System.err.println("Unknown operating system ...");
            return 0;
        }
    }

}
