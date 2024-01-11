package abyss.codeeditor.ui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class StartThemeController implements Initializable {

    @FXML
    private StackPane stackPane; // Updated to StackPane

    @FXML
    private ImageView image1, image2, image3, image4, image5;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        animateImages();
    }

    private void animateImages() {
        Timeline timeline = new Timeline();

        // Animate image1
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(image1.opacityProperty(), 0)
                ),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(image1.opacityProperty(), 1)
                ),
                new KeyFrame(Duration.seconds(4),
                        new KeyValue(image1.opacityProperty(), 0)
                )
        );

        // Delay start of image2 animation by 2 seconds
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(image2.opacityProperty(), 0)
                ),
                new KeyFrame(Duration.seconds(4),
                        new KeyValue(image2.opacityProperty(), 1)
                ),
                new KeyFrame(Duration.seconds(6),
                        new KeyValue(image2.opacityProperty(), 0)
                )
        );

        // Continue adding animations for the rest of the images with increasing delay

        timeline.play();

        // Add listener for scene size changes
        Stage stage = (Stage) stackPane.getScene().getWindow();
        stackPane.widthProperty().addListener((observable, oldValue, newValue) -> updateImageSizes());
        stackPane.heightProperty().addListener((observable, oldValue, newValue) -> updateImageSizes());
    }

    // Method to update image sizes based on the stackPane size
    private void updateImageSizes() {
        double width = stackPane.getWidth();
        double height = stackPane.getHeight();

        image1.setFitWidth(width);
        image1.setFitHeight(height);

        // Update sizes for other images similarly
    }
}
