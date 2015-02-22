package camcontrols;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author Dominik Pauli
 * @version 0.2
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private GridPane cameraGrid;
    
    //TESTING VARIABLES AND METHODS
    @FXML
    private Pane pane1;
    
    @FXML
    private Pane pane2;
    
    @FXML
    private Pane pane3;
    
    @FXML
    private Pane pane4;
    
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
       
    //column Constrains declaration
    private  ColumnConstraints colCon1 = new ColumnConstraints();
    private  ColumnConstraints colCon2 = new ColumnConstraints();
    
    //row Constrains declrataion
    private RowConstraints row1Con = new RowConstraints();
    private RowConstraints row2Con = new RowConstraints();
    
    @FXML
    private void handleButtonCam1Bigger(ActionEvent event) {
        
        System.out.println("cam1 is now BIG");
    }
    
    @FXML
    private void handleButtonCam2Bigger(ActionEvent event) {
        
        System.out.println("cam2 is now BIG");
    }
    
    @FXML
    private void handleButtonCam3Bigger(ActionEvent event) {
        
        System.out.println("cam3 is now BIG");
    }
    
    @FXML
    private void handleButtonCam4Bigger(ActionEvent event) {
        
        System.out.println("cam4 is now BIG");
    }
    
    @FXML
    private void handleButtonCamDefault(ActionEvent event) {
        
        System.out.println("cameras are all the same size");
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
        colCon1.setPercentWidth(25);
        colCon2.setPercentWidth(75);
    }
    
    /*
    private void resizGrid(){
 
         ColumnConstraints col1 = new ColumnConstraints();
         col1.setPercentWidth(75);
         ColumnConstraints col2 = new ColumnConstraints();
         col2.setPercentWidth(25);
         cameraGrid.getColumnConstraints().clear();
         cameraGrid.getColumnConstraints().addAll(col1,col2);

         RowConstraints row1 = new RowConstraints();
         row1.setPercentHeight(75);
         RowConstraints row2 = new RowConstraints();
         row2.setPercentHeight(25);
         cameraGrid.getRowConstraints().clear();
         cameraGrid.getRowConstraints().addAll(row1,row2);
         
    }*/
    
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO(Dominik): Inicialization
    }    
    
}
