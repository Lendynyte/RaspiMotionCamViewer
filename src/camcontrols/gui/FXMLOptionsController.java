package camcontrols.gui;

import camcontrols.configEditing.Parser;
import camcontrols.dependencies.MotionCamera1;
import camcontrols.dependencies.MotionCamera2;
import java.net.URL;
import java.util.ResourceBundle;
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
 * @version 0.2
 */
public class FXMLOptionsController implements Initializable
{

    //control buttons
    @FXML
    private Button applyButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button saveButton;
    
    //main anchor pane
    @FXML
    private AnchorPane mainPane;

    //Combo box for picking camera to edit
    @FXML
    private ComboBox<String> cBoxCam;

    //Combo box for picking camera resolution
    @FXML
    private ComboBox<String> cBoxResolution;

    //auto brightness on/off
    @FXML
    private CheckBox chckAutoBrightness;

    //alert email enable
    @FXML
    private CheckBox chckAlerMail;
    
    //enable remote image storage
    @FXML
    private CheckBox chckRemoteStore;
    
    //TODO(Dominik):maybe remove this?
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
    
    //SLIDER VARIABLES START
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
    //SLIDER VARIABLES END

    //TOOLTIP VARIABLES START
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
    //TOOLTIP VARIABLES END

    //parser instance
    private Parser parser;
    
    //BUTTON HANDLING START
    //TODO(Dominik): Create implementation
    @FXML
    private void handleButtonApplyButton(final ActionEvent event)
    {
        saveToXMLSaveFile();
        applyToCamera();
        applyToConfigFile();
        System.out.println("apply button");
        //TODO(Dominik):remove this later just testing
        System.out.println(this.sldrBrightness.getValue());
        //TODO(Dominik):get changed variables and produce configuration file maybe create method for this   
    }

    //TODO(Dominik):Load existing configuration on options menu startup
    //TODO(Dominik):Use single menu or use checkbox to show advanced?
    //TODO(Dominik):Think about options categorization
    //TODO(Dominik): reimplement cameras as singletons? use somethind to comunicate betweeen classes?
    //TODO(Dominik): when the window for options is open make camera views noninteractive
    //TODO(Dominik):add labels for sliders
    /**
     * This method closes the options window
     *
     * @param event button clicked
     */
    @FXML
    private void handleButtonCancelButton(final ActionEvent event)
    {
        System.out.println("Cancel button");
        this.mainPane.getScene().getWindow().hide();
    }

    /**
     *
     * @param event button clicked
     */
    @FXML
    private void handleButtonResetToDefaultButton(final ActionEvent event)
    {
        //TODO(Dominik):implementation
        this.setDefaultValues();
        System.out.println(getValueFromPercentage(this.sldrHue.getValue()));
        System.out.println("Reset button");
    }
    
    @FXML
    private void handleButtonSaveButton(final ActionEvent event)
    {
       saveToXMLSaveFile();
       System.out.println("Save Button");
    }   
    //BUTTON HANDLING END

    //SLIDER HANDLING START
    //TODO(Dominik):check for default values and preset them to the form
    //TODO(Dominik):maybe do not snap to ticks
    //TODO(Dominik):TEST
    /**
     * This method changes value of brightness in selected camera to value of 0
     * - 255 depending on slider value
     */
    private void handlSldrBrightness()
    {
        //TODO(Dominik):cehck if there is not a better way to do this
        switch (this.cBoxCam.getSelectionModel().getSelectedItem())
        {
            case "Camera 1":
                if (!MotionCamera1.getInstance().isCamAutoBrightness())
                {
                    MotionCamera1.getInstance().setCamBrightness(getValueFromPercentage(this.sldrBrightness.getValue()));
                    break;
                }
                break;
            case "Camera 2": //TODO(Dominik):fix
                if (!MotionCamera2.getInstance().isCamAutoBrightness())
                {
                    MotionCamera2.getInstance().setCamBrightness(getValueFromPercentage(this.sldrBrightness.getValue()));
                    break;
                }
                break;
            default://TODO(Dominik):test
                System.out.println("nothing selected");//should not happen
                break;
        }

    }

