package org.example.marinelife;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class SplashController {
    @FXML private StackPane splashRoot;
    @FXML private ImageView backgroundImage;

    @FXML
    private void initialize() {
        backgroundImage.fitWidthProperty().bind(
                splashRoot.widthProperty()
        );
        backgroundImage.fitHeightProperty().bind(
                splashRoot.heightProperty()
        );

        splashRoot.widthProperty().addListener(
                (observable, oldValue, newValue) -> cropImage()
        );
        splashRoot.heightProperty().addListener(
                (observable, oldValue, newValue) -> cropImage()
        );
    }

    private void cropImage() {
        Image image = backgroundImage.getImage();
        double width = splashRoot.getWidth();
        double height = splashRoot.getHeight();

        if (image == null || width <= 0 || height <= 0) {
            return;
        }

        double imageWidth = image.getWidth();
        double imageHeight = image.getHeight();
        double windowRatio = width / height;
        double imageRatio = imageWidth / imageHeight;

        if (windowRatio > imageRatio) {
            double cropHeight = imageWidth / windowRatio;
            double top = (imageHeight - cropHeight) / 2;

            backgroundImage.setViewport(
                    new Rectangle2D(
                            0, top, imageWidth, cropHeight
                    )
            );
        } else {
            double cropWidth = imageHeight * windowRatio;
            double left = (imageWidth - cropWidth) / 2;

            backgroundImage.setViewport(
                    new Rectangle2D(
                            left, 0, cropWidth, imageHeight
                    )
            );
        }
    }

    @FXML
    private void continueToLogin() {
        MarineLifeApplication.show("Login.fxml");
    }
}