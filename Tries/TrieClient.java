/**
 * Created by Sandeep on 7/23/2017.
 */
public class TrieClient {
    public static void main(String[] args) {
        Trie obj = new Trie();

        obj.addWord("see");
        obj.addWord("saw");
        obj.addWord("sew");
        obj.addWord("same");
        obj.addWord("cat");
        obj.addWord("dog");
        obj.addWord("tree");
        obj.addWord("trek");
        obj.addWord("art");
        obj.addWord("arts");
        obj.addWord("as");

//        System.out.println(obj.searchWord("same"));

        obj.removeWord("art");
        obj.removeWord("as");
        obj.display();

    }
}
