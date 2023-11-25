package com.example.dictionary;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class Hangman extends Application {
    private Dictionary dictionary;
    private String chosenWord;
    private char[] guessedWord;
    private int attemptsLeft;

    public void setChosenWord(String chosenWord) {
        this.chosenWord = chosenWord;
    }

    public String getChosenWord() {
        return chosenWord;
    }


}
