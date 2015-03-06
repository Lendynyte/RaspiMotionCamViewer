/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camcontrols;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Dominik
 */
public class FXMLDocumentLiteRevController implements Initializable {

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

    private final ColumnConstraints colCon1 = new ColumnConstraints();
    private final ColumnConstraints colCon2 = new ColumnConstraints();

    //row Constrains declrataion
    private final RowConstraints row1Con = new RowConstraints();
    private final RowConstraints row2Con = new RowConstraints();

    //TODO(Dominik):create method makeCamBig that takes care of these
    private void makeCam1Big() {
        //resize
        firstColumnSizeIncrease(this.colCon1, this.colCon2);

        //apply our resized properties
        resizeCollums(colCon1, colCon2);

        System.out.println("cam1 is now BIG");
    }

    private void makeCam2Big() {
        //resize
        secondColumnSizeIncrease(colCon1, colCon2);

        resizeCollums(colCon1, colCon2);

        System.out.println("cam2 is now BIG");
    }

    private void makeCamDefaultSize() {
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
    private void handleCamFocusEvent(final MouseEvent mouseEvent) {
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
    private void handleCamHighlightEvent(final MouseEvent mouseEvent) {
        ((Node) mouseEvent.getSource()).setStyle("-fx-background-color: orange;");
    }

    /**
     * This method changes Anchor pane background color to black to unhighlight
     * it
     *
     * @param mouseEvent mouse left object boundaries
     */
    @FXML
    private void handleCamUnhighlightEvent(final MouseEvent mouseEvent) {
        ((Node) mouseEvent.getSource()).setStyle("-fx-background-color: black;");
    }

    /**
     * This method changes contents of cameraGrid collumn constrains observable
     * list to change size of camera views
     *
     * @param colCon1 constrains of column1
     * @param colCon2 constrains of column2
     */
    private void resizeCollums(ColumnConstraints colCon1, ColumnConstraints colCon2) {
        cameraGrid.getColumnConstraints().clear();
        cameraGrid.getColumnConstraints().addAll(colCon1, colCon2);
    }

    /**
     * This method increases width of col1 and decrases height of col2
     *
     * @param colCon1 constrains of col1
     * @param colCon2 constrains of col2
     */
    private void firstColumnSizeIncrease(ColumnConstraints colCon1, ColumnConstraints colCon2) {
        colCon1.setPercentWidth(75);
        colCon2.setPercentWidth(25);
    }

    /**
     * This method increases width of col1 and decrases height of col2
     *
     * @param colCon1 constrains of col1
     * @param colCon2 constrains of col2
     */
    private void secondColumnSizeIncrease(ColumnConstraints colCon1, ColumnConstraints colCon2) {
        colCon1.setPercentWidth(25);
        colCon2.setPercentWidth(75);
    }

    /**
     *
     * @param colCon1 constrains of column1
     * @param colCon2 constrains of column2
     */
    private void setDefaultGrid(ColumnConstraints colCon1, ColumnConstraints colCon2) {
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
    private void handleMenuResetCameraSizesAction(final ActionEvent event) {
        this.setDefaultGrid(colCon1, colCon2);
        System.out.println("Camera sizes reseted...");
    }

    /**
     * Control used in menuBar to close window
     *
     * @param event
     */
    @FXML
    private void handleMenuCloseAction(final ActionEvent event) {
        System.out.println("Closing...");
        this.mainPane.getScene().getWindow().hide();
    }

    /**
     * This methods show basic about popup window
     *
     * @param event
     */
    @FXML
    private void handleMenuAboutAction(final ActionEvent event) {
        WindowMenuClass wmc = new WindowMenuClass();
        wmc.createAboutWindow();
    }

    //TODO(Dominik): create method setCameraURLs
    @FXML
    private void handleMenuStartStreamAction(final ActionEvent event) {
        //setCameraURLs
        testStream();
    }

    @FXML
    private void handleMenuStopStreamAction(final ActionEvent event) {
        // preloadStream();
        //startStream();
    }

    //TODO(Dominik): think about hgbar vbar policy
    // z https://community.oracle.com/thread/2320727
    //TODO(Dominik):fix still does not feel right
    private void testStream() {
        pane1.setContent(new ImageView() {
            {
                imageProperty().set(new Image("file:C://Users/Dominik/Desktop/em.jpg"));
                setPreserveRatio(false);
                setSmooth(true);

                fitWidthProperty().bind(pane1.widthProperty());
                fitHeightProperty().bind(pane1.heightProperty());
            }
        });

        pane2.setContent(new ImageView() {
            {
                imageProperty().set(new Image("file:C://Users/Dominik/Desktop/em.jpg"));
                setPreserveRatio(false);
                setSmooth(true);

                fitWidthProperty().bind(pane1.widthProperty());
                fitHeightProperty().bind(pane1.heightProperty());
            }
        });

    }

    @FXML
    private void handleMenuOptionsMenu(final ActionEvent event) {
        WindowMenuClass wmc = new WindowMenuClass();
        wmc.createOptionsWindow();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
