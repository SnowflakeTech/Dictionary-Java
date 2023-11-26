package com.example.dictionary;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class DictionaryUtilities extends Dictionary {
    DictionaryUtilities() {

    }
    public void sortInTrie(Word target) {
        if (target.dictionary.size() > 1) {
            for (int i = target.dictionary.size() - 1; i > 0; i--) {
                if (target.dictionary.get(i).getTarget() < target.dictionary.get(i - 1).getTarget()) {
                    Collections.swap(target.dictionary, i, i - 1);
                }
            }
        }
    }

    public void formatContent(int index, String firstWord, String secondWord) {
        String formattedIndex = String.format("%5s", index);
        String formattedFirstWord = String.format("|%-12s", firstWord);

        System.out.printf("%s%s|%s%n", formattedIndex, formattedFirstWord, secondWord);
    }

    public String formatContentAndReturn(int index, String firstWord, String secondWord) {
        String formattedIndex = String.format("%5s", index);
        String formattedFirstWord = String.format("|%-12s", firstWord);
        return formattedIndex + formattedFirstWord + "| " + secondWord;
    }

    static void insertFromFileUtils(Word dictionaryEng, Word dictionaryVie) {
        Scanner input;
        Scanner parser;
        String english;
        String vietnamese;
        String lines;

        try {
            FileReader filePath = new FileReader("D:\\PhamSon Java\\Dictionary\\src\\main\\java\\com\\example\\dictionary\\dictionaries.txt");
            input = new Scanner(filePath);

            while (input.hasNextLine()) {
                lines = input.nextLine();
                parser = new Scanner(lines);
                parser.useDelimiter("\t");

                english = parser.next();
                vietnamese = parser.next();

                //Input
                dictionaryEng.addWordToTrie(english, vietnamese);
                dictionaryVie.addWordToTrie(vietnamese, english);
            }
            System.out.println("Imported from file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Export to file (Utility Method).
     */
    static void dictionaryExportToFileUtils(Word english) {
        // Output date
        Date today = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String strDate= dateFormatter.format(today);
        String dictFileName = String.format("src/output%s.txt", strDate);

        try (BufferedWriter out = new BufferedWriter(new FileWriter(dictFileName))) {
            ArrayList<String> dictToFile = new ArrayList<>();
            english.numbersOfDictionaryToFile(dictToFile, "", 0);
            String inputLine;
            int index = 0;
            do {
                inputLine = dictToFile.get(index);
                out.write(inputLine);
                out.newLine();
                ++index;
            } while (index < dictToFile.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String standardize(String s) {
        if (s.length() == 1) {
            return s.toUpperCase();
        } else {
            return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
        }
    }
}