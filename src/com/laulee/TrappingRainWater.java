package com.laulee;

import javax.swing.text.html.HTML;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by laulee on 2020/3/26.
 *
 * 42题
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * 来源：力扣（LeetCode）
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        int[] height = new int[]{527,40,913,13};  //数组值太大且太长，无法执行。

        int trap = trappingRainWater.trap(height);
        System.out.println(trap);
    }

    public int trap(int[] height) {
        if(height.length == 0){
            return 0;
        }
        int max = Arrays.stream(height).max().getAsInt();
        List<Integer> collect = Arrays.stream(height).boxed().collect(Collectors.toList());
        Deque<Integer> heightQ = new ArrayDeque<Integer>(collect);

        return dfs(heightQ,max);
    }

    public int dfs(Deque<Integer> heightQ,int max){
        for (int j = heightQ.size(); j > 0; j--) {
            if (heightQ.peekFirst() <= 0) {
                heightQ.pollFirst();
                continue;
            }else{
                break;
            }
        }

        for (int j = heightQ.size(); j > 0; j--) {
            if (heightQ.peekLast() <= 0) {
                heightQ.pollLast();
                continue;
            }else{
                break;
            }
        }

        int count = (int)heightQ.stream().filter(ele -> {
            return ele == 0 || ele < 0;
        }).count();

        if (max != 0 ) {
            Deque<Integer> deque = new ArrayDeque<Integer>(heightQ.size());
            heightQ.forEach(h -> {
                deque.add(--h);
            });
            count += dfs(deque,--max);
        }
        return count;
    }

}
