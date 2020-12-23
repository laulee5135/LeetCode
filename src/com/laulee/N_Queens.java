package com.laulee;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 51.
 * n皇后问题研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回所有不同的n皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * Created by laulee on 2020/9/21.
 */
public class N_Queens {

    public static void main(String[] args) {
        N_Queens nQueens = new N_Queens();
        List<List<String>> lists = nQueens.solveNQueens(4);
    }

    public List<List<String>> solveNQueens(int n) {

        List<List<String>> finalList = new ArrayList<>();
        if (n == 0) {
            return finalList;
        }

        Boolean[] mainDiagonal = new Boolean[n];
        Boolean[] subDiagonal = new Boolean[n];

        dfs(n,finalList);
        return finalList;
    }

    public void dfs(int n, List<List<String>> finalList){
        for (int i = 0; i < n; i++) {
            System.out.println("");
        }


    }
}
