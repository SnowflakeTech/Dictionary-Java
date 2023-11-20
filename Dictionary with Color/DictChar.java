package uet.oop.ourtreedictionary;

import java.util.ArrayList;

/**
 * DictChar class.
 *
 * 1. Each DictChar object is a branch of a *tree*
 *    It can be the root, or the parent of other child-branches.
 *
 * 2. Each branch contains a character and their meaning at that branch.
 *    For example: D: Chữ D
 *                 -> O: Làm
 *                    -> G: Con chó
 */

public class DictChar {

    //----------------------------------------------------------------------------------------------------------------------------------//

    private char character;                                             // The character of the branch
    private String meaning;                                             // The meaning of the word of that branch
    ArrayList<DictChar> afterChar = new ArrayList<>();          // An ArrayList of DictChar

    //----------------------------------------------------------------------------------------------------------------------------------//

    /**
     * Getters and Setters.
     */
    void setCharacter(char c) {
        character = c;
    }

    void setMeaning(String m) {
        meaning = m;
    }

    char getCharacter() {
        return character;
    }

    String getMeaning() {
        return meaning;
    }

    //----------------------------------------------------------------------------------------------------------------------------------//

    /**
     * Constructor.
     */
    DictChar(char inputChar, String inputMeaning) {
        setCharacter(inputChar);
        setMeaning(inputMeaning);
    }

    //----------------------------------------------------------------------------------------------------------------------------------//

    /**
     * Find a DictChar in this branch:
     * - Return -1 if not found.
     * - Return index if found.
     */
    int findChar(char ch) {
        if (afterChar.size() > 0) {
            for (int j = 0; j < afterChar.size(); j++) {
                if (afterChar.get(j).getCharacter() == ch) {
                    return j;
                }
            }
        }
        return -1;
    }

    //----------------------------------------------------------------------------------------------------------------------------------//

    /**
     * Recursive method to put a word and its meaning into the tree.
     */
    void addWord(String word, String meaning) {
        char ch = word.charAt(0);

        int loc = findChar(ch);

        if (loc == -1) {
            if (word.length() == 1) {
                this.afterChar.add(new DictChar(ch, meaning));
            } else {
                this.afterChar.add(new DictChar(ch, " "));
                this.afterChar.get(this.afterChar.size() - 1).addWord(word.substring(1), meaning);
            }
        } else {
            if (word.length() == 1) {
                this.afterChar.get(loc).setMeaning(meaning);
            } else {
                this.afterChar.get(loc).addWord(word.substring(1), meaning);
            }
        }
        DictionaryUtilities.ArrSort(this);
    }

    //----------------------------------------------------------------------------------------------------------------------------------//

    /**
     * Recursive method to delete a word and ít meaning from the tree.
     * Return "This word in not exist!" if not exist.
     */
    String deleteWord(String inputWord) {
        char ch = inputWord.charAt(0);

        for (int i = 0; i < afterChar.size(); i++) {
            if (afterChar.get(i).getCharacter() == ch) {
                if (inputWord.length() == 1) {
                    if (!afterChar.get(i).meaning.equals(" ")) {
                        afterChar.remove(i);
                        return "Found and deleted!";
                    }
                } else {
                    String res =  afterChar.get(i).deleteWord(inputWord.substring(1));
                    if (res.equals("Found and deleted!") && afterChar.get(i).afterChar.size() == 0) {
                        afterChar.remove(i);
                    }
                    return res;
                }
            }
        }
        return "This word does not exist!";
    }

    //----------------------------------------------------------------------------------------------------------------------------------//

    /**
     * Recursive method to search a word and return ít meaning.
     * return "we can't find it" if there's no word.
     */
    String searchWord(String inputWord) {
        String noFound = "We can't find it!";
        char ch = inputWord.charAt(0);

        for (DictChar dictChar : afterChar) {
            if (dictChar.getCharacter() == ch) {
                if (inputWord.length() == 1) {
                    if (!dictChar.getMeaning().equals(" ")) {
                        return dictChar.getMeaning();
                    }
                } else {
                    return dictChar.searchWord(inputWord.substring(1));
                }
            }
        }
        return noFound;
    }

    //----------------------------------------------------------------------------------------------------------------------------------//

    /**
     * Recursive method to search the branch of the last char of word
     */
    DictChar searchPart(String part) {
        char ch = part.charAt(0);

        for (DictChar dictChar : afterChar) {
            if (dictChar.getCharacter() == ch) {
                if (part.length() == 1) {
                    return dictChar;
                } else {
                    return dictChar.searchPart(part.substring(1));
                }
            }
        }
        return new DictChar(' ', "");
    }

    //----------------------------------------------------------------------------------------------------------------------------------//

    /**
     * Recursive method to print all the word include number and meaning in dictionary in order.
     * flag 0 to print to cmd.
     * flag 1 to print to visual app. (not yet)
     * return number of word in that branch.
     */
    int printAll(String wordForm, int index) {
        wordForm += getCharacter();
        if (!getMeaning().equals(" ")) {
            index += 1;
            DictionaryUtilities.formatStringAndPrint(index, wordForm, getMeaning());
        }
        if (afterChar.size() != 0) {
            for (DictChar dictChar : afterChar) {
                index = dictChar.printAll(wordForm, index);
            }
        }
        return index;
    }

    //----------------------------------------------------------------------------------------------------------------------------------//

    String printAllWord(String wordForm, String listWord) {
        wordForm += getCharacter();
        if (!getMeaning().equals(" ")) {
            listWord += wordForm + "\n";
        }
        if (afterChar.size() != 0) {
            for (DictChar dictChar : afterChar) {
                listWord = dictChar.printAllWord(wordForm, listWord);
            }
        }
        return listWord;
    }

    String printAllMean(String wordForm, String listWord) {
        wordForm += getCharacter();
        if (!getMeaning().equals(" ")) {
            listWord += getMeaning() + "\n";
        }
        if (afterChar.size() != 0) {
            for (DictChar dictChar : afterChar) {
                listWord = dictChar.printAllMean(wordForm, listWord);
            }
        }
        return listWord;
    }

    //----------------------------------------------------------------------------------------------------------------------------------//

    /**
     *  Modified printAll() to match the dict-to-file method.
     *  Goddamn I'm stoopid af, this is unoptimized but it works lol!
     */
    int dictToFileFiller(ArrayList<String> dictToFile, String wordForm, int index) {
        wordForm += getCharacter();

        if (!getMeaning().equals(" ")) {
            index += 1;
            dictToFile.add(DictionaryUtilities.formatStringAndReturn(index, wordForm, getMeaning()));
        }

        if (afterChar.size() != 0) {
            for (DictChar dictChar : afterChar) {
                index = dictChar.dictToFileFiller(dictToFile, wordForm, index);
            }
        }
        return index;
    }

    //----------------------------------------------------------------------------------------------------------------------------------//
}