import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    JTextField usrname;
    JPasswordField usrpwd;
    JButton confirm, cancel;

    public Login() {
        setSize(180, 200);
        Toolkit tk = Toolkit.getDefaultToolkit();
        setLocation((tk.getScreenSize().width - getSize().width) / 2, (tk.getScreenSize().height - getSize().height) / 2);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        validate();
    }

    private void init() {
        setLayout(new FlowLayout());
        add(new JLabel("用户名："));
        usrname = new JTextField(10);
        add(usrname);
        add(new JLabel("密  码："));
        usrpwd = new JPasswordField(10);
        add(usrpwd);
        confirm = new JButton("登录");
        cancel=new JButton("取消");
        add(confirm);
        add(cancel);
        confirm.addActionListener(this);
        cancel.addActionListener(this);
        /*confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usrnames = usrname.getText();
                String usrpwds = String.copyValueOf(usrpwd.getPassword());
                if (usrnames.equals("admin") && usrpwds.equals("123")) {
                    JOptionPane.showMessageDialog(null, "登录成功", "登录", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "登录失败", "登录", JOptionPane.ERROR_MESSAGE);

                }

            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });*/

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirm){
            String usrnames =usrname.getText();
            String usrpwds = String.copyValueOf(usrpwd.getPassword());
            if (usrnames.equals("admin") && usrpwds.equals("123")) {
                JOptionPane.showMessageDialog(null, "登录成功", "登录", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "登录失败", "登录", JOptionPane.ERROR_MESSAGE);

            }
        }
        else if (e.getSource()==cancel){
            System.exit(0);
        }
    }
}
class Text{
        public static void main(String[] args) {
            Login login=new Login();
        }
}