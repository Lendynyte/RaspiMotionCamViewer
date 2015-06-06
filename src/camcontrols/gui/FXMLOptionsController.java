package camcontrols.gui;

import camcontrols.comunication.CameraAvailabilityTester;
import camcontrols.comunication.SshCamerahandler;
import camcontrols.configEditing.ConfigEditor;
import camcontrols.configEditing.Parser;
import camcontrols.dependencies.ApplicationVariables;
import camcontrols.dependencies.MotionCamera1;
import camcontrols.dependencies.MotionCamera2;
import camcontrols.dependencies.MotionCameraInterface;
import camcontrols.saving.XMLCameraHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dominik Pauli
 * @version 0.3
 */
public class FXMLOptionsController implements Initializable
{

    //<editor-fold defaultstate="collapsed" desc="Control buttons">
    @FXML
    private Button btnApply;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnLoad;

    @FXML
    private Button btnClose;
//</editor-fold>

    //main anchor pane
    @FXML
    private AnchorPane mainPane;

    //<editor-fold defaultstate="collapsed" desc="Combo boxes">
    //Combo box for picking camera resolution
    @FXML
    private ComboBox<String> cBoxResolution;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Checkboxes">
    //auto brightness on/off
    @FXML
    private CheckBox chckAutoBrightness;

    //alert email enable
    @FXML
    private CheckBox chckAlerMail;

    //enable remote image storage
    @FXML
    private CheckBox chckRemoteStore;

    @FXML
    private CheckBox chckEnableBrightness;

    @FXML
    private CheckBox chckEnableContrast;

    @FXML
    private CheckBox chckEnableHue;

    @FXML
    private CheckBox chckEnableSaturation;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Text fields">
//framerate text field
    @FXML
    private TextField tfFramerate;

    //URL to motion stream
    @FXML
    private TextField tfCamURL;

    //email for alerts
    @FXML
    private TextField tfAlertEmail;

    //path to remote camera storage
    @FXML
    private TextField tfremoteStoragePath;

    //camera display name
    @FXML
    private TextField tfCamName;

    @FXML
    private TextField tfResult;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Sliders">
//Slider for camera brightness
    @FXML
    private Slider sldrBrightness;

    //Slider for camera contrast
    @FXML
    private Slider sldrContrast;

    //Slider for camera hue
    @FXML
    private Slider sldrHue;

    //Slider for camera saturation
    @FXML
    private Slider sldrSaturation;

    //Slider for camera quality
    @FXML
    private Slider sldrQuality;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Tooltips">
    //tooltip for brightness slider
    @FXML
    private Tooltip tltpBrightSldr;

    //tooltip for contrast slider
    @FXML
    private Tooltip tltpContSldr;

    //tooltip for hue slider
    @FXML
    private Tooltip tltpHueSldr;

    //tooltip for saturation slider
    @FXML
    private Tooltip tltpSaturSldr;

    //tooltip for quality slider
    @FXML
    private Tooltip tltpQualSldr;
    //</editor-fold>

    //sshHandle instance
    private SshCamerahandler sshHandler;

    //variable for checking data from form
    private boolean isError = false;

    //<editor-fold defaultstate="collapsed" desc="Button handling">
    @FXML
    private void handleButtonApply(final ActionEvent event)
    {
       // if (pingCamera())
        {
            switch (this.mainPane.getScene().getRoot().getId())
            {
                case "1":
                {
                    applyToCamera(MotionCamera1.getInstance(), this.mainPane.getScene().getRoot().getId());
                    //saveToXMLSaveFile(MotionCamera1.getInstance(), this.mainPane.getScene().getRoot().getId());
                    applyToConfigFile(MotionCamera1.getInstance());
                    //this.tfResult.setText("Configuration applied to camera ...");
                    //TODO(Dominik): uncomment
                    //applySettingsToCamera(MotionCamera1.getInstance());

                    //TODO(Dominik): check if restart works this fast
                    // restartCameraAction();
                    //TODO(Dominik): have to change url in conf this is here for now
                    MotionCamera1.getInstance().setURL("192.168.1.10");

                }
                break;
                case "2":
                {
                    applyToCamera(MotionCamera2.getInstance(), this.mainPane.getScene().getRoot().getId());
                    //saveToXMLSaveFile(MotionCamera2.getInstance(), this.mainPane.getScene().getRoot().getId());
                    applyToConfigFile(MotionCamera2.getInstance());
                    //this.tfResult.setText("Configuration applied to camera ...");
                    //applySettingsToCamera(MotionCamera2.getInstance());

                    //TODO(Dominik): check if restart works this fast
                    // restartCameraAction();
                    //TODO(Dominik): have to change url in conf this is here for now
                    MotionCamera1.getInstance().setURL("192.168.1.10");
                }
                break;
            }
        }
       /* else
        {
            // this.tfResult.setText("Unable to reach camera to apply settings ...");
            System.out.println("UNABLE to ping");
        }*/
    }

