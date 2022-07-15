public class two_sums {

// Time complexity --> O(n^2) 

    public int[] twoSum(int[] nums, int target) {
        for (int i=0;i<nums.length;i++) {
            int num = nums[i];
            for (int k = i+1 ; k < nums.length ; k++) {
                System.out.println("k ="+i);
                int complement = target - num;
                if (nums[k]==complement) {
                    return new int[]{i,k};
                }
            }
        }
        return null;
    }
}
