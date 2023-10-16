import java.util.*;
import java.io.*;

public class DictionaryCommandline {
    DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public void sortWords() {
        // Sắp xếp danh sách từ theo thứ tự alphabet
        Collections.sort(dictionaryManagement.dictionary.words, new Comparator<Word>() {
            public int compare(Word w1, Word w2) {
                return w1.word_target.compareTo(w2.word_target);
            }
        });
    }

    public void showAllWords() {
        sortWords();
        System.out.println("No | English | Vietnamese");
        for (int i = 0; i < dictionaryManagement.dictionary.words.size(); i++) {
            Word word = dictionaryManagement.dictionary.words.get(i);
            System.out.println((i + 1) + " | " + word.word_target + " | " + word.word_explain);
        }
    }

    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandline();
        dictionaryManagement.findMean("play");
        showAllWords();
    }
}
