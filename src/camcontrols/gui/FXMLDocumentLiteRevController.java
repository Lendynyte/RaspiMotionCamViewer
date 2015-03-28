package camcontrols.gui;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Dominik Pauli
 * @version 0.2
 */
public class FXMLDocumentLiteRevController implements Initializable
{

    //<editor-fold defaultstate="collapsed" desc="FXML form variables">
    @FXML
    private AnchorPane mainPane;

    @FXML
    private GridPane cameraGrid;

    @FXML
    private MenuBar menuBar;

    //webView containers
    @FXML
    private ScrollPane pane1;
    //private AnchorPane pane1;

    @FXML
    private ScrollPane pane2;

    @FXML
    private TextField testTFURL1;

    @FXML
    private TextField testTFURL2;
    //</editor-fold>

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

    @FXML
    private void handleMenuStartStreamAction(final ActionEvent event)
    {
        testStream();
    }

    @FXML
    private void handleMenuStopStreamAction(final ActionEvent event)
    {
        // preloadStream();
        //startStream();
        startCam1Stream(null);
    }

    //TODO(Dominik): image View timelapse
    //TODO(Dominik): maybe little rework
    // z https://community.oracle.com/thread/2320727
    //TODO(Dominik):fix still does not feel right
    private void testStream()
    {
        pane1.setContent(new ImageView()
        {
            {
             //file:C://Users/Dominik/Desktop/em.jpg
                //http://192.168.1.3:8081/stream.mjpg
                imageProperty().set(new Image("http://192.168.1.3:8081/stream.jpeg"));
                setPreserveRatio(false);
                setSmooth(true);

                fitWidthProperty().bind(pane1.widthProperty());
                fitHeightProperty().bind(pane1.heightProperty());
            }
        });

        //"file:C://Users/Dominik/Desktop/em.jpg"
        //TODO(Dominik):load from camera remove testing
        try
        {
            //  startCam1Stream(this.testTFURL1.getText());
        }
        catch (Exception e)
        {
            System.out.println("no URL in cam 1");
        }

        try
        {
            // startCam2Stream();
        }
        catch (Exception e)
        {
            System.out.println("no URL in cam 2");
        }

        /* pane2.setContent(new ImageView()
         {
         {
         imageProperty().set(new Image("file:C://Users/Dominik/Desktop/em.jpg"));
         setPreserveRatio(false);
         setSmooth(true);

         fitWidthProperty().bind(pane1.widthProperty());
         fitHeightProperty().bind(pane1.heightProperty());
         }
         });*/
    }

    //refreshing is lagging a lot may not need needs testing
    private void startCam1Stream(String URL)
    {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);

        //TODO: It is lagging each time the timeline is refreshed if the furation is under 1 sec the windows is not responding at all
        KeyFrame playStream = new KeyFrame(Duration.seconds(.2),//(.0200),
                new EventHandler<ActionEvent>()
                {

                    @Override
                    public void handle(ActionEvent event)
                    {
                        pane1.setContent(new ImageView()
                        {
                            {
                                imageProperty().set(new Image("http://192.168.1.3:8081/stream.mjpg"));
                                setPreserveRatio(false);
                                setSmooth(true);

                                fitWidthProperty().bind(pane1.widthProperty());
                                fitHeightProperty().bind(pane1.heightProperty());
                            }

                        });
                    }

                });

        timeline.getKeyFrames().add(playStream);
        timeline.play();
    }

    private void startCam2Stream() throws MalformedURLException, IOException
    {
        this.pane2.setContent(new ImageView()
        {
            {

                URL url = new URL("http://192.168.1.3:8081");
                System.out.println(url.getFile());
                System.out.println(url.getContent().toString());
                System.out.println(url.openConnection());
                System.out.println(url.openStream());
                url.openConnection();
                url.openStream();
                InputStream is = new BufferedInputStream(url.openStream());
                System.out.println(is.available());
                System.out.println(is.read());
                System.out.println(is.toString());
                System.out.println(url.getContent());
                System.out.println(url.getHost());
                System.out.println(url.getPort());

                // InputStream is = new InputStream
                imageProperty().set(new Image(is));
                setPreserveRatio(false);
                setSmooth(true);

                fitWidthProperty().bind(pane1.widthProperty());
                fitHeightProperty().bind(pane1.heightProperty());
            }
        });
    }

    @FXML
    private void handleMenuOptionsMenu(final ActionEvent event)
    {
        WindowMenuClass wmc = new WindowMenuClass();
        wmc.createOptionsWindow();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // testStream();
    }

}
