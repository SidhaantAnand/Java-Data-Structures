import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Sandeep on 7/9/2017.
 */
public class GenericTreeMain {
    private Node root;
    int size = 0;

    private class Node {
        int data;
        ArrayList<Node> children;

        Node(int data) { // You only need data to make a new Node
            this.data = data;
            this.children = new ArrayList<>();
        }
    } // end of Node class

    public GenericTreeMain() {
        Scanner scrn = new Scanner(System.in);

        this.root = construct(scrn, null, 0);

    }

    private Node construct(Scanner scrn, Node parent, int i) {

        // condition for root
        if (parent == null) {
            System.out.println("Enter data for root");
        } else {
            System.out.println("Enter the data for " + i + "th child of " + parent.data);
        }
        int childData = scrn.nextInt();
        Node child = new Node(childData);
        this.size++;

        System.out.println("Enter the number of children for " + childData);
        int numGrandchildren = scrn.nextInt();

        for (int j = 1; j <= numGrandchildren; j++) {
            Node grandChild = this.construct(scrn, child, i);
            child.children.add(grandChild);
        }

        return child;
    }

    public void display() {
        this.display2(this.root);
    }

    private void display2(Node node) {
        System.out.print(node.data + " => ");

        for (Node child : node.children)
            System.out.print(child.data + ", ");
        System.out.println("END");
        for (Node child : node.children)
            this.display2(child);
    }

    public int sizeByRecursion() {
        int count = sizeByRecursion2(this.root);
        return count;
    }

    private int sizeByRecursion2(Node node) // node takes root and count is 0
    {
        int rv = 0;
        for (Node child : node.children)
            rv = rv + sizeByRecursion2(child);

        rv = rv + 1;
        return rv;


    }

    public boolean find(int findval) {
        boolean index = find2(this.root, findval, false);
        return index;
    }

    private boolean find2(Node node, int findval, boolean flag) {
        if (node.data == findval) {
            flag = true;
            return flag;
        }

        for (Node child : node.children) {
            if (child.data == findval) {
                flag = true;
                return flag;
            } else {
                find2(child, findval, flag);
            }
        }

        return flag;

    }

    public int max() {
        int maxx = max2(this.root, this.root.data);
        return maxx;
    }

    private int max2(Node node, int max) {
        if (node.data > max)
            max = node.data;

        for (Node child : node.children) {
            if (child.data > max)
                max = child.data;

            else
                max2(child, max);
        }

        return max;

    }

    public int height() {
        int arr[] = new int[this.root.children.size()];
        int height = height2(this.root, arr, 0);
        return height;
    }

    public int height2(Node node, int arr[], int k) {
        for (Node child : node.children) {
            int recResult = height2(child, arr, k);
            arr[k] = recResult;
            k++;
        }

        int h = arr[0];
        if (k == arr.length) {

            for (int i = 0; i < arr.length; i++) {
                if (h < arr[i])
                    h = arr[i];
            }
        }

        return h;
    }

    public void Mirror() {
        ArrayList<Integer> al = new ArrayList<>();
        Mirror2(this.root, al);
        System.out.println("came back");
    }

    private void Mirror2(Node node, ArrayList<Integer> al) {
        for (int i = 0; i < node.children.size(); i++) {
            al.add(node.children.get(i).data);
        }

        ArrayList<Integer> al2 = new ArrayList<>();

        for (int p = 0; p < al.size(); p++)
            al2.add(al.get(al.size() - 1 - p));

        for (int i = 0; i < node.children.size(); i++) {
            node.children.get(i).data = al2.get(i);
        }

        ArrayList<Integer> al3 = new ArrayList<>();

        for (int i = 0; i < node.children.size(); i++)
            Mirror2(node.children.get(i), al3);

        return;

    }

    public boolean isIsomorphic(GenericTreeMain other) {
        boolean check = isIsomorphic2(other, this.root, other.root, true);
        return check;
    }

