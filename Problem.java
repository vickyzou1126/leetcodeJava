package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
}
