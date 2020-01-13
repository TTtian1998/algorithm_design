package BranchAndBound.Num3;

/**
 * @author 田泽鑫
 * @date 2019/12/25
 * @description : TSP旅行商分支限界法问题
 */

import java.util.*;

class TSP
{

    static int N = 4;

    // 存储最佳路径
    static int final_path[] = new int[N + 1];

    // visited[] 记录路径中顶点是否访问
    static boolean visited[] = new boolean[N];

    //存储最短行程的最终最小权重
    static int final_res = Integer.MAX_VALUE;

    // 临时方案复制一份给最终方案
    static void copyToFinal(int curr_path[])
    {
        for (int i = 0; i < N; i++)
            final_path[i] = curr_path[i];
        final_path[N] = curr_path[0];
    }

    //用于查找在顶点i处具有终点的最小值
    static int firstMin(int adj[][], int i)
    {
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < N; k++)
            if (adj[i][k] < min && i != k)
                min = adj[i][k];
        return min;
    }
    //函数以第二个最小边代价为顶点i
    static int secondMin(int adj[][], int i)
    {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int j=0; j<N; j++)
        {
            if (i == j)
                continue;

            if (adj[i][j] <= first)
            {
                second = first;
                first = adj[i][j];
            }
            else if (adj[i][j] <= second &&
                    adj[i][j] != first)
                second = adj[i][j];
        }
        return second;
    }

    // curr_bound->根节点的下限
    // curr_weight->存储到目前为止的路径权重
    // 在搜索中移动时，level->当前级别
    // 空间树
    // curr_path []->解决方案的存储位置
    static void TSPRec(int adj[][], int curr_bound, int curr_weight,
                       int level, int curr_path[])
    {
        //基本情况是当我们达到N级时
        //表示我们已经覆盖了所有节点一次
        if (level == N)
        {
            //检查最后一个顶点是否有边
            //返回第一个顶点的路径
            if (adj[curr_path[level - 1]][curr_path[0]] != 0)
            {
                //具有解决方案
                int curr_res = curr_weight +
                        adj[curr_path[level-1]][curr_path[0]];

               //更新最佳路径
                if (curr_res < final_res)
                {
                    copyToFinal(curr_path);
                    final_res = curr_res;
                }
            }
            return;
        }

       //递归构建搜索树
        for (int i = 0; i < N; i++)
        {
            //考虑下一顶点是否不同
            if (adj[curr_path[level-1]][i] != 0 &&
                    visited[i] == false)
            {
                int temp = curr_bound;
                curr_weight += adj[curr_path[level - 1]][i];


                if (level==1)
                    curr_bound -= ((firstMin(adj, curr_path[level - 1]) +
                            firstMin(adj, i))/2);
                else
                    curr_bound -= ((secondMin(adj, curr_path[level - 1]) +
                            firstMin(adj, i))/2);
                //如果当前下界比final_res小,需要探索下一个顶点
                if (curr_bound + curr_weight < final_res)
                {
                    curr_path[level] = i;
                    visited[i] = true;
                    //进入下一次搜搜
                    TSPRec(adj, curr_bound, curr_weight, level + 1,
                            curr_path);
                }

                //否则我们必须通过重置来修剪节点
                //所有对curr_weight和curr_bound的更改
                curr_weight -= adj[curr_path[level-1]][i];
                curr_bound = temp;

                // 重置访问数组
                Arrays.fill(visited,false);
                for (int j = 0; j <= level - 1; j++)
                    visited[curr_path[j]] = true;
            }
        }
    }


    static void TSP(int adj[][])
    {
        int curr_path[] = new int[N + 1];

        // 计算根节点的初始下限
        // 1/2 * (a+b + c+d +...) 所有的边.
        // 初始化访问数组
        int curr_bound = 0;
        Arrays.fill(curr_path, -1);
        Arrays.fill(visited, false);

        // 计算初始界限
        for (int i = 0; i < N; i++)
            curr_bound += (firstMin(adj, i) +
                    secondMin(adj, i));

        //四舍五入为整数
        curr_bound = (curr_bound==1)? curr_bound/2 + 1 :
                curr_bound/2;

        //从第一个顶点开始
        visited[0] = true;
        curr_path[0] = 0;


        TSPRec(adj, curr_bound, 0, 1, curr_path);
    }

    public static void main(String[] args)
    {
        //图的邻接矩阵
        int adj[][] = {{0, 10, 15, 20},
                       {10, 0, 35, 25},
                       {15, 35, 0, 30},
                       {20, 25, 30, 0}};

        TSP(adj);

        System.out.printf("最低费用为: %d\n", final_res);
        System.out.printf("旅行路径 : ");
        for (int i = 0; i <= N; i++)
        {
            System.out.printf("%d ", final_path[i]);
        }
    }
}
