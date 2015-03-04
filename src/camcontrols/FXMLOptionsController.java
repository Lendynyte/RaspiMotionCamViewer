package camcontrols;

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

    //BUTTON HANDLING START
    //TODO(Dominik): Create implementation
    @FXML
    private void handleButtonApplyButton(final ActionEvent event)
    {
        System.out.println("apply button");
        //TODO(Dominik):remove this later just testing
        System.out.println(this.sldrBrightness.getValue());
        //TODO(Dominik):get changed variables and produce configuration file maybe create method for this
    }

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
                if (!MotionCamera1SingletonTest.getInstance().isCamAutoBrightness())
                {
                    MotionCamera1SingletonTest.getInstance().setCamBrightness(getValueFromPercentage(this.sldrBrightness.getValue()));
                    break;
                }
                break;
            case "Camera 2": //TODO(Dominik):fix
               /* if (!MotionCamera2SingletonTest.getInstance().isCamAutoBrightness())
                 {
                 MotionCamera2SingletonTest.getInstance().setCamBrightness(getValueFromPercentage(this.sldrBrightness.getValue()));
                 } */ break;
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
                MotionCamera1SingletonTest.getInstance().setCamConstrast(getValueFromPercentage(this.sldrContrast.getValue()));
                break;
            case "Camera 2":
                //  MotionCamera2SingletonTest.getInstance().setCamConstrast(getValueFromPercentage(this.sldrContrast.getValue()));
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
                MotionCamera1SingletonTest.getInstance().setCamConstrast(getValueFromPercentage(this.sldrHue.getValue()));
                break;
            case "Camera 2":
                //  MotionCamera2SingletonTest.getInstance().setCamConstrast(getValueFromPercentage(this.sldrHue.getValue()));
                break;
            default://TODO(Dominik):test
                System.out.println("nothing selected");//should not happen
                break;
        }
    }

    /**
     * This method changes value of saturation in selected camera to value of 
     * 0 - 255 depending on slider value
     */
    private void handleSldrSaturation()
    {
        //TODO(Dominik):check of there is not better way
        switch (this.cBoxCam.getSelectionModel().getSelectedItem())
        {
            case "Camera 1":
                MotionCamera1SingletonTest.getInstance().setCamConstrast(getValueFromPercentage(this.sldrSaturation.getValue()));
                break;
            case "Camera 2":
                //  MotionCamera2SingletonTest.getInstance().setCamConstrast(getValueFromPercentage(this.sldrSaturation.getValue()));
                break;
            default://TODO(Dominik):test
                System.out.println("nothing selected");//should not happen
                break;
        }
    }

    /**
     * This method changes value of quality in selected camera to value of 50/75/100
     * depending on slider value
     */
    private void handleSldrQuality()
    {
        switch (this.cBoxCam.getSelectionModel().getSelectedItem())
        {
            case "Camera 1":
                MotionCamera1SingletonTest.getInstance().setCamQuality((int)this.sldrQuality.getValue());//I can cast to int because the values are 50/75/100
                break;
            case "Camera 2":
                //MotionCamera2SingletonTest.getInstance().setCamQuality((int)this.sldrQuality.getValue());//I can cast to int because the values are 50/75/100
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
        return (int) Math.round(2.55 * percentage);
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
            MotionCamera1SingletonTest.getInstance().setCamAutoBrightness(true);
            //MotionCamera2SingletonTest.getInstance().setCamAutoBrightness(true);
        }
        else
        {
            this.sldrBrightness.disableProperty().setValue(Boolean.FALSE);
            MotionCamera1SingletonTest.getInstance().setCamAutoBrightness(false);
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

    //TODO(Dominik): Maybe add tooltips to cameras and buttons ? may be nice
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        InitializeCBoxCam();
        InitializeCBoxResolution();
    }

}
