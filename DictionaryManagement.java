import java.util.Scanner;

public class DictionaryManagement {
     Dictionary dictionary = new Dictionary();

    public void insertFromCommandline() {
        Scanner input = new Scanner(System.in);
        System.out.println("Nhập số lượng từ vựng:");
        int number = input.nextInt();
        for (int i = 0; i < number; i++) {
            System.out.println("Nhập từ tiếng Anh:");
            String english = input.next();
            System.out.println("Nhập giải thích bằng tiếng Việt:");
            String vietnamese = input.next();
            Word newWord = new Word(english, vietnamese);
            dictionary.words.add(newWord);
        }
        input.close();
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
}
