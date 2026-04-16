package Demo;
public class Animal {
    private String name;
    private int age;
    public final String Color = "Brown";
    private int num=10;
    public int getage(){
        return age;
    }
    public void setage(int age){
        this.age=age;
    }
    public String getname(){
        return name;
    }
    public void setname(String name){
        this.name=name;
    }
    public int getNum(){
        return num;
    }
    public void setNum(int num){
        this.num=num;
    } //以上是Animal类的属性和方法


    public void eat(String food){
        System.out.println(name+" is eating "+food);
    }
    public void sleep(){
        System.out.println(name+" is sleeping");
    }
    public void shout(){
        System.out.println(name+" is shouting");
    }
   public String info() {
        return super.toString() + "\n" + "Name: " + name + ", Age: " + age + ", Color: " + Color + ", Num: " + num;
    }
}