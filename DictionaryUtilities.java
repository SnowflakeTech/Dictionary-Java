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

    static void insertFromFileUtils(Word dictionaryEng, Word dictionaryVie) {
        Scanner sc;    // Using scanner to receive data from external file.
        Scanner parser;    // Using scanner to parse strings from external file.
        String wordEng;
        String wordVie;
        String readLine;

        try {
            // Open file
            FileReader dictionaryFile = new FileReader("D:\\PhamSon Java\\Dictionary\\src\\main\\java\\com\\example\\dictionary\\dictionaries.txt");

            // Read file
            sc = new Scanner(dictionaryFile);

            // Imply that the input file is P E R F E C T
            while (sc.hasNextLine()) {
                readLine = sc.nextLine();
                parser = new Scanner(readLine);
                parser.useDelimiter("\t");

                wordEng = parser.next();
                wordVie = parser.next();

                //Input
                dictionaryEng.addWordToTrie(wordEng, wordVie);
                dictionaryVie.addWordToTrie(wordVie, wordEng);
            }
            System.out.println("Imported from file!");
        } catch (IOException e) {
            System.out.println("<!> Make sure you have dictionaries.txt in the src folder <!>");
            // e.printStackTrace();  for debugging
        }
    }

    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Export to file (Utility Method).
     */
    static void dictionaryExportToFileUtils(Word dictionaryEng) {
        // Output date
        Date today = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String strDate= dateFormatter.format(today);
        String dictFileName = String.format("src/output%s.txt", strDate);

        try (BufferedWriter out = new BufferedWriter(new FileWriter(dictFileName))) {
            ArrayList<String> dictToFile = new ArrayList<>();
            dictionaryEng.numbersOfDictionaryToFile(dictToFile, "", 0);
            String inputLine;
            int index = 0;
            do {
                inputLine = dictToFile.get(index);
                out.write(inputLine);
                out.newLine();
                ++index;
            } while (index < dictToFile.size());
        } catch (IOException e) {
            System.out.println("<!> Error during reading/writing <!>");
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
