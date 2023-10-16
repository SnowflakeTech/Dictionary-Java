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

    public void searchByEnglish(String english) {
        for (Word word : dictionary.words) {
            if (word.word_target.equals(english)) {
                System.out.println("Từ tiếng Anh: " + word.word_target);
                System.out.println("Giải thích tiếng Việt: " + word.word_explain);
                return;
            }
        }
        System.out.println("Không tìm thấy từ tiếng Anh: " + english);
    }

    public void searchByVietnamese(String vietnamese) {
        for (Word word : dictionary.words) {
            if (word.word_explain.equals(vietnamese)) {
                System.out.println("Từ tiếng Anh: " + word.word_target);
                System.out.println("Giải thích tiếng Việt: " + word.word_explain);
                return;
            }
        }
        System.out.println("Không tìm thấy từ có nghĩa: " + vietnamese);
    }

}
