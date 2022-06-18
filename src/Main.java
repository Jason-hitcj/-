import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 *
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    public static final Object MAIN_LOCK = new Object();


    public static void main(String[] args) {
        System.out.println("Hello World");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("hello");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //第一个界面
        ChangeFrame newFrame = new ChangeFrame();
        JPanel newMainPanel = newFrame.getMainPanel();
        frame.setContentPane(newMainPanel);
        frame.setVisible(true);

        synchronized (MAIN_LOCK) {
            while (newMainPanel.isVisible()) {
                // 主线程等待菜单面板关闭
                try {
                    MAIN_LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        // 移除第一个菜单 panel，后面加入计算器 Panel，实现界面切换
        frame.remove(newMainPanel);

        //第二个界面
        SimpleCalculator aCalculator = new SimpleCalculator();
        JPanel newCalculator = aCalculator.getMainPanel();
        frame.setContentPane(newCalculator);
        frame.setVisible(true);

    }
}