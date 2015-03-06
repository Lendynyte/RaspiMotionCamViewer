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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dominik
 */
public class FXMLTestVersionPickerController implements Initializable
{

    @FXML
    private Button btn4CAm;

    @FXML
    private Button btn2Cam;

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnHelp;
    
    @FXML
    private Button btn2CamRev1;

    @FXML
    private void handleBtn2CamRevAction(final ActionEvent event)
    {
         try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocumentLiteRev.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Help");
            stage.show();
        } catch (Exception e)
        {
            System.err.println("There was an error loading help menu FXML file");
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleBtn4CamAction(final ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Help");
            stage.show();
        } catch (Exception e)
        {
            System.err.println("There was an error loading help menu FXML file");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBtn2CamAction(final ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocumentLite.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Help");
            stage.show();
        } catch (Exception e)
        {
            System.err.println("There was an error loading help menu FXML file");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonOptionsAction(final ActionEvent event)
    {
        WindowMenuClass wmc = new WindowMenuClass();
        wmc.createOptionsWindow();
    }

    @FXML
    private void handleButtonHelpMenuTEstAction(final ActionEvent event)
    {
        WindowMenuClass wmc = new WindowMenuClass();
        wmc.createAboutWindow();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

}
