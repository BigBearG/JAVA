package Base;

public class Product {
    private int no;
    private String name;
    private float prince;
    private int number;

    public Product() {
    }

    public Product(int no, String name, float prince, int number) {
        this.no = no;
        this.name = name;
        this.prince = prince;
        this.number = number;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrince() {
        return prince;
    }

    public void setPrince(float prince) {
        this.prince = prince;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void show(){
        System.out.println(no+name+prince+number);
    }
}
