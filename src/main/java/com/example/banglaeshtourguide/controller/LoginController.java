package com.example.banglaeshtourguide.controller;

import com.example.banglaeshtourguide.dao.UserDAO;
import com.example.banglaeshtourguide.model.User;
import com.example.banglaeshtourguide.util.PasswordUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label messageLabel;

    private UserDAO userDAO = new UserDAO();

    @FXML
    private void initialize() {
        messageLabel.setText("");
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            messageLabel.setText("Please enter username and password.");
            return;
        }

        User user = userDAO.getUserByUsername(username);
        if (user == null) {
            messageLabel.setText("Invalid username or password.");
            return;
        }

        // Hash the entered password using SHA-256
        String hashedInput = PasswordUtil.sha256(password);

        // Compare with stored hash
        if (!hashedInput.equalsIgnoreCase(user.getPasswordHash())) {
            messageLabel.setText("Invalid username or password.");
            return;
        }

        // ✅ Successful login — load the main Tour Guide view
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/banglaeshtourguide/tour-guide-view.fxml"));
            Scene mainScene = new Scene(loader.load(), 800, 600);

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Bangladesh Tour Guide");
            stage.setScene(mainScene);
        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Failed to load main view: " + e.getMessage());
        }
    }
}
