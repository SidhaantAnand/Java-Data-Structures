/**
 * Created by Sandeep on 7/12/2017.
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sandeep on 7/10/2017.
 */

// 10 true 20 true 40 false false true 50 false false true 30 true 60 false false true 70 false false

public class BSTMain {

    // What is BST?
    // BST is a binary tree with left node and right node of each parent being smaller and larger respectively than the parent itself

   // data members must be made private

    private Node root;
    /* Note: when we make the object of an inner class a global data member of the outer class, we will be able to access the data
              members of the inner class by using that object (root.left)*/
    private int size = 0;


    private class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) { // will be called whenever we have to make a new Node
            this.data = data;
            this.left = left;
            this.right = right;
        }
    } // end of Node class

    public BSTMain() {
    }

    public void add(int item) {

        /*

        1} if size = 0; make the root
        2) for entering data in BST we'll comepare with parent.left and parent.right and add wherever the parent.left or parent.right
           turns null.
        3)  Therefore, the order in which we add is important. Thus, BST sucks
         */
        if (this.size == 0) {
            this.root = new Node(item, null, null);
            this.size++;
            return; // note: unnecessary
        } else
            this.add2(item, this.root);
    }

    private void add2(int item, Node node) {

        if (node == null)
            return;
        if (node.data > item) {
            Node newnode = new Node(item, null, null);
            this.size++;
            if (node.left == null) {
                node.left = newnode;
            } else
                add2(item, node.left);
        }

        if (node.data < item) {
            Node newnode = new Node(item, null, null);
            this.size++;
            if (node.right == null)
                node.right = newnode;

            else
                add2(item, node.left);
        } else {
            // ignore
        }
    }

    public void display() {
        display2(this.root);
    }

    private void display2(Node node) {
        if (node == null)
            return;

        if (node.left == null)
            System.out.print("END => ");
        else
            System.out.print(node.left.data + " => ");

        System.out.print(node.data);

        if (node.right == null)
            System.out.print(" <= END");
        else
            System.out.print(" <= " + node.right.data);

        System.out.println();

        this.display2(node.left);
        this.display2(node.right);
    }

    public int sizeByRecursion() {
        int recResult = sizeByRecursion2(this.root);
        return recResult;
    }

    private int sizeByRecursion2(Node node) {
        int leftcount = 0;
        int rightcount = 0;
        int rv = 0;
        if (node == null)
            return rv;

        if (node.left != null)
            leftcount = sizeByRecursion2(node.left);

        if (node.right != null)
            rightcount = sizeByRecursion2(node.right);

        rv = leftcount + rightcount + 1;
        return rv;
    }


    public int height() {
        int result = height2(this.root);
        return result;
    }

    private int height2(Node node) {
        if (node == null) {
            return -1;
        }

        int maxheightleft = 0;
        int maxheightright = 0;
        int rv = 0;


        maxheightleft = height2(node.left);

        maxheightright = height2(node.right);

        rv = (int) Math.max(maxheightleft, maxheightright) + 1;

        return rv;

    }


    public void mirror() {
        mirror2(this.root);
    }

    public void mirror2(Node node) {
        if (node == null)
            return;

        if (node.left != null && node.right != null) {
            int tmp = node.left.data;
            node.right.data = node.left.data;
            node.left.data = tmp;
        } else if (node.left == null) {
            node.left.data = node.right.data;
            node.right.data = 0;
        } else {
            node.right.data = node.left.data;
            node.left.data = 0;
        }

        mirror2(node.left);
        mirror2(node.right);
    }

    public int maxDistanceBetweenAnyTwoRoots() {
        int result = maxDistanceBetweenAnyTwoRoots2(this.root);
        return result;
    }

    private int maxDistanceBetweenAnyTwoRoots2(Node node) {
        int leftdiameter = 0;
        int rightdiameter = 0;
        int rv = 0;
        if (node == null) {
            return 0;
        }
        leftdiameter = height2(node.left) + 1;

        rightdiameter = height2(node.right) + 1;

        rv = leftdiameter + rightdiameter;
        return rv;
    }

    private void preOrder() {
        preOrder2(this.root);
    }

    public void preOrder2(Node node) {
        System.out.print(node.data);
        System.out.println(node.left);
    }

    public int max() {
        int result = max2(this.root);
        return result;
    }

    private int max2(Node node) {
        if (node.right == null)
            return node.data;

        return (max2(node.right));
    }

    public int min() {
        int result = min2(this.root);
        return result;
    }

    private int min2(Node node) {
        if (node.left == null)
            return node.data;

        return (min2(node.left));
    }

    public boolean find(int item) {
        boolean result = find2(item, this.root);
        return result;
    }

    private boolean find2(int item, Node node) {
        if (node == null)
            return false;
        if (node.data == item)
            return true;

        else if (node.data > item)
            return (find2(item, node.left));

        else
            return (find2(item, node.right));
    }

    public void printInRange(int l1, int l2) {
        printInRange2(l1, l2, this.root);
    }

    private void printInRange2(int l1, int l2, Node node) {
        if (node == null)
            return;
        if (node.data > l1 && node.data < l2)
            System.out.println(node.data);

        printInRange2(l1, l2, node.left);
        printInRange2(l1, l2, node.right);
    }

    public BSTMain sortedArrIsGivenMakeBST(int arr[])
    {
        BSTMain obj = sortedArrIsGivenMakeBST2(arr,this.root, new BSTMain());
        return obj;
    }

    private BSTMain sortedArrIsGivenMakeBST2(int arr[], Node node, BSTMain obj) {

        if(node == null)
            return obj;
        int searchIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == node.data) {
                searchIndex = i;
                break;
            }
        }
        obj.add(arr[searchIndex]);
        sortedArrIsGivenMakeBST2(arr,node.left,obj);
        sortedArrIsGivenMakeBST2(arr,node.right, obj);
        return obj;





    }





    public void remove(int item) throws Exception {
        if (this.size == 0)
            throw new Exception("No  elements found");

        remove2(item, this.root, null, false);
    }

    private void remove2(int item, Node node, Node parent, boolean isLeftChild) {
        if(node == null){
            return;
        }

        if (item > node.data)
            remove2(item, node.right, node, false);

        else if (item < node.data)
            remove2(item, node.left, node, true);

        else {
            // node stores the node we have to delete

            if (node.right == null && node.left == null) {

                if(isLeftChild == true)
                parent.left = null;
                else
                parent.right = null;
                this.size--;
            } else if (node.right == null && node.left != null) {
                if (isLeftChild == true)
                    parent.left = node.left;
                else
                    parent.right = node.left;

                this.size--;
            } else if (node.left == null && node.right != null) {
                if (isLeftChild == true)
                    parent.left = node.right;
                else
                    parent.right = node.right;

                this.size--;
            } else {
                // both node.left and node.right aint zero

                int leftmax = max2(node.left);
                node.data = leftmax;
                remove2(leftmax, node.left, node, true);


            }
        }

    }

    private class SumHeap
    {
        int sum;
    }

    public void replaceEveryNodeWithSumOfAllGreaterNodes()
    {
        SumHeap obj = new SumHeap();
        replaceEveryNodeWithSumOfAllGreaterNodes2(this.root, obj);
    }

    private void replaceEveryNodeWithSumOfAllGreaterNodes2(Node node, SumHeap obj)
    {
        if(node == null)
            return;

        replaceEveryNodeWithSumOfAllGreaterNodes2(node.right, obj);

        int tmp = node.data;
        node.data = obj.sum;
        obj.sum = obj.sum + tmp;

       // replaceEveryNodeWithSumOfAllGreaterNodes2(node.left,obj);
    }



}