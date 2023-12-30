package abyss.codeeditor.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/abyss/codeeditor/ui/App.fxml"));
        Scene scene = new Scene(loader.load(), 800, 600);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/abyss/codeeditor/ui/App.css")).toExternalForm());

        primaryStage.setTitle("Simple Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}