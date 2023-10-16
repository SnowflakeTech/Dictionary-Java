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
        Scanner input = new Scanner(System.in);
        System.out.println("Chọn chức năng:");
        System.out.println("1. Nhập dữ liệu từ bàn phím");
        System.out.println("2. Hiển thị tất cả các từ");
        System.out.println("3. Tìm kiếm từ tiếng Anh");
        System.out.println("4. Tìm kiếm từ tiếng Việt");
        System.out.println("5. Thoát chương trình");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                dictionaryManagement.insertFromCommandline();
                break;

            case 2:
                showAllWords();
                break;

            case 3:
                System.out.print("Nhập từ tiếng Anh cần tìm: ");
                String find_Eword = input.next();
                dictionaryManagement.searchByEnglish(find_Eword);
                break;

            case 4:
                System.out.println("Nhập từ tiếng Việt cần tìm: ");
                String find_Vmean = input.next();
                dictionaryManagement.searchByVietnamese(find_Vmean);
                break;

            case 5:
                System.out.println("Exit");
                System.exit(0);
                break;

            default:
                dictionaryBasic();
                break;
        }
    }




}