    /**
     * This method closes the options window and cancels all settings
     *
     * @param event button clicke
     */
    @FXML
    private void handleButtonCancel(final ActionEvent event)
    {
        ApplicationVariables.getInstance().setIsOptionsOpen(false);
        this.mainPane.getScene().getWindow().hide();
    }

    /**
     * This method closes the options window
     *
     * @param event button clicke
     */
    @FXML
    private void handleButtonClose(final ActionEvent event)
    {
        ApplicationVariables.getInstance().setIsOptionsOpen(false);
        this.mainPane.getScene().getWindow().hide();
    }

    /**
     *
     * @param event button clicked
     */
    @FXML
    private void handleButtonResetToDefault(final ActionEvent event)
    {
        this.setDefaultValues();
        this.setDefaultSingletonValues();
    }

    @FXML
    private void handleButtonSave(final ActionEvent event)
    {
        switch (this.mainPane.getScene().getRoot().getId())
        {
            case "1":
            {
                applyToCamera(MotionCamera1.getInstance(), this.mainPane.getScene().getRoot().getId());
                saveToXMLSaveFile(MotionCamera1.getInstance(), this.mainPane.getScene().getRoot().getId());

            }
            break;
            case "2":
            {
                applyToCamera(MotionCamera2.getInstance(), this.mainPane.getScene().getRoot().getId());
                saveToXMLSaveFile(MotionCamera2.getInstance(), this.mainPane.getScene().getRoot().getId());
            }
            break;
        }
    }

    @FXML
    private void handleButtonLoad(final ActionEvent event)
    {
        loadXMLSave();
        loadSingletonToForm();
    }

    //</editor-fold>
    //TODO(Dominik):lookup documentation for enable contrast etc....
    //TODO(Dominik):maybeoly 1 brightnesss checkbox
    //TODO(Dominik):rewrite this one
    //SLIDER HANDLING START
    /**
     *
     * @return
     */
    private int getFXMLSldrBrightness()
    {
        if (this.sldrBrightness.disableProperty().getValue())
        {
            return 0;
        }
        else
        {
            return getValueFromPercentage(this.sldrBrightness.getValue());
        }
    }

    /**
     *
     * @return
     */
    private int getFXMLSldrContrast()
    {
        if (this.sldrContrast.disableProperty().getValue())
        {
            return 0;
        }
        else
        {
            return getValueFromPercentage(this.sldrContrast.getValue());
        }
    }

    /**
     *
     * @return
     */
    private int getFXMLSldrHue()
    {
        if (this.sldrHue.disableProperty().getValue())
        {
            return 0;
        }
        else
        {
            return getValueFromPercentage(this.sldrHue.getValue());
        }
    }

    /**
     *
     * @return
     */
    private int getFXMLSldrSaturation()
    {
        if (this.sldrSaturation.disableProperty().getValue())
        {
            return 0;
        }
        else
        {
            return getValueFromPercentage(this.sldrSaturation.getValue());
        }
    }

    /**
     *
     * @return
     */
    private int getFXMLSldrQuality()
    {
        return (int) this.sldrQuality.getValue();
    }
    //SLIDER HANDLING END

    /**
     * This method calculates value of percentage from number 255 it is used for
     * calculating slider values for configuration file
     *
     * @param percentage percentage to calculate from
     * @return value from percentage
     */
    private int getValueFromPercentage(double percentage)
    {
        return (int) Math.floor(2.55 * percentage);
    }

