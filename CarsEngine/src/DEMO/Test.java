package DEMO;

public class Test {
    public static void main(String[] args) {
        Car car = new Car();
//        Car.Engine engine = car.new Engine();
//        Car.Engine engine1=new Car().new Engine();
        Car.Engine engine=new Car.Engine();
        engine.run();
        engine.start();
        engine.method();

    }
}
