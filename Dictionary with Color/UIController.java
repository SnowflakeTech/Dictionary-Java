package uet.oop.ourtreedictionary;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Objects;

import static uet.oop.ourtreedictionary.GoogleTransAPIClient.*;

public class UIController {

    DictionaryManagementUI dictionary = new DictionaryManagementUI();
    DictAudio audio = new DictAudio();

    //-----------------------------------------------------------------------------------------------------------------------------//

    /**
     * Search word panel.
     */

    @FXML
    private TextField engFieldWord = new TextField();       // English word field.

    @FXML
    private TextField vieFieldWord = new TextField();       // Vietnamese word field.

    /**
     * Translate when button is pressed.
     */
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
            vieFieldWord.setText(dictionary.dictionaryLookup(wordEn, dictionary.dictionaryEng));
        } else if (wordEn.equals("") && !wordVi.equals("")) {
            engFieldWord.setText(dictionary.dictionaryLookup(wordVi, dictionary.dictionaryVie));
        } else {
            engFieldWord.setText("");
            vieFieldWord.setText("");
        }
    }

    /**
     * Clear other field when this field's text change.
     */
    @FXML
    protected void clearEngWord() {
        engFieldWord.setText("");
    }

    @FXML
    protected void clearVieWord() {
        vieFieldWord.setText("");
    }

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Search part panel.
     */

    @FXML
    private TextField engFieldPart = new TextField();           // English input word field.

    @FXML
    private TextField vieFieldPart = new TextField();           // Vietnamese input word field.

    @FXML
    private TextArea engAreaPart = new TextArea();              // English output word field.

    @FXML
    private TextArea vieAreaPart = new TextArea();              // Vietnamese output word field.

    /**
     * Search all English words that have the part and its meaning.
     */
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
            engAreaPart.setText(dictionary.dictionarySearcherIn(word, dictionary.dictionaryEng));
            vieAreaPart.setText(dictionary.dictionarySearcherOut(word, dictionary.dictionaryEng));
        }
    }

    /**
     * Search all Vietnamese words that have the part and its meaning.
     */
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
            vieAreaPart.setText(dictionary.dictionarySearcherIn(word, dictionary.dictionaryVie));
            engAreaPart.setText(dictionary.dictionarySearcherOut(word, dictionary.dictionaryVie));
        }
    }

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Add word.
     */

    @FXML
    private TextField addWordEng = new TextField();                 // English input word field.

    @FXML
    private TextField addWordVie = new TextField();                 // Vietnamese input word field.

    @FXML
    private Label isAddDone = new Label();                          // Label return system respond action success or not

    /**
     * Add a pair of English and Vietnamese word to dictionary.
     */
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
            dictionary.addWord(wordEng, wordVie);
            isAddDone.setText("Done");
        } else {
            isAddDone.setText("Input the word and its meaning");
        }
    }

                    //---------------------------------//

    /**
     * Delete word.
     */

    @FXML
    private TextField deleteWordEng = new TextField();              // English input word field.

    @FXML
    private TextField deleteWordVie = new TextField();              // Vietnamese input word field.

    @FXML
    private Label isDelDone = new Label();                          // Label return system respond action success or not

    /**
     * Delete a pair of English and Vietnamese word to dictionary.
     */
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
            isDelDone.setText(dictionary.deleteWord(wEng, dictionary.dictionaryEng));
        } else if (wEng.equals("") && !wVie.equals("")) {
            isDelDone.setText(dictionary.deleteWord(wVie, dictionary.dictionaryVie));
        } else {
            isDelDone.setText("Delete an English or a Vietnamese word at one time");
        }
    }

                    //---------------------------------//

    /**
     * Toggle stuff support below action.
     */

    @FXML
    private ToggleButton togEng = new ToggleButton();

    @FXML
    private ToggleButton togVie = new ToggleButton();

    /**
     * Make an English/Vietnamese switch.
     */
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

                    //---------------------------------//

    @FXML
    private TextField modWordOld = new TextField();                 // old word input field.

    @FXML
    private TextField modWordNew = new TextField();                 // new word input field.

    @FXML
    private Label isModWordDone = new Label();                      // Label return system respond action success or not

    @FXML
    private Label modifyWordLabel = new Label();

    @FXML
    private Button refressButton = new Button();

    /**
     * Do nothing (reset state-view).
     */
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

    /**
     * Modify the word but keep it meaning / modify its meaning but keep the word.
     */
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
                        dictionary.dictionaryModWord(
                                modWordOldStr, modWordNewStr, dictionary.dictionaryEng));
            } else {

                isModWordDone.setText(
                        dictionary.dictionaryModWord(
                                modWordOldStr, modWordNewStr, dictionary.dictionaryVie));
            }
        } else {
            isModWordDone.setText("Input both old and new word");
        }
    }

    //----------------------------------------------------------------------------------------------------------------//

    @FXML
    private Label isExported = new Label();                         // Label return system respond action success or not

    /**
     * Export all words to file
     */
    @FXML
    protected void exportToFile() {
        dictionary.dictionaryExportToFile();
        isExported.setText("Done");
    }

    /**
     * Re-import words.
     */
    @FXML
    protected void importFromFile() {
        dictionary.insertFromFile();
        isExported.setText("Done");
    }

    //----------------------------------------------------------------------------------------------------------------//

    @FXML
    private Label transStatus = new Label();

    @FXML
    private Label addToDictTrans = new Label();

    @FXML
    private TextField inputFieldTrans = new TextField();

    @FXML
    private TextField outputFieldTrans = new TextField();

    /**
     * Clear output field when the input change.
     */

    @FXML
    protected void clearOutputField() {
        outputFieldTrans.setText("");
    }

    /**
     * Toggle stuff support below action.
     */

    @FXML
    private ToggleButton toEngTrans = new ToggleButton();

    @FXML
    private ToggleButton toVietTrans = new ToggleButton();

    /**
     * Make an English/Vietnamese switch.
     */
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

    //---------------------------------//

    /**
     * Translate a word (using GoogleTransAPI).
     */
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

    /**
     * Add a word and its translation to the dictionary.
     */

    @FXML
    protected void addWordTrans() {
            boolean bothNotEmpty = !Objects.equals(translateThisWord, "") && !Objects.equals(translatedWord, "");

            if (bothNotEmpty) {
                if (toEngTrans.isSelected()) {
                    dictionary.addWord(translatedWord, translateThisWord);
                } else {
                    dictionary.addWord(translateThisWord, translatedWord);
                }
                addToDictTrans.setText("Done");
            } else {
                addToDictTrans.setText("Input the word and translate it first");
            }
    }

    //----------------------------------------------------------------------------------------------------------------//

    @FXML
    protected void playEngAudio() {
        String wordEn;
        wordEn = engFieldWord.getText();

        if (!wordEn.equals("")) {
            audio.play(wordEn);
        }
    }

    //----------------------------------------------------------------------------------------------------------------//
}