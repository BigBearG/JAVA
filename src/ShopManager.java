import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
public class ShopManager {
    JFrame jf,jf1,jf2,jf3;
    JMenu jm;
    JMenuBar jmb;
    JMenuItem jm1,jm2,jm3,jm4,jm5,jm6;
    JTextArea ta;
    JLabel jl;
    ShopManager(){
        jf=new JFrame("商品管理系统");
        jm1=new JMenuItem("销售员资料管理",new ImageIcon("1.jpg"));
        jm2=new JMenuItem("客户资料管理",new ImageIcon("2.jpg"));
        jm3=new JMenuItem("商品资料管理",new ImageIcon("3.jpg"));
        jm4=new JMenuItem("退出",new ImageIcon("4.jpg"));
        jm4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        jmb=new JMenuBar();
        jm=new JMenu("基本资料");
        jmb.add(jm);
        jm.add(jm1);
        jm.add(jm2);
        jm.add(jm3);
        jm.add(jm4);
        ImageIcon im=new ImageIcon("5.jpg");
        jl=new JLabel(im);
        jf.add(jl);
        jf.setLayout(new FlowLayout());
        jf.setJMenuBar(jmb);
        jf.setSize(450, 550);
        Toolkit tk=Toolkit.getDefaultToolkit();
        jf.setLocation((tk.getScreenSize().width-jf.getSize().width)/2	,(tk.getScreenSize().height-jf.getSize().height)/2 );
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.validate();
    }

    public static void main(String[] args) {
        new ShopManager();
    }
}