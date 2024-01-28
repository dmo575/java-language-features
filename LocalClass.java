/* Example of:
 * Nested class
 * lambda
 * method reference
 * anonymous class
 * nested method reference
 */

import java.util.concurrent.Callable;
import java.lang.Runnable;

/* a normal class */
public class LocalClass {

    public static void main(String[] args) {
        LocalClass local = new LocalClass();
        local.local_method();
    }

    LocalClass() {
        System.out.println("Local class initialized");
    }

    class NestedClass {
        void nested_method() {System.out.println("nested class' method");}
    }

    public void local_method() {

        System.out.println("------- Anonimous class");
        // anonimous class
        AClass anon = new AClass() {
            // here we use it to override a uperclass' (AClass) method
            void method() {System.out.println("anon class method");};
            // here we use it to override an implementation of superclass' (AClass) func method from the AnInterface interface
            public void func() {System.out.println("anon interface method");}
            // ^ Those are the only uses I have found for anonymous classes, since when you create the variable (anon) you assign it the value AClass, so there is no way for
            // you to be able to call anything you define in here that AClass doesnt have access to given that your reference variable anon is indeed AClass.
        };

        anon.print_field();
        anon.method();
        anon.func();

        System.out.println("------- Lambda");
        // lambdas using the Callable and Runnable interfaces
        call_lambda_callable(() -> {System.out.println("This is a lambda (callable)"); return null;});
        call_lambda_runnable(() -> {System.out.println("This is a lambda (runnable)");});
        //lambda taking in an argument
        another_call_method_reference((String t) -> System.out.println(t));

        System.out.println("------- Method Reference");
        // here we are passing the method method_reference from anon, that takes in no parameters and returns nothing, to a function that expects a method just like that
        call_method_reference(anon::method_reference);


        System.out.println("------- Nested method's reference");
        // this is basically the same thing as Method Reference's section but from a nested class.
        NestedClass nested = new NestedClass();
        call_method_reference(nested::nested_method);

        /* The only thing that you need to worry about with the interfaces is that the method that takes in a lambda (anon class with single method) or method reference
         * needs to be expecting a functional interface type whos method signature aligns with the one you are passing in.
         * For complex method signatures you will have to create your own just like AnInterface, but for most generic and simple ones, Java offers you
         * Callable<T> and Runnable.
         * 
         * Callable<T>
         * T is the return type of the method you pass in
         * If you want to use Callable<T> with a method that has no return, you will have to explicitly return Void or use Runnable
         * 
         * Runnable
         * No return type
         * 
         * If you want to also take in arguments, there are some variations of Callable and Runnable I believe that use generics but for the most complex cases you will have
         * to make your own interface.
         * 
         * Java accepts any lambda and method reference that matches the recepients interface's signature, meaning parameters and return type.
         */
    }

    // Expects a function that takes no arguments and returns null, must explicitly return null (thats what the Void means, else change the return type there)
    public void call_lambda_callable(Callable<Void> cal) {
        try 
        {
            cal.call();
        }
        catch(Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }

    // expects a lambda that has no return type and takes no arguments
    public void call_lambda_runnable(Runnable func) {
        func.run();
    }
    
    // same as above but this time we define the interface
    public void call_method_reference(AnInterface in) {
        in.func();
    }

    // this time our interface is one with a method that takes in an argument.
    public void another_call_method_reference(AnotherInterface in) {
        in.anotherFunc("lambda that takes in an argument");
    }

}

class AClass implements AnInterface{
    String field = "field data";
    void method() {System.out.println("class method");}
    void print_field() {System.out.println(field);}
    public void func() {System.out.println("interface method");}
    public void method_reference() {System.out.println("method reference");}
}

interface AnInterface {
    void func();
}

interface AnotherInterface {
    void anotherFunc(String t);
}