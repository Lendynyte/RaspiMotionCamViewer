package camcontrols.gui;

import camcontrols.comunication.CameraAvailabilityTester;
import camcontrols.comunication.SshCamerahandler;
import camcontrols.dependencies.ApplicationVariables;
import camcontrols.dependencies.MotionCamera1;
import camcontrols.dependencies.MotionCamera2;
import camcontrols.dependencies.MotionCameraInterface;
import camcontrols.saving.XMLCameraHandler;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;
import java.awt.image.BufferedImage;
import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Dominik Pauli
 * @version 0.8
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

    @FXML
    private Button btnC1Ping;

    @FXML
    private Button btnC2Ping;

    @FXML
    private Label lblPingC1Result;

    @FXML
    private Label lblPingC2Result;

    private Timeline timelineCam1;

    private Timeline timelineCam2;
    
    
    private WritableImage image1;
    private WritableImage image2;

    private ImageView imageView1;
    private ImageView imageView2;

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
        ((Node) mouseEvent.getSource()).setStyle("-fx-background-color: darkcyan;");
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
     *
     * @param event
     */
    @FXML
    private void handleMenuToggleFullscreenAction(final ActionEvent event)
    {
        Stage primaryStage = (Stage) this.mainPane.getScene().getWindow();
        if (primaryStage.isFullScreen())
        {
            primaryStage.setFullScreen(Boolean.FALSE);
        }
        else
        {
            primaryStage.setFullScreen(Boolean.TRUE);
        }
    }

    /**
     * This methods show basic about popup window
     *
     * @param event
     */
    @FXML
    private void handleMenuAboutAction(final ActionEvent event)
    {
        new WindowMenuClass().createAboutWindow();
    }

    @FXML
    private void handleMenuCam1StartStreamAction(final ActionEvent event)
    {
        if (pingCamera(MotionCamera1.getInstance()))
        {
            openWebcam(0);

            timelineCam1 = startCamStream(0);
            lblPingC1Result.setText("Stream started on camera: " + MotionCamera1.getInstance().getName());
            timelineCam1.setCycleCount(Animation.INDEFINITE);
            timelineCam1.play();
        }
        else
        {
            lblPingC1Result.setText("Unable to reach camera: " + MotionCamera1.getInstance().getName());
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleMenuCam2StartStreamAction(final ActionEvent event)
    {
        if (pingCamera(MotionCamera2.getInstance()))
        {
            //TODO(Dominik): change to for 2 cams
            openWebcam(0);

            timelineCam2 = startCamStream(1);
            lblPingC2Result.setText("Stream started on camera: " + MotionCamera2.getInstance().getName());
            timelineCam2.setCycleCount(Animation.INDEFINITE);
            timelineCam2.play();
        }
        else
        {
            lblPingC2Result.setText("Unable to reach camera: " + MotionCamera2.getInstance().getName());
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleMenuCam1StopStreamAction(final ActionEvent event)
    {
        if (this.timelineCam1.getStatus().equals(Animation.Status.RUNNING))
        {
            this.timelineCam1.stop();
            this.lblPingC1Result.setText("Stream stopped on camera: " + MotionCamera1.getInstance().getName());
            this.startImageInit("/offline.png", this.imageView1);
        }
        else
        {
            System.out.println("Nothing to stop");
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleMenuCam2StopStreamAction(final ActionEvent event)
    {
        if (this.timelineCam2.getStatus().equals(Animation.Status.RUNNING))
        {
            this.timelineCam2.stop();
            this.lblPingC1Result.setText("Stream stopped on camera: " + MotionCamera2.getInstance().getName());
            this.startImageInit("/offline.png", this.imageView2);
        }
        else
        {
            System.out.println("Nothing to stop");
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleMenuCam1ResetCameraAction(final ActionEvent event)
    {
        restartCamera(MotionCamera1.getInstance(), 1);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleMenuCam2ResetCameraAction(final ActionEvent event)
    {
        restartCamera(MotionCamera2.getInstance(), 2);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleMenuCam1OptionsMenu(final ActionEvent event)
    {
        new WindowMenuClass().createCamera1OptionsWindow();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleMenuCam2OptionsMenu(final ActionEvent event)
    {
        new WindowMenuClass().createCamera2OptionsWindow();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleMenuApplicationSettings(final ActionEvent event)
    {
        new WindowMenuClass().createAboutApplicationSettingsWindow();
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

    /**
     *
     * @param event
     */
    @FXML
    private void handleBtnPingCam1(final ActionEvent event)
    {

        if (pingCamera(MotionCamera1.getInstance()))
        {
            lblPingC1Result.setText("Camera " + MotionCamera1.getInstance().getName() + " is responding.");
        }
        else
        {
            lblPingC1Result.setText("Unable to reach camera: " + MotionCamera1.getInstance().getName());
        }

    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleBtnPingCam2(final ActionEvent event)
    {
        if (pingCamera(MotionCamera2.getInstance()))
        {
            lblPingC2Result.setText("Camera " + MotionCamera2.getInstance().getName() + " is responding.");
        }
        else
        {
            lblPingC2Result.setText("Unable to reach camera: " + MotionCamera2.getInstance().getName());
        }

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
     */
    private void registerCameras()
    {
        try
        {
            IpCamDeviceRegistry.register("Camera 1", "http://" + MotionCamera1.getInstance().getURL() + ":8081/video.mjpg", IpCamMode.PUSH);
            IpCamDeviceRegistry.register("Camera 2", "http://" + MotionCamera2.getInstance().getURL() + ":8081/video.mjpg", IpCamMode.PUSH);
        }
        catch (MalformedURLException ex)
        {
            System.err.println("Registering cameras failed ...");
        }
    }

    /**
     * This method is used for opening cameras
     *
     * @param camId 0 for camera 1 1 for camera 2
     */
    private void openWebcam(int camId)
    {
        switch (camId)
        {
            case 0:
            {
                if (pingCamera(MotionCamera1.getInstance()) && !Webcam.getWebcams().get(0).isOpen())
                {
                    Webcam.getWebcams().get(0).open();
                }
            }
            break;
            case 1:
            {
                if (pingCamera(MotionCamera2.getInstance()) && !Webcam.getWebcams().get(1).isOpen())
                {
                    Webcam.getWebcams().get(1).open();
                }
            }
            break;
        }
    }

    /**
     *
     * @param camId 0 for cam1 1 for cam2
     */
    private void closeWebcam(int camId)
    {
        switch (camId)
        {
            case 0:
            {
                if (Webcam.getWebcams().get(0).isOpen())
                {
                    Webcam.getWebcams().get(0).close();
                }
            }
            break;
            case 1:
            {
                if (Webcam.getWebcams().get(1).isOpen())
                {
                    Webcam.getWebcams().get(1).close();
                }
            }
            break;
        }
    }

    /**
     *
     * @param MotioCamera
     */
    private void restartCamera(MotionCameraInterface MotioCamera, int camId)
    {
        if (pingCamera(MotioCamera))
        {
            new SshCamerahandler().restartCamera(MotioCamera);
        }
        else
        {
            switch (camId)
            {
                case 1:
                    this.lblPingC1Result.setText("Unable to reach camera: " + MotioCamera.getName());
                    break;
                case 2:
                    this.lblPingC2Result.setText("Unable to reach camera: " + MotioCamera.getName());
            }
        }
    }

    /**
     *
     * @param grabbedImage
     */
    private void repaintImage1(BufferedImage grabbedImage)
    {
        if (image1.getWidth() != grabbedImage.getWidth() || image1.getHeight() != grabbedImage.getHeight())
        {
            this.image1 = new WritableImage(grabbedImage.getWidth(), grabbedImage.getHeight());
            this.imageView1.setImage(this.image1);
        }

        PixelWriter pixelWriter = image1.getPixelWriter();

        for (int x = 0; x < grabbedImage.getWidth(); x++)
        {
            for (int y = 0; y < grabbedImage.getHeight(); y++)
            {
                pixelWriter.setArgb(x, y, grabbedImage.getRGB(x, y));
            }
        }
    }

    /**
     *
     * @param grabbedImage
     */
    private void repaintImage2(BufferedImage grabbedImage)
    {
        if (image2.getWidth() != grabbedImage.getWidth() || image2.getHeight() != grabbedImage.getHeight())
        {
            this.image2 = new WritableImage(grabbedImage.getWidth(), grabbedImage.getHeight());
            this.imageView2.setImage(this.image2);
        }

        PixelWriter pixelWriter = image2.getPixelWriter();

        for (int x = 0; x < grabbedImage.getWidth(); x++)
        {
            for (int y = 0; y < grabbedImage.getHeight(); y++)
            {
                pixelWriter.setArgb(x, y, grabbedImage.getRGB(x, y));
            }
        }
    }

    /**
     *
     * @param imageView
     * @param webcamNumber
     * @return
     */
    private Timeline startCamStream(int webcamNumber)
    {
        return new Timeline(new KeyFrame(Duration.seconds(2), ev ->
        {
            switch (webcamNumber)
            {
                case 0:
                    Platform.runLater(() ->
                    {
                        repaintImage1(Webcam.getWebcams().get(0).getImage());
                    });
                    break;

                case 1:
                    Platform.runLater(() ->
                    {
                        //TODO(Dominik):change webcam number to 1 in get
                        repaintImage2(Webcam.getWebcams().get(0).getImage());
                    });
                    break;
            }
        }));
    }

    /**
     *
     */
    private void initImageViews()
    {
        this.image1 = new WritableImage((int) this.pane1.getWidth(), (int) this.pane1.getHeight());
        this.image2 = new WritableImage((int) this.pane2.getWidth(), (int) this.pane2.getHeight());

        //Imageview 1
        this.imageView1 = new ImageView();
        this.imageView1.setImage(this.image1);

        this.imageView1.setPreserveRatio(false);
        this.imageView1.setSmooth(true);
        this.imageView1.fitWidthProperty().bind(pane1.widthProperty());
        this.imageView1.fitHeightProperty().bind(pane1.heightProperty());

        this.timelineCam1 = new Timeline();

        this.pane1.setContent(this.imageView1);

        //ImageView 2
        this.imageView2 = new ImageView();
        this.imageView2.setImage(this.image2);

        this.imageView2.setPreserveRatio(false);
        this.imageView2.setSmooth(true);
        this.imageView2.fitWidthProperty().bind(pane2.widthProperty());
        this.imageView2.fitHeightProperty().bind(pane2.heightProperty());

        this.timelineCam2 = new Timeline();

        this.pane2.setContent(this.imageView2);
    }

    /**
     * This method pings camera using ping console command and returns result as
     * boolean
     *
     * @return True if camera is reachable false if it isnt
     */
    private boolean pingCamera(MotionCameraInterface MotionCamera)
    {
        return new CameraAvailabilityTester().isReachable(MotionCamera.getURL(), 200);
    }

    /**
     *
     * @param MotionCamera
     */
    private void runMotion(MotionCameraInterface MotionCamera)
    {
        new SshCamerahandler().runMotion(MotionCamera, 200);
    }

    /**
     * This method is used for initializing application variables and is called
     * on load
     */
    private void startInit()
    {
        //TODO(Dominik):change variables for linux
        ApplicationVariables.getInstance().setXmlSaveDirectoryPath("c:\test");
        ApplicationVariables.getInstance().setInstallDirectoryPath("c:\test");

    }

    /**
     * This method sets image to init image
     *
     * @param initImagePath
     * @return
     */
    private void startImageInit(String initImagePath, ImageView imageView)
    {
        imageView.setImage(new Image(getClass().getResourceAsStream(initImagePath)));
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
        //TODO(Dominik): handle when i cannot connect to not crash  
        //TODO(Dominik): currently cannot change ip when program is running if stop then load it will crash
        //TODO(Dominik): remove testing
        //TODO(Dominik): have to do checking for cameras if i have only 1 to not load it etc...

        Platform.runLater(() ->
        {
            //APPLICATION VARIABLES INITIALIZATION
            startInit();

            //IMAGEVIEW INITIALIZATION
            initImageViews();

            //TODO(Dominik): here maybe use the same technique as with loading image puting it into src folder?
            //SETTING UP PATHS FROM XML
            XMLCameraHandler xmlHandler = new XMLCameraHandler();
            if (xmlHandler.checkForXMLSave(MotionCamera1.getInstance().getXMLSavePath() + "/cam1.xml"))
            {
                //  xmlHandler.loadCameraURLs(MotionCamera1.getInstance(), "/cam1.xml");
            }
            if (xmlHandler.checkForXMLSave(MotionCamera2.getInstance().getXMLSavePath() + "cam2.xml"))
            {
                //   xmlHandler.loadCameraURLs(MotionCamera1.getInstance(), "/cam1.xml");
            }

            //STREAN PREPARATION
            MotionCamera1.getInstance().setURL("192.168.1.102");
            MotionCamera2.getInstance().setURL("192.168.1.102");

            //CAMERA REGISTERING
            registerCameras();
            System.out.println(Webcam.getWebcams());

            //TODO(Dominik): not do at init
            //TODO(Dominik): test if it works like this and have to reregister cams when ip is changed and then reopen
            //openWebcam(0);
            //openWebcam(1);
            
            //LOADING INIT IMAGES
            this.startImageInit("/offline.png", imageView1);
            this.startImageInit("/offline.png", imageView2);

        });
    }
}
