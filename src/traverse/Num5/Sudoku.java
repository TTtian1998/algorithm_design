package Traverse.Num5;

import java.time.Instant;
import java.util.Arrays;

/**
 * @author 田泽鑫
 * @date 2019/12/25
 * @description： 数独 构造一个9X9矩阵表示棋盘
 * 从做到右开始检查每一个没有填值的空格
 * 在第一个空格放置从1到9放置数字，检查是否可以放在这个位置
 * 可以，则继续放置下一个空位，否则，退回去，更换第一个空格的数字。
 * 直到放置完所有空格
 */
public class Sudoku {
    private int[][] metrics;

    public Sudoku(int[][] metrics) {
        this.metrics = metrics;
    }

    public static void main(String[] args) {

        int[][] metrics = {
                {0, 6, 9, 0, 0, 5, 0, 2, 7},
                {0, 0, 0, 3, 0, 8, 0, 0, 1},
                {0, 0, 2, 0, 0, 0, 0, 0, 8},
                {3, 8, 0, 6, 7, 4, 2, 5, 9},
                {9, 0, 0, 8, 3, 0, 6, 1, 4},
                {6, 2, 4, 0, 0, 9, 0, 0, 0},
                {4, 9, 3, 2, 5, 7, 1, 8, 6},
                {0, 7, 0, 9, 0, 1, 0, 0, 5},
                {0, 1, 8, 4, 6, 3, 0, 9, 2}
        };

        Sudoku sudoku = new Sudoku(metrics);
        long start = Instant.now().toEpochMilli();
        sudoku.backTrace(0, 0);
        long end = Instant.now().toEpochMilli();
        System.out.println("耗时(ms)：" + (end - start));

    }

    public void backTrace(int i, int j) {
        // 最后一个位置,那么找到了一个解
        if (i == 8 && j == 9) {
            print(metrics);
            return;
        }

        // 当前放置第i行的第10个数字,需要换行
        if (j > 8) {
            i = i + 1;
            j = 0;
        }

        //如果是0，说明需要放置一个数字，不是0 继续放置下一个位置
        if (metrics[i][j] == 0) {
            for (int k = 1; k <= 9; k++) {
                if (check(i, j, k)) {
                    metrics[i][j] = k;
                    backTrace(i, j + 1);
                }
                metrics[i][j] = 0;
            }
        } else {
            backTrace(i, j + 1);
        }
    }
    private boolean check(int row, int col, int number){
        //检查行
        for (int i = 0;i<9;i++){
            if (i==col){
                continue;
            }
            if (metrics[row][i]==number){
                return false;
            }
        }
        //检查列
        for (int i = 0;i<9;i++){
            if (i==row){
                continue;
            }
            if (metrics[i][col]==number){
                return false;
            }
        }
        int tempRow = row / 3;
        int tempCol= col / 3;
        //检查小九宫格
        for (int i = 0;i<3;i++){
            for (int j =0;j<3;j++){
                if (metrics[tempRow*3+i][tempCol*3+j]==number){
                    return false;
                }
            }
        }
        return true;
    }
    //打印
    private void print(int[][] metrics) {
        for (int i = 0; i < metrics.length; i++) {
            System.out.println(Arrays.toString(metrics[i]));
        }
    }

}
