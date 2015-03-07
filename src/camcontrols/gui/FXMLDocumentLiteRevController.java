/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;
import camcontrols.dependencies.*;
/**
 * FXML Controller class
 *
 * @author Dominik
 */
public class FXMLDocumentLiteRevController implements Initializable
{

    @FXML
    private AnchorPane mainPane;

    @FXML
    private GridPane cameraGrid;

    @FXML
    private MenuBar menuBar;

    //TESTING VARIABLES AND METHODS
    //webView containers
    @FXML
    private ScrollPane pane1;
    //private AnchorPane pane1;

    @FXML
    private ScrollPane pane2;

    //ImageViews
    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private TextField testTFURL1;

    @FXML
    private TextField testTFURL2;

    private final ColumnConstraints colCon1 = new ColumnConstraints();
    private final ColumnConstraints colCon2 = new ColumnConstraints();

    //row Constrains declrataion
    private final RowConstraints row1Con = new RowConstraints();
    private final RowConstraints row2Con = new RowConstraints();

    //TODO(Dominik):create method makeCamBig that takes care of these
    private void makeCam1Big()
    {
        //resize
        firstColumnSizeIncrease(this.colCon1, this.colCon2);

        //apply our resized properties
        resizeCollums(colCon1, colCon2);

        System.out.println("cam1 is now BIG");
    }

    private void makeCam2Big()
    {
        //resize
        secondColumnSizeIncrease(colCon1, colCon2);

        resizeCollums(colCon1, colCon2);

        System.out.println("cam2 is now BIG");
    }

    private void makeCamDefaultSize()
    {
        //resize
        setDefaultGrid(colCon1, colCon2);

        //apply our resized properties
        resizeCollums(colCon1, colCon2);

        System.out.println("cameras are all the same size");
    }

