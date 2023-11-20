package uet.oop.ourtreedictionary;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("dictGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Prevent fullscreen
        stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                stage.setMaximized(false);
                ThEbEsTfEaTuRe();
            }
        });

        stage.setTitle("Un-Dictionary!");
        stage.setScene(scene);
        stage.getIcons().add(appIcon());

        stage.show();
    }

    private void ThEbEsTfEaTuRe() {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ%22");
            try {
                desktop.browse(oURL);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Image appIcon() {
        return new Image(
                            Objects.requireNonNull(
                                Application.class.getResourceAsStream("deathnote_icon.png")
                         )
                    );
    }

    public static void main(String[] args) {
        launch();
    }
}