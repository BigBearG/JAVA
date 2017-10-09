package Base;

import Base.Product;

import java.util.ArrayList;

public class ProductManager {
    ArrayList<Product> products=new ArrayList<Product>();
    public ProductManager() {
        Product p1=new Product(1,"apple",1,1);
        products.add(p1);
        Product p2=new Product(2,"blanana",2,2);
        products.add(p2);
    }
    public Object[][] listToArray(){
        Object[] one=products.toArray();
        Object[][] two=new Object[99][4];
        for (int i=0;i<one.length;i++){
            Product p=(Product)one[i];
            two[i][0]=p.getNo();
            two[i][1]=p.getName();
            two[i][2]=p.getPrince();
            two[i][3]=p.getNumber();
        }
        return two;
    }
    public void show(){
        for (int i=0;i<products.size();i++){
            products.get(i).show();
        }
    }

    public void addProduct(Product product){
       products.add(product);
    }
    public void deleteProduct(Product product){

    }
}
