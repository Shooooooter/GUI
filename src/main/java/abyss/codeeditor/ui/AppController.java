package abyss.codeeditor.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppController {

    @FXML
    private TextArea codeTextArea;

    @FXML
    private Label statusBar;

    @FXML
    private ListView<String> directoryListView;

    @FXML
    private StackPane directoryPane;

    @FXML
    private TextArea terminalTextArea;

    @FXML
    private TextField terminalInputField;

    @FXML
    private StackPane terminalPane;

    @FXML
    private void chooseDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(directoryPane.getScene().getWindow());

        directoryListView.getItems().clear();
        if (selectedDirectory != null) {
            File[] directories = selectedDirectory.listFiles(File::isDirectory);
            if (directories != null) {
                for (File directory : directories) {
                    directoryListView.getItems().add(directory.getName());
                }
                statusBar.setText("Directories loaded from: " + selectedDirectory.getAbsolutePath());
            } else {
                statusBar.setText("No subdirectories found in the selected directory.");
            }
        } else {
            statusBar.setText("No directory selected.");
        }
    }

    @FXML
    private void executeCommand() {
        String command = terminalInputField.getText();
        executeCommand(command, terminalTextArea);
    }

    private void executeCommand(String command, TextArea terminalTextArea) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                terminalTextArea.appendText(line + "\n");
            }

            int exitCode = process.waitFor();
            terminalTextArea.appendText("Exit Code: " + exitCode + "\n");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            terminalTextArea.appendText("Error executing the command.\n");
        }
    }
}