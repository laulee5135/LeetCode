package com.laulee;

import java.util.ArrayList;
import java.util.List;

/**
 * 46.
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * Created by laulee on 2020/7/21.
 */
public class Permutations {

    public static void main(String[] args) {
        Permutations permutations = new Permutations();

        int[] nums = {1,2,3};
        List<List<Integer>> permute = permutations.permute(nums);
        System.out.println(permute);
    }


    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }

        return list;
    }
}
