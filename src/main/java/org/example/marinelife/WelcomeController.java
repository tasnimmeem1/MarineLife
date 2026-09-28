package org.example.marinelife;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;

public class WelcomeController {
    @FXML private VBox sidebar;
    @FXML private TilePane cards;
    @FXML private Label sectionTitle;
    @FXML private VBox galleryView;
    @FXML private VBox detailView;
    @FXML private Label detailName;
    @FXML private Label detailCategory;
    @FXML private Label detailDescription;
    @FXML private StackPane detailImage;

    private final List<MarineItem> items = List.of(
            new MarineItem(
                    "Jellyfish", "Animals", "Jellyfish.jpg",
                    "A graceful ocean drifter",
                    "Jellyfish float through the ocean and use gentle " +
                            "pulses to move. Their trailing tentacles help them " +
                            "catch food."
            ),
            new MarineItem(
                    "Sea Turtle", "Animals", "Seaturtle.jpg",
                    "A long-distance swimmer",
                    "Sea turtles travel across oceans. They return to " +
                            "beaches to lay their eggs."
            ),
            new MarineItem(
                    "Dolphin", "Animals", "dolphin.jpeg",
                    "A social marine mammal",
                    "Dolphins live in groups, communicate with sounds, " +
                            "and surface regularly to breathe air."
            ),
            new MarineItem(
                    "Coral", "Animals", "coral.jpeg",
                    "A home for reef life",
                    "Corals are tiny animals living together in colonies. " +
                            "Coral reefs shelter many ocean species."
            ),
            new MarineItem(
                    "Seagrass", "Plants", "Seagrass.jpeg",
                    "An underwater flowering plant",
                    "Seagrass grows in shallow water. Its meadows shelter " +
                            "young animals and help coastal ecosystems."
            ),
            new MarineItem(
                    "Kelp", "Plants", "Kelp.jpeg",
                    "A towering seaweed",
                    "Kelp is a large brown alga. Kelp forests create " +
                            "underwater habitat for marine life."
            )
    );

    @FXML
    private void initialize() {
        showCategory("All Marine Life");
    }

    @FXML
    private void toggleMenu() {
        sidebar.setVisible(!sidebar.isVisible());
        sidebar.setManaged(sidebar.isVisible());
    }

    @FXML
    private void showAll() {
        showCategory("All Marine Life");
    }

    @FXML
    private void showAnimals() {
        showCategory("Animals");
    }

    @FXML
    private void showPlants() {
        showCategory("Plants");
    }

    @FXML
    private void backToGallery() {
        detailView.setVisible(false);
        detailView.setManaged(false);
        galleryView.setVisible(true);
        galleryView.setManaged(true);
    }

    @FXML
    private void logOut() {
        MarineLifeApplication.show("Login.fxml");
    }

    private void showCategory(String category) {
        backToGallery();
        sectionTitle.setText(category);
        cards.getChildren().clear();

        items.stream()
                .filter(item ->
                        category.equals("All Marine Life") ||
                                item.category().equals(category)
                )
                .map(this::makeCard)
                .forEach(cards.getChildren()::add);
    }

    private VBox makeCard(MarineItem item) {
        Label category = new Label(item.category().toUpperCase());
        category.getStyleClass().add("tag");

        Label name = new Label(item.name());
        name.getStyleClass().add("card-title");

        Label summary = new Label(item.summary());
        summary.getStyleClass().add("muted");

        Button more = new Button("Learn more  →");
        more.getStyleClass().add("secondary-button");
        more.setOnAction(event -> openDetail(item));

        VBox card = new VBox(
                9,
                makePicture(item, 245, 145),
                category,
                name,
                summary,
                more
        );

        card.getStyleClass().add("marine-card");
        card.setPrefWidth(270);
        return card;
    }

    private void openDetail(MarineItem item) {
        detailName.setText(item.name());
        detailCategory.setText(item.category().toUpperCase());
        detailDescription.setText(item.description());
        detailImage.getChildren().setAll(
                makePicture(item, 420, 280)
        );

        galleryView.setVisible(false);
        galleryView.setManaged(false);
        detailView.setVisible(true);
        detailView.setManaged(true);
    }

    private StackPane makePicture(
            MarineItem item,
            double width,
            double height
    ) {
        StackPane holder = new StackPane();
        holder.getStyleClass().add("image-placeholder");
        holder.setPrefSize(width, height);
        holder.setMinHeight(height);

        URL imageFile = getClass().getResource(item.image());

        if (imageFile != null) {
            ImageView image = new ImageView(
                    new Image(imageFile.toExternalForm())
            );
            image.setFitWidth(width);
            image.setFitHeight(height);
            image.setPreserveRatio(true);
            holder.getChildren().add(image);
        } else {
            Label placeholder = new Label("◈\n" + item.name());
            placeholder.getStyleClass().add("placeholder-label");
            holder.getChildren().add(placeholder);
        }

        return holder;
    }
}