    private boolean isIsomorphic2(GenericTreeMain other, Node node1, Node node2, boolean flag) {
        int count1 = 0;
        int count2 = 0;

        for (int i = 1; i <= node1.children.size(); i++)
            count1++;

        for (int i = 1; i <= node2.children.size(); i++)
            count2++;

        if (count1 != count2) {
            flag = false;
            return false;
        }

        int i = 0;
        int j = 0;

        while ((i < node1.children.size()) || (j < node2.children.size())) {
            isIsomorphic2(other, node1.children.get(i), node2.children.get(j), flag);
            i++;
            j++;
        }

        return flag;
    }

    public void printLevel(int level) {
        printLevel2(this.root, level, 1);
        return;
    }

    private void printLevel2(Node node, int level, int distance) {

        for (int i = 0; i < node.children.size(); i++) {
            {

                if (level == distance) {
                    for (int j = 0; j < node.children.size(); j++)
                        System.out.print(node.children.get(j).data + ", ");
                } else {
                    distance++;
                    printLevel2(node.children.get(i), level, distance);
                }

            }
        }
        return;
    }

    public void preTraversal() {
        System.out.println(this.root.data);
        preTraversal2(this.root);
    }

    private void preTraversal2(Node node) {


        for (int i = 0; i < node.children.size(); i++) {
            System.out.println(node.children.get(i).data);
            preTraversal2(node.children.get(i));
        }
        return;
    }

    public int maximumScore() {
        int recResult = maximumScore2(this.root);
        return recResult;
    }

    private int maximumScore2(Node node) {
        int maxRoot = 0;
        int score = 0;
        int scorechild = 0;
        for (int i = 0; i < node.children.size(); i++) {
            score = score + node.children.get(i).data;
        }


        //   System.out.println("score is " + score);

        for (int i = 0; i < node.children.size(); i++) {

            scorechild = maximumScore2(node.children.get(i));
            System.out.println("score is " + score + " scorechild is " + scorechild + " node.data is" + node.data);
            if (score > scorechild)
                maxRoot = node.data;
            System.out.println("maxRoot " + maxRoot);
        }

//        for (int i = 0; i < node.children.size(); i++)
//            maximumScore2(node.children.get(i));

        return maxRoot;
    }

//    private Node maxScoreNode(Node node)
//    {
//
//    }
//
//    private class MaxScorePair
//    {
//
//    }
//
//    private MaxScorePair maxScorePair(Node node)
//    {
//
//    }
//
//    public int maxScoreBtr
//    {
//
//    }


//        for(int j = 0; j< node.children.size(); j++)
//            {
//                for(int i = 0; i< node.children.get(i).children.size(); i++) {
//
//                    Node child = node.children.get(i).children.get(node.children.get(i).children.size() - j - 1);
//
//                    int tmp = child.data;
//                    child.data = node.children.get(i).children.get(i).data;
//                    node.children.get(i).children.get(i).data = tmp;
//                }
//
//                Mirror2(node.children.get(j));
//        }
//        return;

    private class PreOrder {

        boolean found;
        Node pred;
        Node succ;


    }

    /*
    Notes
     */

    public int PreOrderPredessor(int item) // Generic Tree main object has called this method
    {
        PreOrder po = new PreOrder();
        this.PreOrderPredessor2(this.root, item, po);
        return po.pred.data;
    }

    private void PreOrderPredessor2(Node node, int item, PreOrder po) // instead of the GenericTreeMain object, theobject of class PReORderhas called this mthof
    {
        if(po.found == false)
        {
            if(node.data == item)
                po.found = true;
            else
                po.pred = node;
        }
        for(int i = 0; i<node.children.size(); i++)
            PreOrderPredessor2(node.children.get(i), item, po);
    }

    public int PreOrderSuccessor(int item)
    {
        PreOrder po = new PreOrder();
        this.PreOrderSuccessor2(this.root, item, po);
        return po.succ.data;
    }

    private void PreOrderSuccessor2(Node node, int item, PreOrder po)
    {
        if(po.found == false)
        {
            if(node.data == item)
                po.found = true;
            else
                po.pred = node;
        }

        else
        {
            if(po.succ == null)
                po.succ = node;
        }
        for(int i = 0; i<node.children.size(); i++)
            PreOrderSuccessor2(node.children.get(i), item, po);
    }

    private class forNextLarger
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

    }




