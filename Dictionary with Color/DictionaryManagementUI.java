package uet.oop.ourtreedictionary;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static uet.oop.ourtreedictionary.DictionaryUtilities.dictionaryExportToFileUtils;
import static uet.oop.ourtreedictionary.DictionaryUtilities.insertFromFileUtils;

public class DictionaryManagementUI {

    //-----------------------------------------------------------------------------------------------------------------------------//

    DictChar dictionaryEng = new DictChar(' ', " ");      // init the Eng -> Vie dictionary.
    DictChar dictionaryVie = new DictChar(' ', " ");      // init the Vie -> Eng dictionary.

    //-----------------------------------------------------------------------------------------------------------------------------//

    DictionaryManagementUI() {
        insertFromFile();
    }

    /**
     * return meaning of the word user want.
     */
    String dictionaryLookup(String word, DictChar dictionary) {
        return dictionary.searchWord(word);
    }

    //-----------------------------------------------------------------------------------------------------------------------------//

    /**
     * Print all the word with the beginning is part and meaning.
     */
    String dictionarySearcherIn(String part, DictChar dictionary) {
        DictChar fWord = dictionary.searchPart(part);
        if (fWord.getCharacter() == dictionary.getCharacter()) {
           return "No word found!";
        } else {
            return fWord.printAllWord(part.substring(0,part.length() - 1), "");
        }
    }

    /**
     * Print all meaning of words with the beginning is part.
     */
    String dictionarySearcherOut(String part, DictChar dictionary) {
        DictChar fWord = dictionary.searchPart(part);
        if (fWord.getCharacter() == dictionary.getCharacter()) {
            return "No word found!";
        } else {
            return fWord.printAllMean(part.substring(0,part.length() - 1), "");
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------//

    /**
     * Add a word and its meaning to dictionary.
     */
    void addWord(String wordEng, String wordVie) {
        dictionaryEng.addWord(wordEng, wordVie);
        dictionaryVie.addWord(wordVie, wordEng);
    }

    //-----------------------------------------------------------------------------------------------------------------------------//

    /**
     * Delete a word and its meaning from dictionary.
     */
    String deleteWord(String word, DictChar dictionary) {
        String mean = dictionary.searchWord(word);
        if (mean.equals("We can't find it!")) {
            return mean;
        } else {
            if (dictionary.equals(dictionaryEng)) {
                dictionaryEng.deleteWord(word);
                dictionaryVie.deleteWord(mean);
            } else {
                dictionaryEng.deleteWord(mean);
                dictionaryVie.deleteWord(word);
            }
            return "Done";
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------//

    /**
     * Replace the word but keep its meaning.
     */
    String dictionaryModWord(String word, String newWord, DictChar dictionary) {
        String mean = dictionary.searchWord(word);
        if (mean.equals("We can't find it!")) {
            return mean;
        } else {
            if (dictionary.equals(dictionaryEng)) {
                dictionaryEng.deleteWord(word);
                dictionaryVie.deleteWord(mean);
                dictionaryEng.addWord(newWord, mean);
                dictionaryVie.addWord(mean, newWord);
            } else {
                dictionaryVie.deleteWord(word);
                dictionaryEng.deleteWord(mean);
                dictionaryVie.addWord(newWord, mean);
                dictionaryEng.addWord(mean, newWord);
            }
            return "Done";
        }
    }

    //----------------------------------------------------------------------------------------------------------------------------------//

    /**
     * Replace the meaning of the given word.
     */
    String dictionaryModMeaning(String word, String newMean, DictChar dictionary) {
        String oldMean = dictionary.searchWord(word);
        if (oldMean.equals("We can't find it!")) {
            return oldMean;
        } else {
            if (dictionary.equals(dictionaryEng)) {
                dictionaryEng.deleteWord(word);
                dictionaryVie.deleteWord(oldMean);
                dictionaryEng.addWord(word, newMean);
                dictionaryVie.addWord(newMean, word);
            } else {
                dictionaryVie.deleteWord(word);
                dictionaryEng.deleteWord(oldMean);
                dictionaryVie.addWord(word, newMean);
                dictionaryEng.addWord(newMean, word);
            }
            return "Done";
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------//

    /**
     * Insert from file.
     */
    void insertFromFile() {
        insertFromFileUtils(dictionaryEng, dictionaryVie);
    }

    //-----------------------------------------------------------------------------------------------------------------------------//

    /**
     * Export to file (output<outputDate>).
     * Thanks to: https://stackoverflow.com/a/6745127
     */
    void dictionaryExportToFile() {
        dictionaryExportToFileUtils(dictionaryEng);
    }

    //-----------------------------------------------------------------------------------------------------------------------------//
}
