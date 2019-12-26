package DynamicProgram.Num2;

import java.util.Scanner;

/**
 * @author 田泽鑫
 * @date 2019/12/25
 * @description
 */
public class Yacht {
    public static int M = 200;
    public static int[][] f= new int[M][M];

    public static void main(String[] args) {
        System.out.print("请输入终点出租站序号(正整数):");
        int n = new Scanner(System.in).nextInt();
        //输入费用
        for (int i = 1;i<n;i++){
            for (int j = i+1;j<=n;j++){
                System.out.print("请输入从"+i+"站到"+j+"站的费用");
                f[i][j] = new Scanner(System.in).nextInt();
            }
        }
        System.err.println("最低费用为:"+minCost(n));
    }
    public static int minCost(int n){
        int i,j,k;
        int t =n-1;
        j = n;
        //车站序号下标从1开始，数组下标从0开始。
        for (i = n-2;i>=1;i--){
            for (k = t;k<=n-1;k++){
                if (f[i][j]>(f[i][k]+f[k][j])){
                    f[i][j] = f[i][k]+f[k][j];
                }else {
                    f[i][j] = f[i][j];
                }
            }
            t--;
        }
        return f[1][n];
    }
}
