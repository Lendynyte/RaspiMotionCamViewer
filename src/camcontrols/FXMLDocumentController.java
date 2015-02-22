package camcontrols;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
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
 */
public class FXMLDocumentController implements Initializable {
    
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
    private final  ColumnConstraints colCon1 = new ColumnConstraints();
    private final  ColumnConstraints colCon2 = new ColumnConstraints();
    
    //row Constrains declrataion
    private final RowConstraints row1Con = new RowConstraints();
    private final RowConstraints row2Con = new RowConstraints();
    
    @FXML
    private void handleButtonCam1Bigger(ActionEvent event) {
        //resize
        firstRowSizeIncrease(this.row1Con, this.row2Con);
        firstColumnSizeIncrease(this.colCon1, this.colCon2);
        
        //apply our resized properties
        resizeRows(row1Con, row2Con);
        resizeCollums(colCon1, colCon2);
        
        System.out.println("cam1 is now BIG");
    }
    
    @FXML
    private void handleButtonCam2Bigger(ActionEvent event) {
        //resize
        firstRowSizeIncrease(row1Con, row1Con);
        secondColumnSizeIncrease(colCon1, colCon2);
        
        //apply our resized properties
        resizeRows(row1Con, row2Con);
        resizeCollums(colCon1, colCon2);
        
        System.out.println("cam2 is now BIG");
    }
    
    @FXML
    private void handleButtonCam3Bigger(ActionEvent event) {
        //resize
        secondRowSizeIncrease(row1Con, row1Con);
        firstColumnSizeIncrease(colCon1, colCon2);
        
        //apply our resized properties
        resizeRows(row1Con, row2Con);
        resizeCollums(colCon1, colCon2);
        
        System.out.println("cam3 is now BIG");
    }
    
    @FXML
    private void handleButtonCam4Bigger(ActionEvent event) {
        //resize
        secondRowSizeIncrease(row1Con, row1Con);
        secondColumnSizeIncrease(colCon1, colCon2);
        
        //apply our resized properties
        resizeRows(row1Con, row2Con);
        resizeCollums(colCon1, colCon2);
        
        System.out.println("cam4 is now BIG");
    }
    
    @FXML
    private void handleButtonCamDefault(ActionEvent event) {
        //resize
        setDefaultGrid(colCon1, colCon2, row1Con, row2Con);
        
        //apply our resized properties
        resizeRows(row1Con, row2Con);
        resizeCollums(colCon1, colCon2);
        
        System.out.println("cameras are all the same size");
    }
    
    @FXML
    private void handleButtonTestStream(ActionEvent event) {
        startTestStream(event);
    }
    
    private void startTestStream(ActionEvent event){
        cam1.setURL("http://www.seznam.cz");
        cam1.setName("cam1");
        
        WebEngine webEngine1 = this.webView1.getEngine();
        webEngine1.load(cam1.getURL());

        //TODO: move creating cameras to initializing method
        //MotionCam cam2 = new MotionCam();
       // cam2.setURL("http://i97.photobucket.com/albums/l238/ssj3fox/catgirlurday/1adfba6dc32b30883c14e5d0b5e0a4fd.png");
        cam2.setURL("http://www.seznam.cz");
        cam2.setName("cam2");
        
        WebEngine webEngine2 = this.webView2.getEngine();
        webEngine2.load(cam2.getURL());
        
        
        //TODO: move creating cameras to initializing method
        //MotionCam cam3 = new MotionCam();
        //cam3.setURL("http://i97.photobucket.com/albums/l238/ssj3fox/catgirlurday/1adfba6dc32b30883c14e5d0b5e0a4fd.png");
        cam3.setURL("http://www.seznam.cz");
        cam3.setName("cam3");
        
        WebEngine webEngine3 = this.webView3.getEngine();
        webEngine3.load(cam3.getURL());
        
        
        
        //TODO: move creating cameras to initializing method
        //MotionCam cam4 = new MotionCam();
        //cam4.setURL("http://i97.photobucket.com/albums/l238/ssj3fox/catgirlurday/1adfba6dc32b30883c14e5d0b5e0a4fd.png");
        cam4.setURL("http://www.seznam.cz");
        cam4.setName("cam4");
        
        WebEngine webEngine4 = this.webView4.getEngine();
        webEngine4.load(cam4.getURL());
    }
    
    
    
    
    //TESTING VARIABLES AND METHODS END
    
    /**
     * This method changes contents of cameraGrid collumn constrains observable list to change size of camera views
     * 
     * @param colCon1 constrains of column1
     * @param colCon2 constrains of column2
     */
    private void resizeCollums(ColumnConstraints colCon1, ColumnConstraints colCon2){
        cameraGrid.getColumnConstraints().clear();
        cameraGrid.getColumnConstraints().addAll(colCon1, colCon2);
    }
    
    /**
     * This method changes contents of cameraGrid collumn constrains observable list to change size of camera views
     * 
     * @param rowCon1 constrains of row1
     * @param rowCon2 constrains of row2
     */
    private void resizeRows(RowConstraints rowCon1, RowConstraints rowCon2){
        cameraGrid.getRowConstraints().clear();
        cameraGrid.getRowConstraints().addAll(rowCon1,rowCon2);
    }
    
    /**
     * This method increases height of row1 and decrases height of row2
     * 
     * @param rowCon1 constrains of row1
     * @param rowCon2 constrains of row2
     */
    private void firstRowSizeIncrease(RowConstraints rowCon1, RowConstraints rowCon2){
         row1Con.setPercentHeight(75);
         row2Con.setPercentHeight(25);
    }
    
    /**
     * This method increases height of row2 and decrases height of row1
     * 
     * @param rowCon1 constrains of row1
     * @param rowCon2 constrains of row2
     */
    private void secondRowSizeIncrease(RowConstraints rowCon1, RowConstraints rowCon2){
        row1Con.setPercentHeight(25);
        row2Con.setPercentHeight(75);
    }
    
    /**
     * This method increases width of col1 and decrases height of col2
     * 
     * @param colCon1 constrains of col1
     * @param colCon2 constrains of col2
     */
    private void firstColumnSizeIncrease(ColumnConstraints colCon1, ColumnConstraints colCon2){
         colCon1.setPercentWidth(75);
         colCon2.setPercentWidth(25);
    }
    
    /**
     * This method increases width of col1 and decrases height of col2
     * 
     * @param colCon1 constrains of col1
     * @param colCon2 constrains of col2
     */
    private void secondColumnSizeIncrease(ColumnConstraints colCon1, ColumnConstraints colCon2){
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
                                RowConstraints rowCon1, RowConstraints rowCon2){
        //set rows
        row1Con.setPercentHeight(50);
        row2Con.setPercentHeight(50);
        
        //set columns
        colCon1.setPercentWidth(50);
        colCon2.setPercentWidth(50);
    }
 
    
    //TODO(IMPORTANT): DOES NOT WORK
    //TODO: test if this actually closes the window
    /**
     * Control usef In menubar to close window
     *
     * @param event
     */
    @FXML
    private void handleMenuCloseAction(final ActionEvent event) {
        System.out.println("Closing...");
        this.menuBar.getScene().getWindow().hide();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO(Dominik): Inicialization
    }    
    
}
