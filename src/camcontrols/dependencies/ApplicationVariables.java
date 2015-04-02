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

}