    /**
     * This method changes value of contrast in selected camera to value of 0 -
     * 255 depending on slider value
     */
    private void handleSldrContrast()
    {
        //TODO(Dominik):check of there is not better way
        switch (this.cBoxCam.getSelectionModel().getSelectedItem())
        {
            case "Camera 1":
                MotionCamera1.getInstance().setCamConstrast(getValueFromPercentage(this.sldrContrast.getValue()));
                break;
            case "Camera 2":
                MotionCamera2.getInstance().setCamConstrast(getValueFromPercentage(this.sldrContrast.getValue()));
                break;
            default://TODO(Dominik):test
                System.out.println("nothing selected");//should not happen
                break;
        }
    }

    /**
     * This method changes value of hue in selected camera to value of 0 - 255
     * depending on slider value
     */
    private void handleSldrHue()
    {
        //TODO(Dominik):check of there is not better way
        switch (this.cBoxCam.getSelectionModel().getSelectedItem())
        {
            case "Camera 1":
                MotionCamera1.getInstance().setCamConstrast(getValueFromPercentage(this.sldrHue.getValue()));
                break;
            case "Camera 2":
                MotionCamera2.getInstance().setCamConstrast(getValueFromPercentage(this.sldrHue.getValue()));
                break;
            default://TODO(Dominik):test
                System.out.println("nothing selected");//should not happen
                break;
        }
    }

    /**
     * This method changes value of saturation in selected camera to value of 0
     * - 255 depending on slider value
     */
    private void handleSldrSaturation()
    {
        //TODO(Dominik):check of there is not better way
        switch (this.cBoxCam.getSelectionModel().getSelectedItem())
        {
            case "Camera 1":
                MotionCamera1.getInstance().setCamConstrast(getValueFromPercentage(this.sldrSaturation.getValue()));
                break;
            case "Camera 2":
                MotionCamera2.getInstance().setCamConstrast(getValueFromPercentage(this.sldrSaturation.getValue()));
                break;
            default://TODO(Dominik):test
                System.out.println("nothing selected");//should not happen
                break;
        }
    }

