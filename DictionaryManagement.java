import java.util.Scanner;
import java.util.InputMismatchException;

public class DictionaryManagement {
    Dictionary dictionary = new Dictionary();

    public void insertFromCommandline() {
        Scanner input = new Scanner(System.in);
    
        try {
            int number = -1;
            boolean validInput = false;
    
            while (!validInput) {
                System.out.print("Nhập số lượng từ vựng: ");
    
                try {
                    number = input.nextInt();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Số lượng từ vựng không hợp lệ. Vui lòng nhập một số nguyên.");
                    input.nextLine(); // Consume the invalid input
                }
            }
    
            if (number <= 0) {
                System.out.println("Số lượng từ vựng không hợp lệ!");
                return;
            }
    
            input.nextLine(); // Consume the newline character
    
            for (int i = 0; i < number; i++) {
                System.out.print("Nhập từ tiếng Anh thứ " + (i + 1) + ": ");
                String english = input.nextLine();
    
                if (english.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
                    System.out.println("Từ tiếng Anh không hợp lệ. Vui lòng nhập từ không chứa ký tự đặc biệt.");
                    i--; // Decrement the index to re-enter this word.
                    continue;
                }
    
                System.out.print("Nhập giải thích bằng tiếng Việt thứ " + (i + 1) + ": ");
                String vietnamese = input.nextLine();
    
                if (vietnamese.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
                    System.out.println("Nghĩa tiếng Việt không hợp lệ. Vui lòng nhập nghĩa không chứa ký tự đặc biệt.");
                    i--; // Decrement the index to re-enter this word.
                    continue;
                }
    
                Word newWord = new Word(english, vietnamese);
                dictionary.getAllWords().add(newWord);
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public void editWord(String oldWordTarget, String newWordTarget, String newWordExplain) {
        for (Word word : dictionary.getAllWords()) {
            if (word.word_target.equals(oldWordTarget)) {
                word.word_target = newWordTarget;
                word.word_explain = newWordExplain;
                break;
            }
        }
    }

    public void removeWord(String wordTarget) {
        dictionary.getAllWords().removeIf(word -> word.word_target.equals(wordTarget));
    }

    public String findMean(String englishWord) {
        for(Word word : dictionary.getAllWords()) {
            if(word.getWord_target().equals(englishWord)) {
                return word.getWord_explain();
            }
        }
        return "Từ k có nghĩa";
    }


    public void dictionaryExportToFile(String file) {

    }

    public void dictionaryLookup(String word) {
        for (Word entry : dictionary.getAllWords()) {
            if (entry.getWord_target().equalsIgnoreCase(word)) {
                System.out.println("Meaning: " + entry.getWord_explain());
                return;
            }
        }
        System.out.println("Word not found in the dictionary.");
    }

    public void dictionarySearcher (String prefix) {

    }

    public void insertFromFile(String file) {

    }


}
