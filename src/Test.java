public class Test {
    static abstract class Human{}
    static class Man extends Human{}
    static class Women extends Human{}
    public void sayHello(Man man){
        print("man");
    }
    public void sayHello(Women women){
        print("women");
    }
    public void sayHello(Human human)
    {
        print("human");
    }
    static void print(String s)
    {
        System.out.println(s);
    }
    public static void main(String[] args) {
        Test test=new Test();
        Human man=new Man();
        Human women=new Women();
        test.sayHello((Man) man);
        test.sayHello(women);
    }
    private static int testFinal(){
        int x;
        try {
            x=100;
//            System.out.println("try");
//            String a=null;
//            a.charAt(0);
            return x;
        }catch (Exception e)
        {
            x=200;
//            System.out.println("catch");
            return x;
        }finally {
            x=300;
//            System.out.println("finally");
//            return x;
        }
    }

}