    /**
     *
     */
    private void setDefaultSingletonValues()
    {
        switch (this.mainPane.getScene().getRoot().getId())
        {
            case "1":
            {
                new ConfigEditor().loadDefaultConfigFile(new Parser(), MotionCamera1.getInstance());
                System.out.println("Default config file loaded ...");
            }
            break;
            case "2":
            {
                new ConfigEditor().loadDefaultConfigFile(new Parser(), MotionCamera2.getInstance());
                System.out.println("Default config file loaded ...");
            }
            break;
        }
    }

    /**
     * This method sets default values for options form
     */
    private void setDefaultValues()
    {
        this.chckEnableBrightness.selectedProperty().setValue(Boolean.TRUE);
        this.chckEnableContrast.selectedProperty().setValue(Boolean.TRUE);
        this.chckEnableHue.selectedProperty().setValue(Boolean.TRUE);
        this.chckEnableSaturation.selectedProperty().setValue(Boolean.TRUE);
        this.sldrBrightness.disableProperty().setValue(Boolean.TRUE);
        this.sldrContrast.disableProperty().setValue(Boolean.TRUE);
        this.sldrHue.disableProperty().setValue(Boolean.TRUE);
        this.sldrSaturation.disableProperty().setValue(Boolean.TRUE);
        this.cBoxResolution.getSelectionModel().selectFirst();
        this.tfFramerate.setText(null);
        this.chckAutoBrightness.selectedProperty().setValue(Boolean.FALSE);
        this.sldrBrightness.setValue(0);
        this.sldrContrast.setValue(0);
        this.sldrHue.setValue(0);
        this.sldrSaturation.setValue(0);
        this.sldrQuality.setValue(75);
        this.tfCamName.setText(null);
        this.tfCamURL.setText(null);
        this.tfAlertEmail.setText(null);
        this.tfFramerate.setText(null);
        this.tfremoteStoragePath.setText(null);
        this.chckAlerMail.selectedProperty().setValue(Boolean.FALSE);
        this.chckRemoteStore.selectedProperty().setValue(Boolean.FALSE);
    }

    /**
     * This method changes autobrightness camera property and disables
     * brightness property
     *
     * @param event checkobox checked event
     */
    @FXML
    private void handleCheckBoxAutoBrightness(final ActionEvent event)
    {
        if (this.chckAutoBrightness.isSelected())
        {
            this.sldrBrightness.setValue(0);
            this.sldrBrightness.disableProperty().setValue(Boolean.TRUE);
        }
        else
        {
            this.sldrBrightness.disableProperty().setValue(Boolean.FALSE);
        }
    }

    /**
     * This method enables automatic mailing and disables place for email adress
     * if not selected
     *
     * @param event checkobox checked event
     */
    @FXML
    private void handleCheckBoxSendAutoEmail(final ActionEvent event)
    {
        if (this.chckAlerMail.isSelected())
        {
            this.tfAlertEmail.disableProperty().setValue(Boolean.FALSE);
            this.tfAlertEmail.textProperty().setValue(null);
        }
        else
        {
            this.tfAlertEmail.setText(null);
            this.tfAlertEmail.disableProperty().setValue(Boolean.TRUE);
        }
    }

    /**
     * This method enables remote storage or disables if unchecked
     *
     * @param event checkobox checked event
     */
    @FXML
    private void handleCheckBoxRemoteImages(final ActionEvent event)
    {
        if (this.chckRemoteStore.isSelected())
        {
            this.tfremoteStoragePath.disableProperty().setValue(Boolean.FALSE);
        }
        else
        {
            this.tfremoteStoragePath.setText(null);
            this.tfremoteStoragePath.disableProperty().setValue(Boolean.TRUE);
        }
    }

