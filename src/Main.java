import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        MedFilt medFilt = new MedFilt();
        medFilt.filt();
    }

    public static void main(String[] args){launch(args);}
}