    //TODO(Dominik): still terible but it works
    //TODO(Dominik):make this thing shorter
    /**
     * This method handle mouse input on camera panes on first click in
     * increases their size afterthey are set to bigger size next click reset
     * the size
     *
     * @param event
     */
    @FXML
    private void handleCamFocusEvent(final MouseEvent mouseEvent)
    {
        //TODO(Dominik): change to switch
      /*  if (((Styleable) mouseEvent.getSource()).getId().equals(this.cam1.getHandle()) && mouseEvent.getButton() == MouseButton.PRIMARY)
         {
         if (!this.cam1.isIsFocused())
         {
         makeCam1Big();
         this.cam1.setIsFocused(true);
         this.cam2.setIsFocused(false);
         this.cam3.setIsFocused(false);
         this.cam4.setIsFocused(false);
         }
         else
         {
         makeCamDefaultSize();

         //TODO(Dominik):check if this helps
         resetZooms();

         this.cam1.setIsFocused(false);
         }
         }

         if (((Styleable) mouseEvent.getSource()).getId().equals(this.cam2.getHandle()) && mouseEvent.getButton() == MouseButton.PRIMARY)
         {
         if (!this.cam2.isIsFocused())
         {
         makeCam2Big();
         this.cam2.setIsFocused(true);
         this.cam1.setIsFocused(false);
         this.cam3.setIsFocused(false);
         this.cam4.setIsFocused(false);
         }
         else
         {
         makeCamDefaultSize();
         this.cam2.setIsFocused(false);
         }
         }*/
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
     * This method changes contents of cameraGrid collumn constrains observable
     * list to change size of camera views
     *
     * @param colCon1 constrains of column1
     * @param colCon2 constrains of column2
     */
    private void resizeCollums(ColumnConstraints colCon1, ColumnConstraints colCon2)
    {
        cameraGrid.getColumnConstraints().clear();
        cameraGrid.getColumnConstraints().addAll(colCon1, colCon2);
    }

    /**
     * This method increases width of col1 and decrases height of col2
     *
     * @param colCon1 constrains of col1
     * @param colCon2 constrains of col2
     */
    private void firstColumnSizeIncrease(ColumnConstraints colCon1, ColumnConstraints colCon2)
    {
        colCon1.setPercentWidth(75);
        colCon2.setPercentWidth(25);
    }

    /**
     * This method increases width of col1 and decrases height of col2
     *
     * @param colCon1 constrains of col1
     * @param colCon2 constrains of col2
     */
    private void secondColumnSizeIncrease(ColumnConstraints colCon1, ColumnConstraints colCon2)
    {
        colCon1.setPercentWidth(25);
        colCon2.setPercentWidth(75);
    }

    /**
     *
     * @param colCon1 constrains of column1
     * @param colCon2 constrains of column2
     */
    private void setDefaultGrid(ColumnConstraints colCon1, ColumnConstraints colCon2)
    {
        //set columns
        colCon1.setPercentWidth(50);
        colCon2.setPercentWidth(50);
    }

    /**
     * This method resets camera sizes
     *
     * @param event
     */
    @FXML
    private void handleMenuResetCameraSizesAction(final ActionEvent event)
    {
        this.setDefaultGrid(colCon1, colCon2);
        System.out.println("Camera sizes reseted...");
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
        testStream();
    }

    @FXML
    private void handleMenuStopStreamAction(final ActionEvent event)
    {
        // preloadStream();
        //startStream();
    }
    /*
     private void handleStreamEvent() {
     Timeline timeline = new Timeline();
     timeline.setCycleCount(Animation.INDEFINITE);

     //TODO: It is lagging each time the timeline is refreshed if the furation is under 1 sec the windows is not responding at all
     KeyFrame playStream = new KeyFrame(Duration.seconds(1),//(.0200),
     new EventHandler<ActionEvent>() {

     /*
     *   Get URL and set the Image in ImageView to image on the URL
     */
    /*   @Override
     public void handle(ActionEvent event) {
     //uncoment commented for now because of errors from no url

                        
                        
     if (GLOBAL_URL != "") {
     Image image = new Image(GLOBAL_URL); // edit after I get right url
     imageView.setImage(image);
                            
     //TODO: try different container
     imageView.fitWidthProperty().bind(camContainer1.widthProperty());
     imageView.fitHeightProperty().bind(camContainer1.heightProperty());
                            
                            
     }
     }
     });

     timeline.getKeyFrames().add(playStream);
     timeline.play();
     }
    
     */

    //TODO(Dominik): image View timelapse
    //TODO(Dominik): challenge make focused cam big and the other one smaller on side
    //TODO(Dominik): maybe little rework
    //TODO(Dominik): think about hgbar vbar policy
    // z https://community.oracle.com/thread/2320727
    //TODO(Dominik):fix still does not feel right
    private void testStream()
    {
        /*  pane1.setContent(new ImageView() {
         {
         imageProperty().set(new Image("file:C://Users/Dominik/Desktop/em.jpg"));
         setPreserveRatio(false);
         setSmooth(true);

         fitWidthProperty().bind(pane1.widthProperty());
         fitHeightProperty().bind(pane1.heightProperty());
         }
         });*/

        //"file:C://Users/Dominik/Desktop/em.jpg"
        
        
        //TODO(Dominik): add if statement to check if url is not empty after i have the url config ready maybe move to config file
        try
        {
            startCam1Stream(this.testTFURL1.getText());
        }
        catch (Exception e)
        {
            System.out.println("no URL in cam 1");
        }

        try
        {
            startCam2Stream();
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
        KeyFrame playStream = new KeyFrame(Duration.seconds(1),//(.0200),
                new EventHandler<ActionEvent>()
                {

                    @Override
                    public void handle(ActionEvent event)
                    {
                        pane1.setContent(new ImageView()
                        {
                            {
                                imageProperty().set(new Image("http://192.168.1.3"));
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
        // TODO
       // testStream();
    }

}
