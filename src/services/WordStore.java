package services;

public interface WordStore {
    /**
     * @param word
     */
    void store(String word);

    /**
     * Prints out all of the words stored in the field.
     */
    void print();

    /**
     * Prints out the first n words stored in the field.
     *
     * @param n
     */
    void print(int n);

}
