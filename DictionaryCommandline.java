package org.example;
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
        while (true) {
            System.out.println("Chọn chức năng:");
            System.out.println("1. Nhập dữ liệu từ bàn phím");
            System.out.println("2. Hiển thị tất cả các từ");
            System.out.println("3. Tìm kiếm từ tiếng Anh");
            System.out.println("4. Tìm kiếm từ tiếng Việt");
            System.out.println("0. Thoát chương trình");
            if (!input.hasNextInt()) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                input.nextLine(); // consume the invalid input
                continue;
            }
            int choice = input.nextInt();
            input.nextLine(); // consume the newline left-over
            switch (choice) {
                case 1:
                    dictionaryManagement.insertFromCommandline();
                    break;
                case 2:
                    showAllWords();
                    break;
                case 3:
                    if (!input.hasNextLine()) {
                        System.out.println("Không có từ tiếng Anh để tìm.");
                        break;
                    }
                    String find_Eword = input.nextLine();
                    dictionaryManagement.searchByEnglish(find_Eword);
                    break;
                case 4:
                    if (!input.hasNextLine()) {
                        System.out.println("Không có từ tiếng Việt để tìm.");
                        break;
                    }
                    String find_Vmean = input.nextLine();
                    dictionaryManagement.searchByVietnamese(find_Vmean);
                    break;
                case 0:
                    System.out.println("Exit");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}
