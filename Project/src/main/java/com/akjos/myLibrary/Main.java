package com.akjos.myLibrary;

import com.akjos.myLibrary.database.DataBaseCreator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(Locale.US);
        DataBaseCreator.init();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainScrean.fxml"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.message");
        loader.setResources(resourceBundle);
        Parent parent = loader.load();
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle(resourceBundle.getString("title.application"));
        primaryStage.show();

    }
}
