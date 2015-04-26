package camcontrols.dependencies;

/**
 *
 * @author Dominik Pauli
 * @version 0.2
 */
public class ApplicationVariables
{

    private static ApplicationVariables instance = null;

    protected ApplicationVariables()
    {
        //to stop instantatiaon
        this.isHelpOpen = false;
        this.isOptionsOpen = false;
        this.isCreatingFile = false;
        this.isSendingFile = false;
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

    //variable determining if help menu is open
    private boolean isHelpOpen;

    //variable determining if application settings menu is open
    private boolean isSettingsOpen;

    //applicatin is creating file
    private boolean isCreatingFile;

    //application is sending file
    private boolean isSendingFile;

    //save directory path
    private String xmlSaveDirectoryPath;

    //install directory path
    private String installDirectoryPath;

    public boolean isIsCreatingFile()
    {
        return isCreatingFile;
    }

    public void setIsCreatingFile(boolean isCreatingFile)
    {
        this.isCreatingFile = isCreatingFile;
    }

    public boolean isIsSendingFile()
    {
        return isSendingFile;
    }

    public void setIsSendingFile(boolean isSendingFile)
    {
        this.isSendingFile = isSendingFile;
    }

    public boolean isIsSettingsOpen()
    {
        return isSettingsOpen;
    }

    public void setIsSettingsOpen(boolean isSettingsOpen)
    {
        this.isSettingsOpen = isSettingsOpen;
    }

    public String getXmlSaveDirectoryPath()
    {
        return xmlSaveDirectoryPath;
    }

    public void setXmlSaveDirectoryPath(String xmlSaveDirectoryPath)
    {
        this.xmlSaveDirectoryPath = xmlSaveDirectoryPath;
    }

    public String getInstallDirectoryPath()
    {
        return installDirectoryPath;
    }

    public void setInstallDirectoryPath(String installDirectoryPath)
    {
        this.installDirectoryPath = installDirectoryPath;
    }

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
