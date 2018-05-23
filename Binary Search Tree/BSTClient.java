/**
 * Created by Sandeep on 7/12/2017.
 */
public class BSTClient {
    public static void main(String[] args) throws Exception{
        BSTMain obj = new BSTMain();
        obj.add(50);
        obj.add(25);
        obj.add(75);
        obj.add(12);
        obj.add(37);
        obj.add(62);



        obj.display();

        System.out.println();
        System.out.println();

        obj.replaceEveryNodeWithSumOfAllGreaterNodes();
        obj.display();

//        int result = obj.max();
//        System.out.println(result);

      //  boolean result = obj.find(62);
      //  obj.printInRange(11,26);

//        int arr[] = {12,62,25,37,50,75};
//        BSTMain obj3 =   obj.sortedArrIsGivenMakeBST(arr);
//        obj3.display();

//        System.out.println("After removal");
//        System.out.println();
//        obj.remove(75);
//        obj.display();

    }
}
