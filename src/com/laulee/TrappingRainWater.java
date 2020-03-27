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
        int[] height = new int[]{ 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};  //数组值太大且太长，无法执行。

        int trap = trappingRainWater.trap1(height);
        System.out.println(trap);
    }

    public int trap(int[] height) {
        if(height.length == 0){
            return 0;
        }
        int max = Arrays.stream(height).max().getAsInt();
        List<Integer> collect = Arrays.stream(height).boxed().collect(Collectors.toList());
        Deque<Integer> heightQ = new ArrayDeque<Integer>(collect);

        return dfs1(heightQ,max);
    }


    /**
     * AC
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int sum = 0;
        //最两端的列不用考虑，因为一定不会有水。所以下标从 1 到 length - 2
        for (int i = 1; i < height.length - 1; i++) {
            int max_left = 0;
            //找出左边最高
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > max_left) {
                    max_left = height[j];
                }
            }
            int max_right = 0;
            //找出右边最高
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > max_right) {
                    max_right = height[j];
                }
            }
            //找出两端较小的
            int min = Math.min(max_left, max_right);
            //只有较小的一段大于当前列的高度才会有水，其他情况不会有水
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;

    }



    /**
     * lc代码
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        int sum = 0;
        int max = Arrays.stream(height).max().getAsInt();
        for (int i = 1; i <= max; i++) {
            boolean isStart = false; //标记是否开始更新 temp
            int temp_sum = 0;
            for (int j = 0; j < height.length; j++) {
                if (isStart && height[j] < i) {
                    temp_sum++;
                }
                if (height[j] >= i) {
                    sum = sum + temp_sum;
                    temp_sum = 0;
                    isStart = true;
                }
            }
        }
        return sum;
    }


    /**
     * 第二个版思路：也是按行查找，每行小于等于当前高度的肯定有水。
     * @param heightQ
     * @param max
     * @return
     */
    public int dfs1(Deque<Integer> heightQ, int max){
        int sum = 0;
        for (int i = 0; i < max; i++) {

            //以下两个for是为了每次去掉上一层已经计算过有水的位置。，每行开头或结果小于当前高度删除。
            for (int j = heightQ.size(); j > 0; j--) {
                if (heightQ.peekFirst() <= i) {
                    heightQ.pollFirst();
                    continue;
                }else{
                    break;
                }
            }

            for (int j = heightQ.size(); j > 0; j--) {
                if (heightQ.peekLast() <= i) {
                    heightQ.pollLast();
                    continue;
                }else{
                    break;
                }
            }

            final int m = i;
            int count = (int)heightQ.stream().filter(ele -> {
                return ele <= m;
            }).count();

            sum += count;
        }

        return sum;
    }


    /**
     * 第一版
     * @param heightQ
     * @param max
     * @return
     */
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
