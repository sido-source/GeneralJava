package string;

public class IsSubsequence {

    public static void main(String[] args) {
        System.out.println(isSubsequence("acb", "ahbgdc"));
    }

    public static boolean isSubsequence(String s, String t) {

        if (s.length() == 0) return true;

        int indexS = 0;
        boolean res= false;

        for (Character c : t.toCharArray()) {
            if (s.charAt(indexS) == c) {
                indexS++;
                if (indexS == s.length()){
                    return true;
                }
            }
        }

        return false;
    }
}
