import java.util.HashMap;


public class twosum{


    public int[] solution(int[] nums , int target){

        int complement;
        int[] listSolution = new int[2];
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        
        for (int i = 0 ; i < nums.length ; i++) {
            hmap.put(nums[i], i);
            complement = target - nums[i];
            if (hmap.containsKey(complement)) {
                listSolution[0] = i;
                listSolution[1] = hmap.get(complement);
                return listSolution;
            }
        }
        return listSolution;
    }
}


/**
 * 
 * import java.util.HashMap;

public class two_sums_hashmap {
    public int[] twoSum(int[] nums, int target) {

        int[] solution = new int[2];
        HashMap<Integer,Integer> hmap = new HashMap<Integer,Integer>();
        // KEY , VALUE
        // NUMBER VALUE, index --> in this case
        for (int i=0;i<nums.length;i++) {
            if (hmap.containsKey(target-nums[i])) {
                solution[0] = i;
                solution[1] = hmap.get(target-nums[i]);
            }
            hmap.put(nums[i], i);
        }
        return solution;
    }
}

 */
