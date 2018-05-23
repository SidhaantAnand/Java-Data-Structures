import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sandeep on 7/23/2017.
 */
public class Trie {

    /*
    Trie is a data structure which is best used to search for a word in say a e-book or a file.
    All data structures takes O(N)time to search even Hashmaps. This reduces it to

    So the root is empty
    its children contain alphabets

                                  root
                       a                      b                  z
                       r                      a                  e
                       t                      d                  b
                                                                 r
                                                                 a

         The letter t,d,a have terminalsmeaning that they are the last letter of a word.
         Now if we want to put the word arts in the Trie all we do is add 's' to the end of "a-r-t" and set 's' as a terminal
         Now both 't' and 's' are terminals meaning that both "art" and "arts" have been added to the file

         If we want to add a word "zen" then we will get a branch from 'e' (z-e) and add 'n' so it becomes "z-e-n" with n as the terminal
     */

    Node root;
    private int numWords;

    private class Node {
        boolean isTerminal;
        HashMap<Character, Node> children;
        Character data;

        Node(Character data, boolean isTerminal) {
            this.data = data;
            this.isTerminal = isTerminal;
            this.children = new HashMap<>();
        }
    }

    public Trie() {
        this.root = new Node('\0', false);
    }

    public void addWord(String word) {
        addWord(word, this.root);
    }

    private void addWord(String word, Node node) {
        if (word.length() == 0) {
            if (node.isTerminal == false) {
                node.isTerminal = true;
                this.numWords++;
                return;
            }
            return;
        }

        char cc = word.charAt(0);
        String ros = word.substring(1);
        Node child = node.children.get(cc);

        if (child == null) {
            child = new Node(cc, false);
            node.children.put(cc, child);
        }

        this.addWord(ros, child);
    }

    public void display() {
        display2(this.root, "");
    }

    private void display2(Node node, String ans) {
        if (node.isTerminal == true)
            System.out.println(ans);

        Set<Map.Entry<Character, Node>> entries = node.children.entrySet();

        for (Map.Entry<Character, Node> entry : entries) {
            this.display2(entry.getValue(), ans + entry.getKey());
        }
    }

    public boolean searchWord(String item) {
        boolean result = searchWord(item, this.root);
        return result;
    }

    private boolean searchWord(String word, Node node) {

        if (word.length() == 0)
        {
            if(node.isTerminal == true)
                return true;
            else
                return false;
        }


        char cc = word.charAt(0);
        String ros = word.substring(1);
        Node child = node.children.get(cc);

        if (child == null)
            return false;

        else
            return(this.searchWord(ros, child));


    }

    public void removeWord(String word)
    {
        removeWord(word, this.root);
    }

    private void removeWord(String word, Node node)
    {
        if(word.length() == 0) // When the word is found. Way to trhow the control after the recrusive call
        {
            if(node.isTerminal == true)
            {
                node.isTerminal = false; // This is important since while displaying we display till we encounter the terminal
                this.numWords--;
            }
            return; // control needs to go after thr call iirrestive if it was terminal or not
        }

        char cc = word.charAt(0);
        String ros = word.substring(1);
        Node child = node.children.get(cc);

        if (child == null)
            return; // The word is notfound. BEst is to throw an exception

        this.removeWord(ros,child);

        if(node.isTerminal == false && node.children.size() == 0)
            node.children.remove(cc);
    }
}
