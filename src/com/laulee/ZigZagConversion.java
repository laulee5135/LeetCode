package com.laulee;

/**
 * Created by laulee on 2020/4/3.
 * 6题
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * 示例 1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 * 来源：力扣（LeetCode）
 */
public class ZigZagConversion {

    public static void main(String[] args) {
        ZigZagConversion zigZagConversion = new ZigZagConversion();
        String s = "LEETCODEISHIRING";
        int numRows = 4;
        String convert = zigZagConversion.convert(s, numRows);
        System.out.println(convert.equals("LDREOEIIECIHNTSG"));
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        int step = numRows * 2 - 2; // 间距,第一行和最后一行的间距相同 ，找规律发现是 N - 2 + N
        int index = 0;// 记录s的下标
        int len = s.length();
        int add = 0; // 真实的间距
        String ret = "";
        for (int i = 0; i < numRows; i++){ // i表示行号
            index = i;
            add = i * 2;
            while (index < len){ //超出字符串长度计算下一层
                ret += s.charAt(index); // 当前行的第一个字母
                add = step - add;// 第一次间距是step -2*i，第二次是2*i,
                index += (i == 0 || i == numRows-1) ? step : add; // 0行和最后一行使用step间距，其余使用add间距
            }
        }
        return ret;

    }

}
