package org.ming.leetcodeoj.sort;

/**
 * 最长上升子序列
 * @author: LeoLee
 * @date: 2019/11/25 16:58
 */
public class Leetcodeoj_300_20191125 {

    /*
        给定一个无序的整数数组，找到其中最长上升子序列的长度。
        示例:
        输入: [10,9,2,5,3,7,101,18]
        输出: 4
        解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
        说明:
        可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
        你算法的时间复杂度应该为 O(n2) 。
        进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static void main(String[] args) {
        Leetcodeoj_300_20191125 leetcodeoj = new Leetcodeoj_300_20191125();
//        int[] array = new int[]{1,2,3,4,5,6,10,2,3,4};
        int[] array = new int[]{10,9,2,5,3,7,101,18};
        System.out.println(leetcodeoj.lengthOfLIS_2(array));

    }

    /**
     * 动态规划
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return 1;
        }

        int[] dp = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            dp[i] = 1;
        }
        for(int i= 0;i < nums.length;i++){
            dp[i]= 1;
            for(int j = 0;j < i;j++){
                if(nums[j] < nums[i] && dp[j] >= dp[i]){
                    dp[i] = dp[j] + 1;
                }
            }
        }
        int max = 0;
        for( int i = 0; i < nums.length; i++){
            if(max < dp[i]){
                max = dp[i];
            }
        }
        return max;
    }

    // NlogN
    public int lengthOfLIS_2(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return 1;
        }
        // 新数组
        int[] newArray = new int[nums.length+1];
        // len=1 的原因是默认的自增子序列为1
        int len = 1,left,right,mid;
        newArray[len] = nums[0];
        // N
        for(int i= 1;i < nums.length;i++){
            if(nums[i] > newArray[len]){
                len ++;
                newArray[len] = nums[i];
            }else{
                left = 1; right = len;
                // log2N
                while(left<=right){
                    mid = (left+right)/2 ;
                    if(newArray[mid]<nums[i]) {
                        left = mid+1;
                    } else {
                        right = mid-1;
                    }
                }
                newArray[left] = nums[i];
            }
            print(newArray);
        }
        return len;
    }


    /**
     * 脑子不好，打印来搞
     * @param array
     */
    public void print(int[] array){
        for(int i=0;i<array.length;i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
