/* Example of an interface
 * 
 * do:
 * javac Interfaces.java
 * java Interfaces
 */

public class Interfaces {
 
    String field;
    public static void main(String[] args) {
        
        AClass cl = new AClass();
        cl.public_method();

        AnInterface in = new AClass();
        //in.public_method();

        AnInterface.SubClass sub = new AnInterface.SubClass();
        System.out.println(sub.field);
    }
}

class AClass implements AnInterface {

    public void public_method() {
        System.out.println("implementation of interface's public_method");
        System.out.println(SubClass.static_field);

        SubClass sub = new SubClass();
        System.out.println(sub.field);
    }
}

interface AnInterface {
    public void public_method();
    default void default_method() {
        System.out.println("default_method");
        /* Defaults methods got introduces in Java 8 to allow programmers to modify their interfaces without breaking compatability with other code that already implemented
         * the interface.
         */
    }
    static void static_method() {
        System.out.println("static_method");
    };

    class SubClass {
        String field = "subclass field";
        static String static_field = "subclass static field";
    }
}