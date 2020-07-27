package com.laulee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *  
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * Created by laulee on 2020/7/21.
 * 对数组进行排序。
 * 遍历排序后数组：
 * 若 nums[i]>0nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 00，直接返回结果。
 * 对于重复元素：跳过，避免出现重复解
 * 令左指针 L=i+1，右指针 R=n-1，当 L<R 时，执行循环：
 * 当 nums[i]+nums[L]+nums[R]==0nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,RL,R 移到下一位置，寻找新的解
 * 若和大于 0，说明 nums[R] 太大，R 左移
 * 若和小于 0，说明 nums[L] 太小，L 右移
 *
 */
public class Sum3 {

    public static void main(String[] args) {
        Sum3 sum3 = new Sum3();
        int[] nums = {-1, 0, 1, 2, -1, -4};
//        int[] nums = {-2,0,0,2,2};
        List<List<Integer>> lists = sum3.threeSum(nums);
        System.out.println(lists);
    }

    /**
     * 重复解是因为数组中有重复的数字造成的。所以先排序，把所有的重复解都整合到一起，再通过寻找下一个left或者right时进行去重
     *
     * 如果确定了第一个元素nums[i]，那么寻找剩下的两个数字，就转换为两数只和了。
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return list;
        }
        Arrays.sort(nums);
        int len = nums.length;

        for (int i = 0; i < len; i++) {

            int curr = nums[i];
            if (curr > 0) {
                return list;
            }

            //如果有重复值，跳过，只需要计算一次
            if (i > 0 && curr == nums[i-1]){
                continue;
            }

            int left = i+1,right = len-1;

            while (left < right){
                int tmp = curr + nums[left] + nums[right];
                if (tmp == 0) {
                    List<Integer> lis = new ArrayList<>();
                    lis.add(curr);
                    lis.add(nums[left]);
                    lis.add(nums[right]);
                    list.add(lis);
                    //处理左指针右边重复数据
                    while(left < right && nums[left] == nums[left+1]){
                        left ++;
                    }
                    //处理右指针左边重复数据
                    while (left < right && nums[right] == nums[right-1]) {
                        right --;
                    }
                    left ++;
                    right --;
                }else if(tmp > 0){
                    right --;
                }else if(tmp < 0){
                    left ++;
                }
            }
        }

        return list;
    }


    public List<List<Integer>> threeSum2(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) { // 每个人
            for (int j = i + 1; j < nums.length - 1; j++) { // 依次拉上其他每个人
                for (int k = j + 1; k < nums.length; k++) { // 去问剩下的每个人
                    if (nums[i] + nums[j] + nums[k] == 0) { // 我们是不是可以一起组队
                        List<Integer> lis = new ArrayList<>();
                        lis.add(nums[i]);
                        lis.add(nums[j]);
                        lis.add(nums[k]);
                        res.add(lis);
                    }
                }
            }
        }

        return res;
    }

}
