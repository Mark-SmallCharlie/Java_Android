package Demo;

public class Test extends Animal {
    public static void main(String[] args) {
        Dog dog = new Dog(); // create a dog object
        //dog.shout(); // call the shout method of the dog object
        //dog.eat(); // call the eat method of the dog object
        Animal an = dog;//向上转型
        System.out.println("向上转型后调用的方法");
        an.shout(); //
        Dog d = (Dog)an; //向下转型
        System.out.println("向下转型后调用的方法");
        d.eat(); //
        Animal al = new Dog(); // 向上转型实例化Animal对象
        System.out.println("Animal al=new dog():"+(al instanceof Dog));
        System.out.println("Animal al=new dog():"+(al instanceof Animal));
        Animal al2 = new Dog(); // create a dog object
        System.out.println("Animal al2=new dog():"+(al2 instanceof Dog));
        System.out.println("Animal al2=new dog():"+(al2 instanceof Animal));


        //boolean equals()方法//判断两个对象是否相等
        //int hashCode()方法//返回对象的哈希码值
        //String toString()方法//返回对象的字符串表示
        //Object clone()方法//创建并返回一个对象的拷贝
        //Class<?> getClass()方法//返回对象的类对象
        Animal animal=new Animal();
        System.out.println(animal.toString());

    }
}
