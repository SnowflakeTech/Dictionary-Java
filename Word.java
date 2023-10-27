package com.example.dictionary;

public class Word {
    public String word_target;
    public String word_explain;

    /**
     * Constructor Word.
     * @param word_target English
     * @param word_explain Vietnamese
     */
    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
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

    /**
     * Sắp xếp.
     * @param other từ khác
     * @return mảng đã sắp xếp
     */
    public int compareTo(Word other) {
        return this.word_target.compareToIgnoreCase(other.word_target);
    }
}
