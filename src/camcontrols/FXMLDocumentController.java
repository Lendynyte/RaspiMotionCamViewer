package camcontrols;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Dominik Pauli
 * @version 0.2
 */
public class FXMLDocumentController implements Initializable
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
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane2;

    @FXML
    private AnchorPane pane3;

    @FXML
    private AnchorPane pane4;

    //test buttons
    @FXML
    private Button btnCamera1Bigger;

    @FXML
    private Button btnCamera2Bigger;

    @FXML
    private Button btnCamera3Bigger;

    @FXML
    private Button btnCamera4Bigger;

    @FXML
    private Button btnDefaultCameraSize;

    @FXML
    private Button btnTestStream;

    //webViews
    @FXML
    private WebView webView1;

    @FXML
    private WebView webView2;

    @FXML
    private WebView webView3;

    @FXML
    private WebView webView4;

    //webView webEngines
   /* private WebEngine webEngine1;
     private WebEngine webEngine2;
     private WebEngine webEngine3;
     private WebEngine webEngine4;*/
    //camera instances
    //TODO: move create initialize method
    private final MotionCamera cam1 = new MotionCamera();
    private final MotionCamera cam2 = new MotionCamera();
    private final MotionCamera cam3 = new MotionCamera();
    private final MotionCamera cam4 = new MotionCamera();

    //column Constrains declaration
    private final ColumnConstraints colCon1 = new ColumnConstraints();
    private final ColumnConstraints colCon2 = new ColumnConstraints();

    //row Constrains declrataion
    private final RowConstraints row1Con = new RowConstraints();
    private final RowConstraints row2Con = new RowConstraints();

    //TODO(Dominik):create method makeCamBig that takes care of these
    private void makeCam1Big()
    {
        //resize
        firstRowSizeIncrease(this.row1Con, this.row2Con);
        firstColumnSizeIncrease(this.colCon1, this.colCon2);

        //apply our resized properties
        resizeRows(row1Con, row2Con);
        resizeCollums(colCon1, colCon2);

        System.out.println("cam1 is now BIG");
    }

    private void makeCam2Big()
    {
        //resize
        firstRowSizeIncrease(row1Con, row1Con);
        secondColumnSizeIncrease(colCon1, colCon2);

        //apply our resized properties
        resizeRows(row1Con, row2Con);
        resizeCollums(colCon1, colCon2);

        System.out.println("cam2 is now BIG");
    }

    private void makeCam3Big()
    {
        //resize
        secondRowSizeIncrease(row1Con, row1Con);
        firstColumnSizeIncrease(colCon1, colCon2);

        //apply our resized properties
        resizeRows(row1Con, row2Con);
        resizeCollums(colCon1, colCon2);

        System.out.println("cam3 is now BIG");
    }

    private void makeCam4Big()
    {
        //resize
        secondRowSizeIncrease(row1Con, row1Con);
        secondColumnSizeIncrease(colCon1, colCon2);

        //apply our resized properties
        resizeRows(row1Con, row2Con);
        resizeCollums(colCon1, colCon2);

        System.out.println("cam4 is now BIG");
    }

    private void makeCamDefaultSize()
    {
        //resize
        setDefaultGrid(colCon1, colCon2, row1Con, row2Con);

        //apply our resized properties
        resizeRows(row1Con, row2Con);
        resizeCollums(colCon1, colCon2);

        System.out.println("cameras are all the same size");
    }

    @FXML
    private void handleButtonTestStream(ActionEvent event)
    {
        startTestStream(event);
    }

    //TODO(Dominik): rework this to work better
    private void startTestStream(ActionEvent event)
    {
        cam1.setURL("http://i97.photobucket.com/albums/l238/ssj3fox/catgirlurday/1adfba6dc32b30883c14e5d0b5e0a4fd.png");
        //cam1.setURL("http://www.seznam.cz");
        cam1.setName("cam1");

        WebEngine webEngine1 = this.webView1.getEngine();
        webEngine1.load(cam1.getURL());

        //TODO: move creating cameras to initializing method
        //MotionCam cam2 = new MotionCam();
        cam2.setURL("http://i97.photobucket.com/albums/l238/ssj3fox/catgirlurday/1adfba6dc32b30883c14e5d0b5e0a4fd.png");
        //cam2.setURL("http://www.seznam.cz");
        cam2.setName("cam2");

        WebEngine webEngine2 = this.webView2.getEngine();
        webEngine2.load(cam2.getURL());

        //TODO: move creating cameras to initializing method
        //MotionCam cam3 = new MotionCam();
        cam3.setURL("http://i97.photobucket.com/albums/l238/ssj3fox/catgirlurday/1adfba6dc32b30883c14e5d0b5e0a4fd.png");
        //cam3.setURL("http://www.seznam.cz");
        cam3.setName("cam3");

        WebEngine webEngine3 = this.webView3.getEngine();
        webEngine3.load(cam3.getURL());

        //TODO: move creating cameras to initializing method
        //MotionCam cam4 = new MotionCam();
        cam4.setURL("http://i97.photobucket.com/albums/l238/ssj3fox/catgirlurday/1adfba6dc32b30883c14e5d0b5e0a4fd.png");
        //cam4.setURL("http://www.seznam.cz");
        cam4.setName("cam4");

        WebEngine webEngine4 = this.webView4.getEngine();
        webEngine4.load(cam4.getURL());
    }

    //TODO(Dominik):rework this is just for now because black do not initialize webengine again
    private void setBlackStartingColor()
    {
        // webView1.setBlendMode(BlendMode.OVERLAY);
        //  webView2.setBlendMode(BlendMode.OVERLAY);
        //  webView3.setBlendMode(BlendMode.OVERLAY);
        //  webView4.setBlendMode(BlendMode.OVERLAY);
    }

    //TODO(Dominik):chánge
    private void setHandles()
    {
        this.cam1.setHandle("cam1");
        this.cam2.setHandle("cam2");
        this.cam3.setHandle("cam3");
        this.cam4.setHandle("cam4");
    }

    //TODO(Dominik):change
    private void setIDs()
    {
        this.pane1.setId(this.cam1.getHandle());
        this.pane2.setId(this.cam2.getHandle());
        this.pane3.setId(this.cam3.getHandle());
        this.pane4.setId(this.cam4.getHandle());
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
        if (((Styleable) mouseEvent.getSource()).getId().equals(this.cam1.getHandle()))
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
                this.cam1.setIsFocused(false);
            }
        }

        if (((Styleable) mouseEvent.getSource()).getId().equals(this.cam2.getHandle()))
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
        }

        else if (((Styleable) mouseEvent.getSource()).getId().equals(cam3.getHandle()))
        {
            if (!this.cam3.isIsFocused())
            {
                makeCam3Big();
                this.cam3.setIsFocused(true);
                this.cam1.setIsFocused(false);
                this.cam2.setIsFocused(false);
                this.cam4.setIsFocused(false);
            }
            else
            {
                makeCamDefaultSize();
                this.cam3.setIsFocused(false);
            }

        }
        else if (((Styleable) mouseEvent.getSource()).getId().equals(cam4.getHandle()))
        {
            if (!this.cam4.isIsFocused())
            {
                makeCam4Big();
                this.cam4.setIsFocused(true);
                this.cam1.setIsFocused(false);
                this.cam2.setIsFocused(false);
                this.cam3.setIsFocused(false);
            }
            else
            {
                makeCamDefaultSize();
                this.cam4.setIsFocused(false);
            }
        }
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
     * This method changes contents of cameraGrid collumn constrains observable
     * list to change size of camera views
     *
     * @param rowCon1 constrains of row1
     * @param rowCon2 constrains of row2
     */
    private void resizeRows(RowConstraints rowCon1, RowConstraints rowCon2)
    {
        cameraGrid.getRowConstraints().clear();
        cameraGrid.getRowConstraints().addAll(rowCon1, rowCon2);
    }

    /**
     * This method increases height of row1 and decrases height of row2
     *
     * @param rowCon1 constrains of row1
     * @param rowCon2 constrains of row2
     */
    private void firstRowSizeIncrease(RowConstraints rowCon1, RowConstraints rowCon2)
    {
        row1Con.setPercentHeight(75);
        row2Con.setPercentHeight(25);
    }

    /**
     * This method increases height of row2 and decrases height of row1
     *
     * @param rowCon1 constrains of row1
     * @param rowCon2 constrains of row2
     */
    private void secondRowSizeIncrease(RowConstraints rowCon1, RowConstraints rowCon2)
    {
        row1Con.setPercentHeight(25);
        row2Con.setPercentHeight(75);
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
     * @param rowCon1 constrains of row1
     * @param rowCon2 constrains of row2
     */
    private void setDefaultGrid(ColumnConstraints colCon1, ColumnConstraints colCon2,
                                RowConstraints rowCon1, RowConstraints rowCon2)
    {
        //set rows
        row1Con.setPercentHeight(50);
        row2Con.setPercentHeight(50);

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
        this.setDefaultGrid(colCon1, colCon2, row1Con, row2Con);
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

    //TODO(Dominik): Move to help menu class
    /**
     * This methods show basic about popup window
     *
     * @param event
     */
    @FXML
    private void handleMenuAboutAction(final ActionEvent event)
    {
        //initialize dialog
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

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
                dialog.close();
            }
        });

        //create Label with about
        Label lbl = new Label("This is a thing yo no \n ff fsdf sdf\nwffefef\njijijij\njojjjo\nffgfg\nw");

        //put vBox in stage
        VBox vbHelp = new VBox();
        vbHelp.setSpacing(50);

        //set everything to center and make tex label expand
        VBox.setVgrow(lbl, Priority.ALWAYS);
        vbHelp.alignmentProperty().set(Pos.TOP_CENTER);

        //add everzthing to vBox
        vbHelp.getChildren().addAll(lbl, btnClose);

        //add everything to scene
        Scene scene = new Scene(vbHelp, 300, 250);

        //show dialog
        dialog.setScene(scene);
        dialog.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO(Dominik): Inicialization
        //setBlackStartingColor();
        setHandles();
        setIDs();
    }

}
