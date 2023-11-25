package com.example.dictionary;

import java.util.ArrayList;

public class Word extends Dictionary{
    private String word_target;
    private String word_explain;
    private char target;

    public DictionaryUtilities dictionaryUtilities;
    public char getTarget() {
        return target;
    }

    public void setTarget(char target) {
        this.target = target;
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public Word(char target, String word_explain) {
        this.word_explain = word_explain;
        this.target = target;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    @Override
    public String toString() {
        return word_target + "\t" + word_explain;
    }

    public int compareTo(Word other) {
        return this.word_target.compareToIgnoreCase(other.word_target);
    }

    public int findPosition(char c) {
        if (dictionary.size() > 0) {
            for (int i = 0; i < dictionary.size(); i++) {
                if (dictionary.get(i).getTarget() == c) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void addWordToTrie(String word_Target, String word_Explain) {
        char c = word_Target.charAt(0);
        int index = findPosition(c);

        if (index == -1) {
            if (word_Target.length() == 1) {
                this.dictionary.add(new Word(c, word_Explain));
            } else {
                this.dictionary.add(new Word(c, " "));
                this.dictionary.get(this.dictionary.size() - 1).addWordToTrie(word_Target.substring(1), word_Explain);
            }
        } else {
            if (word_Target.length() == 1) {
                this.dictionary.get(index).setWord_explain(word_explain);
            } else {
                this.dictionary.get(index).addWordToTrie(word_target.substring(1), word_explain);
            }
        }
        dictionaryUtilities.sortInTrie(this);
    }

    public String removeWordInTrie(String word) {
        char c = word.charAt(0);

        for (int i = 0; i < dictionary.size(); i++) {
            if (dictionary.get(i).getTarget() == c) {
                if (word.length() == 1) {
                    if (!dictionary.get(i).getWord_explain().equals(" ")) {
                        dictionary.remove(i);
                        return "Find and Remove Complete !";
                    }
                } else {
                    String result = dictionary.get(i).removeWordInTrie(word.substring(1));
                    if (result.equals("Find and Remove Complete !") && dictionary.get(i).dictionary.size() == 0) {
                        dictionary.remove(i);
                    }
                    return result;
                }
            }
        }
        return "The word does not exist !";
    }

    public String searchWordInTrie(String word) {
        char c = word.charAt(0);
        for (Word w : dictionary) {
            if (word.length() == 1) {
                if(!w.getWord_explain().equals(" ")) {
                    return w.getWord_explain();
                }
            } else {
                return w.searchWordInTrie(word.substring(1));
            }
        }
        return "The word does not exist !";
    }
    public Word searchPart(String part) {
        char c = part.charAt(0);

        for (Word w : dictionary) {
            if (w.getTarget() == c) {
                if (part.length() == 1) {
                    return w;
                } else {
                    return w.searchPart(part.substring(1));
                }
            }
        }
        return new Word(' ', "");
    }

    public int numberOfBranches(String word, int index) {
        word += getTarget();
        if (!getWord_explain().equals(" ")) {
            index ++;
            dictionaryUtilities.formatStringAndPrint(index, word, getWord_explain());
        }
        if (dictionary.size() != 0) {
            for (Word w : dictionary) {
                index = w.numberOfBranches(word, index);
            }
        }
        return index;
    }

    String getAllWords(String word, String list) {
        word += getTarget();
        if (!getWord_explain().equals(" ")) {
            list += word + "\n";
        }
        if (dictionary.size() != 0) {
            for (Word w : dictionary) {
                list = w.getAllWords(word, list);
            }
        }
        return list;
    }

    public String getAllMeans(String word, String list) {
        word += getTarget();
        if (!getWord_explain().equals(" ")) {
            list += getWord_explain() + "\n";
        }
        if (dictionary.size() != 0) {
            for (Word w : dictionary) {
                list = w.getAllMeans(word, list);
            }
        }
        return list;
    }

    public int numbersOfDictionaryToFile(ArrayList<String> Words, String word, int index) {
        word += getTarget();

        if (!getWord_explain().equals(" ")) {
            index += 1;
            Words.add(dictionaryUtilities.formatStringAndReturn(index, word, getWord_explain()));
        }

        if (dictionary.size() != 0) {
            for (Word w : dictionary) {
                index = w.numbersOfDictionaryToFile(Words, word, index);
            }
        }
        return index;
    }


}
