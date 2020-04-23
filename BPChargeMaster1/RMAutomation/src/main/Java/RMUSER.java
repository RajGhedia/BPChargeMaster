public class RMUSER {

    String name;
    int age;
    int heightInInches;
    String eyeColor;

    public RMUSER(){

    }
    public void speak(){

        System.out.println("Hello, my name is "+name);
        System.out.println("I am" + heightInInches + "Inch tall");
        System.out.println("My age is" + age);
        System.out.println("Eye is " + eyeColor);
    }

    public void eat(){
        System.out.println("eating .....");
    }

     public void working() {
        System.out.println("Working");

    }
}
