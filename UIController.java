package com.example.dictionary;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Objects;

import static com.example.dictionary.TranslateAPI.*;

public class UIController {

    DictionaryUI dictionaryUI = new DictionaryUI();
    FreeTTS audio = new FreeTTS();

    @FXML
    private TextField engFieldWord = new TextField();

    @FXML
    private TextField vieFieldWord = new TextField();

    @FXML
    protected void searchWord() {
        String wordEn = "";
        String wordVi = "";
        if (!engFieldWord.getText().equals("")) {
            wordEn = DictionaryUtilities.standardize(engFieldWord.getText());
        }
        if (!vieFieldWord.getText().equals("")) {
            wordVi = DictionaryUtilities.standardize(vieFieldWord.getText());
        }
        if (!wordEn.equals("") && wordVi.equals("")) {
            vieFieldWord.setText(dictionaryUI.dictionaryLookup(wordEn, dictionaryUI.toVietnamese));
        } else if (wordEn.equals("") && !wordVi.equals("")) {
            engFieldWord.setText(dictionaryUI.dictionaryLookup(wordVi, dictionaryUI.toEnglish));
        } else {
            engFieldWord.setText("");
            vieFieldWord.setText("");
        }
    }

    @FXML
    protected void clearEngWord() {
        engFieldWord.setText("");
    }

    @FXML
    protected void clearVieWord() {
        vieFieldWord.setText("");
    }

    @FXML
    private TextField engFieldPart = new TextField();           // English input word field.

    @FXML
    private TextField vieFieldPart = new TextField();           // Vietnamese input word field.

    @FXML
    private TextArea engAreaPart = new TextArea();              // English output word field.

    @FXML
    private TextArea vieAreaPart = new TextArea();              // Vietnamese output word field.

    @FXML
    protected void searchPartEng() {
        String word = "";
        if (!engFieldPart.getText().equals("")) {
            word = DictionaryUtilities.standardize(engFieldPart.getText());
        }
        if (word.equals("")) {
            engAreaPart.setText("");
            vieAreaPart.setText("");
        } else {
            vieFieldPart.setText("");
            engAreaPart.setText(dictionaryUI.getAllWordsUI(word, dictionaryUI.toVietnamese));
            vieAreaPart.setText(dictionaryUI.getAllMeansUI(word, dictionaryUI.toVietnamese));
        }
    }

    @FXML
    protected void searchPartVie() {
        String word = "";
        if (!vieFieldPart.getText().equals("")) {
            word = DictionaryUtilities.standardize(vieFieldPart.getText());
        }
        if (word.equals("")) {
            engAreaPart.setText("");
            vieAreaPart.setText("");
        } else {
            engFieldPart.setText("");
            vieAreaPart.setText(dictionaryUI.getAllWordsUI(word, dictionaryUI.toEnglish));
            engAreaPart.setText(dictionaryUI.getAllMeansUI(word, dictionaryUI.toEnglish));
        }
    }

    @FXML
    private TextField addWordEng = new TextField();                 // English input word field.

    @FXML
    private TextField addWordVie = new TextField();                 // Vietnamese input word field.

    @FXML
    private Label isAddDone = new Label();

    @FXML
    protected void addWord() {
        String wordEng = "";
        String wordVie = "";
        if (!addWordEng.getText().equals("")) {
            wordEng = DictionaryUtilities.standardize(addWordEng.getText());
        }
        if (!addWordVie.getText().equals("")) {
            wordVie = DictionaryUtilities.standardize(addWordVie.getText());
        }

        boolean bothNotEmpty = !Objects.equals(wordEng, "") && !Objects.equals(wordVie, "");

        if (bothNotEmpty) {
            dictionaryUI.addWordUI(wordEng, wordVie);
            isAddDone.setText("Done");
        } else {
            isAddDone.setText("Input the word and its meaning");
        }
    }

    @FXML
    private TextField deleteWordEng = new TextField();              // English input word field.

    @FXML
    private TextField deleteWordVie = new TextField();              // Vietnamese input word field.

    @FXML
    private Label isDelDone = new Label();                          // Label return system respond action success or not

    @FXML
    protected void deleteWord() {
        String wEng = "";
        String wVie = "";
        if (!deleteWordEng.getText().equals("")) {
            wEng = DictionaryUtilities.standardize(deleteWordEng.getText());
        }
        if (!deleteWordVie.getText().equals("")) {
            wVie = DictionaryUtilities.standardize(deleteWordVie.getText());
        }
        if (!wEng.equals("") && wVie.equals("")) {
            isDelDone.setText(dictionaryUI.removeWordUI(wEng, dictionaryUI.toVietnamese));
        } else if (wEng.equals("") && !wVie.equals("")) {
            isDelDone.setText(dictionaryUI.removeWordUI(wVie, dictionaryUI.toEnglish));
        } else {
            isDelDone.setText("Delete an English or a Vietnamese word at one time");
        }
    }

    @FXML
    private ToggleButton togEng = new ToggleButton();

    @FXML
    private ToggleButton togVie = new ToggleButton();

    @FXML
    protected void toggleReady() {
        togEng.setSelected(true);
        togVie.setSelected(false);
    }

