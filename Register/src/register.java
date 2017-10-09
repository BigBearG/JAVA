import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;

public class register extends JFrame implements ActionListener,ItemListener{
    JTextField muserid,musername,manswer,mage;
    JPasswordField mpass,mconfirmpwd;
    JComboBox<String> mquestion;
    JRadioButton mmale,mfemale,mnosex;
    ButtonGroup msex;
    JButton msubmit,mcancel;
    String sex="没有性别";
    String question="1+1?";
    String userid,username,answer,age,userpwd,confirmpwd;
    String[] mquestions={
            "1+1?",
            "2+1?",
            "3+1?",
            "4+1?",
            "5+1?",
            "6+1?",
            "7+1?",
    };
    public  register(){
        setTitle("注册");
        setBounds(200,200,500,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9,1));
        JPanel[] jp=new JPanel[10];
        jp[1]=new JPanel();
        jp[1].add(new JLabel("*账号"));
        muserid=new JTextField(10);
        jp[1].add(muserid);

        jp[2]=new JPanel();
        jp[2].add(new JLabel("*昵称"));
        musername=new JTextField(10);
        jp[2].add(musername);

        jp[3]=new JPanel();
        jp[3].add(new JLabel("*密码"));
        mpass=new JPasswordField(10);
        jp[3].add(mpass);

        jp[4]=new JPanel();
        jp[4].add(new JLabel("*确认密码"));
        mconfirmpwd=new JPasswordField(10);
        jp[4].add(mconfirmpwd);

        jp[5]=new JPanel();
        jp[5].add(new JLabel("密码保护问题"));
        mquestion=new JComboBox<>(mquestions);
        mquestion.addItemListener(this);
        jp[5].add(mquestion);

        jp[6]=new JPanel();
        jp[6].add(new JLabel("密码保护答案"));
        manswer=new JTextField(10);
        jp[6].add(manswer);

        jp[7]=new JPanel();
        jp[7].add(new JLabel("性别"));
        msex=new ButtonGroup();
        mmale=new JRadioButton("我是帅哥");
        mmale.addItemListener(this);
        mfemale=new JRadioButton("我是美女");
        mfemale.addItemListener(this);
        mnosex=new JRadioButton("没有性别",true);
        mnosex.addItemListener(this);
        msex.add(mmale);
        msex.add(mfemale);
        msex.add(mnosex);
        jp[7].add(mmale);
        jp[7].add(mfemale);
        jp[7].add(mnosex);

        jp[8]=new JPanel();
        jp[8].add(new JLabel("年龄"));
        mage=new JTextField(10);
        mage.addActionListener(this);
        jp[8].add(mage);

        jp[9]=new JPanel();
        msubmit=new JButton("注册");
        msubmit.addActionListener(this);
        mcancel=new JButton("取消");
        mcancel.addActionListener(this);
        jp[9].add(msubmit);
        jp[9].add(mcancel);

        for (int i=1;i<jp.length;i++){
            add(jp[i]);
        }
        validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if (e.getSource()==msubmit){
                userid=muserid.getText().toString();
                username=musername.getText().toString();
                userpwd= String.copyValueOf(mpass.getPassword());
                confirmpwd= String.copyValueOf(mconfirmpwd.getPassword());
                answer=manswer.getText().toString();
                age=mage.getText().toString();
                if (userpwd.equals(confirmpwd)){
                    saveData();
                    Login login=new Login();
                }else {
                    JOptionPane.showMessageDialog(null, "两次输入密码不一致", "注册失败", JOptionPane.ERROR_MESSAGE);
                }
            }
    }

    private void saveData() {
        File path=new File("Data");
        if (!path.exists()){
                path.mkdir();
        }
        File file=new File(path+"/data.text");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fios=null;
        BufferedWriter buos=null;
        try {
            fios=new FileWriter(file);
            buos=new BufferedWriter(fios);
            buos.write(userid);
            buos.newLine();
            buos.write(username+"="+userpwd);
            buos.newLine();
            buos.write(question+"="+answer);
            buos.newLine();
            buos.write(sex);
            buos.newLine();
            buos.write(age);
            buos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                buos.close();
                fios.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void itemStateChanged(ItemEvent e){
        question=mquestion.getSelectedItem().toString();
        if (e.getSource()==mmale){
           sex=mmale.getText().toString();
        }
        if (e.getSource()==mfemale){
             sex=mfemale.getText().toString();
        }
        if (e.getSource()==mnosex){
            sex=mnosex.getText().toString();
        }
    }
}
