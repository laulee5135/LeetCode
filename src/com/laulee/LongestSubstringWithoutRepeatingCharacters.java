package com.laulee;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by laulee on 2020/6/24.
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 思路：滑动窗口
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {

        LongestSubstringWithoutRepeatingCharacters lo = new LongestSubstringWithoutRepeatingCharacters();
//        int len = lo.lengthOfLongestSubstring2("abcabcbb");
        int len = lo.lengthOfLongestSubstring2("abba");
//        int len = lo.lengthOfLongestSubstring2("pwwkew");
//        int len = lo.lengthOfLongestSubstring2("abcdefg");
//                int len = lo.lengthOfLongestSubstring2("davdf");
        System.out.println(len);

    }


    /**
     * 滑动窗口 （窗口：left<-->i）
     * 时间复杂度 O(n)
     * 空间复杂度O(n)
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int left = 0;
        int max = 0;
        HashMap<Character,Integer> h = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (h.containsKey(c)) {   //h.get(c)当前值第一次出现的位置
                //滑动窗口是指两个指针移动（left，i）并不是对map增删操作。left只能向右移动
                left =  left > h.get(c) + 1 ? left : h.get(c) + 1  ; //比较大小是因为无论前面出现过几个重复的值，都当应该以最右边的left为准，left只能前进不能后退。 +1，指向下一个
            }

            h.put(c,i);
            max = max > i - left+1 ? max : i - left+1;  //+1，下标变长度
        }

       return max;

    }




    /**
     * 暴力解法
     * 时间复杂度：
     * 空间复杂度：
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(j) == c) {
                    max = max > j-i ? max : j-i;
                }
            }
        }
        return max;
    }


}
