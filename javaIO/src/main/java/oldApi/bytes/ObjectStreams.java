package oldApi.bytes;

import java.io.*;

class Student implements Serializable {
    private String name;
    private int age;

    // Constructor, getters, setters
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
// ObjectInput/OutputStream can serialize data
public class ObjectStreams {

    public static void main(String[] args) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            Student student = new Student("Alice", 20);
            oos.writeObject(student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"))) {
            Student student = (Student) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
