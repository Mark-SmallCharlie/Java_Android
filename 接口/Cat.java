package Demo;

public class Cat extends Animal {
    private  String color;
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color=color;
    }

    @Override
    public void eat(String food) {
        System.out.println("Cat eats " + food);
    }

    @Override
    public void sleep() {
        System.out.println("Cat is sleeping");
    }
    //@Override

}
