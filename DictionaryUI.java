package com.example.dictionary;
import static com.example.dictionary.DictionaryUtilities.dictionaryExportToFileUtils;
import static com.example.dictionary.DictionaryUtilities.insertFromFileUtils;
public class DictionaryUI {

    Word toEnglish = new Word(' ', " ");
    Word toVietnamese = new Word(' ', " ");

    DictionaryUI() {
        insertFromFile();
    }

    public String dictionaryLookup(String word, Word words) {
        return words.searchWordInTrie(word);
    }

    public String getAllWordsUI(String part, Word words) {
        Word word = words.searchPart(part);
        if (word.getTarget() == words.getTarget()) {
            return "No word found!";
        } else {
            return word.getAllWords(part.substring(0,part.length() - 1), "");
        }
    }

    public String getAllMeansUI(String part, Word words) {
        Word word = words.searchPart(part);
        if (word.getTarget() == words.getTarget()) {
            return "No word found!";
        } else {
            return word.getAllMeans(part.substring(0,part.length() - 1), "");
        }
    }

    public void addWordUI(String eng, String vie) {
        toEnglish.addWordToTrie(vie, eng);
        toVietnamese.addWordToTrie(eng, vie);
    }

    public String removeWordUI(String word, Word words) {
        String word_Explain = words.searchWordInTrie(word);
        if (word_Explain.equals("We can't find it!")) {
            return word_Explain;
        } else {
            if (words.equals(toVietnamese)) {
                toVietnamese.removeWordInTrie(word);
                toEnglish.removeWordInTrie(word_Explain);
            } else {
                toVietnamese.removeWordInTrie(word_Explain);
                toEnglish.removeWordInTrie(word);
            }
            return "Done";
        }
    }

    String dictionaryModWord(String word, String newWord, Word words) {
        String vie = words.searchWordInTrie(word);
        if (vie.equals("We can't find it!")) {
            return vie;
        } else {
            if (words.equals(toVietnamese)) {
                toVietnamese.removeWordInTrie(word);
                toEnglish.removeWordInTrie(vie);
                toVietnamese.addWordToTrie(newWord, vie);
                toEnglish.addWordToTrie(vie, newWord);
            } else {
                toEnglish.removeWordInTrie(word);
                toVietnamese.removeWordInTrie(vie);
                toEnglish.addWordToTrie(newWord, vie);
                toVietnamese.addWordToTrie(vie, newWord);
            }
            return "Done";
        }
    }

    void insertFromFile() {
        insertFromFileUtils(toVietnamese, toEnglish);
    }

    void dictionaryExportToFile() {
        dictionaryExportToFileUtils(toVietnamese);
    }
}
