package Recursive.Num1;

import java.util.Scanner;

/**
 * @author 田泽鑫
 * @date 2019/12/23
 * @description: 二项式公式计算
 */
public class Binomial {
    //统计递归版本的基本执行次数
    public static int RecursiveNum = 0;
    //统计备忘录版本的基本执行次数
    public static int memoNum = 0;
    //统计迭代版本的基本执行次数
    public static int IterationNum = 0;
    //备忘录保存
    public static int N = 100;
    //初始化二维数组
    public static int[][] array = new int[N][N];
    public static void main(String[] args)  {
        System.out.println("从n个数中抓取k个元素,请输入n的值:");
        int n = new Scanner(System.in).nextInt();
        System.out.println("请输入k的值:");
        int k = new Scanner(System.in).nextInt();
        try {
            System.out.println("二项式计算结果:"+ RecursiveSelect(n,k));
            System.out.println("递归版本基本语句执行次数:"+ RecursiveNum);
            System.out.println("------------------------:");
            System.out.println("二项式计算结果:"+ memo(n,k));
            System.out.println("备忘录版本基本语句执行次数:"+ memoNum);
            System.out.println("------------------------:");
            System.out.println("二项式计算结果:"+ Iteration(n,k));
            System.out.println("迭代版本基本语句执行次数:"+ IterationNum);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //递归版本
    public static int RecursiveSelect(int n,int k ) throws Exception {
        //无效输入
        if (k<0||n<=0||k>n){
            throw new Exception("无效输入");
        }
        else if (k==1||k+1==n){
            RecursiveNum++;
            return n;
        }
        else if (n==k){
            RecursiveNum++;
            return 1;
        }else if(k==0){
            RecursiveNum++;
            return 1;
        }else {
            RecursiveNum++;
          return RecursiveSelect(n-1,k-1)+RecursiveSelect(n-1,k);
        }
    }
    //备忘录版本
    public static int memo(int n,int k) throws Exception {
        //无效输入
        if (k<0||n<=0||k>n){
            throw new Exception("无效输入");
        }
        //判断备忘录中是否有记录
        if (array[n][k]>0){
            memoNum++;
            return array[n][k];
        }
        if (k==1||k+1==n){
            memoNum++;
            array[n][k] = n;
            return array[n][k];
        }
        if (n==k){
            memoNum++;
            array[n][k] = 1;
            return array[n][k];
        }
        if(k==0){
            memoNum++;
            array[n][k] = 1;
            return array[n][k];
        }
        memoNum++;
        array[n][k] = memo(n-1,k-1)+memo(n-1,k);
        return array[n][k];
    }
    //迭代版本
    public static long Iteration(int n,int k ) throws Exception{
        //无效输入
        if (k<0||n<=0||k>n){
            throw new Exception("无效输入");
        }
        if (k==1||k+1==n){
            IterationNum++;
            return n;
        }
        if (n==k){
            IterationNum++;
            return 1;
        }
        if(k==0){
            return 1;
        }
        long a = caclu(n);
        long b = caclu(n-k);
        long c = caclu(k);
        //组合计算公式
        long temp = a/(b*c);
        return temp;
    }
    //阶乘计算(最大计算到12,之后会溢出)
    public static long caclu(int num){
        int a = 1;
        if (num==1){
            IterationNum++;
            return 1;
        }else {
            for (int i =2 ; i<=num;i++){
                IterationNum++;
                a*= i;
            }
            return a;
        }
    }
}
