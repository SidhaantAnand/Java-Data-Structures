package CodingBlocks;

import java.util.LinkedList;

/**
 * Created by Sandeep on 7/14/2017.
 */
public class HashTableMain<K, V> {


    private int size;
    private LinkedList<HTPair>[] bucketarray;

    private class HTPair // analgous to "Node"
    {
        K key;
        V value;

        public HTPair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public boolean equals(Object obj) { // we cant HTPair obj since the paramters require an object of the Object class
            HTPair obj2 = (HTPair) obj; // type casting object of Object to obbj
            return (this.key.equals(obj2.key));
        }

        public String toString() {
            return (this.key + " , " + this.value);
        }

    }

    public HashTableMain(int capacity) {
        this.size = 0;
        this.bucketarray = new LinkedList[capacity]; // array is of LinkedList. LinkedList is of HTPair
    }

    private int hashFunction(K key) {
        int hs = key.hashCode();
        hs = Math.abs(hs);
        int compressedhs = hs % this.bucketarray.length;
        return compressedhs;
    }

    public void put(K key, V value) {
        HTPair pta /* Pair to be added */ = new HTPair(key, value);

        int compressedhs = hashFunction(key);

        if (this.bucketarray[compressedhs] == null) // No Linked List  is found  at the index "compressedhs"
        {
            this.bucketarray[compressedhs] = new LinkedList<>();
            this.bucketarray[compressedhs].add(pta);
            this.size++;
        } else {
            int searchresult = this.bucketarray[compressedhs].indexOf(pta); // calls equals

            if (searchresult == -1) // no element is found
            {
                this.bucketarray[compressedhs].add(pta);
                this.size++;
            } else {
                this.bucketarray[compressedhs].set(searchresult, pta);
                //  this.size++; WRONG. Since we arent adding another "HTPair" to the
            }
        }

        // <Making put efficent. The code could have stopped  on line 68 and it would have been fine

        /* Why are we doing this?. For putting we need the indexOf method to see if we are supposed to add or update
           and the indexOf method takes O(N). The use of HashMap is that it gets everything down to O(1).

           So we calculate the average number of elements in one LL of bucketarray(lamda)

           if lamda exceeds 2 (any value) then we double the size reducing the value of lamda.

           What does this do? We make sure that each LL doesnt have more than 2 elements  thus the indexOf function
           is not O(N) but infact O(2) since indexOf is only run in one Linkedist courtesy of compressedHS so if we reduce the
           average number of elements in each LL, the complexity reduces
            */

        double lamda = (double) this.size / this.bucketarray.length;
        if(lamda > 2)
            this.reHash();
    }

    public void display() {
        for (int i = 0; i < this.bucketarray.length; i++) {
            if (this.bucketarray[i] == null)
                System.out.println("NULL");

            else {
                System.out.println(this.bucketarray[i]);
            }
        }

    }

    public V get(K key) {
        int compressedhs = hashFunction(key);

        if (this.bucketarray[compressedhs] == null) // No LL at the index
            return null;

        else {
            HTPair pairToBeSearched = new HTPair(key, null);
            int searchresult = this.bucketarray[compressedhs].indexOf(pairToBeSearched);

            if (searchresult == -1)
                return null;

            else {
                this.size--;
                return (this.bucketarray[compressedhs].get(searchresult).value);

            }

        }
    }

    public V remove(K key) {
        int compressedhs = hashFunction(key);

        if (this.bucketarray[compressedhs] == null) // No LL at the index
            return null;

        else {
            HTPair pairToBeSearched = new HTPair(key, null);
            int searchresult = this.bucketarray[compressedhs].indexOf(pairToBeSearched);

            if (searchresult == -1)
                return null;

            else {
                return (this.bucketarray[compressedhs].remove(searchresult).value);

            }

        }
    }

    private void reHash() {
        LinkedList<HTPair>[] oldbucketarray = this.bucketarray;
        this.bucketarray = new LinkedList[2*oldbucketarray.length];
        this.size = 0;

        /* first read the note in put

           So reHash rearranges a
         */
        for(int i = 0; i<oldbucketarray.length; i++)
        {
            HTPair rearrange = oldbucketarray[i].removeFirst();
            this.put(rearrange.key, rearrange.value);
        }
    }
}