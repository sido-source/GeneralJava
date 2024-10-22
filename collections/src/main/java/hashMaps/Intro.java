package hashMaps;

import java.util.HashMap;
import java.util.Map;

public class Intro {
    public static void main(String[] args) {

        Map<Character, Integer> map = new HashMap<>();
        map.put('a', 1);

        if (!map.containsKey('a')) {
            map.put('a', 2);
        } else {
            System.out.println("'a' is already in the map");
        }

        map.putIfAbsent('a', 2);

        if(map.containsValue(1)) {
            System.out.println("1 is already assigned to some key");
        }


        Integer findOrDef = map.getOrDefault('b', 100);

        map.put('a', 5);
        System.out.println(map);

        map.remove('a');
        System.out.println(map.size());
        System.out.println(map);


        map.put('a', 4);
        map.put('b', 5);
        map.put('c', 6);

//        String s  = "qweqweq qew ".split();
//        String s = map.keySet().stream().reduce("result: ", (a, b) -> a + " " + b);

    }
}
