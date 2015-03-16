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

    //variable for checking data from form
    private boolean isError = false;

    //BUTTON HANDLING START
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
    //TODO(Dominik): when the window for options is open make camera views noninteractive
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
        applyToCamera();
        saveToXMLSaveFile();
        //TODO(Dominik): remove testing 
        System.out.println("Save Button");
        System.out.println(MotionCamera1.getInstance().getName());
        System.out.println(getFXMLCamName("1"));
        System.out.println(MotionCamera1.getInstance().getURL());
        System.out.println(MotionCamera1.getInstance().getCamWidth());
        System.out.println(MotionCamera1.getInstance().getCamHeight());
        System.out.println(getFXMLResolutionHeight());
        System.out.println(MotionCamera1.getInstance().getCamFramerate());
        //TODO(Dominik): rename this variable in cameras
        System.out.println(MotionCamera1.getInstance().isCamAutoBrightness());
    }
    //BUTTON HANDLING END

    //SLIDER HANDLING START
    //TODO(Dominik):check for default values and preset them to the form
    //TODO(Dominik):maybe do not snap to ticks
    //TODO(Dominik):TEST
    //TODO(Dominik): rewrite sliders to fit other controls do chcking later just get variables
    /**
     *
     * @return
     */
    private int getFXMLSldrBrightness()
    {
        if (this.chckAutoBrightness.isSelected())
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
        return getValueFromPercentage(this.sldrContrast.getValue());
    }

    /**
     *
     * @return
     */
    private int getFXMLSldrHue()
    {
        return getValueFromPercentage(this.sldrHue.getValue());
    }

    /**
     *
     * @return
     */
    private int getFXMLSldrSaturation()
    {
        return getValueFromPercentage(this.sldrSaturation.getValue());
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
        }
        else
        {
            this.sldrBrightness.disableProperty().setValue(Boolean.FALSE);
        }
    }

    //TODO(Dominik):rewrite
    /**
     * This method enables automatic mailing and disables place for email adress
     * if not selected
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
        ObservableList<String> cameras = FXCollections.observableArrayList(MotionCamera1.getInstance().getName(), MotionCamera2.getInstance().getName());
        if (!this.cBoxCam.getItems().isEmpty())
        {
            this.cBoxCam.getItems().remove(0, 2);
        }
        this.cBoxCam.getItems().addAll(cameras);
        this.cBoxCam.getSelectionModel().selectFirst();
    }

    /**
     * This method initializes Combobox for picking resolution
     */
    private void InitializeCBoxResolution()
    {
        //TODO(Dominik): test resolutions and pick which i want to use
        ObservableList<String> resolutions = FXCollections.observableArrayList("1920 x 1080", "1280 x 720", "800 x 600", "640 x 480", "320 x 240");
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
        if (!isError)
        {
            switch (this.cBoxCam.getSelectionModel().getSelectedIndex())
            {
                case 0:
                {
                    MotionCamera1.getInstance().setCamWidth(getFXMLResolutionWidth());
                    MotionCamera1.getInstance().setCamHeight(getFXMLResolutionHeight());
                    MotionCamera1.getInstance().setCamFramerate(getFXMLFramerate());
                    MotionCamera1.getInstance().setCamAutoBrightness(getFXMLAutoBrightness()); //TODO(Dominik):chec autobrightness/brightness interaction
                    MotionCamera1.getInstance().setCamBrightness(getFXMLSldrBrightness());
                    MotionCamera1.getInstance().setCamConstrast(getFXMLSldrContrast());
                    MotionCamera1.getInstance().setCamHue(getFXMLSldrHue());
                    MotionCamera1.getInstance().setCamSaturation(getFXMLSldrSaturation());
                    MotionCamera1.getInstance().setCamQuality(getFXMLSldrQuality());

                    MotionCamera1.getInstance().setURL(getFXMLURL());
                    //TODO(Dominik): implement remote and emails

                    MotionCamera1.getInstance().setName(getFXMLCamName("1"));

                    InitializeCBoxCam();
                    this.isError = false;
                }
                break;
                case 1:
                {
                    MotionCamera2.getInstance().setCamWidth(getFXMLResolutionWidth());
                    MotionCamera2.getInstance().setCamHeight(getFXMLResolutionHeight());
                    MotionCamera2.getInstance().setCamFramerate(getFXMLFramerate());
                    MotionCamera2.getInstance().setCamAutoBrightness(getFXMLAutoBrightness());
                    MotionCamera2.getInstance().setCamBrightness(getFXMLSldrBrightness());
                    MotionCamera2.getInstance().setCamConstrast(getFXMLSldrContrast());
                    MotionCamera2.getInstance().setCamHue(getFXMLSldrHue());
                    MotionCamera2.getInstance().setCamSaturation(getFXMLSldrSaturation());

                    MotionCamera2.getInstance().setURL(getFXMLURL());
                    //TODO(Dominik): implement remote and emails

                    MotionCamera2.getInstance().setName(getFXMLCamName("2"));
                    InitializeCBoxCam();
                    this.isError = false;
                }
                break;
            }
        }
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

    //TODO(Dominik): URL checking
    /**
     *
     * @return
     */
    private String getFXMLURL()
    {
        return this.tfCamURL.getText().trim();
    }

    //TODO(Dominik): implement these
    //TODO(Dominik): email checking
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
    private String getFXMLCamName(String which)
    {
        if (!this.tfCamName.getText().isEmpty())
        {
            return this.tfCamName.getText().trim();
        }
        else
        {
            return "Camera" + which;
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
            int i = Integer.parseInt(text);
        } catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    /**
     *
     */
    private void applyToConfigFile()
    {
        //TODO(Dominik):take stuff from camera singletons and send to cameras as created config file
    }

    //TODO(Dominik):if i swap camera in menu change load settings from camera singleton
    //TODO(Dominik): Maybe add tooltips to cameras and buttons ? may be nice
    //TODO(Dominik):at start load stuff from xml to form and to camear singletons
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
