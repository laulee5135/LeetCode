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
 * 难点：每个分支上每个数字怎么知道是否已经使用过了。数字的状态。解决：boolean数组，长度等于要遍历数据数组长度。
 * 解决方案：DFS+回溯   （对应的还有广度优先遍历BFS）
 */
public class Permutations {


    public static void main(String[] args) {
        Permutations permutations = new Permutations();

        int[] nums = {1,2,3};
        List<List<Integer>> permute = permutations.permute(nums);
        System.out.println(permute);
    }


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> finalList = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return finalList;
        }
        List<Integer> list = new ArrayList<>();
        boolean[] used = new boolean[nums.length]; //状态变量
        dfs(nums,used,0,list,finalList);
        return finalList;
    }

    private static void dfs(int[] nums,boolean[] used,int dept,List<Integer> path,List<List<Integer>> finalList){

        if (dept == nums.length) { //当遍历的深度==集合的长度，说明已经当前路径已经完成。结束当前路径搜索。
            finalList.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true; //标记当前节点当前路径已经使用过了，因为当前层的i到下层不能再使用了。
            dfs(nums,used,dept+1,path,finalList);
            //下面这两行代码发生 「回溯」，回溯发生在从 深层结点 回到 浅层结点 的过程
            //这里的回溯是path，path循环使用了。
            used[i] = false; //当前层i使用完成之后，释放，以备其他的分支可用。
            path.remove(path.size()-1); //path是有效数据收集器，循环使用，所以每次有效数据被归到最终结果集后需要删除，防止带到下个节点出现重复
        }
    }
}
