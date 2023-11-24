package com.example.dictionary;

import java.util.Collections;

public class DictionaryUtilities extends Dictionary {
    public void sortInTrie(Word target) {
        if (target.dictionary.size() > 1) {
            for (int i = target.dictionary.size() - 1; i > 0; i--) {
                if (target.dictionary.get(i).getTarget() < target.dictionary.get(i - 1).getTarget()) {
                    Collections.swap(target.dictionary, i, i - 1);
                }
            }
        }
    }

    public void formatStringAndPrint(int index, String firstWord, String secondWord) {
        String formattedIndex = String.format("%5s", index);
        String formattedFirstWord = String.format("|%-12s", firstWord);

        System.out.printf("%s%s|%s%n", formattedIndex, formattedFirstWord, secondWord);
    }

    public String formatStringAndReturn(int index, String firstWord, String secondWord) {
        String formattedIndex = String.format("%5s", index);
        String formattedFirstWord = String.format("|%-12s", firstWord);
        return formattedIndex + formattedFirstWord + "| " + secondWord;
    }


}
