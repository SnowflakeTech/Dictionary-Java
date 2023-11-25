package com.example.dictionary;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("GUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                stage.setMaximized(false);
            }
        });

        stage.setTitle("Project của nhóm ngôi sao lấp lánh trên dải ngân hà bao la rộng lớn trong không gian vũ trụ");
        stage.setScene(scene);
        stage.getIcons().add(appIcon());

        stage.show();
    }
    public Image appIcon() {
        return new Image(
                Objects.requireNonNull(
                        Application.class.getResourceAsStream("Google Translate.png")
                )
        );
    }
    public static void main(String[] args) {
        launch();
    }
}