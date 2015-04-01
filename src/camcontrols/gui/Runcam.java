
//import com.github.sarxos.webcam.Webcam;
//import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
//import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
//import com.github.sarxos.webcam.ds.ipcam.IpCamMode;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dominik
 */
public class Runcam //extends Application
{

    
    //TODO(Dominik): test in project and implement into project
    //TODO(Dominik): add required libraries
    
    
    
    private BorderPane root;
    private ImageView imgWebCamCapturedImage;
    private static BufferedImage grabbedImage;
    
    // Webcam webCam;
    private BorderPane webCamPane;


  /*  static
    {
        Webcam.setDriver(new IpCamDriver());
    }*/

  /*  @Override
    public void start(Stage primaryStage) throws MalformedURLException
    {
        root = new BorderPane();

      //  Webcam.getWebcams().get(0).open();

       // BufferedImage image = Webcam.getWebcams().get(0).getImage();
        
    

       /* WritableImage wr = null;
        if (image != null)
        {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++)
            {
                for (int y = 0; y < image.getHeight(); y++)
                {
                    pw.setArgb(x, y,image.getRGB(x, y));
                }
            }
        }

          ImageView imView = new ImageView(wr);
        System.out.println(image);
        webCamPane = new BorderPane();
        webCamPane.setStyle("-fx-background-color: #ccc;");
        webCamPane.setCenter(imView);
        root.setCenter(webCamPane);

        //root.setCenter(sn);
        primaryStage.setScene(new Scene(root));
        primaryStage.setHeight(700);
        primaryStage.setWidth(600);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) throws MalformedURLException
    {
        IpCamDeviceRegistry.register("Lignano", "http://192.168.1.4:8081/video.mjpg", IpCamMode.PUSH);

        launch(args);
    }*/
    
}
