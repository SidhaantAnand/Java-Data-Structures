package CodingBlocks;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sandeep on 7/14/2017.
 */
public class HashMapClient {
    public static void main(String[] args) {

       int r =  highestFrequency("aabbbbbbbbbbbbbbbbcdefffffffffff");
       System.out.println(r);

        int arr1[] = {1, 2, 2, 3, 3, 3};
        int arr2[] = {2, 3, 4};
//        ArrayList<Integer> recResult = GCE2(arr1, arr2);
//        System.out.println(recResult);

        int arr[] = {-4,-4,4,3,3,3,-2,2,0,0,0,-3};
      //  zeroSumPairs(arr);

    }

    public static int highestFrequency(String word) {
        HashMap<Character, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            if (freqMap.containsKey(word.charAt(i)) == false)
                freqMap.put(word.charAt(i), 1);

            else {
                int currFreq = freqMap.get(word.charAt(i));
                freqMap.put(word.charAt(i), currFreq + 1);
            }
        }

        System.out.println("size " + freqMap.size());


        Set<Map.Entry<Character, Integer>> entries = freqMap.entrySet();
        for (Map.Entry<Character, Integer> entry : entries) {
            System.out.print(entry.getKey() + " ");
            System.out.println(entry.getValue());
        }

        for (Map.Entry<Character, Integer> entry : entries) {
            entry.setValue(0);
            System.out.print(entry.getKey() + " ");
            System.out.println(entry.getValue());
        }

        ArrayList<Integer> calMax = new ArrayList<>();

        for (Map.Entry<Character, Integer> entry : entries) {
            calMax.add(entry.getValue());
        }

        int max = calMax.get(0);
        for (int i = 1; i < calMax.size(); i++) {
            if (calMax.get(i) > max)
                max = calMax.get(i);
        }

        return max;


    }

    public static ArrayList<Integer> removeDuplicatesInArrayList(ArrayList<Integer> arr) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < arr.size(); i++) {
            if (freqMap.containsKey(arr.get(i)) == false)
                freqMap.put(arr.get(i), 1);

            else {
                int currFreq = freqMap.get(arr.get(i));
                freqMap.put(arr.get(i), currFreq + 1);
            }
        }

        Set<Map.Entry<Integer, Integer>> entries = freqMap.entrySet();
        ArrayList<Integer> rem = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : entries) {
            rem.add(entry.getKey());
        }

        return rem;

    }

    public static ArrayList<Integer> GCE2(int arr1[], int arr2[]) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();


        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {

                    freqMap.put(arr1[i], 1);
                }
            }
        }

        int coun = 0;
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[i] == arr2[j]) {
                    coun++;
                }
            }

            int prev = freqMap.get(arr2[i]);
            if (prev < coun)
                freqMap.put(arr2[i], prev);
            else
                freqMap.put(arr2[i], coun);
            coun = 0;
        }

        Set<Map.Entry<Integer, Integer>> entries = freqMap.entrySet();
        ArrayList<Integer> rem = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : entries) {
            rem.add(entry.getKey());
        }

        return rem;

    }

//    public static ArrayList<Integer> GCE2(int arr1[], int arr2[]) {
//        HashMap<Integer, Integer> freqMap = new HashMap<>();
//
//
//        for (int i = 0; i < arr1.length; i++) {
//            for (int j = 0; j < arr2.length; j++) {
//                if (arr1[i] == arr2[j]) {
//
//                    if (freqMap.containsKey(arr1[i]) == false)
//                        freqMap.put(arr1[i], 1);
//
//                    else {
//                        int currFreq = freqMap.get(arr1[i]);
//                        freqMap.put(arr1[i], currFreq + 1);
//                    }
//                }
//            }
//        }
//
//        int coun = 0;
//        for (int i = 0; i < arr2.length; i++) {
//            for (int j = 0; j < arr1.length; j++) {
//                if (arr2[i] == arr1[j]) {
//                    coun++;
//                }
//            }
//
//            int prev = freqMap.get(arr2[i]);
//            if(prev <coun)
//                freqMap.put(arr2[i], prev);
//            else
//                freqMap.put(arr2[i], coun);
//            coun = 0;
//        }
//
//
//        Set<Map.Entry<Integer, Integer>> entries = freqMap.entrySet();
//        ArrayList<Integer> rem = new ArrayList<>();
//
//        for (Map.Entry<Integer, Integer> entry : entries) {
//            int count = entry.getValue();
//
//            for (int i = 0; i < count; i++)
//                rem.add(entry.getKey());
//        }
//
//        return rem;
//
//    }

//    public static ArrayList<Integer> GCE2(int arr1[], int arr2[]) {
//        HashMap<Integer, Integer> freqMap = new HashMap<>();
//        int arr[] = new int[arr1.length * arr2.length];
//
//        for (int i = 0; i < arr.length; i++) {
//
//            if (freqMap.containsKey(arr1[i]) == false)
//                freqMap.put(arr1[i], 1);
//
//            else {
//                int currFreq = freqMap.get(arr1[i]);
//                freqMap.put(arr1[i], currFreq + 1);
//            }
//        }
//    }

    public static void zeroSumPairs(int arr1[])
    {
//        int arr[] = new int[arr1.length];
//
//        for(int i = 0; i<arr.length; i++)
//        {
//            arr[i] = Math.abs(arr1[i]);
//        }
//        HashMap<Integer, Integer> freqMap = new HashMap<>();
//
//        for (int i = 0; i < arr.length; i++) {
//
//        if (freqMap.containsKey(arr[i]) == false)
//            freqMap.put(arr[i], 1);
//
//        else {
//            int currFreq = freqMap.get(arr[i]);
//            freqMap.put(arr[i], currFreq + 1);
//        }
//    }

        HashMap<Integer,Integer> freqMap = new HashMap<>();

        for (int i = 0; i < arr1.length; i++) {

            if (freqMap.containsKey(arr1[i]) == false)
                freqMap.put(arr1[i], 1);

            else {
                int currFreq = freqMap.get(arr1[i]);
                freqMap.put(arr1[i], currFreq + 1);
            }
        }

        Set<Map.Entry<Integer, Integer>> entries = freqMap.entrySet();


        ArrayList<Integer> rem = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : entries) {

            if(entry.getKey() <0)
                continue;

            if(entry.getKey() == 0)
                continue;

            int count1 = entry.getValue();
            int num2 = -entry.getKey();
            int count2 = freqMap.get(num2);

            if(count1 < count2)
            {
                for(int i  = 0; i<count1; i++)
                    System.out.println(entry.getKey() + " -" + entry.getKey());
            }

            else
            {
                for(int i  = 0; i<count2; i++)
                    System.out.println(entry.getKey() + " -" + entry.getKey());
            }
        }

        int countzeroes = freqMap.get(0);




            for(int i = 1; i<=countzeroes/2; i++)
                System.out.println("0 0");
        }




    }