    /**
     * This method changes value of quality in selected camera to value of
     * 50/75/100 depending on slider value
     */
    private void handleSldrQuality()
    {
        switch (this.cBoxCam.getSelectionModel().getSelectedItem())
        {
            case "Camera 1":
                MotionCamera1.getInstance().setCamQuality((int) this.sldrQuality.getValue());//I can cast to int because the values are 50/75/100
                break;
            case "Camera 2":
                MotionCamera2.getInstance().setCamQuality((int) this.sldrQuality.getValue());//I can cast to int because the values are 50/75/100
                break;
            default://TODO(Dominik):test
                System.out.println("nothing selected");//should not happen
                break;
        }
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

    //TODO(Dominik):add checkbox next to sliders to enable/disable 0 = disabled in motion.conf after that just add +1 in percentages
    /**
     * This method sets default values for options form
     */
    private void setDefaultValues()
    {
        this.cBoxCam.getSelectionModel().selectFirst();
        this.cBoxResolution.getSelectionModel().selectFirst();
        this.tfFramerate.setText(null);
        this.chckAutoBrightness.selectedProperty().setValue(Boolean.FALSE);
        this.sldrBrightness.setValue(0);
        this.sldrContrast.setValue(0);
        this.sldrHue.setValue(0);
        this.sldrSaturation.setValue(0);
        this.sldrQuality.setValue(75);
    }
    
    //TOOLTIP START
    //TODO(Dominik): check after sliders work to see if this works
    /**
     *
     * @param event tooltip showing event
     */
    @FXML
    private void handleActionTltpBrighSldrShowAction(final ActionEvent event)
    {
        //this.tltpBrightSldr.setText(this.sldrBrightness.toString());
    }

    //TODO(Dominik): check after sliders work to see if this works
    /**
     *
     * @param event tooltip showing event
     */
    @FXML
    private void handleActionTltpContSldrShowAction(final ActionEvent event)
    {
        //this.tltpContSldr.setText(this.sldrContrast.toString());
    }

    //TODO(Dominik): check after sliders work to see if this works
    /**
     *
     * @param event tooltip showing event
     */
    @FXML
    private void handleActionTltpHueSldrShowAction(final ActionEvent event)
    {
        // this.tltpHueSldr.setText(this.sldrHue.toString());
    }

    //TODO(Dominik): check after sliders work to see if this works
    /**
     *
     * @param event tooltip showing event
     */
    @FXML
    private void handleActionTltpSaturSldrShowAction(final ActionEvent event)
    {
        // this.tltpSaturSldr.setText(this.sldrSaturation.toString());
    }

    //TODO(Dominik): check after sliders work to see if this works
    /**
     *
     * @param event tooltip showing event
     */
    @FXML
    private void handleActionTltpQualSldrShowAction(final ActionEvent event)
    {
        // this.tltpQualSldr.setText(this.sldrQuality.toString());
    }
    //TOOLTUP END

    /**
     * This method changes autobrightness camera property and disables
     * brightness property
     *
     * @param event checkobox checked event
     */
    @FXML
    private void handleCheckBoxAutoBrightnessCheckBox(final ActionEvent event)
    {
        if (this.chckAutoBrightness.isSelected())
        {
            this.sldrBrightness.disableProperty().setValue(Boolean.TRUE);
            MotionCamera1.getInstance().setCamAutoBrightness(true);
            MotionCamera2.getInstance().setCamAutoBrightness(true);
        }
        else
        {
            this.sldrBrightness.disableProperty().setValue(Boolean.FALSE);
            MotionCamera1.getInstance().setCamAutoBrightness(false);
        }
    }
    
      /**
     * This method enables automatic mailing and disables place for email adress if not selected
     *
     * @param event checkobox checked event
     */
    @FXML
    private void handleCheckBoxSendAutoEmailCheckBox(final ActionEvent event)
    {
        if (this.chckAlerMail.isSelected())
        {
            this.tfAlertEmail.disableProperty().setValue(Boolean.FALSE);
            this.tfAlertEmail.textProperty().setValue(null);
            //TODO(Dominik):send mail
        }
        else
        {
            this.tfAlertEmail.disableProperty().setValue(Boolean.TRUE);
            //TODO(Dominik):turn off mailing
        }
    }
    
      /**
     * This method enables remote storage or disables if unchecked
     *
     * @param event checkobox checked event
     */
    @FXML
    private void handleCheckBoxRemoteImagesCheckBox(final ActionEvent event)
    {
        if (this.chckRemoteStore.isSelected())
        {
            this.tfremoteStoragePath.disableProperty().setValue(Boolean.FALSE);
            //TODO(Dominik):enableremote storage
        }
        else
        {
            this.tfremoteStoragePath.disableProperty().setValue(Boolean.TRUE);
            //TODO(Dominik):disable remote storage
        }
    }

    /**
     * This method initializes Combobox for picking camera
     */
    private void InitializeCBoxCam()
    {
        //TODO(Dominik):decide on number of cameras
        //TODO(Dominik):Maybe load from cam instances or singletons ? decide on implementation
        ObservableList<String> cameras = FXCollections.observableArrayList("Camera 1", "Camera 2", "Camera 3", "Camera 4");
        this.cBoxCam.getItems().addAll(cameras);
        this.cBoxCam.getSelectionModel().selectFirst();
    }

    /**
     * This method initializes Combobox for picking resolution
     */
    private void InitializeCBoxResolution()
    {
        //TODO(Dominik): test resolutions and pick which i want to use
        ObservableList<String> resolutions = FXCollections.observableArrayList("1920 x 1080", "1280 x 720", "1024 x 768", "800 x 600", "640 x 480", "320 x 240");
        this.cBoxResolution.getItems().addAll(resolutions);
        this.cBoxResolution.getSelectionModel().selectFirst();
    }

    /**
     * This methods set the textboxes for email and remote path as uninteractive
     */
    private void initializeTextBoxes()
    {
        this.tfAlertEmail.disableProperty().setValue(Boolean.TRUE);
        this.tfremoteStoragePath.disableProperty().setValue(Boolean.TRUE);
    }
    
    /**
     * 
     */
    private void saveToXMLSaveFile()
    {
        //TODO(Dominik):implement saving settings to save file
    }
    
    /**
     * 
     */
    private void applyToCamera()
    {
        //TODO(Dominik):add all changes to motionCameraSIngletons
        //maybe do this right avay so i dont fuck up maybe well see just have to test for right camera
    }
    
    /**
     * 
     */
    private void applyToConfigFile()
    {
        //TODO(Dominik):take stuff from camera singletons and send to cameras as created config file
    }
    
    //TODO(Dominik): Maybe add tooltips to cameras and buttons ? may be nice
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //TODO(Dominik):if save then do not initialize to default
        initializeTextBoxes();
        InitializeCBoxCam();
        InitializeCBoxResolution();
    }

}
