package traverse.Num3;

/**
 * @author 田泽鑫
 * @date 2019/12/23
 * @description
 */
public class Vertex {
    ObjState objState = new ObjState();     // 对象状态信息
    String outputMessage;                   // 输出时要显示的信息
    public Vertex(int manState, int wolfState, int sheepState,
                  int vegetableState, String outputMessage){
        // 初始化工作
        objState.man = manState;
        objState.wolf = wolfState;
        objState.sheep = sheepState;
        objState.vegetable = vegetableState;
        this.outputMessage = outputMessage;
    }
}