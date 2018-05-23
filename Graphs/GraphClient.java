import java.util.ArrayList;

/**
 * Created by Sandeep on 7/29/2017.
 */
public class GraphClient {
    public static void main(String[] args) throws Exception {
        GraphMain obj = new GraphMain();


        obj.addVertex("A");
        obj.addVertex("B");
        obj.addVertex("C");
        obj.addVertex("D");
        obj.addVertex("E");
        obj.addVertex("F");
        obj.addVertex("G");

        obj.addEdge("A", "B");
        obj.addEdge("A", "C");
        obj.addEdge("B", "D");
        obj.addEdge("C", "D");
        obj.addEdge("D", "E");
        obj.addEdge("E", "F");
        obj.addEdge("E", "G");
        obj.addEdge("F", "G");

      //  obj.display();
       // obj.removeVertex("D");
    //    obj.display();

        // aman Phone number: 9953658169

//        System.out.println(obj.hasPathFromToIterativeBFT("A", "G"));

      //  System.out.println(obj.checkIfTheGraphIsConnected());

      //  obj.printBST();

//        ArrayList<ArrayList<String>> rc = obj.gcc();
//        System.out.println(rc);

       // System.out.println(obj.isBipartite());
        System.out.println(obj.isAcyclic());
    }
}
