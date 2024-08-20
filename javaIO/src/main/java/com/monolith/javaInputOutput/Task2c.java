package com.monolith.javaInputOutput;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Task2c {

    public static void main(String[] args) {
        String str = "Tschüssł";
        byte[] bytesOfString = str.getBytes();
        String encoded_String = new String(bytesOfString, Charset.forName("windows-1252"));
//        assertNotEquals(encoded_String, str);

        System.out.println(encoded_String);
        ByteBuffer encode = StandardCharsets.UTF_8.encode(str);
        System.out.println(Charset.forName("windows-1252").decode(encode));
        System.out.println(new String(str.getBytes(StandardCharsets.UTF_8), Charset.forName("windows-1252")));
        System.out.println(StandardCharsets.UTF_8.decode(encode));
        System.out.println(StandardCharsets.UTF_8.decode(StandardCharsets.UTF_8.encode(str)));

        String polishText = "zażółć gęślą jaźń";
        byte[] utf8Bytes = polishText.getBytes(StandardCharsets.UTF_8);
        String decodedText = new String(utf8Bytes, StandardCharsets.UTF_8);
        System.out.println(decodedText);  // Outputs: zażółć gęślą jaźń

        String decodedText1 = new String(utf8Bytes, Charset.forName("windows-1250"));
        System.out.println(decodedText1);

        // correct
        //https://www.ascii-code.com/CP1250/191
        String s = "П";
        byte[] bytezz = s.getBytes(Charset.forName("ISO-8859-5"));
        System.out.println(new String(bytezz, Charset.forName("windows-1250")));

        ByteBuffer encode1 = Charset.forName("ISO-8859-5").encode(s);
        System.out.println(Charset.forName("windows-1250").decode(encode1));

        // hebrew
        String hebrew = "ףסנ" ;
        byte[] hebrewBytes = hebrew.getBytes(Charset.forName("ISO-8859-8"));
        System.out.println(new String (hebrewBytes, Charset.forName("windows-1250")));

    }
}
