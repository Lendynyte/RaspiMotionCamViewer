package camcontrols;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author Dominik Pauli
 * @version 0.2
 * @deprecated 
 */
public class FXMLDocumentController implements Initializable
{
    
    //TODO(Dominik):Obsolete probably going to be removed

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

    //webViews
    @FXML
    private WebView webView1;

    @FXML
    private WebView webView2;

    @FXML
    private WebView webView3;

    @FXML
    private WebView webView4;

    //camera instances
    //TODO: move create initialize method use different constructor
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
        startStream();
    }

    //TODO(Dominik): rework this to work better
    private void startStream()
    {
        preloadStream();
        
        WebEngine webEngine1 = this.webView1.getEngine();
        webEngine1.load(cam1.getURL());

        WebEngine webEngine2 = this.webView2.getEngine();
        webEngine2.load(cam2.getURL());

        WebEngine webEngine3 = this.webView3.getEngine();
        webEngine3.load(cam3.getURL());

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

    //TODO(Dominik):chánge, create in constructor
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

    //TODO(Dominik):does not work
    //TODO(Dominik):check solution for the random zooming this does not help may be caused by the image site i use in preload now
    private void resetZooms()
    {
        webView1.setZoom(1);
        webView2.setZoom(1);
        webView3.setZoom(1);
        webView4.setZoom(1);
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
        if (((Styleable) mouseEvent.getSource()).getId().equals(this.cam1.getHandle()) && mouseEvent.getButton() == MouseButton.PRIMARY)
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
        }

        else if (((Styleable) mouseEvent.getSource()).getId().equals(cam3.getHandle()) && mouseEvent.getButton() == MouseButton.PRIMARY)
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
        else if (((Styleable) mouseEvent.getSource()).getId().equals(cam4.getHandle()) && mouseEvent.getButton() == MouseButton.PRIMARY)
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

    //TODO(Dominik): Make this link to our logo file
    /**
     * This method sets stream screens to starting Image
     */
    private void preloadStream()
    {
        //TODO(Dominik):make the path relative
       /* cam1.setURL("file:C://Users/Dominik/Desktop/bcbcbc/CamControlUiv2/src/camcontrols/preload.html");
        cam2.setURL("file:C://Users/Dominik/Desktop/bcbcbc/CamControlUiv2/src/camcontrols/preload.html");
        cam3.setURL("file:C://Users/Dominik/Desktop/bcbcbc/CamControlUiv2/src/camcontrols/preload.html");
        cam4.setURL("file:C://Users/Dominik/Desktop/bcbcbc/CamControlUiv2/src/camcontrols/preload.html");*/
        
         cam1.setURL("http://192.168.1.3");
         cam2.setURL("http://192.168.1.3");
         cam3.setURL("http://192.168.1.3:8081");
         cam4.setURL("http://192.168.1.3");
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

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //TODO(Dominik):change pref widh height and min height inside gridview so it does not obscure options pane
        // TODO(Dominik): Inicialization
        //setBlackStartingColor();
        setHandles();
        setIDs();
        preloadStream();
        startStream();
    }

}
