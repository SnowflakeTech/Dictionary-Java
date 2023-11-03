package com.example.dictionary;
import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.InputMismatchException;

public class DictionaryManagement extends Dictionary {
    Dictionary dictionary = new Dictionary();
    Scanner input = new Scanner(System.in);
    public static final String Path_In = "D:\\PhamSon Java\\Dictionary\\src\\main\\java\\com\\example\\dictionary\\dictionaries.txt";
    public static final String Path_Out = "D:\\PhamSon Java\\Dictionary\\src\\main\\java\\com\\example\\dictionary\\dictionaries_out.txt";

    /**
     * Hàm kiểm tra ký tự hợp lệ.
     * @param input đầu vào
     * @return boolean
     */
    private boolean isValidInput(String input) {
        // Define a regular expression pattern to allow only alphanumeric characters and spaces
        String pattern = "^[a-zA-Z0-9\\s]*$";
        return input.matches(pattern);
    }

    /**
     * Chèn từ trong cmd.
     */
    public void insertFromCommandline() {
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

                if (!isValidInput(english)) {
                    System.out.println("Từ tiếng Anh không hợp lệ. Vui lòng nhập từ không chứa ký tự đặc biệt.");
                    i--; // Decrement the index to re-enter this word.
                    continue;
                }

                System.out.print("Nhập giải thích bằng tiếng Việt thứ " + (i + 1) + ": ");
                String vietnamese = input.nextLine();

                if (!isValidInput(vietnamese)) {
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

    public Word findWordByEnglish(String wordToFind) {
        for (Word word : dictionary.getAllWords()) {
            if (word.getWord_target().equalsIgnoreCase(wordToFind)) {
                return word;
            }
        }
        return null; // Trả về null nếu không tìm thấy từ
    }

    public void editWord(String wordToEdit) {
        Word word = findWordByEnglish(wordToEdit);

        if (word != null) {

            System.out.println("Tìm thấy từ: " + word.getWord_target());
            System.out.print("Nhập từ Tiếng Anh mới: ");
            String newWordTarget = input.nextLine();

            if (isValidInput(newWordTarget)) {
                System.out.print("Nhập nghĩa Tiếng Việt mới: ");
                String newExplanation = input.nextLine();

                if (isValidInput(newExplanation)) {
                    // Update the English word and Vietnamese explanation of the word
                    word.setWord_target(newWordTarget);
                    word.setWord_explain(newExplanation);

                    System.out.println("Từ đã được cập nhật.");
                } else {
                    System.out.println("Đầu vào không hợp lệ cho phần giải thích bằng tiếng Việt. Hãy tránh các ký tự đặc biệt.");
                }
            } else {
                System.out.println("\n" + "Đầu vào không hợp lệ cho từ tiếng Anh. Hãy tránh các ký tự đặc biệt.");
            }
        } else {
            System.out.println("Không tìm thấy từ trong từ điển.");
        }
    }

    /**
     * Hàm boolean khi tìm kiếm từ trong từ điển.
     * @param WordTarget từ cần tìm
     * @return
     */
    public boolean findAndDeleteWordByEnglish(String WordTarget) {
        int index = -1;

        for (int i = 0; i < dictionary.getAllWords().size(); i++) {
            if (dictionary.getAllWords().get(i).getWord_target().equalsIgnoreCase(WordTarget)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            dictionary.getAllWords().remove(index);
            return true; // Trả về true nếu từ được tìm thấy và xóa thành công
        } else {
            return false; // Trả về false nếu từ không tồn tại trong từ điển
        }
    }

    /**
     * Hàm xoá từ.
     */
    public void deleteWord() {
        System.out.print("Enter the English word you want to delete: ");
        String wordToDelete = input.nextLine();
        if (isValidInput(wordToDelete)) {
            System.out.println("Từ tiếng Anh không hợp lệ. Vui lòng nhập từ không chứa ký tự đặc biệt.");
        }
        boolean deleted = findAndDeleteWordByEnglish(wordToDelete);

        if (deleted) {
            System.out.println("Word has been deleted.");
        } else {
            System.out.println("Word not found in the dictionary.");
        }
    }

    public void dictionaryExportToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Path_Out))) {
            for (Word entry : dictionary.getAllWords()) {
                writer.write(entry.toString() + "\n");
            }
            System.out.println("Data exported to " + Path_Out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tìm kiếm từ trong từ điển
     * @param word từ tiếng anh cần tìm
     */
    public void dictionaryLookup(String word) {
        System.out.println("Nhập từ cần tìm kiếm: ");
        for (Word entry : dictionary.getAllWords()) {
            if (entry.getWord_target().equalsIgnoreCase(word)) {
                System.out.println("Meaning: " + entry.getWord_explain());
                return;
            }
        }
        System.out.println("Word not found in the dictionary.");
    }

    /**
     * Hàm tìm từ với tiền tố cho trước
     * @param prefix tiền tố
     * @author Phạm Tiến Sơn
     */
    public void dictionarySearcher (String prefix) {
        System.out.println("Nhp tiền tố của một từ Tếng Anh" + prefix + ":");
        for (Word word : dictionary.getAllWords()) {
            if (word.getWord_target().startsWith(prefix)) {
                System.out.println(word.getWord_target());
            }
        }
    }

    public void insertFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Path_In));
             BufferedWriter writer = new BufferedWriter(new FileWriter(Path_Out))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");

                if (parts.length == 2) {
                    Word word = new Word(parts[0], parts[1]);
                    dictionary.getAllWords().add(word);
                } else {
                    // Xử lý lỗi dòng không hợp lệ (có thể ghi vào tệp lỗi)
                    System.out.println("Dòng không hợp lệ: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}