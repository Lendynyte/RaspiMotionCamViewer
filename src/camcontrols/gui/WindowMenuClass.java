package camcontrols.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Dominik Pauli
 * @version 0.1
 */
public class WindowMenuClass
{

    /**
     * This methods show basic about popup window
     *
     */
    public void createAboutWindow()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLHelpWindow.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Help");
            stage.show();
        }
        catch (Exception e)
        {
            System.err.println("There was an error loading help menu FXML file");
        }
    }

    /**
     * This method creates options window from fxml file
     */
    public void createOptionsWindow()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLOptions.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Camera options");
            stage.show();
        }
        catch (Exception e)
        {
            System.err.println("There was an error loading options menu FXML file");
        }
    }

}
