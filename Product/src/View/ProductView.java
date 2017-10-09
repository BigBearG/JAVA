package View;

import Base.Product;
import Base.ProductManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductView extends JFrame implements MouseListener, ActionListener {
   private JTable table;
   private Object[] titles={"编号","名称","价格","数量"};
   private Object[][] products;
   private ProductManager pm=new ProductManager();
   private Color mcolor=new Color(255,0,0);
   private Font mfont=new Font("宋体",Font.BOLD,14);
   private JTextField tfno,tfname,tfprice,tfnumber;
   private JButton btadd,btdelete,btmodify,btexit;
   public ProductView() {
      this.setTitle("商品信息管理");
      this.setLayout(new BorderLayout());
      this.setBounds(250,250,800,400);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      products=pm.listToArray();
      table=new JTable(products,titles);
      table.addMouseListener(this);
      JScrollPane p1=new JScrollPane(table);
      Box box=Box.createVerticalBox();
      box.add(p1);
      JPanel p2=new JPanel();
      JLabel label1=new JLabel("商品编号");
      JLabel label2=new JLabel("商品名称");
      JLabel label3=new JLabel("商品价格");
      JLabel label4=new JLabel("商品数量");
      label1.setForeground(mcolor);
      label2.setForeground(mcolor);
      tfno=new JTextField(10);
      tfname=new JTextField(10);
      tfprice=new JTextField(10);
      tfnumber=new JTextField(10);
      p2.add(label1);
      p2.add(tfno);
      p2.add(label2);
      p2.add(tfname);
      p2.add(label3);
      p2.add(tfprice);
      p2.add(label4);
      p2.add(tfnumber);
      box.add(p2);
      JPanel p3=new JPanel();
      JLabel ll=new JLabel("红色输入框为必填内容");
      ll.setForeground(mcolor);
      ll.setFont(mfont);
      btadd=new JButton("新增");
      btadd.addActionListener(this);
      btdelete=new JButton("删除");
      btmodify=new JButton("修改");
      btexit=new JButton("退出");
      p3.add(ll);
      p3.add(btadd);
      p3.add(btdelete);
      p3.add(btmodify);
      p3.add(btexit);
      box.add(p3);
      add(box);
      this.setVisible(true);
   }

   @Override
   public void mouseClicked(MouseEvent e) {
      // 获取被点击的行 
      int row =table.getSelectedRow();
      // 获取点击行的内容 
      TableModel dtm = (TableModel)table.getModel();
      int no=(int)dtm.getValueAt(row,0);
      String name=(String)dtm.getValueAt(row,1);
      Float price=(Float)dtm.getValueAt(row,2);
      int number=(int)dtm.getValueAt(row,3);
      tfno.setText(String.valueOf(no));
      tfname.setText(name);
      tfprice.setText(String.valueOf(price));
      tfnumber.setText(String.valueOf(number));

   }
   @Override
   public void mousePressed(MouseEvent e) {

   }

   @Override
   public void mouseReleased(MouseEvent e) {

   }

   @Override
   public void mouseEntered(MouseEvent e) {

   }

   @Override
   public void mouseExited(MouseEvent e) {

   }
   @Override
   public void actionPerformed(ActionEvent e) {
      String no= tfno.getText();
      String name=tfname.getText();
      String price= tfprice.getText();
      String number= tfnumber.getText();
      if (no.equals("")||name.equals("")){
         JOptionPane.showMessageDialog(null, "红色输入框内必须输入", "新增失败", JOptionPane.ERROR_MESSAGE);

      }else if (e.getSource()==btadd){
         Product product=new Product(Integer.parseInt(no),name,Float.parseFloat(price),Integer.parseInt(number));
         pm.addProduct(product);
         DefaultTableModel model = new DefaultTableModel(pm.listToArray(),titles);
         table.setModel(model);
         pm.show();
      }
   }
}
