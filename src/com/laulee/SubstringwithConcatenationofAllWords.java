package com.laulee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by laulee on 2020/7/15.
 *
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 *
 * 输入：
 *   s = "wordgoodgoodgoodbestword",
 *   words = ["word","good","best","word"]
 * 输出：[]
 *
 * 30题
 */
public class SubstringwithConcatenationofAllWords {

    public static void main(String[] args) {
//        String s = "barfoothefoobarman";
//        String[] words = {"foo","bar"};
        String s = "wordgoodgoodgoodbestword";
         String[] words = {"word","good","best","word"};
//        String s = "aabbaabbaabb";
//        String[] words = {"bb","aa","bb","aa","bb"};
        SubstringwithConcatenationofAllWords ss = new SubstringwithConcatenationofAllWords();
        List<Integer> index = ss.findSubstring(s, words);

        System.out.println(index);
//
//        Map<String,Integer> map = new HashMap();
//        map.put("aa",3);
//        map.put("bb",4);
//
//        Map map1 = new HashMap();
//        map1.put("aa",4);
//        map1.put("bb",3);
//
//        System.out.println(map.hashCode() == map1.hashCode());
//

    }


    /**
     *     假设words数组长度为L，word单词长度为WL,遍历字符串s, 下标记做i，
     *     需要比对的单词起始坐标则为 [i, i+WL, i+2WL ... i+(L-1)*WL]
     *     如果i满足条件，各个单词的第k位之和一定相等
     *     即：words[0][k] + words[1][k] + ... + words[L-1][k] == s[i + k] + s[i+WL + k] + ... + s[i+(L-1)*WL + k]
     *     反之，若对于i，满足后者条件的i则可能为正确结果，这个时候直接校验即可。
     *
     *     总结：先找出符合特征的下标，再对符合下标的结果进行校验，完全符合则输出。
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring2(String s, String[] words) {

        return null;
    }

    /**
     * 思路：滑动窗口+hashmap
     * 时间复杂度：两个for循环：假如s的长度位n，word的个数为m,那么时间复杂度即为 O(m*n)
     * 空间复杂度：2*O(m)
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> index = new ArrayList<>();
        if (s == null || words == null || s.length() == 0 || words.length == 0 || words[0].length() > s.length()) {
            return index;
        }


        Map<String,Integer> wordsMap = new HashMap<>();
        int wordNum = words.length;
        //使用hashmap解决无法计算words中是否匹配子串的问题，不计顺序的情况下，words中每个word组合方式很多，太复杂，间接转换统计key的个数，以及比较每个key出现的次数（可重复）。
        for (int i = 0; i < wordNum; i++) {
            String word = words[i];
            Integer val = wordsMap.getOrDefault(word, 0);
            wordsMap.put(word, val + 1);
        }

        int wordLen = words[0].length();


        for (int i = 0; i < s.length() - wordLen * wordNum + 1;i++) { // - wordLen * wordNum 小于word长度的子串肯定不符合条件。

            //应该获取的子串的长度 == words子串的长度 == wordLen * wordNum
            String subStr = s.substring(i,i + wordLen * wordNum); //取出与words等长的字符，并对该字串进行遍历验证是否适合word中的内容
            Map<String,Integer> subStrMap = new HashMap<>();
            boolean flag = true;
            for (int j = 0; j < subStr.length(); ) {
                String substring = subStr.substring(j, j += wordLen);
                Integer subVal = subStrMap.getOrDefault(substring, 0);
                subStrMap.put(substring,subVal+1);

                //每次装入之后检查装入的数据是否符合规范。否则返回。但是这么返回之后并不能确定当前子串最终是否适合word。所以设立一个标记位，只要
                //该标记没有触发，那么该数据就是合法的。
                if (!wordsMap.containsKey(substring) || wordsMap.get(substring) < subStrMap.get(substring)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                index.add(i);
            }

        }

        return index;
    }
}
