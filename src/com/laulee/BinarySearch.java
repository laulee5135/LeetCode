package com.laulee;

/**
 * Created by laulee on 2020/5/13.
 *
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 *
 * 示例 1:
 *
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 示例 2:
 *
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 *  
 *
 * 提示：
 *
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 *
 */
public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();

        int[] nums = {-1,0,3,5,9,12};

        int target = 9;
//        int search = binarySearch.search(nums, target);
//        System.out.println(search);
        System.out.println(5/2);
    }

    public int search(int[] nums, int target) {

        int left = 0, right = nums.length - 1;
        //while中为什么是<=: 如果是<=那么当前while的终止条件是[right+1,right]（也就是[3,2]）,如果是while中是》<,那么while的终止条件是[right,right]
        //也就是[2,2]，所以在当下while中会漏掉right（2）的情况，所以要<=。
        while (left <= right) {
            int pivot = left + (right - left) / 2;   //等同于(left + right) / 2，但能有效防止left + right太大直接相加导致内存溢出。
            if (target == nums[pivot]){
                return pivot;
            }

            if (target < nums[pivot]) {
                right = pivot - 1;  //这里和下面为什么要+1或者-1，因为如上所述是在一个双闭区间[搜索区间]中查找，临界值已经查找过了，所以要+1 or -1
            }else{
                left = pivot + 1;
            }

        }

        return -1;
    }


}
