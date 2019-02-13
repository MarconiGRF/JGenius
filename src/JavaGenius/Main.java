package JavaGenius;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    //if(!token){
    //    Login.start
    //}

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        primaryStage.setTitle("JavaGenius Lyrics");
        primaryStage.setScene(new Scene(root,650,540));
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("JavaGenius.png")));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
