package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class Problem {
	public static void main(String[] args) {
		
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

    // 26. Remove Duplicates from Sorted Array
    public int removeDuplicates(int[] nums) {
    	int index=1;
    	int val = nums[0];
    	for(int i=1; i<nums.length;i++) {
    		if(nums[i]!=val) {
    			val = nums[i];
    			nums[index] = val;
    			index++;
    		}
    	}
        return index;
    }

    // 27. Remove Element
    public int removeElement(int[] nums, int val) {
         int index=0;
         for(int i=0;i<nums.length;i++) {
        	 if(nums[i]!=val) {
        		 nums[index] = nums[i];
        		 index++;
        	 }
         }
         
         return index;
    }

    // 31. Next Permutation
    public static void nextPermutation(int[] nums) {
    	Hashtable<Integer, Integer> dictionary = new Hashtable<Integer, Integer>();
    	
    	int len = nums.length;
    	dictionary.put(len-1, nums[len-1]);
    	
    	for(int i=len-2;i>=0;i--) {
    		var currentVal = nums[i];
    		var values= dictionary.entrySet() 
    		          .stream() 
    		          .filter(map -> map.getValue() > currentVal) 
    		          .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()))
    		          .values().toArray();
    		
    		if (values.length > 0) {
    			java.util.Arrays.sort(values);
        		var indexes= dictionary.entrySet() 
      		          .stream() 
      		          .filter(map -> map.getValue() == values[0]) 
      		          .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()))
  		              .keySet().toArray();
    			java.util.Arrays.sort(indexes);
		        int index = (int)indexes[0];
		        int val = nums[index];
    			nums[index] = nums[i];
    			nums[i]=val;
    			java.util.Arrays.sort(nums,i+1, len);
    			return;
    		}
    		dictionary.put(i, nums[i]);
    		
    	}
    	java.util.Arrays.sort(nums);
    }

    // 33. Search in Rotated Sorted Array
    
    public int search(int[] nums, int target) {
    	int len = nums.length;
    	if (len==1) {
    		return nums[0]==target ? 0 : -1;
    	}
    	
    	if (nums[0]<nums[len-1]) {
    		return search(nums, target, 0,len-1);
    	}else {
    		int startIndex=0;
    		int endIndex=len-1;
    		while(startIndex <= endIndex) {
    			int mid = startIndex + (endIndex - startIndex)/2;
    			if(nums[mid]==target) return mid;
          
                if ((mid==0 || nums[mid] > nums[mid-1]) && (mid==len-1 || nums[mid] > nums[mid+1]))
                {
                    if (target > nums[mid]) return -1;
    				if (nums[0] > target) {
    					return search(nums, target, mid+1, len-1);
    				}
    				return search(nums, target, 0, mid-1);
                }else if (nums[mid] >nums[0]) {
    				startIndex++;
    			}else{
                    endIndex--;
                }
    		}
    	}
    	
        return -1;
        
    }
    
    private int search(int[] nums, int target, int startIndex, int endIndex) {
        if (startIndex>endIndex) return -1;
    	int mid = startIndex + (endIndex-startIndex)/2;
    	if (nums[mid]==target) return mid;
    	else if (nums[mid]<target) {
    		//if (mid+!==startIndex) return -1;
    		return search(nums, target, mid+1, endIndex);
    	}
    	else {
    		//if (endIndex==mid) return -1;
    		return search(nums, target, startIndex, mid-1);
    	}
    }

    // 34. Find First and Last Position of Element in Sorted Array
    public int[] searchRange(int[] nums, int target) {
    	int[] res = new int[] {-1, -1};
        int len = nums.length;
        if(len==0 || target< nums[0]) 
        	return res;
        
        var index = search(nums, target, 0, len-1);
        
        if (index==-1) return res;
        int leftIndex=index;
        int rightIndex=index;
        while (leftIndex>=0 && nums[leftIndex] == target) {
        	leftIndex--;
        }
        
        while (rightIndex <=len-1 &&nums[rightIndex] == target) {
        	rightIndex++;
        }
        return new int[] {leftIndex+1, rightIndex-1};
    }

    // 35. Search Insert Position
    public int searchInsert(int[] nums, int target) {
    	if (nums[0]>target) return 0;
        int len=nums.length;
    	int startIndex=0;
		int endIndex=len-1;
		while(startIndex <= endIndex) {
			int mid = startIndex + (endIndex - startIndex)/2;
			if(nums[mid]==target) return mid;
            
			if ((mid==0 || nums[mid] <target) && (mid==len-1 || nums[mid+1] > target))
            {
                return mid+1;
            }else if (nums[mid] > target) {
            	endIndex--;
				
			}else{
				startIndex++;
            }
		}
		return -1;
    }

    // 36. Valid Sudoku
	public boolean isValidSudoku(char[][] board) {
		Hashtable<Integer, List<String>> dictionary = new Hashtable<Integer, List<String>>();
		Hashtable<Integer, List<String>> culdictionary = new Hashtable<Integer, List<String>>();
		
		for(int i=0;i<9;i++) {
			HashSet set = new HashSet();
			for(int j=0;j<9;j++) {
				if(board[i][j]!='.') {
					if(!set.add(board[i][j])) {
						return false;
					}
					
					var block = (i/3)*3+(j/3);
					if(!dictionary.containsKey(block)) {
						dictionary.put(block, new ArrayList<String>());
					}
					if(dictionary.get(block).contains(Character.toString(board[i][j]))) {
						return false;
					}else {
						var list = dictionary.get(block);
						list.add(Character.toString(board[i][j]));
						dictionary.replace(block, list);
                        
					}
					if(!culdictionary.containsKey(j)) {
						culdictionary.put(j, new ArrayList<String>());
					}
					if(culdictionary.get(j).contains(Character.toString(board[i][j]))) {
						return false;
					}else {
						var list = culdictionary.get(j);
						list.add(Character.toString(board[i][j]));
						culdictionary.replace(j, list);
					}
				}
			}
		}
		
		return true;
			        
    }

	// 39. Combination Sum

	// dp
	public static List<List<Integer>> combinationSum_1(int[] candidates, int target) {
		java.util.Arrays.sort(candidates);
        if (target < candidates[0])
		{
			return new ArrayList<>();
		}
		var intList = Arrays.stream(candidates).boxed().toList();

		Hashtable<Integer, List<List<Integer>>> dictionary = new Hashtable<Integer, List<List<Integer>>>();
		
		int value = candidates[0];
		
		while(value <= target) {
			var set = new HashSet<String>();
			var temp = new ArrayList<List<Integer>>();
			if (intList.contains(value)) {
				temp.add(Arrays.asList(value));
			}
			for(int i=candidates[0]; i<=value-candidates[0];i++) {
				var list1 = dictionary.get(i);
				var list2 = dictionary.get(value-i);
				for (var l1 : list1) {
					for(var l2: list2) {
						var tempL= new ArrayList<Integer>();
						tempL.addAll(l1);
						tempL.addAll(l2);
						Collections.sort(tempL);
						if (set.add(tempL.toString())) {
							temp.add(tempL);
						}
						
					}
				}
			}
			
			dictionary.put(value, temp);
            value++;
		}
		return dictionary.get(target);
	}      

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		combinationSum(candidates, target, 0, 0, new ArrayList(), list);
		return list;
	}
	
	private void combinationSum(int[] candidates, int target, int sum, int startIndex, List<Integer> temp, List<List<Integer>> list) {
		if(sum>target) return;
		if(sum==target) {
			list.add(new ArrayList(temp));
			return;
		}
		
		for(int i=startIndex; i<candidates.length;i++) {
			temp.add(candidates[i]);
			combinationSum(candidates, target, sum+candidates[i], i, temp, list);
			temp.remove(temp.size()-1);
		}
	}

	// 40. Combination Sum II
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		java.util.Arrays.sort(candidates);
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		combinationSum2(candidates, target, 0, 0, new ArrayList(), list);
		return list; 
    }
	
	private void combinationSum2(int[] candidates, int target, int sum, int startIndex, List<Integer> temp, List<List<Integer>> list) {
		if(sum>target) return;
		if(sum==target) {
			list.add(new ArrayList(temp));
			return;
		}
		
		for(int i=startIndex; i<candidates.length;i++) {
			if(candidates[i]>target) return;
            
			temp.add(candidates[i]);
			combinationSum2(candidates, target, sum+candidates[i], i+1, temp, list);
			temp.remove(temp.size()-1);
            while(i<candidates.length-1 && candidates[i]==candidates[i+1]){
                i++;
            }
		}
	}

	// 45. Jump Game II
	public int jump(int[] nums) {
		int len = nums.length;
        int[] dp = new int[len];
        
        for(int i=1;i<len;i++) {
        	dp[i] = Integer.MAX_VALUE;
        }
        
        for(int i=0;i<len;i++) {
        	for(int j=1;j<=nums[i] && i+j<len;j++) {
        		dp[i+j] = Math.min(dp[i+j], dp[i]+1);
        	}
        }
        return dp[len-1];
    }

	// 46. Permutations
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		permute(nums, nums.length, list, new ArrayList());
		return list;
    }
	
	private void permute(int[] nums, int len, List<List<Integer>> list, List<Integer> temp) {
		if (temp.size() == len) {
			list.add(new ArrayList(temp));
			return;
		}
		for(int i=0;i<len;i++) {
			if(!temp.contains(nums[i])){
				temp.add(nums[i]);
				permute(nums, len, list,temp);
				temp.remove(temp.size()-1);
			}
		}
	}

	// 47. Permutations II
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		HashSet<String> set = new HashSet();
		permute(nums, nums.length, set, new ArrayList());
		for(var str : set) {
			var temp = new ArrayList<Integer>();
			for(var s : new ArrayList<String>(Arrays.asList(str.split(",")))) {
				temp.add(Integer. parseInt(s));
			}
			list.add(temp);
		}
		return list;
    }
	
	private void permute(int[] nums, int len, HashSet<String> set, List<Integer> temp) {
		if (temp.size() == len) {
			var str="";
			for(var i : temp) {
				str+=nums[i]+",";
			}
			set.add(str);
			return;
		}
		for(int i=0;i<len;i++) {
			if(!temp.contains(i)){
				temp.add(i);
				permute(nums, len, set,temp);
				temp.remove(temp.size()-1);
			}
		}
	}

	// 48. Rotate Image
	
	//49. Group Anagrams
    public List<List<String>> groupAnagrams(String[] strs) {
    	List<List<String>> res = new ArrayList();
    	Hashtable<String, List<String>> dic = new  Hashtable<String, List<String>>();
    	for(var s : strs) {
    		var sorteds = sortString(s);
    		var value = new ArrayList<String>();
    		if (!dic.containsKey(sorteds)) {
				dic.put(sorteds, new ArrayList());
			}
    		value = (ArrayList<String>) dic.get(sorteds);
    		value.add(s);
    		dic.replace(sorteds, value);
    	}
    	for(var s : dic.values()) {
    		res.add(s);
    	}
    	return res;
    }
    
    private String sortString(String inputString)
    {
        // Converting input string to character array
        char tempArray[] = inputString.toCharArray();
 
        // Sorting temp array using
        Arrays.sort(tempArray);
 
        // Returning new sorted string
        return new String(tempArray);
    }
}
