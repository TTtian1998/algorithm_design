package DynamicProgram.Num3;

import java.util.Scanner;

/**
 * @author 田泽鑫
 * @date 2019/12/25
 * @description: 最长回文子序列问题  转换成求最长公共子序列的问题
 */
class LPS {

    // 求最长公共子序列
    static String lcs(String a, String b) {
        int m = a.length();
        int n = b.length();
        char X[] = a.toCharArray();
        char Y[] = b.toCharArray();

        int L[][] = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //如果第一个字符和最后一个字符相同
                //这里为什么要用i-1，j-1，因为数组中的下标从0开始
                if (X[i - 1] == Y[j - 1]) {
                    L[i][j] = L[i - 1][j - 1] + 1;
                }
                else {
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
                }
            }
        }

        // 打印LCS
        int index = L[m][n];
        System.out.println("最长回文子序列长度为: " + index);
        // 创建一个长度为index+1的数组
        char[] lcs = new char[index + 1];

        //从最右下角开始，并在lcs中逐一存lcs[]
        int i = m, j = n;
        while (i > 0 && j > 0) {
            //如果X []和Y []中的当前字符相同，则当前字符是LCS的一部分
            if (X[i - 1] == Y[j - 1]) {
                //将当前字符放入结果
                lcs[index - 1] = X[i - 1];
                i--;
                j--;
                //减少i，j和index的值
                index--;
            }
            //如果不相同，则找到两个中较大的一个，并朝更大的值方向移动
            else if (L[i - 1][j] > L[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        String ans = "";
        for (int x = 0; x < lcs.length; x++) {
            ans += lcs[x];
        }
        return ans;
    }

    // 返回最长回文子序列
    static String longestPalSubseq(String str) {
        // 该字符串的倒序字符串
        String rev = str;
        rev = reverse(rev);
        return lcs(str, rev);
    }

    //将字符串倒序
    static String reverse(String str) {
        String ans = "";
        //使用toCharArray将String转换为字符数组
        char[] try1 = str.toCharArray();

        for (int i = try1.length - 1; i >= 0; i--) {
            ans += try1[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("请输入字符串: ");
        String str =new Scanner(System.in).next();
        System.out.println(longestPalSubseq(str));
    }
}