    //TODO(Dominik): remove replaced with auto brightness
    /**
     *
     * @param event
     */
    @FXML
    private void handleCheckBoxEnableBrightness(final ActionEvent event)
    {
        if (this.chckEnableBrightness.isSelected())
        {
            this.sldrBrightness.disableProperty().setValue(Boolean.TRUE);
        }
        else
        {
            this.sldrBrightness.disableProperty().setValue(Boolean.FALSE);
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleCheckBoxEnableContrast(final ActionEvent event)
    {
        if (this.chckEnableContrast.isSelected())
        {
            this.sldrContrast.disableProperty().setValue(Boolean.TRUE);
        }
        else
        {
            this.sldrContrast.disableProperty().setValue(Boolean.FALSE);
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleCheckBoxEnableHue(final ActionEvent event)
    {
        if (this.chckEnableHue.isSelected())
        {
            this.sldrHue.disableProperty().setValue(Boolean.TRUE);
        }
        else
        {
            this.sldrHue.disableProperty().setValue(Boolean.FALSE);
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleCheckBoxEnableSaturation(final ActionEvent event)
    {
        if (this.chckEnableSaturation.isSelected())
        {
            this.sldrSaturation.disableProperty().setValue(Boolean.TRUE);
        }
        else
        {
            this.sldrSaturation.disableProperty().setValue(Boolean.FALSE);
        }
    }

    /**
     * This method initializes Combobox for picking resolution
     */
    private void InitializeCBoxResolution()
    {
        ObservableList<String> resolutions = FXCollections.observableArrayList("1920 x 1080", "1280 x 720", "800 x 600", "640 x 480", "320 x 240");
        this.cBoxResolution.getItems().addAll(resolutions);
        this.cBoxResolution.getSelectionModel().selectFirst();
    }

    /**
     * This methods set the textboxes for email and remote path as uninteractive
     */
    private void initializeTextBoxes()
    {
        this.tfCamName.setText(getCameraName());
        this.tfCamURL.setText("NO URL");
        this.tfFramerate.setText("2");
        this.tfAlertEmail.disableProperty().setValue(Boolean.TRUE);
        this.tfremoteStoragePath.disableProperty().setValue(Boolean.TRUE);
    }

    /**
     *
     * @return
     */
    private String getCameraName()
    {
        switch (this.mainPane.getScene().getRoot().getId())
        {
            case "1":
            {
                return MotionCamera1.getInstance().getName();
            }
            case "2":
            {
                return MotionCamera2.getInstance().getName();
            }
        }
        return "Unnamed Camera";
    }

    //TODO(Dominik): all values have to be set before doing this fix
    //TODO(Dominik): check if this works
    /**
     *
     * @param motionCamera
     * @param camId
     */
    private void saveToXMLSaveFile(MotionCameraInterface motionCamera, String camId)
    {
        XMLCameraHandler xmlHandler = new XMLCameraHandler();

        if (ApplicationVariables.getInstance().getOperatingSystem() == 1)
        {
            new Parser().createConfigFolders("C://CamControls/src/");
            createXMLSave(motionCamera, "C://CamControls/src/cam" + camId + "Save.xml");
        }

        //LINUX
        else if (ApplicationVariables.getInstance().getOperatingSystem() == 2)
        {
            new Parser().createConfigFolders("/home/pi/CamControls/src/");
            createXMLSave(motionCamera, "/home/pi/CamControls/src/cam" + camId + "Save.xml");
        }

        //OTHER
        else
        {
            System.err.println("Unknown operating system ...");
            System.err.println("Creating save file failed ...");
        }
    }

    /**
     *
     * @param MotionCamera
     * @param savePath
     */
    private void createXMLSave(MotionCameraInterface MotionCamera, String savePath)
    {
        new XMLCameraHandler().createCamSave(MotionCamera, savePath);
    }

    //TODO(Dominik): check if works
    /**
     *
     * @param motionCamera
     * @param camId
     */
    private void applyToCamera(MotionCameraInterface motionCamera, String camId)
    {
        if (!isError)
        {
            saveToCameraSingleton(motionCamera, camId);
            this.isError = false;
        }
    }

    /**
     *
     */
    private void saveToCameraSingleton(MotionCameraInterface MotionCamera, String camNumberToName)
    {
        MotionCamera.setCamWidth(getFXMLResolutionWidth());
        MotionCamera.setCamHeight(getFXMLResolutionHeight());
        MotionCamera.setCamFramerate(getFXMLFramerate());
        MotionCamera.setCamAutoBrightness(getFXMLAutoBrightness());
        MotionCamera.setCamBrightness(getFXMLSldrBrightness());
        MotionCamera.setCamConstrast(getFXMLSldrContrast());
        MotionCamera.setCamHue(getFXMLSldrHue());
        MotionCamera.setCamSaturation(getFXMLSldrSaturation());

        MotionCamera.setURL(getFXMLURL());
        //TODO(Dominik): implement remote and emails

        MotionCamera.setName(getFXMLCamName(camNumberToName));
    }

    /**
     * This metod gets resolution from FXML optioons form and sets it in
     * Motioncam singleton
     */
    private int getFXMLResolutionWidth()
    {
        int width = 1280;
        switch (this.cBoxResolution.getSelectionModel().getSelectedIndex())
        {
            case 0:
                width = 1920;
                break;
            case 1:
                width = 1280;
                break;
            case 2:
                width = 800;
                break;
            case 3:
                width = 640;
                break;
            case 4:
                width = 320;
                break;
        }
        return width;
    }

    /**
     * This metod gets resolution from FXML optioons form and sets it in
     * Motioncam singleton
     */
    private int getFXMLResolutionHeight()
    {
        int height = 720;
        switch (this.cBoxResolution.getSelectionModel().getSelectedIndex())
        {
            case 0:
                height = 1080;
                break;
            case 1:
                height = 720;
                break;
            case 2:
                height = 600;
                break;
            case 3:
                height = 480;
                break;
            case 4:
                height = 240;
                break;
        }
        return height;
    }

    /**
     * This method gets Framerate variable from fxml options form
     *
     * @param camHandle
     */
    private int getFXMLFramerate()
    {
        //TODO(Dominik): mabe opnly parse once ?
        int i = 2;
        String fromForm = this.tfFramerate.getText().trim();
        if (isNumber(fromForm))
        {
            i = Integer.parseInt(fromForm);
            if (i <= 100 && i >= 0)
            {
                return i;
            }
            else
            {
                tfFramerate.setText("Out of range! (0 - 100)");
            }
        }
        else
        {
            tfFramerate.setText("Not valid number!");
        }
        this.isError = true;
        return -1;
    }

    /**
     *
     * @return
     */
    private boolean getFXMLAutoBrightness()
    {
        return this.chckAutoBrightness.isSelected();
    }

    /**
     *
     * @return
     */
    private String getFXMLURL()
    {
        if (this.tfCamURL.getText().isEmpty())
        {
            //TODO(Dominik): return right camera name
            return "Camera";
        }
        return this.tfCamURL.getText().trim();
    }

    /**
     *
     * @return
     */
    private boolean getFXMLAlerEmail()
    {
        return this.chckAlerMail.isSelected();
    }

    /**
     *
     * @return
     */
    private String getFXMLCamName(String camNumber)
    {
        if (!this.tfCamName.getText().isEmpty())
        {
            return this.tfCamName.getText().trim();
        }
        else
        {
            return "Camera" + camNumber;
        }
    }

    /**
     * this method check if text is a number and returns true if it is
     *
     * @param text
     * @return
     */
    private boolean isNumber(String text)
    {
        try
        {
            int isNumber = Integer.parseInt(text);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    /**
     * This method is called when loading XML save to singleton it handles
     * calling of method loaxXMLtoSingleton for right camera and with right path
     */
    private void loadXMLSave()
    {
        switch (this.mainPane.getScene().getRoot().getId())
        {
            case "1":
            {
                loadXMLtoSingleton(MotionCamera1.getInstance(), "1");
            }
            break;
            case "2":
            {
                loadXMLtoSingleton(MotionCamera2.getInstance(), "2");
            }
            break;
        }
    }

    //TODO(Dominik): check this works after update
    /**
     * This method loads xml to for if xml save is not found it loads default
     * configuration
     *
     * @param XMLPath
     * @param MotionCamera
     */
    private void loadXMLtoSingleton(MotionCameraInterface MotionCamera, String camId)
    {
        XMLCameraHandler xmlHandler = new XMLCameraHandler();

        //WINDOWS
        if (ApplicationVariables.getInstance().getOperatingSystem() == 1)
        {
            if (xmlHandler.checkForXMLSave("C://CamControls/src/cam" + camId + "Save.xml"))
            {
                xmlHandler.LoadXMLFile("C://CamControls/src/cam" + camId + "Save.xml", MotionCamera);
            }
            else
            {
                System.out.println("XML file not found loading default configuration ...");
                this.setDefaultSingletonValues();
                this.setDefaultValues();
            }
        }

        //LINUX
        else if (ApplicationVariables.getInstance().getOperatingSystem() == 2)
        {

            if (xmlHandler.checkForXMLSave("/home/pi/CamControls/src/cam" + camId + "Save.xml"))
            {
                xmlHandler.LoadXMLFile("/home/pi/CamControls/src/cam" + camId + "Save.xml", MotionCamera);
            }
            else
            {
                System.out.println("XML file not found loading default configuration ...");
                this.setDefaultSingletonValues();
                this.setDefaultValues();
            }
        }

        //OTHER
        else
        {
            System.err.println("Unknown operating system ...");
            System.out.println("XML file not found loading default configuration ...");
            this.setDefaultSingletonValues();
            this.setDefaultValues();
        }
    }

    /**
     *
     */
    private void loadSingletonToForm()
    {
        switch (this.mainPane.getScene().getRoot().getId())
        {
            case "1":
            {
                loadToForm(MotionCamera1.getInstance());
            }
            break;
            case "2":
            {
                loadToForm(MotionCamera2.getInstance());
            }
            break;
        }
    }

    /**
     *
     */
    private void loadToForm(MotionCameraInterface MotionCamera)
    {
        //TODO(Dominik):read resolution and set it in combo box
        this.tfFramerate.setText(Integer.toString(MotionCamera.getCamFramerate()));
        this.chckAutoBrightness.selectedProperty().setValue(MotionCamera.isCamAutoBrightness());
        //TODO(Dominik):recalculate these values back to slider values create method
        this.sldrBrightness.setValue(MotionCamera.getCamBrightness());
        this.sldrContrast.setValue(MotionCamera.getCamConstrast());
        this.sldrHue.setValue(MotionCamera.getCamHue());
        this.sldrSaturation.setValue(MotionCamera.getCamSaturation());
        this.sldrQuality.setValue(MotionCamera.getCamQuality());
        this.tfCamURL.setText(MotionCamera.getURL());
        this.tfCamName.setText(MotionCamera.getName());
    }

    //TODO(Dominik): THESE THING NEEDS TO BE MADE FOR LINUX/WINDOWS AND I HAVE TO PICK RIGHT MOTIONCAMERA BASED ON THE RIGHT WINDOW LIKE THIS IT ONLY WORKS FOR CAM 1
    /**
     *
     * @param motionCamera
     */
    private void changeConfigItems(MotionCameraInterface motionCamera)
    {
        //TODO(Dominik): check if this works
        String abr;
        if (motionCamera.isCamAutoBrightness())
        {
            abr = "on";
        }
        abr = "off";

        //WINDOWS
        if (ApplicationVariables.getInstance().getOperatingSystem() == 1)
        {

            new ConfigEditor().editConfigList(new Parser(), motionCamera, "C://CamControls/src/save/cam" + this.mainPane.getScene().getRoot().getId() + "/motion.conf", motionCamera.getCamWidth() + "",
                    motionCamera.getCamHeight() + "", motionCamera.getCamRotation() + "", motionCamera.getCamFramerate() + "", abr, motionCamera.getCamBrightness() + "", motionCamera.getCamConstrast() + "",
                    motionCamera.getCamHue() + "", motionCamera.getCamSaturation() + "", motionCamera.getCamQuality() + "");
        }

        //LINUX MAINLY MADE FOR RASPBERRY PI USER PI
        else if (ApplicationVariables.getInstance().getOperatingSystem() == 2)
        {

            new ConfigEditor().editConfigList(new Parser(), motionCamera, "/home/pi/CamControls/src/save/cam" + this.mainPane.getScene().getRoot().getId() + "/motion.conf", motionCamera.getCamWidth() + "",
                    motionCamera.getCamHeight() + "", motionCamera.getCamRotation() + "", motionCamera.getCamFramerate() + "", abr, motionCamera.getCamBrightness() + "", motionCamera.getCamConstrast() + "",
                    motionCamera.getCamHue() + "", motionCamera.getCamSaturation() + "", motionCamera.getCamQuality() + "");
        }

        //UNKNOWN OPERATING SYSTEM
        else
        {
            System.err.println("Cannot find install directory ...");
        }
    }

    //TODO(Dominik): check if this works after update
    /**
     * This method creates config for camera in path from MotionCamera URL
     *
     * @param motionCamera
     */
    private void applyToConfigFile(MotionCameraInterface motionCamera)
    {
        changeConfigItems(motionCamera);
        new ConfigEditor().createConfig(new Parser(), motionCamera, this.mainPane.getScene().getRoot().getId());
    }

    //TODO(Dominik): check if this works after update
    //TODO(Dominik): check parameters 
    /**
     *
     * @param motionCamera
     */
    private void applySettingsToCamera(MotionCameraInterface motionCamera)
    {
        //TODO(Dominik): use interface or picj right camera
        if (pingCamera())
        {
            //WINDOWS
            if (ApplicationVariables.getInstance().getOperatingSystem() == 1)
            {
                new SshCamerahandler().sendConfFile(motionCamera, "C://CamControls/src/save/cam" + this.mainPane.getScene().getRoot().getId() + "/motion.conf", 10000);
            }

            //LINUX
            else if (ApplicationVariables.getInstance().getOperatingSystem() == 2)
            {
                new SshCamerahandler().sendConfFile(motionCamera, "/home/pi/CamControls/src/save/cam" + this.mainPane.getScene().getRoot().getId() + "/motion.conf", 10000);
            }

            //OTHER
            else
            {
                System.err.println("Unknown operating system ...");
            }

        }
    }

    /**
     * This method resetarts camera chosen by the window id and calls ethod from
     * sshCameraHandler to restart it over ssh
     *
     */
    private void restartCameraAction()
    {
        if (pingCamera())
        {
            switch (this.mainPane.getScene().getRoot().getId())
            {
                case "1":
                    new SshCamerahandler().restartCamera(MotionCamera1.getInstance());
                    break;

                case "2":
                    new SshCamerahandler().restartCamera(MotionCamera2.getInstance());
                    break;
            }
        }
        else
        {
            System.err.println("Unable to restart camera ...");
        }
    }

    /**
     * This method pings camera using ping console command and returns result as
     * boolean
     *
     * @return True if camera is reachable false if it isnt
     */
    private boolean pingCamera()
    {
        switch (this.mainPane.getScene().getRoot().getId())
        {
            case "1":
                return new CameraAvailabilityTester().isReachable("http://" + MotionCamera1.getInstance().getURL(), 10000);
            case "2":
                return new CameraAvailabilityTester().isReachable("http://" + MotionCamera2.getInstance().getURL(), 10000);
            default: //should never happen
                System.err.println("Wrong window handle ...");
                return false;
        }
    }

    //TODO(Dominik): PATHS ARE BROKEN HAVE TO FIX BUT IT WORKS I HAVE NO IDE HOW
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //TODO(Dominik): remove later and load 
        //WINDOWS
        //  MotionCamera1.getInstance().setConfigPath("j://test/cam1");
        //  MotionCamera2.getInstance().setConfigPath("j://test/cam2");
        // ApplicationVariables.getInstance().setInstallDirectoryPath("j://test");
        //  ApplicationVariables.getInstance().setXmlSaveDirectoryPath("j://test/cam1");
        //RASPBERRY PI 
        MotionCamera1.getInstance().setConfigPath("/home/pi");
        //MotionCamera1.getInstance().setConfigPath("/etc");
        //MotionCamera2.getInstance().setConfigPath("/etc");

        MotionCamera1.getInstance().setURL("192.168.1.10");
        MotionCamera1.getInstance().setCamLogin("pi");
        MotionCamera1.getInstance().setCamPassword("raspberry");

        ApplicationVariables.getInstance().setInstallDirectoryPath("/home/pi/CamControls");
        ApplicationVariables.getInstance().setXmlSaveDirectoryPath("/home/pi/CamControls");

        Platform.runLater(() ->
        {
            initializeTextBoxes();
            InitializeCBoxResolution();

            //loadXMLSave();
        });
        ApplicationVariables.getInstance().setIsOptionsOpen(true);
    }

}
