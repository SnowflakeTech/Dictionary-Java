package com.example.dictionary;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Game extends Application {
    private static List<String> words;
    private static String wordToGuess;
    private static char[] guessedWord;
    private static final int maxAttempts = 6;
    private static int attemptsLeft;
    private static StringBuilder guessedLetters;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        words = readEnglishWords("D:\\PhamSonJava\\Dictionary\\src\\main\\java\\com\\example\\dictionary\\dictionaries.txt");
        selectRandomWord();
        initializeGame();

        // Create JavaFX UI components
        Label wordLabel = new Label();
        Label attemptsLabel = new Label();
        Label guessedLettersLabel = new Label();
        TextField inputField = new TextField();

        // Create a layout
        VBox root = new VBox(10);
        root.getChildren().addAll(wordLabel, attemptsLabel, guessedLettersLabel, inputField);

        // Create a scene
        Scene scene = new Scene(root, 300, 200);

        // Set up the stage
        primaryStage.setTitle("Hangman Game");
        primaryStage.setScene(scene);

        // Set up event handling for input
        inputField.setOnAction(e -> {
            String input = inputField.getText().toLowerCase();
            inputField.clear();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                showAlert("Please enter a valid letter.");
                return;
            }

            char guessedLetter = input.charAt(0);

            guessedLetters.append(guessedLetter);

            if (wordToGuess.indexOf(guessedLetter) != -1) {
                updateGuessedWord(guessedLetter);
            } else {
                attemptsLeft--;
                showAlert("Incorrect guess.");
            }

            // Update UI
            updateUI(wordLabel, attemptsLabel, guessedLettersLabel);

            if (attemptsLeft <= 0 || isWordGuessed()) {
                displayGameResult(primaryStage);
            }
        });

        // Show the stage
        primaryStage.show();

        // Initial UI update
        updateUI(wordLabel, attemptsLabel, guessedLettersLabel);
    }

    private void updateUI(Label wordLabel, Label attemptsLabel, Label guessedLettersLabel) {
        // Update UI components with current game state
        wordLabel.setText("Word: " + new String(guessedWord));
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
        guessedLettersLabel.setText("Guessed letters: " + guessedLetters.toString());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hangman Game");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

        public static List<String> readEnglishWords(String filename) {
            List<String> englishWords = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // Split each line into Vietnamese and English words
                    String[] words = line.split("\t");

                    // Check if there are at least two words in the line
                    if (words.length >= 2) {
                        // Add the English word to the list
                        englishWords.add(words[0].trim());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return englishWords;
        }

    // Các phần còn lại giữ nguyên từ mã trò chơi trước đó
    private static void selectRandomWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        wordToGuess = words.get(randomIndex);
    }

    private static void initializeGame() {
        guessedWord = new char[wordToGuess.length()];
        for (int i = 0; i < wordToGuess.length(); i++) {
            guessedWord[i] = '_';
        }

        attemptsLeft = maxAttempts;
        guessedLetters = new StringBuilder();
    }

    private static void displayGameState() {
        System.out.println("\nWord: " + new String(guessedWord));
        System.out.println("Attempts left: " + attemptsLeft);
        System.out.println("Guessed letters: " + guessedLetters.toString());
    }

    private static void promptForGuess() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a letter: ");
        String input = scanner.nextLine().toLowerCase();

        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            System.out.println("Please enter a valid letter.");
            return;
        }

        char guessedLetter = input.charAt(0);

        guessedLetters = new StringBuilder().append(guessedLetter);

        if (wordToGuess.indexOf(guessedLetter) != -1) {
            updateGuessedWord(guessedLetter);
        } else {
            attemptsLeft--;
            System.out.println("Incorrect guess.");

            // Kiểm tra lượt đoán sau khi giảm đi 1
            if (attemptsLeft <= 0) {
                // Thoát ứng dụng khi kết thúc game
                System.exit(0);
            }
        }
    }

    private static void updateGuessedWord(char guessedLetter) {
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guessedLetter) {
                guessedWord[i] = guessedLetter;
            }
        }
    }

    private static boolean isWordGuessed() {
        return new String(guessedWord).equals(wordToGuess);
    }

    private static void displayGameResult(Stage primaryStage) {
        String message;
        if (isWordGuessed()) {
            message = "Congratulations! You guessed the word: " + wordToGuess;
        } else {
            message = "Sorry, you ran out of attempts. The word was: " + wordToGuess;
        }

        // Sử dụng JavaFX Alert để hiển thị thông báo
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Result");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

        // Thoát ứng dụng khi kết thúc game
        primaryStage.close();
    }
}
