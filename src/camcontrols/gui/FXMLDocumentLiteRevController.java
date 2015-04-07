package camcontrols.gui;

import camcontrols.dependencies.MotionCamera1;
import camcontrols.dependencies.MotionCamera2;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Dominik Pauli
 * @version 0.4
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

    //</editor-fold>
    /**
     * Set ipcam webcam Driver
     */
    static
    {
        Webcam.setDriver(new IpCamDriver());
    }

    /**
     * This method has to be called only once
     *
     * @throws MalformedURLException
     */
    private void registerCameras() throws MalformedURLException
    {
        IpCamDeviceRegistry.register("Camera 1", MotionCamera1.getInstance().getURL() + ":8081/video.mjpg", IpCamMode.PUSH);
        IpCamDeviceRegistry.register("Camera 2", MotionCamera2.getInstance().getURL() + ":8081/video.mjpg", IpCamMode.PUSH);
    }

    /**
     *
     */
    private void openWebcams()
    {
        //TODO(Dominik): implement
    }

    
    
    
    
    
    
    
    
    
    //TODO(Dominik): add timeline variables
    //TODO(Dominik): add variable for open menu isOpen
    //TODO(Dominik): when menu is open pause timelines
    //TODO(Dominik): when menu is closed start timelines
    
    //TODO(Dominik): Check if on windows and use webcampanel if on rpi use timeline
    //TODO(Dominik): check for cameras only after pinging them first
    //TODO(Dominik): show warning only when i debug mode
    //TODO(Dominik):implement debug mode
    
    
    
    
    
    
    
    
    //TODO(Dominik): fix check if cameras exist
    /**
     *
     */
    private void closeWebcams()
    {
        if (Webcam.getWebcams().get(0).isOpen())
        {
            Webcam.getWebcams().get(0).close();
        }
        if (Webcam.getWebcams().get(1).isOpen())
        {
            Webcam.getWebcams().get(1).close();
        }
    }

    /**
     *
     * @param grabbedImage
     * @return
     */
    private ImageView repaintImage(BufferedImage grabbedImage)
    {
        WritableImage writableImage = new WritableImage(grabbedImage.getWidth(), grabbedImage.getHeight());
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int x = 0; x < grabbedImage.getWidth(); x++)
        {
            for (int y = 0; y < grabbedImage.getHeight(); y++)
            {
                pixelWriter.setArgb(x, y, grabbedImage.getRGB(x, y));
            }
        }
        return new ImageView(writableImage);
    }

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
        //TODO(Dominik): add checking for cameras and stuff

        //TODO(Dominik): using only one camera input for now cuz i dont have 2 cameras
        startCamStream(this.pane1, 0);
        startCamStream(this.pane2, 0);
    }

    @FXML
    private void handleMenuStopStreamAction(final ActionEvent event)
    {
        //TODO(Dominik): implement
    }

    /**
     * This method puts imageView inside of pane and fits its width and height
     */
    private void initializePane(ScrollPane pane)
    {
        pane.setContent(new ImageView()
        {
            {
                setPreserveRatio(false);
                setSmooth(true);

                fitWidthProperty().bind(pane.widthProperty());
                fitHeightProperty().bind(pane.heightProperty());
            }
        });
    }

    /**
     *
     * @param pane
     * @param webcamNumber
     */
    private void startCamStream(ScrollPane pane, int webcamNumber)
    {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), ev ->
        {
            pane.setContent(repaintImage(Webcam.getWebcams().get(webcamNumber).getImage()));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleMenuOptionsMenu(final ActionEvent event)
    {
        WindowMenuClass wmc = new WindowMenuClass();
        wmc.createOptionsWindow();
    }

    //TODO(Dominik): handle when i cannot connect to not crash
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        /*try
        {
            //TODO(Dominik): remove testing
            MotionCamera1.getInstance().setURL("http://192.168.1.10");
            MotionCamera2.getInstance().setURL("http://192.168.1.10");

            registerCameras();

            System.out.println(Webcam.getWebcams());

            //  openWebcams();
            Webcam.getWebcams().get(0).open();
            System.out.println(Webcam.getWebcams().get(0).isOpen());

            System.out.println(Webcam.getWebcams().get(0).getImage());

            //  testStream();
            initializePane(this.pane1);
            initializePane(this.pane2);
        }
        catch (MalformedURLException e)
        {

            System.err.println("Registering cameras failed");
        }*/

    }

}
