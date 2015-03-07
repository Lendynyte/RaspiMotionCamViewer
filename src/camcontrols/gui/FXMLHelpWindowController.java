package camcontrols.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dominik Pauli
 * @version 0.1
 */
public class FXMLHelpWindowController implements Initializable
{

    //main Anchor pane
    @FXML
    private AnchorPane mainPane;

    //close button
    @FXML
    private Button btnClose;

    //Help text label
    @FXML
    private Label lblHelp;

    //hand close operation of the button
    @FXML
    private void handleActionCloseHelpWindowAction(final ActionEvent event)
    {
        System.out.println("Cancel help");
        this.mainPane.getScene().getWindow().hide();
    }

    /**
     * This method sets help text in help window
     */
    private void setHelpText()
    {
        this.lblHelp.setText("Help Help\nHelp Help\nHelp Help\nHelp Help\nHelp Help\nHelp Help\nHelp Help\nHelp Help\nHelp Help\nHelp Help\n");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.setHelpText();
    }

}
