import java.util.*;
import java.io.*;
public class DictionaryManagement {
     Dictionary dictionary = new Dictionary();

    public void insertFromCommandline() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Nhập số lượng từ vựng: ");
            int number = input.nextInt();
            for (int i = 0; i < number; i++) {
                System.out.print("Nhập từ tiếng Anh thứ " + (i+1) + ": ");
                String english = input.next();
                System.out.print("Nhập giải thích bằng tiếng Việt: " );
                String vietnamese = input.next();
                Word newWord = new Word(english, vietnamese);
                dictionary.words.add(newWord);
            }
            input.close();
        }
        catch(InputMismatchException e) {
            System.out.println("Số lượng từ vựng k hợp lệ!");
        }
        catch(Exception e) {
            System.out.println("Lỗi" + e.getMessage());
        }
    }

    public void editWord(String oldWordTarget, String newWordTarget, String newWordExplain) {
        for (Word word : dictionary.words) {
            if (word.word_target.equals(oldWordTarget)) {
                word.word_target = newWordTarget;
                word.word_explain = newWordExplain;
                break;
            }
        }
    }

    public void removeWord(String wordTarget) {
        dictionary.words.removeIf(word -> word.word_target.equals(wordTarget));
    }

    public String findMean(String englishWord) {
        for(Word word : dictionary.words) {
            if(word.getWord_target().equals(englishWord)) {
                return word.getWord_explain();
            }
        }
        return "Từ k có nghĩa";
    }
}
