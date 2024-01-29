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

        // error, all interface fields are implicitly static public and final
        //cl.public_field = "some value";

        System.out.println("This is \n\t a sample text");
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

    // all these are ALL public static final fields implicitly, contrary to what they look like
    final String field = "first field";
    static String static_field = "second field";
    public String public_field = "third field";

    // all methods are implicitly public
    public void public_method();
    default void default_method() {
        System.out.println("default_method");
        /* Defaults methods got introduces in Java 8 to allow programmers to modify their interfaces without breaking compatability with other code that already implemented
         * the interface.
         * 
         * The difference between these and static methods is that default ones are instance methods while static ones are class/interface methods.
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