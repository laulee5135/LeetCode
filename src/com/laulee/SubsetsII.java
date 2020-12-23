package com.laulee;

import java.util.*;

/**
 * 90.
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 * Created by laulee on 2020/8/26.
 */
public class SubsetsII {

    public static void main(String[] args) {
        SubsetsII subsetsII = new SubsetsII();
        int[] nums = {3,9};
        List<List<Integer>> lists = subsetsII.subsetsWithDup2(nums);
        System.out.println(lists);
    }


    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> finalList = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            finalList.add(new ArrayList<>());
            return finalList;
        }

        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                map.put(num,map.get(num)+1);
            }else{
                map.put(num,1);
            }
        }
        final List<Integer> subSet = new ArrayList<>();
        map.forEach((key,val)->{
            int temp = finalList.size();
            for(int m=0;m<temp;m++){
                List<Integer> sub = subSet;
                sub = finalList.get(m);
                for(int k=1;k<=key;k++){
                    sub.add(key);
                    finalList.add(sub);
                }
            }
        });
        return finalList;
    }


    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> finalList = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            finalList.add(new ArrayList<>());
            return finalList;
        }
        List<Integer> col = new ArrayList<>();
        Arrays.sort(nums); //进行排序，后续如果某个分支下层比上
        boolean[] used = new boolean[nums.length]; //相当于一个锁
        dfs(nums,used,0,nums[0]-1,col,finalList);  //upNum num[0] >
        finalList.add(new ArrayList<>());// 空集是所有集合的子集
        return finalList;
    }

    private static void dfs(int[] nums,boolean[] used,int dept,int upNum,List<Integer> col,List<List<Integer>> finalList){
        for (int i = 0; i < nums.length; i++) {

            if(i>0 && nums[i] == nums[i-1] && used[i-1] == false){ //去重;
                continue;
            }
            if(used[i]){ //当前分支如果上一层使用过了，那么下一层将不再使用该数
                continue;
            }
            //需要将当前分支的上层数据透传到下层使用。
            if(nums[i] < upNum){  //当前分支上层和下层的数据需要进行比较，因为已经做了排序，所以如果当前分支下层比上层小，那么就停止遍历。（从树形图得知）
                continue;
            }
            col.add(nums[i]);
            used[i] = true;
            finalList.add(new ArrayList<>(col));

            dfs(nums,used,dept+1,nums[i],col,finalList);
            used[i] = false; //完成任务，释放
            col.remove(col.size()-1);

        }
    }

}
