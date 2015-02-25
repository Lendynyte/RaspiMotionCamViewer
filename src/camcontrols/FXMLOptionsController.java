/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camcontrols;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dominik
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

    //TODO(Dominik): Create implementation
    @FXML
    private void handleButtonApplyButton(final ActionEvent event)
    {
        System.out.println("apply button");
    }

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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }

}
