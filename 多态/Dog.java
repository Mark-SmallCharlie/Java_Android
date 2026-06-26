package Demo;

public class Dog extends Animal {
    private String Color ;
    public  String getColor(){
        return Color;

    }
    public void setColor(String color){
        this.Color = color;
    }
    @Override
    public void eat(String food) {
        super.eat(food);
    }
    @Override
    public void sleep() {
        System.out.println(getname()+" is sleeping");
    }

    @Override
    public String info() {
        return super.info();
    }
    //    @Override
//    public int getNum() {
//        return super.getNum();
//    }

    public Dog(){

    }
//    public Dog(String name,int age,String Color){
//        this.name = name;
//        this.age = age;
//        this.Color = Color;
//
//    }

}
