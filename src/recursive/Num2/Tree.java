package Recursive.Num2;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * @author 田泽鑫
 * @date 2019/12/25
 * @description :swing 递归绘制分形树
 */
class Tree extends JPanel {
    //递归次数(次数大于15时计算的很慢) 共有recursiveNum-3+1条黑色树干,其余为上色树干(树叶)
    public static int recursiveNum = 12;
    // 起始树枝粗细
    public static int trunkWidth=13;
    //定义颜色数组
    private static Color[] colors={Color.MAGENTA,Color.BLUE,Color.RED,Color.PINK};
    //创建随机对象
    private static final Random rand = new Random();
    //倾斜度，随机倾斜度，树枝长度
    private double angle,randomAngle,branchLength;
    //反余弦
    private double PI = Math.acos(-1.0);

    //获取随机颜色值的静态方法
    private static Color getColor(){
        return colors[rand.nextInt(colors.length)];
    }
    //使用Graphics类绘制
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //Point表示 (x,y) 坐标空间中的位置的点
        Point p1 = new Point(this.getWidth()/2,this.getHeight()-5);
        Point p2 = new Point(this.getWidth()/2,this.getHeight()*6/7);
        display(g,recursiveNum,trunkWidth,p1,p2);
    }
    //重新绘制
    public void run(){
        repaint();
    }
    //递归绘制分形树
    private void display(Graphics g,int n,int trunkWidth,Point p1,Point p2){

        if (n>=0){
            Graphics2D graphics2D=(Graphics2D)g;
            //线段(树枝)粗细
            BasicStroke stroke=new BasicStroke(trunkWidth);
            graphics2D.setStroke(stroke);
            // 树干 上黑色
            if(n>=3){
                graphics2D.setColor(Color.BLACK);
            }else{
                //树叶 随机上颜色
                graphics2D.setColor(getColor());
            }
            //开始是起始树干起点坐标p1和终点坐标p2,之后是根据两点参数绘制线段
            graphics2D.drawLine(p1.x, p1.y, p2.x, p2.y);
            Point p3 = leftPoint(p1,p2);
            Point p4 = rightPoint(p1,p2);
            System.out.print("线段起点坐标:(" + p1.x +", "+p1.y+") -- " + "线段终点坐标:(" + p2.x+", "+p2.y+") -- ");
            System.out.println("n的值:" + n );
            //绘制左枝
            display(graphics2D, n-1,trunkWidth*4/5, p2, p3);
            //绘制右枝
            display(graphics2D, n-1, trunkWidth*4/5,p2, p4);
        }
    }
    //左树枝
    private Point leftPoint(Point p1,Point p2){
        Point p = new Point();
        //将矩形坐标 (x, y) 转换成极坐标 (r, theta)，返回所得角 theta
        angle = Math.atan2(p2.x-p1.x,p1.y-p2.y);
        //                  减
        randomAngle = angle - PI/(Math.random()*15+2);
        branchLength = Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y))*4/5;
        p.x= (int)(p2.x + branchLength*Math.sin(randomAngle));
        p.y= (int)(p2.y - branchLength*Math.cos(randomAngle));
        return p;
    }
    //右树枝
    private Point rightPoint(Point p1,Point p2){
        Point p = new Point();
        angle = Math.atan2(p2.x-p1.x,p1.y-p2.y);
        //                  加
        randomAngle = angle + PI/(Math.random()*15+2);
        branchLength = Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y))*4/5;
        p.x= (int)(p2.x + branchLength*Math.sin(randomAngle));
        p.y= (int)(p2.y - branchLength*Math.cos(randomAngle));
        if (p.x==0){
            System.out.println(p1.x+" "+p1.y+" "+p2.x+" "+p2.y+" "+angle);
        }
        return p;
    }
}
