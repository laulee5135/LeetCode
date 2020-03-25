package com.laulee;

import java.util.*;

/**
 * Created by laulee on 2020/3/23.
 *
 * 20题
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 *
 * 来源：力扣（LeetCode）
 */
public class ValidParentheses {

    public static void main(String[] args) {
//        System.out.println(isValid("()[]{}"));
        System.out.println(isValid1("(("));
    }

    /**
     * 优化
     * @param s
     * @return
     */
    public static boolean isValid1(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Map<Character,Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                deque.push(c);
            }else {
                if (deque.isEmpty() || !deque.pop().equals(map.get(c))) {
                    return false;
                }
            }
        }
        return deque.isEmpty();
    }


    public static boolean isValid(String s) {

        if (s == null || s.length() == 0) {
            return true;
        }
        if (s.length() % 2 != 0) {
            return false;
        }

        Stack<Character> st = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                st.push(c);
            }else{
                if (st.empty()) {
                    return false;
                }
                switch (c){
                    case ')':
                        c = '(';
                        break;
                    case ']':
                        c = '[';
                        break;
                    case '}':
                        c = '{';
                        break;
                }

                if (st.pop() == c) {
                    continue;
                }else {
                    return false;
                }

            }
        }
        if (st.empty()) {
            return true;
        }
        return false;
    }
}
