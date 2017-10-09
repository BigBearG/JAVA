import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Notepad0 extends JFrame implements ActionListener {
    //菜单
    JMenu fileMenu,editMenu;
    //“文件”的菜单项
    JMenuItem fileMenu_New,fileMenu_Open,fileMenu_Save,fileMenu_SaveAs,fileMenu_Exit;
    //"编辑"的菜单项
    JMenuItem editMenu_Cut,editMenu_Copy,editMenu_Paste,editMenu_All;
    //“文本”编辑区域
    JTextArea editArea;
    private String filePath;
    private String fileName;
    boolean flag = false;
    public Notepad0() {
        super("Java记事本");
        //创建菜单条
        JMenuBar menuBar=new JMenuBar();
        //创建文件菜单及菜单项并注册事件监听
        fileMenu=new JMenu("文件(F)");
        fileMenu.setMnemonic('F');//设置快捷键ALT+F

        fileMenu_New=new JMenuItem("新建(N)");
        fileMenu_New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        fileMenu_New.addActionListener(this);

        fileMenu_Open=new JMenuItem("打开(O)...");
        fileMenu_Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
        fileMenu_Open.addActionListener(this);

        fileMenu_Save=new JMenuItem("保存(S)");
        fileMenu_Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
        fileMenu_Save.addActionListener(this);

        fileMenu_SaveAs=new JMenuItem("另存为(A)...");
        fileMenu_SaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
        fileMenu_SaveAs.addActionListener(this);

        fileMenu_Exit=new JMenuItem("退出(X)");
        fileMenu_Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
        fileMenu_Exit.addActionListener(this);

        //创建编译菜单及菜单项 并注册监听事件
        editMenu=new JMenu("编辑(E)");
        editMenu.setMnemonic('E');//设置快捷键ALT+E
        editMenu_Cut=new JMenuItem("剪切(X)");
        editMenu_Cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
        editMenu_Cut.addActionListener(this);

        editMenu_Copy=new JMenuItem("复制(C)");
        editMenu_Copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
        editMenu_Copy.addActionListener(this);

        editMenu_Paste=new JMenuItem("粘贴(V)");
        editMenu_Paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
        editMenu_Paste.addActionListener(this);

        editMenu_All=new JMenuItem("全选(Z)");
        editMenu_All.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_MASK));
        editMenu_All.addActionListener(this);

        //向菜单条添加"文件"菜单及菜单项
        menuBar.add(fileMenu);
        fileMenu.add(fileMenu_New);
        fileMenu.add(fileMenu_Open);
        fileMenu.add(fileMenu_Save);
        fileMenu.add(fileMenu_SaveAs);
        fileMenu.addSeparator();        //分隔线
        fileMenu.add(fileMenu_Exit);

        //向菜单添加“编辑”菜单及菜单项
        menuBar.add(editMenu);
        editMenu.add(editMenu_Cut);
        editMenu.add(editMenu_Copy);
        editMenu.add(editMenu_Paste);
        editMenu.add(editMenu_All);

        //向窗口添加菜单条
        this.setJMenuBar(menuBar);
        //创建文本编辑区并添加滚动条
        editArea=new JTextArea(20,50);
        JScrollPane scroller=new JScrollPane(editArea);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scroller,BorderLayout.CENTER);
        editArea.setWrapStyleWord(true);
        editArea.setLineWrap(true);

        //添加窗口监听器
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                fileName =Notepad0.this.getTitle();
                if (flag ==false){
                    int returnValue = JOptionPane.showConfirmDialog(null,
                            Notepad0.this.getTitle() + "文件还没有保存！需要保存？", "记事本",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (returnValue == JOptionPane.YES_OPTION) {
                        save_asMethod();
                    } else if (returnValue == JOptionPane.NO_OPTION) {
                        System.exit(0);

                    } else
                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }else System.exit(0);

             }
        });
        //设置窗口在屏幕上的位置、大小和可见性
        this.setLocation(100,100);
        this.setSize(650,550);
        this.setVisible(true);
        editArea.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fileMenu_New){
            this.setTitle("无标题——记事本");
            editArea.setText(" ");
        }else if (e.getSource()==fileMenu_Open){
            FileDialog fd1 = new FileDialog(this, "打开",FileDialog.LOAD);
            fd1.setFile("*.txt");
            fd1.setVisible(true);

            fileName = fd1.getFile();
            filePath = fd1.getDirectory();
            this.setTitle(fileName);
            try {
                FileReader fr = new FileReader(filePath + fileName);
                BufferedReader br = new BufferedReader(fr);
                String sinput = "";
                editArea.setText("");
                int lineNum = 0;
                while ((sinput = br.readLine()) != null) {
                    editArea.append(sinput + "\r\n");
                    lineNum++;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        } else if(e.getSource()==fileMenu_Save){
            fileName =this.getTitle();
            try {
                FileWriter fw = new FileWriter(fileName + ".txt");
                String save = editArea.getText();
                fw.write(save);
                fw.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "文件已经成功保存了！", "文件保存",
                    JOptionPane.INFORMATION_MESSAGE);
            flag=true;
        }else if (e.getSource()==fileMenu_SaveAs){
            save_asMethod();
            flag=true;
        }else if (e.getSource()==fileMenu_Exit){
            fileName =this.getTitle();
            if (flag ==false) {
                int returnValue = JOptionPane.showConfirmDialog(null,
                        this.getTitle() + "文件还没有保存！需要保存？", "记事本",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if (returnValue == JOptionPane.YES_OPTION) {
                    save_asMethod();
                } else if (returnValue == JOptionPane.NO_OPTION) {
                    System.exit(0);

                }else
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
            else System.exit(0);
        }
        //编辑菜单
        if (e.getSource()==editMenu_Cut){
            editArea.cut();
        }else if (e.getSource()==editMenu_Copy){
            editArea.copy();
        }else if(e.getSource()==editMenu_Paste){
            editArea.paste();
        }else if (e.getSource()==editMenu_All){
            editArea. selectAll();
        }
    }
    public void save_asMethod() {
        FileDialog fd = new FileDialog(this, "另存为…",
                FileDialog.SAVE);
        fd.setFile("*.txt");
        fd.setVisible(true);
        filePath = fd.getDirectory();
        fileName = fd.getFile();

        try {
            FileWriter fw = new FileWriter(filePath + fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(editArea.getText());
            pw.flush();
            pw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void open(){
       JFileChooser fileChooser=new JFileChooser();
        fileChooser.getSelectedFile();
        fileName = fileChooser.getName();

    }

    public static void main(String[] args) {
        new Notepad0();
    }
}
