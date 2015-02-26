package camcontrols;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
