package map;

import java.util.*;

public class GroupAnagram {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String word: strs){
            char[] chars = word.toCharArray();

            // in order to hash properly we have to sort the chars
            Arrays.sort(chars);
            String sortedWord = new String(chars);

            map.putIfAbsent(sortedWord, new ArrayList<>());
            map.get(sortedWord).add(word);
        }

        return new ArrayList<>(map.values());
    }

}
