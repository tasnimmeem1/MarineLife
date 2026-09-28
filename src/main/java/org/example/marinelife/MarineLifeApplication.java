package org.example.marinelife;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MarineLifeApplication extends Application {
    private static Stage window;

    @Override
    public void start(Stage stage) {
        window = stage;
        window.setTitle("Marine Life Explorer");
        window.setMinWidth(900);
        window.setMinHeight(650);
        show("Splash.fxml");
        window.show();
    }

    public static void show(String filename) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    MarineLifeApplication.class.getResource(filename)
            );
            Parent root = loader.load();

            Scene scene = new Scene(root, 1100, 760);
            scene.getStylesheets().add(
                    MarineLifeApplication.class
                            .getResource("style.css")
                            .toExternalForm()
            );

            window.setScene(scene);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Could not open " + filename, e
            );
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
