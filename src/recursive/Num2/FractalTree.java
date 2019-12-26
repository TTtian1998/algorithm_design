package Recursive.Num2;

/**
 * @author 田泽鑫
 * @date 2019/12/25
 * @description
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FractalTree extends JFrame{
    private Tree t = new Tree();
    private JButton jb = new JButton("重新绘制");
    public FractalTree(){
        //将分形树添加到主面板
        this.add(t);
        JPanel panel = new JPanel();
        panel.add(jb);
        this.add(panel,BorderLayout.NORTH);
        // 刷新按钮点击监听
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.run();
            }
        });
    }
    public static void main(String[] args) {
        FractalTree f = new FractalTree();
        f.setTitle("分形树");
        f.setSize(900,600);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}


