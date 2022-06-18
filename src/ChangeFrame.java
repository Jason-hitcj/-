import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeFrame {
    private JButton onlyOneButton;
    private JPanel mainPanel;

    public ChangeFrame() {
        onlyOneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                synchronized (Main.MAIN_LOCK){
                    // 选定难度，通知主线程结束等待
                    Main.MAIN_LOCK.notify();
                }
            }
        });
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }


}
