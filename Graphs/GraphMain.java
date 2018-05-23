import java.util.*;

/**
 * Created by Sandeep on 7/29/2017.
 */
public class GraphMain {

    private HashMap<String, Vertex> vertices;

    private class Edge {
        Vertex one;
        Vertex two;

        public Edge(Vertex one, Vertex two) {
            this.one = one;
            this.two = two;
        }
    }

    private class Vertex {
        String name;
        HashMap<Vertex, Edge> neighbours;

        public Vertex(String name) {
            this.name = name;
            neighbours = new HashMap<>();
        }

        /* Its important to override hashCode since the predefined java one works for primitives and String
        * In our case, the key is neither a primitve or a String, it's an object of the class Vertex*/

        @Override
        public int hashCode() {
            return this.name.hashCode();
        }

        @Override
        public boolean equals(Object ob) {
            Vertex obj = (Vertex) ob;

            return this.name.equals(obj.name);
        }

        public boolean isAdjacent(Vertex ob) {
            return this.neighbours.containsKey(ob);
        }
    }

    public GraphMain() {
        this.vertices = new HashMap<>();
    }

    public void addVertex(String v) throws Exception {

        if (this.vertices.containsKey(v) == true)
            throw new Exception("The vertex already exists");

        else {
            Vertex toBeAdded = new Vertex(v);
            this.vertices.put(v, toBeAdded);
        }
    }

    public void removeVertex(String v) throws Exception {

        if (this.vertices.containsKey(v) == false)
            throw new Exception("The vertex does not exist");

        Vertex toBeRemoved = this.vertices.get(v);

        Set<Vertex> entries = toBeRemoved.neighbours.keySet();

        for (Vertex entry : entries) {
            entry.neighbours.remove(toBeRemoved);
        }

        this.vertices.remove(v);

//        Set<Map.Entry<Vertex,Edge>> entries = toBeRemoved.neighbours.entrySet();
//
//        for (Map.Entry<Vertex, Edge> entry : entries) {
//            this.r
//        }
    }

    public int numVertices() {
        return this.vertices.size();
    }

    public boolean containsEdge(String s1, String s2) throws Exception {
        Vertex v1 = this.vertices.get(s1);
        Vertex v2 = this.vertices.get(s2);

        if (v1 == null || v2 == null)
            throw new Exception("The vertex is not found for the Strings");

        return v1.isAdjacent(v2);
    }

    public void removeEdge(String s1, String s2) throws Exception {
        Vertex v1 = this.vertices.get(s1);
        Vertex v2 = this.vertices.get(s2);

        if (v1 == null || v2 == null)
            throw new Exception("The vertex is not found for the Strings");

        if (this.containsEdge(s1, s2) == false)
            throw new Exception("The vertices are not adjacent and do notform an edge");

        v1.neighbours.remove(v2);
        v2.neighbours.remove(v1);


    }

    public void addEdge(String s1, String s2) throws Exception {
        Vertex v1 = this.vertices.get(s1);
        Vertex v2 = this.vertices.get(s2);

        if (v1 == null || v2 == null)
            throw new Exception("The vertex is not found for the Strings");

        if (this.containsEdge(s1, s2) == true)
            throw new Exception("The vertices already form an edge");

        Edge edge = new Edge(v1, v2);
        v1.neighbours.put(v2, edge);
        v2.neighbours.put(v1, edge);
    }

    public void display() {
        Collection<Vertex> vertices = this.vertices.values();

        for (Vertex vtx : vertices) {
            System.out.print(vtx.name + "=> ");

            Set<Vertex> neighborsOfVtx = vtx.neighbours.keySet();

            for (Vertex neighs : neighborsOfVtx)
                System.out.print(neighs.name + " , ");

            System.out.println("END");
        }
    }

    private class ha {
        boolean found;

    }


    public boolean hasPathFromTo(String s1, String s2) {
        ha ob = new ha();
        return this.hasPathFromTo(s1, s2, new ArrayList<>(), ob);
    }

