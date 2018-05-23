package CodingBlocks;

/**
 * Created by Sandeep on 7/14/2017.
 */
public class HashTableClient {
    public static void main(String[] args) {
        HashTableMain obj = new HashTableMain(3);
        obj.put("india", 200);
        obj.put("china", 250);
        obj.put("morroco", 300);
        obj.put("thailand", 350);
        obj.put("spain", 40);
        obj.put("belgium", 50);
        obj.put("myanmar", 1);
        obj.put("vietman", 10);
        obj.display();

        System.out.println("--------------------------------");
       // System.out.println( obj.get("india"));

        obj.remove("india");
        obj.display();
    }
}
