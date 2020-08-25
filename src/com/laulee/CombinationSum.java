package com.laulee;

import sun.security.util.Length;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by laulee on 2020/3/23.
 * 39题
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 * 来源：力扣（LeetCode）
 *
 * 误解点：不同结果集中元素相同，但顺序不同不算是结果集
 * 所以要去重
 */
public class CombinationSum {

    List<List<Integer>> finalResults = new ArrayList<>();

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        int[] candidates = {8,7,4,3};
        int target = 7;
        List<List<Integer>> lists = combinationSum.combinationSum(candidates, target);
        System.out.println(lists);

    }

    /**
     * 思想：递归回溯+剪枝
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        Arrays.sort(candidates); //排序，便于剪枝
        Stack<Integer> pre = new Stack<Integer>();
        int len = candidates.length;
        dfs(candidates, target,len, pre,0);
        return finalResults;
    }

    /**
     *
     * @param candidates
     * @param target 每次新组合的数字
     * @param len
     * @param pre 正确组合收集器
     * @param index 每个分支新组合的数开始搜索的位置，比如5下面有3个分支，第一个分支中已经减过2，但是第二个分支中（在整个对5的组合）就不能再减2了，所以
     *              这时可以让下一个新组合的数搜索位置和上一个新组合数的当前搜索位置保持一直即可。
     *              误解点：同一个分支数字可以重复出现，不同分支间数字不能重复。
     */
    public void dfs(int[] candidates, int target,int len, Stack<Integer> pre,int index){   //Depth First Search
        for (int i = index; i < len; i++) {
            int candidate = candidates[i];
            if (target >= candidate) {
                pre.push(candidate);
                int result = target - candidate;
                if (result == 0) {
                    finalResults.add(new ArrayList<>(pre));
                    pre.pop(); //完成使命，进行回收，不能带到上级节点的兄弟节点中。
                    break;
                }else{ //>0
                    dfs(candidates, result,len,pre,i);
                    pre.pop(); //走不通就退一步，回溯。 执行到叶子结点如果还没有符合条件（即下面结果为负数的处理结果），那么说明该分支不符合条件，清除垃圾。
                }

            }else{  //处理结果为负数
                break;
            }
        }
    }

}