    // This is Depth first traversal - recursive. Iterative done later
    private boolean hasPathFromTo(String s1, String s2, ArrayList<String> hey, ha ob) {

        /* In the ArrayList hey we will store the already made calls. Thisis important since without this a Stack overflow Exception
        is faced. So with this once we have stored "AB" we will not again call for "BA" which would be called otherwise since we are
        calling all the neighbours and "B" has "A" has its neighbour just like "A" has "B" as its neighbour
         */

        /*Instead of an ArrayList you could have used a HashMap since adding and coatinsKey is all in O(1) as compared to a
         ArrayList which has O(N)
         */

        Vertex v1 = this.vertices.get(s1);

        if (v1 == null)
            return ob.found;

        if (v1.equals(this.vertices.get(s2)) == true) {
            ob.found = true;
            return ob.found;
        }

        Set<Vertex> neighborsOfV1 = v1.neighbours.keySet();

        for (Vertex neighs : neighborsOfV1) {
            if (neighs != null) {
                boolean flag = false;
                for (int i = 0; i < hey.size(); i++) {

                    if (neighs.name.concat(v1.name).equals(hey.get(i)))
                        flag = true;

                    if (v1.name.concat(neighs.name).equals(hey.get(i)))
                        flag = true;
                }

                if (flag == false) {
                    hey.add(v1.name.concat(neighs.name));
                    hasPathFromTo(neighs.name, s2, hey, ob);
                }
            }
        }

        return ob.found;
    }

    // This is Breadth first traversal
    public boolean hasPathFromToIterativeBFT(String s1, String s2) {
        Vertex v1 = this.vertices.get(s1);
        Vertex v2 = this.vertices.get(s2);

        Queue<Vertex> queue = new LinkedList<>();
        // Queue is a interface which is implemented ny LinkedList

        HashMap<Vertex, Boolean> visited = new HashMap<>();

        queue.offer(v1);
        visited.put(v1, true);

        while (!queue.isEmpty()) {
            Vertex tmp = queue.poll();

            if (tmp.isAdjacent(v2) == true)
                return true;

            Set<Vertex> neighborsOftmp = tmp.neighbours.keySet();

            for (Vertex neighs : neighborsOftmp) {

                if (visited.containsKey(neighs) == false) {

                    visited.put(neighs, true);
                    queue.offer(neighs);
                }
                //  System.out.println("Loop");

            }


        }

        return false;

    }

    // Replace Queue with Stack in BFTIterative

    public boolean hasPathFromToDFTIterative(String s1, String s2) {
        Vertex v1 = this.vertices.get(s1);
        Vertex v2 = this.vertices.get(s2);

        Stack<Vertex> stack = new Stack<>();
        // Queue is a interface which is implemented ny LinkedList

        HashMap<Vertex, Boolean> visited = new HashMap<>();

        stack.push(v1);
        visited.put(v1, true);

        while (!stack.isEmpty()) {
            Vertex tmp = stack.pop();

            if (tmp.isAdjacent(v2) == true)
                return true;

            Set<Vertex> neighborsOftmp = tmp.neighbours.keySet();

            for (Vertex neighs : neighborsOftmp) {

                if (visited.containsKey(neighs) == false) {

                    visited.put(neighs, true);
                    stack.push(neighs);
                }
                //  System.out.println("Loop");

            }


        }

        return false;

    }


    public void printBST() {

        Collection<Vertex> vertices = this.vertices.values();

        Queue<Vertex> queue = new LinkedList<>();
        // Queue is a interface which is implemented ny LinkedList

        HashMap<Vertex, Boolean> visited = new HashMap<>();

        /* We could have simply copied and pasted the hasPathFromToBSTIterative code but instead of checking if tmp.isAdjacent(v2) is true
        or not, we could have simply printed (tmp.name) like we have in this code. That  code would work fine if all graph is connected
        and the link of D and E is made. If the link D and E is broken, then tht code would ony print A B C D and not E, F,G. This is
        because when tmp = D then it would not add any children to the queue (since "A" , "B" wont be added because of visited and "E" wont
        be added since that link was broken.

        To counter this we run a loop through all the vertices  and if any vertex is not added to visited it would run the whole "main code"
        on it.

        In the case when D and E link is broken, the "main code" works for A and prints A B C D and adds A B C D to visited
        v1 = "B" is checked if it exists in the visited hasmap. Since it does, the main code wont be run. The main code wont run for
        C and D but as soon as v1 = "E". The main code runs for E printing E F G
         */
        for (Vertex v1 : vertices) {
            if (visited.containsKey(v1) == false) {
                queue.offer(v1);
                visited.put(v1, true);

                while (!queue.isEmpty()) {
                    Vertex tmp = queue.poll();

                    System.out.print(tmp.name + " , ");

                    Set<Vertex> neighborsOftmp = tmp.neighbours.keySet();

                    for (Vertex neighs : neighborsOftmp) {

                        if (visited.containsKey(neighs) == false) {

                            visited.put(neighs, true);
                            queue.offer(neighs);
                        }
                        //  System.out.println("Loop");

                    }


                }


            }


        }

        System.out.println("END");
    }

