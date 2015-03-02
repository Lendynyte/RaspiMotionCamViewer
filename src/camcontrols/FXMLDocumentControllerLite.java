package camcontrols;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Dominik Pauli
 * @version 0.2
 */
public class FXMLDocumentControllerLite implements Initializable
{

    @FXML
    private AnchorPane mainPane;

    @FXML
    private MenuBar menuBar;

    //TESTING VARIABLES AND METHODS
    //webView containers
    //TODO(Dominik):maybe add gridpane like in cam4 test it is easier to make look better
    //TODO(Dominik):check performance impact on rpi later
    @FXML
    private VBox pane1;

    @FXML
    private VBox pane2;

    //ImageViews
    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private void handleButtonTestStream(ActionEvent event)
    {
        //startStream();
    }

    //TODO(Dominik): reimplement for imageView
    private void startStream()
    {

    }

    //TESTING VARIABLES AND METHODS END
    /**
     * This method changes Anchor pane color to orange to highlight it
     *
     * @param mouseEvent mouse entered object boundaries
     */
    @FXML
    private void handleCamHighlightEvent(final MouseEvent mouseEvent)
    {
        ((Node) mouseEvent.getSource()).setStyle("-fx-background-color: orange;");
    }

    /**
     * This method changes Anchor pane background color to black to unhighlight
     * it
     *
     * @param mouseEvent mouse left object boundaries
     */
    @FXML
    private void handleCamUnhighlightEvent(final MouseEvent mouseEvent)
    {
        ((Node) mouseEvent.getSource()).setStyle("-fx-background-color: black;");
    }

    /**
     * Control used in menuBar to close window
     *
     * @param event
     */
    @FXML
    private void handleMenuCloseAction(final ActionEvent event)
    {
        System.out.println("Closing...");
        this.mainPane.getScene().getWindow().hide();
    }

    /**
     * This methods show basic about popup window
     *
     * @param event
     */
    @FXML
    private void handleMenuAboutAction(final ActionEvent event)
    {
        WindowMenuClass wmc = new WindowMenuClass();
        wmc.createAboutWindow();
    }

    //TODO(Dominik): create method setCameraURLs
    @FXML
    private void handleMenuStartStreamAction(final ActionEvent event)
    {
        //setCameraURLs
        // startStream();
        testStream();
    }

    //TODO(Dominik):implement
    @FXML
    private void handleMenuStopStreamAction(final ActionEvent event)
    {
        startStream();
    }

    @FXML
    private void handleMenuOptionsMenu(final ActionEvent event)
    {
        WindowMenuClass wmc = new WindowMenuClass();
        wmc.createOptionsWindow();
    }

    //TODO(Dominik):fix still does not feel right
    private void testStream()
    {

        //TODO(Dominik):get resolution from cameras and fit it into here later
        this.imageView1.fitWidthProperty().bind(this.pane1.widthProperty());
        this.imageView1.fitHeightProperty().bind(this.pane1.heightProperty());
        this.imageView1.preserveRatioProperty().setValue(Boolean.TRUE);

        this.imageView2.fitWidthProperty().bind(this.pane1.widthProperty());
        this.imageView2.fitHeightProperty().bind(this.pane1.heightProperty());
        this.imageView2.preserveRatioProperty().setValue(Boolean.TRUE);

        this.imageView1.setImage(new Image("file:C://Users/Dominik/Documents/TEST.jpg"));
        this.imageView2.setImage(new Image("file:C://Users/Dominik/Documents/TEST.jpg"));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }

}
