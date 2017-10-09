import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Calculator extends JFrame implements ActionListener,ItemListener{
    JTextField n1,n2;
    JComboBox choicefuhao;
    JButton btn;
    JTextArea showdata;
    String fh="+";
    public Calculator(){
        setSize(300, 200);
        Toolkit tk = Toolkit.getDefaultToolkit();
        setLocation((tk.getScreenSize().width - getSize().width) / 2, (tk.getScreenSize().height - getSize().height) / 2);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        validate();
    }

    private void init() {
        setLayout(new FlowLayout());
        n1=new JTextField(10);
        add(n1);
        choicefuhao=new JComboBox(new String[]{"+","-","*","/"});
        add(choicefuhao);
        n2=new JTextField(10);
        add(n2);
        btn=new JButton("计算");
        add(btn);
        showdata=new JTextArea();
        showdata.setColumns(50);
        showdata.setRows(50);
        add(showdata);
        choicefuhao.addItemListener(this);
        btn.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double nb1= Double.parseDouble(n1.getText());
        double nb2=Double.parseDouble(n2.getText());
        double result = 0;
        switch (fh){
            case "+":result=nb1+nb2;break;
            case "-":result=nb1-nb2;break;
            case "*":result=nb1*nb2;break;
            case "/":result=nb1/nb2;break;
        }
        showdata.setText(nb1+fh+nb2+"="+result);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        fh=choicefuhao.getSelectedItem().toString();
    }
}
class Test{
    public static void main(String[] args) {
        new Calculator();
    }
}