    /*

    NOTE: To print DST just replace queue with Stack in the above "printBST" code
     */

    public boolean checkIfTheGraphIsConnected() {
        int size = this.vertices.size();

        // Running the printBST code for only "A". If it is connected then the size of visited would be same as size since all the
        //   vertices would be stored in it

        Queue<Vertex> queue = new LinkedList<>();
        // Queue is a interface which is implemented ny LinkedList

        HashMap<Vertex, Boolean> visited = new HashMap<>();

        Vertex v1 = this.vertices.get("A");
        queue.offer(v1);
        visited.put(v1, true);

        while (!queue.isEmpty()) {
            Vertex tmp = queue.poll();

            // System.out.print(tmp.name + " , ");

            Set<Vertex> neighborsOftmp = tmp.neighbours.keySet();

            for (Vertex neighs : neighborsOftmp) {

                if (visited.containsKey(neighs) == false) {

                    visited.put(neighs, true);
                    queue.offer(neighs);
                }
                //  System.out.println("Loop");

            }


        }

        return (size == visited.size());
    }

    public ArrayList<ArrayList<String>> gcc() {
        ArrayList<ArrayList<String>> rc = new ArrayList<>();

        Collection<Vertex> vertices = this.vertices.values();

        Queue<Vertex> queue = new LinkedList<>();
        // Queue is a interface which is implemented ny LinkedList

        HashMap<Vertex, Boolean> visited = new HashMap<>();
        Queue<Vertex> store = new LinkedList<>();


        for (Vertex v1 : vertices) {
            if (visited.containsKey(v1) == false) {
                queue.offer(v1);
                visited.put(v1, true);
                store.offer(v1);

                while (!queue.isEmpty()) {
                    Vertex tmp = queue.poll();

//                    System.out.print(tmp.name + " , ");

                    Set<Vertex> neighborsOftmp = tmp.neighbours.keySet();

                    for (Vertex neighs : neighborsOftmp) {

                        if (visited.containsKey(neighs) == false) {

                            visited.put(neighs, true);
                            store.offer(neighs);
                            queue.offer(neighs);
                        }
                        //  System.out.println("Loop");

                    }


                }

                ArrayList<String> hmm = new ArrayList<>();

                for (int i = 0; i < store.size(); i++) {
                    hmm.add(store.poll().name);

                }

                rc.add(hmm);

            }

        }

        return rc;

    }

    public boolean isBipartite() {


        Queue<Vertex> queue = new LinkedList<>();
        HashMap<Vertex, String> visited = new HashMap<>();
        Collection<Vertex> vertices = this.vertices.values();

        for (Vertex v1 : vertices) {
            if (visited.containsKey(v1) == false) {
                queue.offer(v1);
                visited.put(v1, "Red");

                while (!queue.isEmpty()) {
                    Vertex tmp = queue.poll();

//                    System.out.print(tmp.name + " , ");

                    String currentColour = visited.get(tmp);
                    String colourToBeAdded = "";

                    if (currentColour.equals("Red"))
                        colourToBeAdded = "Green";
                    else
                        colourToBeAdded = "Red";

                    Set<Vertex> neighborsOftmp = tmp.neighbours.keySet();

                    for (Vertex neighs : neighborsOftmp) {

                        if (visited.containsKey(neighs) == false) {

                            visited.put(neighs, colourToBeAdded);
                            queue.offer(neighs);
                        } else {
                            String neighboursColour = visited.get(neighs);

                            if (colourToBeAdded.equals(neighboursColour) == false)
                                return false;
                        }

                    }


                }
            }
        }

        return true;
    }

    public boolean isAcyclic() throws Exception {


        Queue<Vertex> queue = new LinkedList<>();
        HashMap<Vertex, Vertex> visited = new HashMap<>();
        Collection<Vertex> vertices = this.vertices.values();

        Vertex reasonWhy;

        for (Vertex v1 : vertices) {
            if (visited.containsKey(v1) == false) {
                queue.offer(v1);
                visited.put(v1, v1);

                while (!queue.isEmpty()) {
                    Vertex tmp = queue.poll();

//                    System.out.print(tmp.name + " , ");

                    Set<Vertex> neighborsOftmp = tmp.neighbours.keySet();

                    for (Vertex neighs : neighborsOftmp) {

                        if (visited.containsKey(neighs) == false) {

                            visited.put(neighs, tmp);
                            queue.offer(neighs);
                        } else {
                            Vertex y = visited.get(v1);

                            if (y.equals(tmp) == false)
                                return true;
                        }

                    }
                }

            }
        }

        return false;
    }
}

// A(A), B(A),