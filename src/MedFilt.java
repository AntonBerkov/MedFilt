import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;


public class MedFilt {
    public void filt() throws IOException {
        File f=new File("1.jpg");
        FileInputStream input = new FileInputStream("1.jpg");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        Button button  = new Button("Ok");
        button.setLayoutX(400);
        button.setLayoutY(500);
        Color[] pixel=new Color[9];
        Pane pane = new Pane();
        pane.getChildren().addAll(imageView,button);
        Scene scene = new Scene(pane,800,600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        int[] R=new int[9];
        int[] B=new int[9];
        int[] G=new int[9];
        File output=new File("ready.jpg");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BufferedImage img= null;
                try {
                    img = ImageIO.read(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for(int i=1;i<img.getWidth()-1;i++)
                    for(int j=1;j<img.getHeight()-1;j++)
                    {
                        pixel[0]=new Color(img.getRGB(i-1,j-1));
                        pixel[1]=new Color(img.getRGB(i-1,j));
                        pixel[2]=new Color(img.getRGB(i-1,j+1));
                        pixel[3]=new Color(img.getRGB(i,j+1));
                        pixel[4]=new Color(img.getRGB(i+1,j+1));
                        pixel[5]=new Color(img.getRGB(i+1,j));
                        pixel[6]=new Color(img.getRGB(i+1,j-1));
                        pixel[7]=new Color(img.getRGB(i,j-1));
                        pixel[8]=new Color(img.getRGB(i,j));
                        for(int k=0;k<9;k++){
                            R[k]=pixel[k].getRed();
                            B[k]=pixel[k].getBlue();
                            G[k]=pixel[k].getGreen();
                        }
                        Arrays.sort(R);
                        Arrays.sort(G);
                        Arrays.sort(B);
                        img.setRGB(i,j,new Color(R[4],B[4],G[4]).getRGB());
                    }
                try {
                    ImageIO.write(img,"jpg",output);
                    FileInputStream input1 = new FileInputStream("ready.jpg");
                    Image image1 = new Image(input1);
                    ImageView imageView1 = new ImageView(image1);
                    imageView1.setLayoutX(350);
                    imageView1.setLayoutY(0);
                    pane.getChildren().addAll(imageView1);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}
