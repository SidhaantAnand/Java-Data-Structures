import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GenericTreeNextLarger {
    private Node root;
    int size = 0;
    private ArrayList<Integer> arr;

    //
    private class Node {
        int data;
        ArrayList<Node> children;

        Node(int data) { // You only need data to make a new Node
            this.data = data;
            this.children = new ArrayList<>();
        }
    } // end of Node class

    public GenericTreeNextLarger() {
        Scanner scrn = new Scanner(System.in);
        arr = new ArrayList<>();

        this.root = construct(scrn, null, 0);

    }

    private Node construct(Scanner scrn, Node parent, int i) {

        // condition for root
        if (parent == null) {
           // System.out.println("Enter data for root");
        } else {
           // System.out.println("Enter the data for " + i + "th child of " + parent.data);
        }
        int childData = scrn.nextInt();
        arr.add(childData);
        Node child = new Node(childData);
        this.size++;

       // System.out.println("Enter the number of children for " + childData);
        int numGrandchildren = scrn.nextInt();

        for (int j = 1; j <= numGrandchildren; j++) {
            Node grandChild = this.construct(scrn, child, i);
            child.children.add(grandChild);
        }

        return child;
    }

    private static class forNextLarger
    {
        int max;
        boolean maxSet;
    }

    public int nextLarger(int item)
    {
        forNextLarger obj = new forNextLarger();
        int result = nextLarger2(item, this.root, obj);
        return result;
    }

    private int nextLarger2(int item, Node node, forNextLarger obj)
    {
        int k = node.data;
        if(node.data > item) {
            if (obj.maxSet == false) {
                obj.max = node.data;
                obj.maxSet = true;
            }
        }

        if(obj.maxSet == true && node.data > item && node.data < obj.max)
            obj.max = node.data;

        for (int i = 0; i < node.children.size(); i++)
            nextLarger2(item, node.children.get(i), obj);

        return obj.max;
    }

    private int hey(int n)
    {
        int a[] = new int[arr.size()];

        for (int i = 0; i < arr.size(); i++)
            a[i] = arr.get(i);

        Arrays.sort(a);

        int index = 0;
        for (int i = 0; i <a.length ; i++) {
            if(a[i] == n)
                index = i;

        }

        if(index < a.length)
        return a[index+1];

        else
            return 0;



    }






    public static void main(String[] args) throws Exception {
        Scanner scrn = new Scanner(System.in);

        GenericTreeNextLarger o = new GenericTreeNextLarger();
        int N = scrn.nextInt();


        System.out.println(o.hey(N));

    }
}