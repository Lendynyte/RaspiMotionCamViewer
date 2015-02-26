package camcontrols;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Dominik Pauli
 * @version 0.2
 */
public class CamControls extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
      /*  Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Raspberry pi surveillance camera system");
        stage.setScene(scene);
        stage.show();*/
        
        //TODO(Dominik):change back to cameras now we use this for testing options menu
        WindowMenuClass wmc = new WindowMenuClass();
        wmc.createOptionsWindow();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
