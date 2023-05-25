import java.util.HashSet;
import java.util.Iterator;

public class HashTables{

    // Problem 4.
    public static void findSumPair(int target, int[] array){
        //TODO.
        // check if the array is empty and return nothing if it is
        if(array.length == 0){
            System.out.println("The array is empty"); 
            return;
        }
        
        HashSet<Integer> mySet = new HashSet<>(); 

        // populate the hash set with values from the array, O(n)
        for(int i = 0; i < array.length; i++){
            mySet.add(array[i]); 
        }
        // assign an iterator object to an iterator over the elements in the has set. 
        Iterator<Integer> l = mySet.iterator(); 
        // iterate over the hash set
        while(l.hasNext())
        {
            int item = l.next();   // sets item to the next item in the set

            // if the current item in the iterator and the target - current item are both in the hash set, 
            // then it is a pair for the target. This takes theta(n) for the worst case, which is having to iterate
            // over the entire hashset (n items) to find a pair. 
            if(mySet.contains(item)&&mySet.contains(target-item)) 
            {
                System.out.println("Pair found: " + item +", "+ (target-item));
                return;
            }
             
        }
        System.out.println("Pair not found.");
    }

    // Problem 5.
    public static int DistinctValues(int[] array){
        // TODO.
        if(array.length==0){return -1;} // empty array so return -1
        HashSet<Integer> mySet = new HashSet<>(); 
        int numVal = 0; 
        // iterate over the entire array, and if mySet.add returns true, then 
        // the item is not in the hashset. When the add() method returns true then a counter is incremented
        for(int i = 0; i < array.length; i++)
        {
            if(mySet.add(Math.abs(array[i]))){
                numVal++; 
            }
        }

        return numVal; 
    }


    public static void main(String[] args){
        // For your tests.
        int[] array = {5}; 
        for(int i = 0; i < 11; i++)
        {
            System.out.print(i + ".) Is There a Sum of Pairs for "+ i+ ": " ); 
            findSumPair(i, array);
            System.out.println("Number of Distinct values: " + DistinctValues(array)); 
        }
    }
}