package string;

import java.util.Arrays;

public class Intro {

    public static void main(String[] args) {
        String s = " SZymon dsads  a1   s3 e";

        char c = s.charAt(1);
        char[] charArray = s.toCharArray();
        s.concat(String.valueOf(c));
        String substring = s.substring(0, 1);
        System.out.println(substring);

        String newString = s.replace('S', 'C');
        System.out.println(s);
        System.out.println(newString);

        String trimered = s.trim();
        System.out.println(trimered);

        String[] split = s.split("\\s+");
        Arrays.stream(split).forEach(System.out::println);

        int ym = s.indexOf("ym");
        System.out.println(ym);

        StringBuilder sb = new StringBuilder(substring);

        sb.append("e");
        sb.reverse();


    }
}
