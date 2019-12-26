package Traverse.Num3;

/**
 * @author 田泽鑫
 * @date 2019/12/23
 * @description： 顶点类
 */
public class Vertex {
    // 对象状态信息
    State state = new State();
    // 输出时要显示的信息
    String outputMsg;
    public Vertex(int farmerState, int wolfState, int sheepState,
                  int cabbageState, String outputMsg){
        // 初始化工作
        state.farmer = farmerState;
        state.wolf = wolfState;
        state.sheep = sheepState;
        state.cabbage = cabbageState;
        this.outputMsg = outputMsg;
    }
}