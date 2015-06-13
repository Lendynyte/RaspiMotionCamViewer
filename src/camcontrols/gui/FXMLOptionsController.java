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
 * @version 0.6
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

    @FXML
    private TextField tfMailLogin;

    @FXML
    private TextField tfMailPass;

    @FXML
    private TextField tfFTPURL;

    @FXML
    private TextField tfFTPLogin;

    @FXML
    private TextField tfFTPPass;

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

    //variable for turning experimental values on/off
    private boolean isExperimental;

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
                    saveToXMLSaveFile(MotionCamera1.getInstance(), this.mainPane.getScene().getRoot().getId());
                    applyToConfigFile(MotionCamera1.getInstance());
                    //this.tfResult.setText("Configuration applied to camera ...");
                    //applySettingsToCamera(MotionCamera1.getInstance());

                    //  new SshCamerahandler().restartMotion(MotionCamera1.getInstance());
                    //  new SshCamerahandler().runMotion(MotionCamera1.getInstance(), 1000000);
                    //TODO(Dominik): check if restart works this fast
                    //restartCameraAction();
                }
                break;
                case "2":
                {
                    applyToCamera(MotionCamera2.getInstance(), this.mainPane.getScene().getRoot().getId());
                    saveToXMLSaveFile(MotionCamera2.getInstance(), this.mainPane.getScene().getRoot().getId());
                    applyToConfigFile(MotionCamera2.getInstance());
                    //this.tfResult.setText("Configuration applied to camera ...");
                    applySettingsToCamera(MotionCamera2.getInstance());

                    new SshCamerahandler().restartMotion(MotionCamera2.getInstance());
                    new SshCamerahandler().runMotion(MotionCamera2.getInstance(), 1000000);

                    //TODO(Dominik): check if restart works this fast
                    //restartCameraAction();
                }
                break;
            }
        }
        // else
        {
            // this.tfResult.setText("Unable to reach camera to apply settings ...");
            System.out.println("UNABLE to ping");
        }
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
        this.tfFramerate.setText("2");
        this.chckAutoBrightness.selectedProperty().setValue(Boolean.FALSE);
        this.sldrBrightness.setValue(0);
        this.sldrContrast.setValue(0);
        this.sldrHue.setValue(0);
        this.sldrSaturation.setValue(0);
        this.sldrQuality.setValue(75);
        this.tfCamName.setText("Camera " + this.mainPane.getScene().getRoot().getId());
        this.tfCamURL.setText("192.168.1.102");
        this.tfMailLogin.setText(null);
        this.tfMailPass.setText(null);
        this.tfFTPLogin.setText(null);
        this.tfFTPPass.setText(null);
        this.tfFTPURL.setText(null);
        initializeRemoteAndMail();
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
     *
     * @param event
     */
    @FXML
    private void handleCheckBoxEnableMail(final ActionEvent event)
    {
        if (this.chckAlerMail.isSelected())
        {
            this.tfMailLogin.disableProperty().setValue(Boolean.FALSE);
            this.tfMailPass.disableProperty().setValue(Boolean.FALSE);
        }
        else
        {
            this.tfMailLogin.disableProperty().setValue(Boolean.TRUE);
            this.tfMailPass.disableProperty().setValue(Boolean.TRUE);
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleCheckBoxEnableRemoteStorage(final ActionEvent event)
    {
        if (this.chckRemoteStore.isSelected())
        {
            this.tfFTPLogin.disableProperty().setValue(Boolean.FALSE);
            this.tfFTPPass.disableProperty().setValue(Boolean.FALSE);
            this.tfFTPURL.disableProperty().setValue(Boolean.FALSE);
        }
        else
        {
            this.tfFTPLogin.disableProperty().setValue(Boolean.TRUE);
            this.tfFTPPass.disableProperty().setValue(Boolean.TRUE);
            this.tfFTPURL.disableProperty().setValue(Boolean.TRUE);
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
        this.tfFramerate.setText("10");
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

    private String getMailLogin()
    {
        return this.tfMailLogin.getText().trim();
    }

    private String getMailPassword()
    {
        return this.tfMailPass.getText().trim();
    }

    private String getFTPURL()
    {
        return this.tfFTPURL.getText().trim();
    }

    private String getFTPLogin()
    {
        return this.tfFTPLogin.getText().trim();
    }

    private String getFTPPassword()
    {
        return this.tfFTPPass.getText().trim();
    }

    //TODO(Dominik): all values have to be set before doing this fix
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
        //TODO(Dominik): mabe only parse once?
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
     *
     */
    private void initializeRemoteAndMail()
    {
        //e-mail
        this.chckAlerMail.selectedProperty().setValue(Boolean.FALSE);
        this.tfMailLogin.disableProperty().setValue(Boolean.TRUE);
        this.tfMailPass.disableProperty().setValue(Boolean.TRUE);

        //remote storage
        this.chckRemoteStore.selectedProperty().setValue(Boolean.FALSE);
        this.tfFTPLogin.disableProperty().setValue(Boolean.TRUE);
        this.tfFTPPass.disableProperty().setValue(Boolean.TRUE);
        this.tfFTPURL.disableProperty().setValue(Boolean.TRUE);
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
        String abr;
        boolean ftpEnabled = false;
        boolean emailEnabled = false;

        if (motionCamera.isCamAutoBrightness())
        {
            abr = "on";
        }
        abr = "off";

        if (!getMailLogin().equals("") && !getMailPassword().equals(""))
        {
            emailEnabled = true;
        }

        if (!getFTPLogin().equals("") && !getFTPPassword().equals("") && !getFTPURL().equals(""))
        {
            ftpEnabled = true;
        }

        //WINDOWS
        if (ApplicationVariables.getInstance().getOperatingSystem() == 1)
        {

            new ConfigEditor().editConfigListEx(new Parser(), motionCamera, "C://CamControls/src/save/cam" + this.mainPane.getScene().getRoot().getId() + "/motion.conf", motionCamera.getCamWidth() + "",
                    motionCamera.getCamHeight() + "", motionCamera.getCamRotation() + "", motionCamera.getCamFramerate() + "", abr, motionCamera.getCamBrightness() + "", motionCamera.getCamConstrast() + "",
                    motionCamera.getCamHue() + "", motionCamera.getCamSaturation() + "", motionCamera.getCamQuality() + "", emailEnabled, this.getMailLogin(), this.getMailPassword(), ftpEnabled, this.getFTPURL(), this.getFTPLogin(), this.getFTPPassword());
        }

        //LINUX MAINLY MADE FOR RASPBERRY PI USER PI
        else if (ApplicationVariables.getInstance().getOperatingSystem() == 2)
        {

            new ConfigEditor().editConfigListEx(new Parser(), motionCamera, "/home/pi/CamControls/src/save/cam" + this.mainPane.getScene().getRoot().getId() + "/motion.conf", motionCamera.getCamWidth() + "",
                    motionCamera.getCamHeight() + "", motionCamera.getCamRotation() + "", motionCamera.getCamFramerate() + "", abr, motionCamera.getCamBrightness() + "", motionCamera.getCamConstrast() + "",
                    motionCamera.getCamHue() + "", motionCamera.getCamSaturation() + "", motionCamera.getCamQuality() + "", true, this.getMailLogin(), this.getMailPassword(), true, this.getFTPURL(), this.getFTPLogin(), this.getFTPPassword());
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

    //TODO(Dominik): test
    /**
     *
     * @param motionCamera
     */
    private void applySettingsToCamera(MotionCameraInterface motionCamera)
    {
        if (pingCamera())
        {
            //WINDOWS
            if (ApplicationVariables.getInstance().getOperatingSystem() == 1)
            {
                new SshCamerahandler().sendConfFile(motionCamera, "C://CamControls/src/save/cam" + this.mainPane.getScene().getRoot().getId() + "/motion.conf", 100000);
                System.out.println("File send ...");
            }

            //LINUX
            else if (ApplicationVariables.getInstance().getOperatingSystem() == 2)
            {
                new SshCamerahandler().sendConfFile(motionCamera, "/home/pi/CamControls/src/save/cam" + this.mainPane.getScene().getRoot().getId() + "/motion.conf", 100000);
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
                //return new CameraAvailabilityTester().isReachable("http://" + MotionCamera1.getInstance().getURL(), 1000000);
                return new CameraAvailabilityTester().isReachable(MotionCamera1.getInstance().getURL(), 1000000);
            case "2":
                //return new CameraAvailabilityTester().isReachable("http://" + MotionCamera2.getInstance().getURL(), 1000000);
                return new CameraAvailabilityTester().isReachable(MotionCamera2.getInstance().getURL(), 1000000);
            default: //should never happen
                System.err.println("Wrong window handle ...");
                return false;
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //RASPBERRY PI 
        MotionCamera1.getInstance().setConfigPath("/etc");
        MotionCamera2.getInstance().setConfigPath("/etc");

        //THESE VALUES ARE SET DURING INITIALIZATION THEY ARE NOT CHANGED 
        //TODO(Dominik): add a way to edit password and login from options menu
        MotionCamera1.getInstance().setURL("192.168.1.102");
        MotionCamera1.getInstance().setCamLogin("pi");
        MotionCamera1.getInstance().setCamPassword("raspberry");
        //NOT USED CURRENTLY
        MotionCamera1.getInstance().setCamRotation(0);

        MotionCamera2.getInstance().setURL("192.168.1.102");
        MotionCamera2.getInstance().setCamLogin("pi");
        MotionCamera2.getInstance().setCamPassword("raspberry");
        //NOT USED CURRENTLY
        MotionCamera2.getInstance().setCamRotation(0);

        //ApplicationVariables.getInstance().setInstallDirectoryPath("/home/pi/CamControls");
        //ApplicationVariables.getInstance().setXmlSaveDirectoryPath("/home/pi/CamControls");
        //EXPERIMENTAL
        this.isExperimental = true;

        Platform.runLater(() ->
        {
            initializeTextBoxes();
            initializeRemoteAndMail();
            //InitializeCBoxResolution(); -- currently disabled beacause of config issues
        });

        ApplicationVariables.getInstance().setIsOptionsOpen(true);
    }

}
