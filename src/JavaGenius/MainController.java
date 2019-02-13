package JavaGenius;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable{

    final String authcode;
    final Stage window;

    MainController(String receivedCode, Stage window){
        this.authcode = receivedCode;
        this.window = window;
    }

    @FXML public TextField searchbar;

    //Search Action
    @FXML void handleEnterSearch(KeyEvent press){

        try {
            if (press.getCode().equals(KeyCode.ENTER)){
                String searchContent = searchbar.getText();

            }


        }

        catch(IOException e){
            e.printStackTrace();
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
