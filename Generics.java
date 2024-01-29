
public class Generics {
    
    public static void main(String[] args) {
        
        Box<String> myStr = new Box<String>();
        myStr.set("Heyo");
        System.out.println(myStr.get());
    }
}

class AClass{
    public <T> void generic_method() {

    }
}


class Box<T> {

    private T obj;
    public void set(T obj) { this.obj = obj; }
    public T get() {return obj;}
    // This generic method expects a K type that extends from AClass and implements the interface AnInterface, this is known as a bounded type parameter
    // notice how the keyword extends is used for both class an interface
    public <K extends AClass & AnInterface, V> void generic_method() {}
}

interface AnInterface {

    
}