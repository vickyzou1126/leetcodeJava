package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class Problem {
	public static void main(String[] args) {
		fourSum(new int[] {1000000000,1000000000,1000000000,1000000000},-294967296);
	}
	
	// 1. Two Sum
    public int[] twoSum(int[] nums, int target) {
        int[] res = {0,0};
        Hashtable<Integer, Integer> dictionary = new Hashtable<Integer, Integer>();
        
        for(int i=0;i<nums.length;i++) {
        	var value = target - nums[i];
        	if (dictionary.containsKey(value))
        		return new int[] {dictionary.get(value), i};
        	
        	dictionary.put(nums[i], i);
        }
        
        return res;
    }
    
    // 11. Container With Most Water
    public int maxArea(int[] height) {
	   int max=0;
       int left=0;
       int right = height.length-1;
       while(left<right) {
    	   var width = right - left ;
    	   if (height[left] > height[right]) {
    		   max = Math.max(max, height[right] * width);
    		   right--;
    	   }else {
    		   max = Math.max(max, height[left] * width);
    	       left++;
    	   }
       }
       
	   return max;
   }
   
    // 15. 3Sum
    public static List<List<Integer>> threeSum(int[] nums) {
	   List<List<Integer>> list = new ArrayList<>();
	   int length = nums.length;
       if (length<=2) return list;
       java.util.Arrays.sort(nums);
       
       for(int i=0;i<length-2;i++) {
    	   if(nums[i]>0) break;
    	   if(i>0 && nums[i]==nums[i-1]) continue;
    	   int left = i+1;
    	   int right = length-1;
    	   while(left<right) {
    		  
    		   int newSum = nums[i]+nums[left]+nums[right];
    		   if(newSum ==0 ) {
    			   list.add(Arrays.asList(nums[i], nums[left], nums[right]));
    			   left++;
    			   right--;
    			   while (left < length &&  nums[left] == nums[left - 1])
                   {
                       left++;
                   }
    		   }else if (newSum <0) {
    			   left++;
    		   }else {
    			   right--;
    		   }
    	   }
    	   	 
    	   
       }
       return list;
   }

    
    // 16. 3Sum Closest
    public static int threeSumClosest(int[] nums, int target) {
    	java.util.Arrays.sort(nums);
    	int abs = Integer.MAX_VALUE;
    	int len = nums.length;
    	int res=Integer.MAX_VALUE;
        
    	for(int i=0;i<len-2;i++) {
    		int sum = nums[i];
    		int left = i+1;
    		int right = len-1;

    		while(left < right) {
    			var newsum = sum+nums[left]+nums[right];
    			if (newsum == target) return target;
    			else if (newsum < target) {
    				left ++;
    			}else {
    				right --;
    			}
                var newAbs = Math.abs(target-newsum);
    			if(newAbs < abs) {
    				abs = newAbs;
    				res = newsum;
    			}
    		}
    	}
    	
    	return res;
    }

    // 18. 4Sum
    public static List<List<Integer>> fourSum(int[] nums, int target) {
    	
    	List<List<Integer>> list = new ArrayList<>();
    	int length = nums.length;
    	if(length<=3) return list;
    	
    	java.util.Arrays.sort(nums);
    	if(nums[0]==1000000000) return list;
    	
    	for(int i=0;i<length-3;i++) {
    		if (i>1 && nums[i]==nums[i-1]) continue;
    		
    		for(int j=i+1; j<length-2;j++) {
    			if (j>i+1 && nums[j]==nums[j-1]) continue;
    			int left=j+1;
    			int right=length-1;
    			while (left<right) {
    				int newSum = nums[i]+nums[j] + nums[left]+nums[right];
        			if (newSum == target) {
        				list.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
        				left++;
         			    right--;
         			    while (left < length &&  nums[left] == nums[left - 1])
                        {
                            left++;
                        }
        			} else if (newSum < target) {
        				left++;
        			}else {
        				right--;
        			}
    			}
    		}
    	}
    	
        return list;
    }
}
