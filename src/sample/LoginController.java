package sample;

import connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private Label registerLogin;


    @FXML
    private Button loginButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView brandingImageView;

    @FXML
    private ImageView lockImageView;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField enterPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       /* File brandingFile = new File("images/1624374588272.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("images/gembok.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);*/
    }

    public void loginButtonOnAction(ActionEvent event) {
        if(event.getSource() == loginButton){
            if (!usernameTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()) {
                validateLogin();
                System.out.println(usernameTextField.getText());
                System.out.println(enterPasswordField.getText());
            } else {
                loginMessageLabel.setText("Please enter username and password");
                loginMessageLabel.setText("You try to login");
            }
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    public void validateLogin() {
        ConnectionClass connection = new ConnectionClass();
        Connection connectionDB = connection.getConnection();

        if (usernameTextField.getText().equals("Syahrul") && enterPasswordField.getText().equals("syahrul")){
            String verfyLogin = "SELECT count(1) FROM user WHERE namaUser = 'Syahrul' AND password = 'syahrul'";
            try {

                Statement statement = connectionDB.createStatement();
                ResultSet queryResult = statement.executeQuery(verfyLogin);
                while (queryResult.next()){
                    if (queryResult.getInt(1) == 1) {
                        Parent root = FXMLLoader.load(getClass().getResource("sample-admin.fxml"));
                        Stage registerStage = new Stage();
                        registerStage.initStyle(StageStyle.UNDECORATED);
                        registerStage.setScene(new Scene(root));
                        registerStage.show();

                    } else {
                        loginMessageLabel.setText("Invalid login. Please try again");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

        }else {
            String verfyLogin = "SELECT count(1) FROM user WHERE namaUser = '" + usernameTextField.getText() + "' AND password = '" + enterPasswordField.getText() + "'";

            try {

                Statement statement = connectionDB.createStatement();
                ResultSet queryResult = statement.executeQuery(verfyLogin);
                while (queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {
                        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                        Stage registerStage = new Stage();
                        registerStage.initStyle(StageStyle.UNDECORATED);
                        registerStage.setScene(new Scene(root));
                        registerStage.show();

                    } else {
                        loginMessageLabel.setText("Invalid login. Please try again");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }

    public void createAccountFrom() {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 520, 458));
            registerStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void daftarRegister(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource()==registerLogin) {
            createAccountFrom();
        }
    }
}
