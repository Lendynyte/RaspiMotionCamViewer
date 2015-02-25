package camcontrols;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Dominik Pauli
 * @version 0.1
 */
public class WindowMenuClass
{
    //TODO(Dominik):Maybe make this as fxml window too ?
    /**
     * This methods show basic about popup window
     *
     */
    public void createAboutWindow()
    {
        //initialize dialog
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);

        //Create button
        Button btnClose = new Button("Close");
        btnClose.setAlignment(Pos.BOTTOM_CENTER);

        //hand close operation of the button
        btnClose.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                System.out.println("Closing the about window...");
                stage.close();
            }
        });

        //create Label with about
        Label lbl = new Label("Help Help\nHelp Help\nHelp Help\nHelp Help\nHelp Help\nHelp Help\nHelp Help\nHelp Help\nHelp Help\nHelp Help\n");

        //put vBox in stage
        VBox vbHelp = new VBox();
        vbHelp.setSpacing(50);

        //set everything to center and make tex label expand
        VBox.setVgrow(lbl, Priority.ALWAYS);
        vbHelp.alignmentProperty().set(Pos.TOP_CENTER);

        //add everzthing to vBox
        vbHelp.getChildren().addAll(lbl, btnClose);

        //add everything to scene
        Scene scene = new Scene(vbHelp, 200, 250);

        //show dialog
        stage.setScene(scene);
        stage.setTitle("Help");
        stage.show();
    }

    /**
     * This method creates options window from fxml file
     */
    public void createOptionsWindow()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLOptions.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Camera options");
            stage.show();
        } catch (Exception e)
        {
            System.err.println("There was an error loading options menu FXML file");
            e.printStackTrace();
        }
    }

}
