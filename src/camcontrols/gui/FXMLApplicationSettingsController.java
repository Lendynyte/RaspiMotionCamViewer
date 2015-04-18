package camcontrols.gui;

import camcontrols.dependencies.ApplicationVariables;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Dominik Pauli
 * @version 0.1
 */
public class FXMLApplicationSettingsController implements Initializable
{

    @FXML
    private VBox mainPane;

    @FXML
    private Button btnApply;

    @FXML
    private Button btnCancel;

    @FXML
    private void handleButtonApply(final ActionEvent event)
    {
        //TODO(Dominik): implement
    }

    @FXML
    private void handleButtonCancel(final ActionEvent event)
    {
        ApplicationVariables.getInstance().setIsSettingsOpen(false);
        this.mainPane.getScene().getWindow().hide();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ApplicationVariables.getInstance().setIsSettingsOpen(true);
    }

}
