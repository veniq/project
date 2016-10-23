package com.veniqs.view;

import com.veniqs.controller.login.SignInButtonHandler;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPanel extends Stage {

	public static final LoginPanel INSTANCE = new LoginPanel(); // singleton

	private BorderPane root;
	private Scene scene;

	private LoginPanel() {
		root = new BorderPane();

		// username
		Label loginLabel = new Label("Username");
		TextField loginTextField = textFieldBuilder();
		VBox loginBox = new VBox(loginLabel, loginTextField);

		// password
		Label passwordLabel = new Label("Password");
		PasswordField passwordTextField = passwordFieldBuilder();
		VBox passwordBox = new VBox(passwordLabel, passwordTextField);

		// error
		Label errorLabel = new Label();
		errorLabel.getStyleClass().add("error");
		VBox errorBox = new VBox(errorLabel);

		// button
		Button signInButton = new Button("Sign in");
		SignInButtonHandler signInButtonHandler = new SignInButtonHandler(loginTextField, passwordTextField, errorLabel);
		signInButton.setOnAction(signInButtonHandler);
		
		
		// last preparing
		VBox signInBox = new VBox(loginBox, passwordBox, signInButton, errorBox);
		signInBox.setPadding(new Insets(20));
		signInBox.setSpacing(10);
		root.setCenter(signInBox);

		scene = new Scene(root, 200, 200);
		this.setScene(scene);
		this.setTitle("Login panel");
		this.show();
	}



	public BorderPane getRoot() {
		return root;
	}

	public Scene getMyScene() {
		return scene;
	}
	
	private PasswordField passwordFieldBuilder() {
		PasswordField pf = new PasswordField();
		pf.setMinWidth(80);
		pf.setMaxWidth(150);
		return pf;
	}
	
	private TextField textFieldBuilder() {
		TextField tf = new TextField();
		tf.setMinWidth(80);
		tf.setMaxWidth(150);
		return tf;
	}

}
