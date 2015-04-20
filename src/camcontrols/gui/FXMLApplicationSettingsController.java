package camcontrols.gui;

import camcontrols.dependencies.ApplicationVariables;
import camcontrols.saving.XMLCameraHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
        loadToVariables();
        new XMLCameraHandler().createApplicationSave();
    }

    @FXML
    private void handleButtonCancel(final ActionEvent event)
    {
        ApplicationVariables.getInstance().setIsSettingsOpen(false);
        this.mainPane.getScene().getWindow().hide();
    }

    /**
     * This method loads bariables from form to ApplicationVariables singleton
     */
    private void loadToVariables()
    {
        //TODO(Dominik):implement
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
        Platform.runLater(() ->
        {
            XMLCameraHandler xmlHandler = new XMLCameraHandler();
            if (xmlHandler.checkForXMLSave(ApplicationVariables.getInstance().getXmlSaveDirectoryPath() + "/appSave.xml"))
            {
                xmlHandler.loadApplicationSave();
            }
            else
            {
                System.out.println("Save not found loading default configuration ...");
                //TODO(Dominik):load default configuration
            }
        });

        ApplicationVariables.getInstance().setIsSettingsOpen(true);
    }

}
