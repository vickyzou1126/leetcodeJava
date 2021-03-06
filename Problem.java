package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
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

    // 53. Maximum Subarray
    public int maxSubArray(int[] nums) {
    	int tempSum = nums[0];
        int res = tempSum;
        for(int i=1;i<nums.length;i++) {
            if(nums[i]>0){
                if(tempSum<0 || tempSum+nums[i]<=0){
                    tempSum=nums[i];
                }
                else{
                     tempSum+=nums[i];
                }
            }else{
                res = Math.max(res, tempSum);
                if(tempSum+nums[i]>0){
                    tempSum+=nums[i];
                } else{
                    tempSum = nums[i];
                }
            }
        }
        
        return Math.max(res, tempSum);
    }

    // 54. Spiral Matrix
    
    // 55. Jump Game
    public boolean canJump(int[] nums) {
    	int len = nums.length;
        boolean[] dp = new boolean[len];
        dp[0]=true;
        for(int i=0;i<len;i++) {
        	if(dp[i]) {
        		for(int j=1;j<=nums[i] && i+j<len;j++) {
        			dp[i+j]=true;
        			if (i+j==len-1) return true;
        		}
        	}
        }
        return dp[len-1];
    }
    
    // 56. Merge Intervals
    public int[][] merge(int[][] intervals) {
    	int len = intervals.length;
    	if (len==1) return intervals;
    	Arrays.sort(intervals, Comparator.comparingDouble(o -> o[0]));
    	int[][] res = new int[len][2];
        int low=intervals[0][0];
        int high = intervals[0][1];
        int counter=0;
        for(int i=1;i<len;i++) {
        	if ((low<=intervals[i][0] && low>= intervals[i][1]) || (intervals[i][0]>= low && intervals[i][0]<=high)) {
        		low = Math.min(low, intervals[i][0]);
        		high = Math.max(high, intervals[i][1]);
        	}else {
        		res[counter][0]=low;
        		res[counter][1]=high;
        		counter++;
        		low = intervals[i][0];
        		high=intervals[i][1];
        	}
        }
        res[counter][0]=low;
		res[counter][1]=high;
        return Arrays.copyOf(res,counter+1);
    }

    // 57. Insert Interval
    public int[][] insert(int[][] intervals, int[] newInterval) {
    	int len = intervals.length;
    	if (len==0) return new int[][] {newInterval};
    	
    	int[][] newintervals= new int[len+1][2];
    	for(int i=0;i<len;i++)
    	{
    		newintervals[i]=intervals[i];
    	}
    	newintervals[len] = newInterval;
    	
    	Arrays.sort(newintervals, Comparator.comparingDouble(o -> o[0]));
    	int[][] res = new int[len+1][2];
        int low=newintervals[0][0];
        int high = newintervals[0][1];
        int counter=0;
        for(int i=1;i<=len;i++) {
        	if ((low<=newintervals[i][0] && low>= newintervals[i][1]) || (newintervals[i][0]>= low && newintervals[i][0]<=high)) {
        		low = Math.min(low, newintervals[i][0]);
        		high = Math.max(high, newintervals[i][1]);
        	}else {
        		res[counter][0]=low;
        		res[counter][1]=high;
        		counter++;
        		low = newintervals[i][0];
        		high=newintervals[i][1];
        	}
        }
        res[counter][0]=low;
		res[counter][1]=high;
        return Arrays.copyOf(res,counter+1);
    }

    // 59. Spiral Matrix II
    
    // 63. Unique Paths II
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	if(obstacleGrid[0][0]==1) return 0;
        int row=obstacleGrid.length;
    	int col=obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        // init
        dp[0][0] = 1;
        
        for(int i=0;i<row;i++) {
        	for(int j=0;j<col;j++) {
        		if(obstacleGrid[i][j]!=1) {
        			if (i>0){
                        dp[i][j] += dp[i-1][j];
                    }
                    if(j>0){
                        dp[i][j] +=dp[i][j-1];
                    }
        		}
        	}
        }
        
        return dp[row-1][col-1];
    }

    // 64. Minimum Path Sum
    public int minPathSum(int[][] grid) {
    	int row=grid.length;
    	int col=grid[0].length;
        int[][] dp = new int[row][col];
        // init
        dp[0][0] = grid[0][0];
        
        for(int i=0;i<row;i++) {
        	for(int j=0;j<col;j++) {
        		if(i==0) {
        			if (j==0) continue;

                    dp[i][j]=dp[i][j-1]+grid[i][j];
        		} else if (j==0) {
                    dp[i][j]=dp[i-1][j]+grid[i][j];
        		} else {
        			dp[i][j]=Math.min(dp[i][j-1], dp[i-1][j])+grid[i][j];
        		}
        	}
        }
        return dp[row-1][col-1];
    }

    // 66. Plus One
    public int[] plusOne(int[] digits) {
    	var len = digits.length;
    	int[] res = new int[len+1];
    	int val = digits[len-1]+1;
    	var carry = val>=10;
    	digits[len-1] = val%10;
    	res[len]=digits[len-1];
    	for(int i=len-2;i>=0;i--) {
    		if(carry) {
    			digits[i]= digits[i]+1;
    			carry = digits[i]>=10;
    	    	digits[i] = digits[i]%10;
    		}
    		res[i+1]=digits[i];
    	}
    	if(carry) {
            res[0]=1;
    		return res;
    	}
    	
        return digits;
    }
    
    // 73. Set Matrix Zeroes
    public void setZeroes(int[][] matrix) {
    	boolean[][] visited = new boolean[matrix.length][matrix[0].length];
    	
    	for(int i=0;i<matrix.length;i++) {
    		for(int j=0;j<matrix[0].length;j++) {
    			if(matrix[i][j]==0 && !visited[i][j]) {
    				for(int k=0;k<matrix[0].length;k++) {
    					if(matrix[i][k]!=0) {
    						matrix[i][k]=0;
        					visited[i][k]=true;
    					}
    				}
    				for(int l=0;l<matrix.length;l++) {
    					if(matrix[l][j]!=0) {
    						matrix[l][j]=0;
        					visited[l][j]=true;
    					}
    				}
    			}
    		}
    	}
    }
    
    // 74. Search a 2D Matrix
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int top=0;
        int bottom=m-1;
        while(top<bottom) {
        	int mid =top + (bottom-top)/2;
        	if(matrix[mid][0]==target) return true;
        	else if(matrix[mid][0]>target) {
        		bottom = mid;
        	}else {
        		if((mid<m-1 && matrix[mid+1][0] > target) || mid==m-1) {
        			for(int i=0;i<n;i++) {
        				if (matrix[mid][i]==target) return true;
        			}
        			return false;
        		} else {
        			top=mid+1;
        		}
        	}
        }
        return false;
    }
    
    
    
    // 75. Sort Colors
    public void sortColors(int[] nums) {
    	 int low=0;
         int len=nums.length;
         int high=len-1;
         while(low<high) {
         	if(nums[low] > nums[high]) {
         		int swap = nums[low];
         		nums[low]=nums[high];
         		nums[high]=swap;
         		
         	}
             high--;
             if(high==low) {
         			low++;
         			high=len-1;
         		}
         }
    }

    // 78. Subsets
    public List<List<Integer>> subsets(int[] nums) {
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
    	subsets(nums, 0, res, new ArrayList<Integer>(), nums.length );
    	return res;
    }
    
    private void subsets(int[] nums, int startIndex, List<List<Integer>> res, List<Integer> temp, int len) {
    	if(startIndex == len) {
    		res.add(new ArrayList(temp));
    		return;
    	}
    	subsets(nums, startIndex+1, res, temp, len );
		temp.add(nums[startIndex]);
		subsets(nums, startIndex+1, res, temp, len );
		temp.remove(temp.size()-1);
    }

    // 79. Word Search
    public boolean exist(char[][] board, String word) {
    	int m=board.length;
    	int n=board[0].length;
    	int len = word.length();
    	boolean[][] visited = new boolean[m][n];
    	for(int i=0;i<m;i++) {
    		for(int j=0;j<n;j++) {
    			if(board[i][j]==word.charAt(0))
    			{
    				visited[i][j]=true;
    				if (!findNext(board, visited, m,n,0,len,word,i,j))
    				{
    					visited[i][j]=false;
    				}else return true;
    			}
    		}
    	}
        return false;
    }
    
    private boolean findNext(char[][] board, boolean[][] visited, int m, int n, int indexAt, int len, String word, int i, int j)
    {
    	if(indexAt==len-1) return true;
    	indexAt++;
    	if (i>0 && board[i-1][j]==word.charAt(indexAt) && !visited[i-1][j]) {
    		visited[i-1][j]=true;
			if (!findNext(board, visited, m,n,indexAt,len, word, i-1, j))
			{
				visited[i-1][j]=false;
			}else return true;
    	}
    	
    	if (i<m-1 && board[i+1][j]==word.charAt(indexAt) && !visited[i+1][j]) {
    		visited[i+1][j]=true;
			if (!findNext(board, visited, m,n,indexAt,len, word, i+1, j))
			{
				visited[i+1][j]=false;
			}else return true;
    	}
    	
    	if(j>0 && board[i][j-1]==word.charAt(indexAt) && !visited[i][j-1]) {
    		visited[i][j-1]=true;
			if (!findNext(board, visited, m,n,indexAt,len, word, i, j-1))
			{
				visited[i][j-1]=false;
			}else return true;
    	}
    	
    	if(j<n-1 && board[i][j+1]==word.charAt(indexAt) && !visited[i][j+1]) {
    		visited[i][j+1]=true;
			if (!findNext(board, visited, m,n,indexAt,len, word, i, j+1))
			{
				visited[i][j+1]=false;
			}else return true;
    	}
    	
    	return false;
    }

    // 80. Remove Duplicates from Sorted Array II
    public int removeDuplicates2(int[] nums) {
    	int counter=1;
    	int preval=nums[0];
    	int len=nums.length;
    	int lowIndex=1;
    	for(int i=1;i<len;i++) {
    		if(nums[i]==preval) {
    			counter++;
    			if(counter<=2) {
                    nums[lowIndex]=nums[i];
    				lowIndex++;
    			}
    		}else {
    			preval = nums[i];
    			nums[lowIndex]=nums[i];
    			counter=1;
                lowIndex++;
    		}
    	}
        return lowIndex;
    }
 
    // 81. Search in Rotated Sorted Array II
    public boolean search2(int[] nums, int target) {
    	// remove duplicate
    	int preval=nums[0];
    	int len=nums.length;
    	int lowIndex=1;
    	for(int i=1;i<len;i++) {
    		if(nums[i]!=preval) {
    			preval = nums[i];
    			nums[lowIndex]=nums[i];
                lowIndex++;
    		}
    	}
        var newNums = Arrays.copyOfRange(nums, 0, lowIndex);
        return search(newNums, target) != -1;
    }
    
    // 88. Merge Sorted Array
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n==0) {
        	return;
        }
        
        int index=0;
        int mIndex=0;
        int nIndex=0;

        while(index < m+n)
        {
            
        	int v1 = mIndex<m ? nums1[mIndex] : Integer.MAX_VALUE;
        	int v2 = nIndex<n ? nums2[nIndex] : Integer.MAX_VALUE;
        	if(v1<=v2) {
        		nums1[index] = v1;
        		mIndex++;
        	} else {
        		if(mIndex<m && index<=mIndex) {
        			int swap = nums1[mIndex];
        			nums1[index] = v2;
                    mIndex++;
                    int temp=nIndex;
                    while(temp<n-1&& swap>nums2[temp+1]){
                        nums2[temp]=nums2[temp+1];
                        temp++;
                    }
                    if(temp<n){
                        nums2[temp] = swap;
                    }
        		} else {
        			nums1[index] = v2;
            		nIndex++;
        		}
        	}
        	index++;
        }
    }

    // 90. Subsets II
    public List<List<Integer>> subsetsWithDup(int[] nums) {
    	java.util.Arrays.sort(nums);
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
    	subsetsWithDup(nums,res, new ArrayList(),nums.length,0);
    	return res;
    }
    
    private void subsetsWithDup(int[] nums, List<List<Integer>> res, List<Integer> temp, int len, int index) {
    	if(index == len) {
    		if(!res.contains(temp)) {
    			res.add(new ArrayList(temp));
            }
    		
    		return;
    	}
    	
    	subsetsWithDup(nums,res,temp,len,index+1);
    	temp.add(nums[index]);
    	subsetsWithDup(nums,res,temp,len,index+1);
    	temp.remove(temp.size()-1);
    }
    
    // 105. Construct Binary Tree from Preorder and Inorder Traversal
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	int len = preorder.length;
        if(len==0) return null;
        var node = new TreeNode(preorder[0]);
        if(len==1) return node; 
        var index=-1;
        for(int i=0;i<len;i++){
            if(inorder[i]==preorder[0]){
                index=i;
                break;
            }
        }
        
        node.left = buildTree(Arrays.copyOfRange(preorder, 1, index+1), Arrays.copyOfRange(inorder, 0, index));
        node.right = buildTree(Arrays.copyOfRange(preorder, index+1, len), Arrays.copyOfRange(inorder, index+1, len));
        return node;
    }

    // 106. Construct Binary Tree from Inorder and Postorder Traversal
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
    	int len = inorder.length;
        if(len==0) return null;
        var node = new TreeNode(postorder[len-1]);
        if(len==1) return node; 
        var index=-1;
        for(int i=0;i<len;i++){
            if(inorder[i]==postorder[len-1]){
                index=i;
                break;
            }
        }

        node.left = buildTree(Arrays.copyOfRange(inorder, 0, index), Arrays.copyOfRange(postorder, 0, index));
        node.right = buildTree(Arrays.copyOfRange(inorder, index+1, len), Arrays.copyOfRange(postorder, index, len-1));
        return node;
    }

    // 108. Convert Sorted Array to Binary Search Tree
    public TreeNode sortedArrayToBST(int[] nums) {
    	int len = nums.length;
        if (len==1) return new TreeNode(nums[0]);
        int index = len/2;
        System.out.println("index:" + index);
        var node = new TreeNode(nums[index]);
        if(index > 0){
            node.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, index));
        }
        
        if(len > index+1 ){
            node.right = sortedArrayToBST(Arrays.copyOfRange(nums, index+1, len));
        }

        return node;
    }
    
    // 118. Pascal's Triangle
    public List<List<Integer>> generate(int numRows) {
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(Arrays.asList(1));

        for(int i=2; i<=numRows;i++) {
         	var last = res.get(i-2);
         	var temp = new ArrayList<Integer>();
         	temp.add(1);
         	for(int j=0;j<i-2;j++) {
         		temp.add(last.get(j)+last.get(j+1));
         	}
         	temp.add(1);
         	res.add(new ArrayList(temp));
        }
         
        return res;
    }

    // 119. Pascal's Triangle II
    public List<Integer> getRow(int rowIndex) {
    	var result = new ArrayList<Integer>();
    	result.add(1);
    	if (rowIndex==0) return result;
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList(result));

        for(int i=2; i<=rowIndex+1;i++) {
         	var last = res.get(i-2);
         	result = new ArrayList<Integer>();
         	result.add(1);
         	for(int j=0;j<i-2;j++) {
         		result.add(last.get(j)+last.get(j+1));
         	}
         	result.add(1);
         	res.add(new ArrayList(result));
        }
         
        return result;
    }

    // 120. Triangle
    public int minimumTotal(List<List<Integer>> triangle) {
    	int len = triangle.size();
        int[][] dp = new int[len][len];
        dp[0][0]=triangle.get(0).get(0);
        
        for(int i=1; i<len;i++) {
        	var temp = dp[i-1];
          
        	var current = triangle.get(i);
        	for(int j=0;j<=i;j++){
        		var val1 = j>0 ? temp[j-1] : Integer.MAX_VALUE;
        		var val2 = j<i ? temp[j] :Integer.MAX_VALUE; ;
        		dp[i][j] = Math.min(val1, val2) + current.get(j); 
        	}
        }
        
        int min = Integer.MAX_VALUE;
        for(int j=0;j<len;j++) {
        	min = Math.min(min, dp[len-1][j]);
        }
        return min;
    }

    // 121. Best Time to Buy and Sell Stock
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int profit = 0;
        for(int i=0;i<prices.length;i++) {
        	min = Math.min(min, prices[i]);
        	profit = Math.max(profit, prices[i]-min);
        }
        return profit;
    }

    // 122. Best Time to Buy and Sell Stock II
    public int maxProfit2(int[] prices) {
        int profit =0;
        int bought = prices[0];
        int sale=prices[0];

        for(int i=1;i<prices.length;i++) {
        	if(prices[i]<sale) {
        		profit = profit + Math.max(0, sale - bought);
        		bought = prices[i];
        	}
        	sale = prices[i];
        }
        
        return profit + Math.max(0, sale - bought);
    }
    
    // 128. Longest Consecutive Sequence
    public int longestConsecutive(int[] nums) {
    	java.util.Arrays.sort(nums);
    	int len = nums.length;
    	if(len<=1) return len;
    	int preVal = nums[0];
    	int max=1;
    	int counter=1;
        for(int i=1;i<len;i++) {
        	if(preVal == nums[i]) continue;
        	if(preVal + 1 < nums[i]) {
        		max= Math.max(max, counter);
        		counter=1;
        		
        	} else {
        		counter++;
        	}
        	preVal = nums[i];
        }

        return Math.max(max, counter);
    }

    // 130. Surrounded Regions
    public void solve(char[][] board) {
    	int m = board.length;
    	int n = board[0].length;
        boolean[][] notFlipping = new boolean[m][n];
        int counter=0;
        boolean init=true;
        while(init || counter>0)
        {
            init=false;
            counter=0;
            for(int i=0;i<m;i++) {
            	for(int j=0;j<n;j++) {
            		if(!notFlipping[i][j] && board[i][j] == 'O') {
            			notFlipping[i][j] = (i==0 || notFlipping[i-1][j])|| (i==m-1 || notFlipping[i+1][j]) || (j==0 || notFlipping[i][j-1]) || (j==n-1 || notFlipping[i][j+1]);
            			if(notFlipping[i][j]){
            				counter++;
            			}
            		}
            	}
            }
        }
        
        
        for(int i=0;i<m;i++) {
        	for(int j=n-1;j>=0;j--) {
        		if(!notFlipping[i][j] && board[i][j] == 'O') {
        			notFlipping[i][j] = (i==0 || notFlipping[i-1][j])|| (i==m-1 || notFlipping[i+1][j]) || (j==0 || notFlipping[i][j-1]) || (j==n-1 || notFlipping[i][j+1]);
        		}
        	}
        }
        
       
        
        for(int i=m-1;i>=0;i--) {
        	for(int j=0;j<n;j++) {
        		if(!notFlipping[i][j] && board[i][j] == 'O') {
        			notFlipping[i][j] = (i==0 || notFlipping[i-1][j])|| (i==m-1 || notFlipping[i+1][j]) || (j==0 || notFlipping[i][j-1]) || (j==n-1 || notFlipping[i][j+1]);
        		}
        	}
        }
        
        
        for(int i=m-1;i>=0;i--) {
        	for(int j=n-1;j>=0;j--) {
        		if(!notFlipping[i][j] && board[i][j] == 'O') {
        			notFlipping[i][j] = (i==0 || notFlipping[i-1][j])|| (i==m-1 || notFlipping[i+1][j]) || (j==0 || notFlipping[i][j-1]) || (j==n-1 || notFlipping[i][j+1]);
        		}
        	}
        }

         for(int i=1;i<m-1;i++) {
        	for(int j=1;j<n-1;j++) {
        		if(!notFlipping[i][j] && board[i][j] == 'O') {
        			board[i][j] = 'X';
        		}
        	}
        }
    }

    // 134. Gas Station
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(Arrays.stream(cost).sum() > Arrays.stream(gas).sum()) return -1;
     	for(int i=0;i<gas.length;i++) {
             if(i==0 || ((gas[i]-cost[i])>(gas[i-1]-cost[i-1]))){
                 if(canCompleteCircuit(gas, cost, i, i+1, gas.length, gas[i])) return i;
             }
     		
     	}
         return -1;
     }
     
     private boolean canCompleteCircuit(int[] gas, int[] cost, int startIndex, int nextIndex, int len, int deposit) {
    	 if(deposit==0) return false;
     	 int temp=(nextIndex-1)%len;
         int temp2=nextIndex % len;
     	 if(cost[temp]>deposit) return false;
         if( temp2==startIndex) return true;
     	 return canCompleteCircuit(gas, cost, startIndex, nextIndex+1, len, deposit-cost[temp]+gas[temp2]);
     }

     // 136. Single Number
     public int singleNumber(int[] nums) {
    	 java.util.Arrays.sort(nums);
    	 int preVal=nums[0];
    	 int counter=1;
    	 for(int i=1;i<nums.length;i++) {
    		 if(nums[i]==preVal) {
    			 counter++;
    		 }else {
    			 if(counter<2) return preVal;
    			 counter=1;
    			 preVal=nums[i];
    		 }
    	 }
    	 return preVal;
     }
     
     // 137. Single Number II
     public int singleNumber2(int[] nums) {
    	 java.util.Arrays.sort(nums);
    	 int preVal=nums[0];
    	 int counter=1;
    	 for(int i=1;i<nums.length;i++) {
    		 if(nums[i]==preVal) {
    			 counter++;
    		 }else {
    			 if(counter<2) return preVal;
    			 counter=1;
    			 preVal=nums[i];
    		 }
    	 }
    	 return preVal;
     }

     // 150. Evaluate Reverse Polish Notation
     
     // 152. Maximum Product Subarray -- to review
     public int maxProduct(int[] nums) {
    	 int n = nums.length;
         if (n == 1) return nums[0];

         var minArr = new int[n];
         var maxArr = new int[n];

         minArr[0] = nums[0];
         maxArr[0] = nums[0];
         
         int max = nums[0];

         for (int i = 1; i < n; i++)
         {
             maxArr[i] = Math.max(Math.max(maxArr[i - 1] * nums[i], minArr[i - 1] * nums[i]), nums[i]);
             minArr[i] = Math.min(Math.min(maxArr[i - 1] * nums[i], minArr[i - 1] * nums[i]), nums[i]);
             max = Math.max(max, maxArr[i]);
         }

         return max;
     }

     // 153. Find Minimum in Rotated Sorted Array
     public int findMin(int[] nums) {
    	 int len = nums.length;
    	 if (len==1) return nums[0];
         if(nums[0]<nums[len-1]) return nums[0];
         
         return findMin(nums,0,len-1, len);
         
     }
     private int findMin(int[] nums, int left, int right, int len) {

    	 int mid = left + (right-left)/2;
    	 
    	 if(nums[mid]>nums[0]) {
    		 return findMin(nums, mid+1, right, len);
    	 }else {
    		 if((mid==0 || nums[mid-1]>nums[0]) && (mid==len-1 || nums[mid+1]>nums[mid])) return nums[mid];
             if(left>mid-1) return nums[mid+1];
    		 return findMin(nums, left, mid-1, len);
    	 }
     }

     // 162. Find Peak Element
     public int findPeakElement(int[] nums) {
    	 int len = nums.length;
    	 if(len==1) return 0;
    	 int index = findPeakElement(nums, 0, (len-1)/2, len);
    	 if(index!=-1) return index;
    	 return findPeakElement(nums, (len-1)/2+1, len-1, len);
     }
     
     private int findPeakElement(int[] nums, int startIndex, int endIndex, int len) {
    	 if(startIndex>endIndex) return -1;
    	 int mid = startIndex + (endIndex-startIndex)/2;
    	 if ((mid==0 || nums[mid-1] < nums[mid]) && (mid==len-1 || nums[mid+1] < nums[mid])) return mid;
    	 int index = findPeakElement(nums, startIndex, mid-1, len);
    	 if(index!=-1) return index;
    	 return findPeakElement(nums, mid+1, endIndex, len);
     }

     // 167. Two Sum II - Input Array Is Sorted
     public int[] twoSum2(int[] numbers, int target) {
    	 int[] res = new int[2];
    	 int startIndex=0;
    	 int endIndex=numbers.length-1;
    	 while(startIndex<endIndex) {
    		 var sum=numbers[startIndex]+numbers[endIndex];
    		 if(sum == target) return new int[] { startIndex+1, endIndex+1 };
    		 else if(sum<target) {
    			 startIndex++;
    		 }else {
    			 endIndex--;
    		 }
    	 }
    	 return res;
     }

     // 169. Majority Element
     public int majorityElement(int[] nums) {
    	 int len=nums.length;
         var dictionary = new Hashtable<Integer, Integer>();
         for(int i=0;i<len;i++) {
        	 if(!dictionary.containsKey(nums[i])) {
        		 dictionary.put(nums[i], 0);
        	 }
        	 var num = dictionary.get(nums[i])+1;
        	 dictionary.replace(nums[i],num);
        	 if(num>=(len/2)+1) return nums[i];
         }
         int majorityElement = 0;
         int counter=0;
         Set<Integer> setOfKeys = dictionary.keySet();
         

         for (Integer key : setOfKeys) {
        	 if(dictionary.get(key) > counter) {
        		 counter = dictionary.get(key);
        		 majorityElement = key;
        	 }
         }
         return majorityElement;
     }

     // 189. Rotate Array
    
     // 198. House Robber
     public int rob(int[] nums) {
    	 int len = nums.length;
    	 if(len==1) return nums[0];
         int[] dp = new int[len];
         dp[0] = nums[0];
         dp[1] = Math.max(dp[0], nums[1]);
         for(int i=2;i<len;i++) {
        	 dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
         }
         return dp[len-1];
     }

     // 200. Number of Islands
     public int numIslands(char[][] grid) {
    	 int m=grid.length;
    	 int n=grid[0].length;
         int num=0;
         for(int i=0;i<m;i++) {
        	 for(int j=0;j<n;j++) {
        		 if(grid[i][j]=='1') {
        			 changeIslands(grid, i ,j,m,n);
        			 num++;
        		 }
        	 }
         }
         return num;
     }
     
     private void changeIslands(char[][] grid, int r, int c,int m, int n)
     {
    	 grid[r][c]='0';
    	 for(int i=r-1;i<=r+1;i++) {
    		 if(i<0 || i>=m || i==r) continue;
    		 if(grid[i][c]=='1') {
    			 changeIslands(grid, i, c, m,n);
    		 }
    	 }
    	 
    	 for(int j=c-1;j<=c+1;j++) {
    		 if(j<0 || j>=n || j==c) continue;
    		 if(grid[r][j]=='1') {
    			 changeIslands(grid, r, j, m,n);
    		 }
    	 }
     }

     // 204. Count Primes
     public int countPrimes(int n) {
    	 if(n<=1) return 0;
    	 
         int[] res = new int[n];
         for(int i=2;i<n;i++) {
        	 res[i] = 1;
         }
         for(int i=2;i<n;i++) {
        	 if(res[i]==0) continue;
        	 int counter=2;
        	 while(counter*i<n) {
        		 res[i*counter] = 0;
        		 counter++;
        	 }
         }
         
         return Arrays.stream(res).filter(x -> x == 1).toArray().length;
     }

     // 209. Minimum Size Subarray Sum
     public int minSubArrayLen(int target, int[] nums) {
    	 int len = nums.length;
         if (nums[0] >= target) return 1;
     	 int res = len+1;
     	 int sum = nums[0];
     	 
     	 int left =0;
     	 int right =0;
     	 for(int i=1; i<len; i++) {
     		 if(nums[i]>=target) return 1;
     		 right = i;
     		 sum+=nums[i];
     		 if (sum>=target) {
     			 while(sum>=target && left<right) {
     				 sum-=nums[left];
     				 left++;
     			 }
     			 res = Math.min(res, right-left+2);
     		 }
     	 }
     	
     	 return res == len+1 ? 0 : res;
     }

     // 213. House Robber II
     public int rob2(int[] nums) {
    	 int len = nums.length;
    	 if(len==1) return nums[0];
         int[] dp = new int[len];
         // from 1
         dp[0] = nums[0];
         dp[1] = nums[0];
         for(int i=2;i<len-1;i++) {
        	 dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
         }
         
         int[] dp2 = new int[len];
         // from 1
         dp2[0] = 0;
         dp2[1] = nums[1];
         for(int i=2;i<len;i++) {
        	 dp2[i] = Math.max(dp2[i-1], dp2[i-2]+nums[i]);
         }
         
         return Math.max(dp[len-2], dp2[len-1]);
     }

     // 215. Kth Largest Element in an Array
     public int findKthLargest(int[] nums, int k) {
    	 Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
    	 for(var v:nums) {
    		 queue.add(v);
    	 }
    	 for(int i=1;i<k;i++) {
    		 queue.poll();
    	 }
    	 return queue.peek();
     }

     // 216. Combination Sum III
     public List<List<Integer>> combinationSum3(int k, int n) {
         List<List<Integer>> res = new  ArrayList();
         
         combinationSum3(k, n, res, new ArrayList<>(), 1);
         
         return res;
     }
     
     public void combinationSum3(int k, int target, List<List<Integer>> res, List<Integer> temp, int val) {
         
    	 int sumVal = temp.stream().mapToInt(i -> i).sum();
         
    	 int size = temp.size();
         System.out.println(sumVal + "val: "+ val+ ", size: "+size);
    	 
    	 
    	 if(size == k) {
    		 if(sumVal == target) {
    			 res.add(new ArrayList<Integer> (temp));
    		 }
    		 return;
    	 }
         if(sumVal > target || size > k || val>target || val > 9) return;
         temp.add(val);
    	 combinationSum3(k, target, res, temp, val+1);
    	 temp.remove(temp.size()-1);
         
    	 if(val+1 <=9){
             combinationSum3(k, target, res, temp, val+1);
         }
     }

     // 217. Contains Duplicate
     public boolean containsDuplicate(int[] nums) {
         var set = new HashSet<Integer> ();
         for(var v : nums) {
        	 if(!set.add(v)) {
        		 return true;
        	 }
         }
         return false;
     }

	 // 219. Contains Duplicate II
     public boolean containsNearbyDuplicate(int[] nums, int k) {
    	 int len=nums.length;
    	 var table =  new Hashtable<Integer, Integer>();
    	 for(int i=0;i<len;i++) {
    		 if(table.containsKey(nums[i])) {
    			 if(i-table.get(nums[i])<=k) return true;
    			 table.replace(nums[i], i);
    		 }else {
    			 table.putIfAbsent(nums[i],i);
    		 }
    	 }
    	 return false;
     }
     
     //	220. Contains Duplicate III

     // 221. Maximal Square
     
     public int maximalSquare(char[][] matrix) {
    	 int m = matrix.length;
         if (m == 0) return 0;
         var n = matrix[0].length;

         var dp = new int[m][n];

         var global = 0;

         for (int i = 0; i < m; i++) {
             dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
             global = Math.max(global,dp[i][0]);
         }

         for (int j = 0; j < n; j++) {
             dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
             global = Math.max(global, dp[0][j]);
         }

         for (int i = 1; i < m; i++) {
             for (int j = 1; j < n; j++) {
                 if (matrix[i][j] == '1') {
                     dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                     global = Math.max(global, dp[i][j]);
                 }
             }
         }

         return global * global;
     }
}

