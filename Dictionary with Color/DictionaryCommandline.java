package uet.oop.ourtreedictionary;

/**
 * DictionaryCommandline class.
 *
 * Main class for cmd user.
 */

public class DictionaryCommandline {

    //----------------------------------------------------------------------------------------------------------------------------------//

    DictionaryManagementCmd cliDictionary = new DictionaryManagementCmd();   // init a dictionary management to use.

    //----------------------------------------------------------------------------------------------------------------------------------//

    /**
     * input from cmd, txt file and print all.
     */
    void dictionaryBasic() {
        cliDictionary.insertFromCommandline();
        cliDictionary.insertFromFile();
        cliDictionary.showAllWords();
    }

    //----------------------------------------------------------------------------------------------------------------------------------//

    /**
     * input from file and search.
     */
    void dictionaryAdvanced() {
        cliDictionary.insertFromFile();
        cliDictionary.optionFromCommandLine();
    }

    //----------------------------------------------------------------------------------------------------------------------------------//

    public static void main(String[] args) {
        DictionaryCommandline test = new DictionaryCommandline();
        test.dictionaryAdvanced();
    }

    //----------------------------------------------------------------------------------------------------------------------------------//
}

