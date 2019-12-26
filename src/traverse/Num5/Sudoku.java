package Traverse.Num5;

import java.time.Instant;
import java.util.Arrays;

/**
 * @author 田泽鑫
 * @date 2019/12/25
 * @description 数独 https://juejin.im/post/5c8f145d6fb9a070e7635e29
 */
public class Sudoku {
    private int[][] metrics;

    public Sudoku(int[][] metrics) {
        this.metrics = metrics;
    }


    public void backTrace(int i, int j) {
        // 最后一个位置了，那么找到了一个解
        if (i == 8 && j == 9) {
            print(metrics);
            return;
        }

        // 当前放置第i行的第10个数字，那么需要换行了
        if (j > 8) {
            i = i + 1;
            j = 0;
        }

        //如果是0，说明需要放置一个数字，不是0 继续放置下一个位置
        if (metrics[i][j] == 0) {
            for (int k = 1; k <= 9; k++) {
                metrics[i][j] = k;
                if (check(i, j, k)) {
                    check(i, j, k);
                    backTrace(i, j + 1);
                }
                metrics[i][j] = 0;
            }
        } else {
            backTrace(i, j + 1);
        }
    }


    //检查的时候把行 列 方块，都看做了一个四个坐标定位的二维数组
    //（这里完全可以遍历循环，不要这样写，虽然看着简洁，影响理解）
    private boolean check(int row, int col, int number) {
        //检查行
        int rowStartX = row, rowStartY = 0, rowEndX = row, rowEndY = 8;
        //检查列
        int colStartX = 0, colEndX = 8, colStartY = col, colEndY = col;
        //检查方块
        int x = row / 3, y = col / 3, mStartX = 3 * x, mStartY = 3 * y, mEndX = mStartX + 2, mEndY = mStartY + 2;

        return !(hasNumber(rowStartX, rowStartY, rowEndX, rowEndY, row, col, number)
                || hasNumber(colStartX, colStartY, colEndX, colEndY, row, col, number)
                || hasNumber(mStartX, mStartY, mEndX, mEndY, row, col, number));
    }

    //检查的时候 要排除掉 当前位置的数字。
    private boolean hasNumber(int startX, int startY, int endX, int endY, int row, int col, int number) {
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                if (x == row && y == col) {
                    continue;
                }
                if (metrics[x][y] == number) {
                    return true;
                }
            }
        }
        return false;
    }


    private void print(int[][] metrics) {
        for (int i = 0; i < metrics.length; i++) {
            System.out.println(Arrays.toString(metrics[i]));
        }
        System.out.println("================");
    }

    public static void main(String[] args) {


        int[][] metrics = {
                {0, 0, 5, 3, 0, 0, 0, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 2, 0},
                {0, 7, 0, 0, 1, 0, 5, 0, 0},
                {4, 0, 0, 0, 0, 5, 3, 0, 0},
                {0, 1, 0, 0, 7, 0, 0, 0, 6},
                {0, 0, 3, 2, 0, 0, 0, 8, 0},
                {0, 6, 0, 5, 0, 0, 0, 0, 9},
                {0, 0, 4, 0, 0, 0, 0, 3, 0},
                {0, 0, 0, 0, 0, 9, 7, 0, 0}
        };

        Sudoku sudoku = new Sudoku(metrics);
        long start = Instant.now().toEpochMilli();
        sudoku.backTrace(0, 0);
        long end = Instant.now().toEpochMilli();
        System.out.println("耗时(ms)：" + (end - start));

    }
}
