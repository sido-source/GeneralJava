package reduce;

import java.util.Arrays;

public class Concatenation {

    public static void main(String[] args) {
        String[] words = {"Hello", "World", "asds"};

        String concat = String.join(" ", words);

        // Why String::concat Works in reduce
        //The key here is how method references work in Java.
        //
        //The method String::concat refers to an instance method of String.
        //When you use String::concat as a method reference, it works like this:
        //The first String is the instance on which the method is called.
        //The second String is the parameter passed to the concat method.
        //In a method reference like String::concat, the first argument to the method reference is the receiver (or the object on which the method is called),
        // and the second argument is the parameter passed to the method. This allows String::concat to behave as a BinaryOperator<String>,
        // even though concat only explicitly takes one argument.

        String reduce = Arrays.stream(words)
                .reduce("", String::concat);

        System.out.println(reduce);

        String result = Arrays.stream(words)
                .reduce(new StringBuilder(), StringBuilder::append, StringBuilder::append)
                .toString();
        System.out.println(result);  // Output: "Hello World"
    }
}
