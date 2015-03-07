package camcontrols.configEditing;

import java.util.ArrayList;

/**
 *
 * @author Dominik
 * @deprecated 
 */
public class CamControl
{

    //TODO(Dominik):this thing needs to be reworked
    private Parser parser;

    /**
     * enum example probably not actually using daemon in menus
     */
    public enum startDaemon
    {

        ON("daemon on"),
        OFF("daemon off");
        //TODO:can I even use getter here? 
        private final String status;

        private startDaemon(String status)
        {
            this.status = status;
        }

        public String getStatus()
        {
            return this.status;
        }
    }

    /**
     * enum to store camera width options subject to change
     */
    public enum camResWidth
    {

        FULLHD("1920"),
        HDREADY("1280"),
        STANDART("800"),
        TV("640");
        //TODO:can I even use getter here? 
        private final String width;

        private camResWidth(String width)
        {
            this.width = width;
        }

        public String getWidth()
        {
            return this.width;
        }
    }

    /**
     * enum to store camera height options subject to change
     */
    public enum camResHeight
    {

        FULLHD("1080"),
        HDREADY("720"),
        STANDART("600"),
        TV("480");
        //TODO:can I even use getter here? 
        private final String height;

        private camResHeight(String height)
        {
            this.height = height;
        }

        public String getHeight()
        {
            return this.height;
        }
    }

    //This will need rework after I get some gui up and running
    /**
     * This method is used for changing resolution of cam by changing values in
     * parsed list using set values
     *
     * @param config list parsed from config file
     * @param resolutionType type of resolution comming from gui
     */
    public void nastavRozliseni(ArrayList<String> config, String resolutionType)
    {
        for (int i = 0; i < config.size() - 1; i++)
        {
            if (config.get(i).contains("width"))
            {
                switch (resolutionType)
                {
                    case "HD":
                        parser.prepisRadek(config, camResHeight.HDREADY.getHeight(), i);
                        break;

                    case "FULL HD":
                        parser.prepisRadek(config, camResHeight.FULLHD.getHeight(), i);
                        break;

                    case "STANDARD":
                        parser.prepisRadek(config, camResHeight.STANDART.getHeight(), i);
                        break;

                    case "TV":
                        parser.prepisRadek(config, camResHeight.TV.getHeight(), i);
                        break;

                    default:
                        parser.prepisRadek(config, "480", i);
                        break;
                }

            }

            if (config.get(i).contains("height"))
            {
                switch (resolutionType)
                {
                    case "FULL HD":
                        parser.prepisRadek(config, camResWidth.FULLHD.getWidth(), i);
                        break;

                    case "HD":
                        parser.prepisRadek(config, camResWidth.HDREADY.getWidth(), i);
                        break;
                    case "STANDARD":
                        parser.prepisRadek(config, camResWidth.STANDART.getWidth(), i);
                        break;
                    case "TV":
                        parser.prepisRadek(config, camResWidth.TV.getWidth(), i);
                        break;
                    default:
                        parser.prepisRadek(config, camResWidth.TV.getWidth(), i);
                        break;
                }
            }
        }
    }
}
