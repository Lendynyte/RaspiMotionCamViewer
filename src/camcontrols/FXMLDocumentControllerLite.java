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
    @FXML
    private VBox pane1;

    @FXML
    private VBox pane2;

    //ImageViews
    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    //camera instances
    //TODO: move create initialize method use different constructor
    private final MotionCamera cam1 = new MotionCamera();
    private final MotionCamera cam2 = new MotionCamera();

    @FXML
    private void handleButtonTestStream(ActionEvent event)
    {
        //startStream();
    }

    //TODO(Dominik): reimplement for imageView
    private void startStream()
    {

    }

    //TODO(Dominik):chánge, create in constructor
    private void setHandles()
    {
        this.cam1.setHandle("cam1");
        this.cam2.setHandle("cam2");
    }

    //TODO(Dominik):change
    private void setIDs()
    {
        this.pane1.setId(this.cam1.getHandle());
        this.pane2.setId(this.cam2.getHandle());
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

    //TODO(Dominik): Make this link to our logo file
    /**
     * This method sets stream screens to starting Image
     */
    private void preloadStream()
    {
        //TODO(Dominik):make the path relative
        cam1.setURL("file:C://Users/Dominik/Desktop/bcbcbc/CamControlUiv2/src/camcontrols/preload.html");
        cam2.setURL("file:C://Users/Dominik/Desktop/bcbcbc/CamControlUiv2/src/camcontrols/preload.html");
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
        startStream();
    }

    @FXML
    private void handleMenuStopStreamAction(final ActionEvent event)
    {
        preloadStream();
        startStream();
    }

    @FXML
    private void handleMenuOptionsMenu(final ActionEvent event)
    {
        WindowMenuClass wmc = new WindowMenuClass();
        wmc.createOptionsWindow();
    }

    //todo fix resizing
    private void testStream()
    {

        /*   this.imageView1.preserveRatioProperty().setValue(Boolean.FALSE);
         this.imageView2.preserveRatioProperty().setValue(Boolean.FALSE);
        
         this.pane1.setMinWidth(1280/2);
         this.pane2.setMinWidth(1280/2);
         //TODO(Dominik):change to relative coordinates later or change anchor pane for smthing else
         this.imageView1.setFitWidth(this.pane1.getWidth());
         this.imageView1.setFitHeight(this.mainPane.getHeight()-40);
        
         this.imageView2.setFitWidth(this.pane2.getWidth());
         this.imageView2.setFitHeight(this.mainPane.getHeight()-40);
        
         //  this.imageView1.fitWidthProperty().bind(this.pane1.widthProperty());
         //   this.imageView2.fitWidthProperty().bind(this.pane2.widthProperty());*/
        //TODO(Dominik):fix in scene editor later
        //this.pane1.fillWidthProperty().setValue(Boolean.TRUE);
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
        testStream();

    }

}
