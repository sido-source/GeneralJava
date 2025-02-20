package oldApi.javaInputOutput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class WrapperForSystemOut {

    //https://codegym.cc/pl/quests/lectures/pl.questcore.level09.lecture07
    public static void main(String[] args) throws Exception
    {
        //Zapisz bie??cy PrintStream w specjalnej zmiennej
        PrintStream consoleStream = System.out;

        //Utw?rz dynamiczn? tablic?
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //Utw?rz adapter dla klasy PrintStream
        PrintStream stream = new PrintStream(outputStream);
        //Ustaw go jako bie??cy System.out
        System.setOut(stream);

        //Wywo?aj funkcj?, kt?ra nie wie nic o naszych zmianach
        printSomething();

        //Konwertuj dane zapisane w naszym ByteArray na ci?g
        String result = outputStream.toString();

        //Przywr?? wszystko do poprzedniego stanu
        System.setOut(consoleStream);

        //Odwr?? ci?g
        StringBuilder stringBuilder = new StringBuilder(result);
        stringBuilder.reverse();
        String reverseString = stringBuilder.toString();

        //Wypisz go do konsoli
        System.out.println(reverseString);
    }

    public static void printSomething()
    {
        System.out.println("Cze??");
        System.out.println("Mam na imi? Amigo");
        System.out.println("Pa, pa!");
    }
}
