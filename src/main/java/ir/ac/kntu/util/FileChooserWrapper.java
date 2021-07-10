package ir.ac.kntu.util;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileChooserWrapper {

    private static FileChooserWrapper instance = new FileChooserWrapper();
    private FileChooser fileChooser;

    private FileChooserWrapper() {
        fileChooser = new FileChooser();
    }

    public static FileChooserWrapper getInstance() {
        return instance;
    }

    public String showOpenDialog(Stage stage) {
        File selectedFile = fileChooser.showOpenDialog(stage);
        return selectedFile.getPath();
    }

    public String showSaveDialog(Stage stage) {
        File selectedFile = fileChooser.showSaveDialog(stage);
        return selectedFile.getPath();
    }
}
