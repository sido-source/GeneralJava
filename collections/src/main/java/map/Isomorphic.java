package map;

import java.util.HashMap;
import java.util.HashSet;

public class Isomorphic {

        public static boolean isIsomorphic(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            // mapping between [s,t]
            HashMap<Character, Character> map = new HashMap<>();
            HashSet<Character> mappedValues = new HashSet<>();

            for (int i = 0; i < s.length(); i++) {
                char charS = s.charAt(i);
                char charT = t.charAt(i);
                // check if charS has already mapped
                if (map.containsKey(charS)) {
                    // If there's already a mapping for charS, check if it maps to charT
                    if (map.get(charS) != charT) {
                        return false;
                    }
                } else {
                    // If charT is already mapped to some other charS, return false
                    if (mappedValues.contains(charT)) {
                        return false;
                    }
                    // Add the mapping from charS to charT and mark charT as mapped
                    map.put(charS, charT);
                    mappedValues.add(charT);
                }
            }

            return true;
        }

    public static void main(String[] args) {
        isIsomorphic("baro", "ditd");
    }

}
