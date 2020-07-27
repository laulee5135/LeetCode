package com.laulee;

import java.util.Arrays;

/**
 * 16.
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 *  
 *
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *  
 *
 * 提示：
 *
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 *
 * Created by laulee on 2020/7/27.
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
        ThreeSumClosest threeSumClosest = new ThreeSumClosest();
//        int[] nums = {-1,2,1,-4};
//        int[] nums = {0,2,1,-3};
        int[] nums = {-3,-2,-5,3,-4};
//        int target = 1;
        int target = -1;
        int i = threeSumClosest.threeSumClosest(nums, target);
        System.out.println(i);
    }

    /**
     * 排序+双指针
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestNum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length; i++) {
            int left = i+1, right = nums.length-1;
            while (left < right) {
                int tmp = nums[i] + nums[left] + nums[right];
                if(tmp == target){
                    return tmp;
                }else if (Math.abs(target-tmp) < Math.abs(target-closestNum)) {
                    closestNum = tmp;
                }

                if(tmp < target){
                    left ++;
                }else if(tmp > target){
                    right --;
                }

            }
        }

        return closestNum;
    }
}
