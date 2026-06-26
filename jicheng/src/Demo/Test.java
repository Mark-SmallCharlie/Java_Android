package Demo;

public class Test {
    public static void main(String[] args) {
        Animal animal = new Animal();// create an Animal object
        Dog dog = new Dog(); // create a Dog object

        dog.setname("Rufus"); // set the name of the dog
        dog.setage(3); // set the age of the dog
        dog.setColor("Black");// set the color of the dog

        System.out.println("The name of the dog is " + dog.getname()
        +"\nThe age of the dog is " + dog.getage()
        +"\nThe color of the dog is " + dog.getColor()
        +"\nThe number of the dog is " + dog.getname());

        dog.eat("gotou");
        dog.sleep();
        dog.info();
        System.out.println("  ");
        Cat cat = new Cat(); // create a Cat object
        cat.setname("Tom"); // set the name of the cat
        cat.setage(2); // set the age of the cat
        cat.setColor("White"); // set the color of the cat
        System.out.println("The name of the cat is " + cat.getname()
                +"\nThe age of the cat is " + cat.getage()
                +"\nThe color of the cat is " + cat.getColor());
        cat.eat("fish");
        cat.sleep();

        //通过接口访问静态方法
        System.out.println("ID:"+Animal2.getId());


        //通过接口访问默认方法
        //调用default方法，需要用接口的对象调用，而不是类名
//        Animal2 animal2 = (Animal2)dog;
//        animal2.show();

        Dog dog2 = new Dog();
        dog2.setname("Omiga");
        dog2.shout();
        dog2.eat("dog food");
        dog2.info();

    }
}


