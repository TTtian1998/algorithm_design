package Traverse.Num3;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author 田泽鑫
 * @date 2019/12/23
 * @description 图的遍历解决农夫过河问题,羊是解题关键，找到邻接矩阵，最后找到一条路径从0000到1111
 */
public class FarmerCrossRiver {
    // 邻接矩阵
    public static int[][] arr = new int[10][10];
    // 顶点集
    public static ArrayList<Vertex> arrayList = new ArrayList<>();
    // 用来保存是否遍历
    public static int[] visited = new int[10];
    //保存路径的栈
    public static Stack<Vertex> stack = new Stack<Vertex>();
    public static ArrayList<Integer> VertexAdjs = new ArrayList<>();
    //初始化顶点集 共十个有效状态
    static {
        arrayList.add(new Vertex(0, 0, 0, 0, "农夫-狼-羊-菜 |河| 空"));//顶点1
        arrayList.add(new Vertex(0, 1, 0, 1, "农夫-羊 |河| 狼-菜"));//顶点6 ！
        arrayList.add(new Vertex(0, 0, 1, 0, "农夫-狼-菜 |河| 羊"));//顶点3
        arrayList.add(new Vertex(0, 0, 0, 1, "农夫-狼-羊 |河| 菜"));//顶点4
        arrayList.add(new Vertex(1, 0, 1, 0, "狼-菜 |河| 农夫-羊"));//顶点5
        arrayList.add(new Vertex(0, 1, 0, 0, "农夫-羊-菜 |河| 狼"));//顶点2！
        arrayList.add(new Vertex(1, 0, 1, 1, "狼 |河| 农夫-羊-菜"));//顶点7
        arrayList.add(new Vertex(1, 1, 0, 1, "羊 |河| 农夫-狼-菜"));//顶点8
        arrayList.add(new Vertex(1, 1, 1, 0, "菜 |河| 农夫-狼-羊"));//顶点9
        arrayList.add(new Vertex(1, 1, 1, 1, "空 |河| 农夫-狼-羊-菜"));//顶点10
    }
    public static void main(String[] args) {
        //顶点连接(无向图)
        for (int i = 0; i < 10; i++) {
            int rowFarmer = arrayList.get(i).state.farmer;
            int rowWolf = arrayList.get(i).state.wolf;
            int rowSheep = arrayList.get(i).state.sheep;
            int rowCabbage = arrayList.get(i).state.cabbage;
            for (int j = 0; j < 10; j++) {
                int columnFarmer = arrayList.get(j).state.farmer;
                int  columnWolf = arrayList.get(j).state.wolf;
                int  columnSheep = arrayList.get(j).state.sheep;
                int  columnCabbage = arrayList.get(j).state.cabbage;
                // 满足两个条件：1 农夫的状态不一样(对岸或彼岸) 2 有且仅有最多一个狼羊菜中的一个对象状态不一样，取绝对值计算
                if (rowFarmer != columnFarmer && (Math.abs(rowWolf - columnWolf) +
                        Math.abs(rowSheep - columnSheep) +
                        Math.abs(rowCabbage - columnCabbage) <= 1)) {
                    // 满足以上条件则满足连通性，置为1
                    arr[i][j] = 1;
                }
            }
        }
        //打印邻接矩阵
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }
        System.out.println("--------------------------");
        visited[0] = 1;
        // 从第一个点找最后一个点
        dfs(1, 10);
    }
    //深度优先遍历
    public static void dfs(int start, int end) {
        if (start == end) {
            print(end);    // 调用print()方法输出结果
            System.out.println();
        }
        for (int i = 0; i < 10; i++) {
            // 有边且没有被访问
            if (arr[start-1][i] > 0 && visited[i] == 0) {
                visited[i] = start;
                dfs(i+1, end);
                // 回溯时置为0
                visited[i] = 0;
            }
        }
    }

    public static void print(int end) {
        // 从最后往前遍历，然后正序输出
        int[] temp = new int[10]; // 保存了倒叙输出的顺序
        int num = 0;    // num表示要输出的个数
        int i = end;    // i表示当前是第几个数
        while (i != 1) {
            // 当i不是第一个数字时，则继续往前找
            temp[num] = visited[i - 1];
            i = temp[num];
            num++;      // num加1
        }
        for (int j = num - 1; j >= 0; j--) {
            System.out.println(arrayList.get(temp[j] - 1).outputMsg);
        }
        System.out.println(arrayList.get(9).outputMsg);
        System.out.println("--------------------------");
    }
}