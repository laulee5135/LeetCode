package com.laulee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47.
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 */
public class Permutations2 {


    public static void main(String[] args) {
        Permutations2 permutations = new Permutations2();

        int[] nums = {2,2,1,1};
        List<List<Integer>> permute = permutations.permuteUnique(nums);
        System.out.println(permute);
    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> finalList = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return finalList;
        }
        List<Integer> list = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums,used,0,list,finalList);
        return finalList;
    }

    private void dfs(int[] nums, boolean[] used,int dept,List<Integer> path,List<List<Integer>> finalList){
        if (dept == nums.length) {
            finalList.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            //剪枝
            //i < nums.length-1 && nums[i] == nums[i+1]当前层当前节点执行完毕之后判断下个节点是否和当前数据一样，如果一样跳过（剪枝）【因为同一层相邻的两个节点下面的分支是一样的。】
            // used[i+1] == false : （第一个分支）撤销到根节点时（是1），path中已经没有数据了（每次回溯被remove），所以第一层的第二个1还是可以进入的，（根据之前的判断得知此时的1和第一层第一个1是重复的，所以这个要跳过）
            if((i < nums.length-1 && nums[i] == nums[i+1]) && used[i+1] == false){ //used[i+1] == false最终的目的是根部第一层下一个重复数字不会进入遍历。
                continue;
            }
            //另外的写法：//used[i-1] == false :used[i-1]是因为nums[i-1]在回退的过程刚刚被撤销了选择
            //if((i > 0 && nums[i] == nums[i-1]) && used[i-1] == false){
            path.add(nums[i]);
            used[i] = true; //为了防止当前分支在下层再次使用该数据，为true表示已经用过
            dfs(nums,used,dept+1,path,finalList);
            used[i] = false; //当前分支使用完毕之后，释放。
            path.remove(path.size()-1);

        }
    }


}
