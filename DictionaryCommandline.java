package com.example.dictionary;
import java.util.*;

public class DictionaryCommandline extends DictionaryManagement {
    Scanner input = new Scanner(System.in);

    public void sortWords() {
        // Sắp xếp danh sách từ theo thứ tự alphabet
        Collections.sort(dictionary, new Comparator<Word>() {
            public int compare(Word w1, Word w2) {
                return w1.getWord_target().compareTo(w2.getWord_target());
            }
        });
    }

    public void showAllWords() {
        sortWords();
        System.out.println("No | English | Vietnamese");
        for (int i = 0; i < dictionary.size(); i++) {
            Word word = dictionary.get(i);
            System.out.println((i + 1) + " | " + word.getWord_target() + " | " + word.getWord_explain());
        }
    }

    /**
     * Làm menu cho từ điển
     */
    public void showMenu() {
        System.out.println("Menu:");
        System.out.println("0 - Exit");
        System.out.println("1 - Add a word");
        System.out.println("2 - Delete a word");
        System.out.println("3 - Edit a word");
        System.out.println("4 - Show all words");
        System.out.println("5 - Look up a word");
        System.out.println("6 - Search for words");
        System.out.println("7 - Access the Game section");
        System.out.println("8 - Import vocabulary from a file");
        System.out.println("9 - Export vocabulary to a file");
    }

    public void dictionaryBasic() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        do {
            showMenu();
            System.out.print("Enter your choice: ");

            try {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    switch (choice) {
                        case 0:
                            System.out.println("Exiting the application.");
                            break;
                        case 1:
                            insertFromCommandline();
                            break;
                        case 2:
                            // Implement delete a word
                            deleteWord();
                            break;
                        case 3:
                            // Implement edit a word
                            System.out.print("Enter a word to edit: ");
                            String word = scanner.nextLine();
                            editWord(word);
                            break;
                        case 4:
                            showAllWords();
                            break;
                        case 5:
                            System.out.print("Enter the word to look up: ");
                            String wordToLookup = scanner.nextLine();
                            dictionaryLookup(wordToLookup);
                            break;
                        case 6:
                            System.out.print("Enter a prefix to search for words: ");
                            String prefix = scanner.nextLine();
                            dictionarySearcher(prefix);
                            break;
                        case 7:

                            break;
                        case 8:
                            insertFromFile();
                            break;
                        case 9:
                            dictionaryExportToFile();
                            break;
                        default:
                            System.out.println("Action not supported.");
                            break;
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Consume the invalid input
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while (choice != 0);
    }
}