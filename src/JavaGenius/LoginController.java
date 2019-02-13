package JavaGenius;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ResourceBundle;


public class LoginController implements Initializable{

    //Login Buttons
    @FXML
    Button login_genius;
    Spinner Spinner1;

    //Buttons Action
    @FXML void handleSubmitPress(ActionEvent event){


        try {

            //Redirect user to Genius Auth webpage.
            Desktop.getDesktop().browse(new URI("https://api.genius.com/oauth/authorize?" +
                    "client_id=Qj3UOnBuH49NwRwkT_u5UtjksNztKOKkYOsodNUQFDeBbnIV-aIuGIt6IWKL9HLM&" +
                    "redirect_uri=http://localhost:57454/&" +
                    "scope=me%20vote&" +
                    "state=authorized&" +
                    "response_type=code"));

            //When redirect, change screen to loading info
            Parent loginLoading = FXMLLoader.load(getClass().getResource("Login_in.fxml"));
            Scene loadingScene = new Scene(loginLoading);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(loadingScene);
            window.show();

            Authentication withGenius = new Authentication();
            String finalAuthorizationCode = withGenius.doAuthentication();

            if (finalAuthorizationCode != null){
                Parent afterAuth = FXMLLoader.load(getClass().getResource("After_Auth.fxml"));
                Scene afterAuthScene = new Scene(afterAuth);
                new MainController(finalAuthorizationCode, window);
                window.setScene(afterAuthScene);
                window.show();
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