    @FXML
    protected void setTogEng() {
        togVie.setSelected(!togEng.isSelected());
    }

    @FXML
    protected void setTogVie() {
        togEng.setSelected(!togVie.isSelected());
    }


    @FXML
    private TextField modWordOld = new TextField();

    @FXML
    private TextField modWordNew = new TextField();

    @FXML
    private Label isModWordDone = new Label();

    @FXML
    private Label modifyWordLabel = new Label();

    @FXML
    private Button refressButton = new Button();

    @FXML
    protected void refresher() {
        if (togEng.isSelected()) {
            modWordOld.setPromptText("Input Old Word");
            modWordNew.setPromptText("Input New Word");
            modifyWordLabel.setText("Modify Word");
        } else {
            modWordOld.setPromptText("Input Old Meaning");
            modWordNew.setPromptText("Input New Meaning");
            modifyWordLabel.setText("Modify Meaning");
        }
    }

    @FXML
    protected void modifyWord() {
        String modWordOldStr = "";
        String modWordNewStr = "";
        if (!modWordOld.getText().equals("")) {
            modWordOldStr = DictionaryUtilities.standardize(modWordOld.getText());
        }
        if (!modWordNew.getText().equals("")) {
            modWordNewStr = DictionaryUtilities.standardize(modWordNew.getText());
        }

        boolean bothNotEmpty = (!Objects.equals(modWordOldStr, "") && !Objects.equals(modWordNewStr, ""));

        if (togEng.isSelected()) {
            modWordOld.setPromptText("Input Old Word");
            modWordNew.setPromptText("Input New Word");
            modifyWordLabel.setText("Modify Word");
        } else {
            modWordOld.setPromptText("Input Old Meaning");
            modWordNew.setPromptText("Input New Meaning");
            modifyWordLabel.setText("Modify Meaning");
        }


        if (bothNotEmpty) {
            if (togEng.isSelected()) {

                isModWordDone.setText(
                        dictionaryUI.editWordUI(
                                modWordOldStr, modWordNewStr, dictionaryUI.toVietnamese));
            } else {

                isModWordDone.setText(
                        dictionaryUI.editWordUI(
                                modWordOldStr, modWordNewStr, dictionaryUI.toEnglish));
            }
        } else {
            isModWordDone.setText("Input both old and new word");
        }
    }



    @FXML
    private Label isExported = new Label();

    @FXML
    protected void exportToFile() {
        dictionaryUI.newDictionaryExportToFile();
        isExported.setText("Done");
    }

    @FXML
    protected void importFromFile() {
        dictionaryUI.newInsertFromFile();
        isExported.setText("Done");
    }

    @FXML
    private Label transStatus = new Label();

    @FXML
    private Label addToDictTrans = new Label();

    @FXML
    private TextField inputFieldTrans = new TextField();

    @FXML
    private TextField outputFieldTrans = new TextField();

    @FXML
    protected void clearOutputField() {
        outputFieldTrans.setText("");
    }

    @FXML
    private ToggleButton toEngTrans = new ToggleButton();

    @FXML
    private ToggleButton toVietTrans = new ToggleButton();

    @FXML
    protected void toggleReadyTrans() {
        toEngTrans.setSelected(false);
        toVietTrans.setSelected(true);
    }

    @FXML
    protected void setTogEngTrans() {
        toVietTrans.setSelected(!toEngTrans.isSelected());
    }

    @FXML
    protected void setTogVieTrans() {
        toEngTrans.setSelected(!toVietTrans.isSelected());
    }

    String translateThisWord = "";
    String translatedWord = "";

    @FXML
    protected void useGoogleTransAPI() {
        if (!inputFieldTrans.getText().equals("")) {
            translateThisWord = DictionaryUtilities.standardize(inputFieldTrans.getText());
        }

        boolean inputNotEmpty = !Objects.equals(translateThisWord, "");

        if (inputNotEmpty) {
            if (toEngTrans.isSelected()) {
                translatedWord = callGoogleTrans(translateThisWord, EN);
            } else {
                translatedWord = callGoogleTrans(translateThisWord, VN);
            }
            outputFieldTrans.setText(translatedWord);
            transStatus.setText("The word has been translated");
        } else {
            transStatus.setText("Input the word you want to translate");
        }
    }

    @FXML
    protected void addWordTrans() {
        boolean bothNotEmpty = !Objects.equals(translateThisWord, "") && !Objects.equals(translatedWord, "");

        if (bothNotEmpty) {
            if (toEngTrans.isSelected()) {
                dictionaryUI.addWordUI(translatedWord, translateThisWord);
            } else {
                dictionaryUI.addWordUI(translateThisWord, translatedWord);
            }
            addToDictTrans.setText("Done");
        } else {
            addToDictTrans.setText("Input the word and translate it first");
        }
    }

    @FXML
    protected void playEngAudio() {
        String wordEn;
        wordEn = engFieldWord.getText();

        if (!wordEn.equals("")) {
            audio.play(wordEn);
        }
    }

    @FXML
    private void accessGame() {
        // Gọi lớp HangmanGame và chạy game
        Game hangmanGame = new Game();
        hangmanGame.start(new Stage());
    }
}