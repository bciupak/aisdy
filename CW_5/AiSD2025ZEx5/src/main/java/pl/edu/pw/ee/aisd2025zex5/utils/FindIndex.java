package pl.edu.pw.ee.aisd2025zex5.utils;

public class FindIndex {
    // metoda do znajdowania indeksu w tablicy tokenów
    public static int findIndex(String[] tokens, int uniqueTokens, String token) {
        for (int i = 0; i < uniqueTokens; i++) {
            if (tokens[i] != null && tokens[i].equals(token)) {
                return i;
            }
        }
        return -1;
    }
}
