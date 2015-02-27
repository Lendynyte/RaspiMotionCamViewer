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
    
    
    //TODO(Dominik): Create implementation
    @FXML
    private void handleButtonApplyButton(final ActionEvent event)
    {
        System.out.println("apply button");
    }

    //TODO(Dominik):Use single menu or use checkbox to show advanced?
    //TODO(Dominik):Think about options categorization
    //TODO(Dominik): reimplement cameras as singletons? use somethind to comunicate betweeen classes?
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
    
    //TODO(Dominik): Make this change stuff in conf
    /**
     * This method changes autobrightness camera property and disables brightness property
     * 
     * @param event checkobox checked event
     */
    @FXML
    private void handleCheckBoxAutoBrightnessCheckBox(final ActionEvent event)
    {
        if(this.chckAutoBrightness.isSelected())
        {
            this.sldrBrightness.disableProperty().setValue(Boolean.TRUE);
        }
        else
        {
            this.sldrBrightness.disableProperty().setValue(Boolean.FALSE);
        }
    }
    
    /**
     * This method initializes Combobox for picking camera
     */
    private void InitializeCBoxCam()
    {
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
