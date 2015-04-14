package camcontrols.gui;

import camcontrols.dependencies.ApplicationVariables;
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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
public class FXMLCamViewController implements Initializable
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
    private Button btnC1Start;

    @FXML
    private Button btnC2Start;

    @FXML
    private Button btnC1Stop;

    @FXML
    private Button btnC2Stop;

    @FXML
    private Button btnC1Reset;

    @FXML
    private Button btnC2Reset;

    @FXML
    private Button btnC1Options;

    @FXML
    private Button btnC2Options;

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="FXML camera highlighting handling">
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

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="FXML menu bar buttons handling">
    /**
     * Control used in menuBar to close window
     *
     * @param event
     */
    @FXML
    private void handleMenuCloseAction(final ActionEvent event)
    {
        System.out.println("Closing...");
        Platform.exit();
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
    private void handleMenuCam1StartStreamAction(final ActionEvent event)
    {
        //TODO(Dominik): add checking for cameras and stuff

        //TODO(Dominik): using only one camera input for now cuz i dont have 2 cameras
        startCamStream(this.pane1, 0);
        startCamStream(this.pane2, 0);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleMenuCam2StartStreamAction(final ActionEvent event)
    {
        //TODO(Dominik):implement
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleMenuCam1StopStreamAction(final ActionEvent event)
    {
        //TODO(Dominik): implement
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleMenuCam2StopStreamAction(final ActionEvent event)
    {
        //TODO(Dominik): implement
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleMenuCam1ResetCameraAction(final ActionEvent event)
    {
        //TODO(Dominik):implement
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleMenuCam2ResetCameraAction(final ActionEvent event)
    {
        //TODO(Dominik):implement
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleMenuCam1OptionsMenu(final ActionEvent event)
    {
        WindowMenuClass wmc = new WindowMenuClass();
        wmc.createCamera1OptionsWindow();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleMenuCam2OptionsMenu(final ActionEvent event)
    {
        WindowMenuClass wmc = new WindowMenuClass();
        wmc.createCamera2OptionsWindow();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="FXML control button handling">
    /**
     *
     * @param event
     */
    @FXML
    private void handleBtnC1Start(final ActionEvent event)
    {
        this.handleMenuCam1StartStreamAction(event);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleBtnC2Start(final ActionEvent event)
    {
        this.handleMenuCam2StartStreamAction(event);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleBtnC1Stop(final ActionEvent event)
    {
        this.handleMenuCam1StopStreamAction(event);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleBtnC2Stop(final ActionEvent event)
    {
        this.handleMenuCam2StopStreamAction(event);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleBtnC1Reset(final ActionEvent event)
    {
        this.handleMenuCam1ResetCameraAction(event);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleBtnC2Reset(final ActionEvent event)
    {
        this.handleMenuCam2ResetCameraAction(event);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleBtnC1Options(final ActionEvent event)
    {
        this.handleMenuCam1OptionsMenu(event);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleBtnC2Options(final ActionEvent event)
    {
        this.handleMenuCam2OptionsMenu(event);
    }

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

    //TODO(Dominik): test if timeline stops when iopen options
    //TODO(Dominik): if this does not work write method that stops timeline on options/help open
    //TODO(Dominik): and it restarts timeline when window closes
    /**
     *
     * @param pane
     * @param webcamNumber
     */
    /*private void startCamStream(ScrollPane pane, int webcamNumber)
     {
     Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), ev ->
     {
     if (!ApplicationVariables.getInstance().isIsHelpOpen() && !ApplicationVariables.getInstance().isIsOptionsOpen()) //both windows closed
     {
     pane.setContent(repaintImage(Webcam.getWebcams().get(webcamNumber).getImage()));
     }
     }));
     timeline.setCycleCount(Animation.INDEFINITE);
     timeline.play();
     }*/
    /**
     *
     * @param pane
     * @param webcamNumber
     */
    private void startCamStream(ScrollPane pane, int webcamNumber)
    {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), ev ->
        {
            if (!ApplicationVariables.getInstance().isIsHelpOpen() && !ApplicationVariables.getInstance().isIsOptionsOpen()) //both windows closed
            {
                Platform.runLater(() ->
                {
                    pane.setContent(repaintImage(Webcam.getWebcams().get(webcamNumber).getImage()));
                });
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    //TODO(Dominik) test this also test without timeline test if it lags
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
        //TODO(Dominik): remove from init block
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
