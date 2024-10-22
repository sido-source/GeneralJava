package string;

public class IndexOfFirstOccurrence {


    // #REPEAT
    public int strStr(String haystack, String needle) {
        // Edge case: if needle is empty, return 0
        if (needle.length() == 0) return 0;

        int haystackLength = haystack.length();
        int needleLength = needle.length();

        // Traverse through the haystack
        for (int i = 0; i <= haystackLength - needleLength; i++) {
            // Check if the substring of haystack starting at i matches needle
            if (haystack.substring(i, i + needleLength).equals(needle)) {
                return i;
            }
        }

        // If no match is found, return -1
        return -1;

    }
}
