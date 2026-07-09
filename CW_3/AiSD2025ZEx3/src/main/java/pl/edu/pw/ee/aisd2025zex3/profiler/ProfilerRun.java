package pl.edu.pw.ee.aisd2025zex3.profiler;
import pl.edu.pw.ee.aisd2025zex3.HashDoubleHashing;
import pl.edu.pw.ee.aisd2025zex3.HashLinearProbing;
import static pl.edu.pw.ee.aisd2025zex3.dataUtils.WordsGenerator.*;
import pl.edu.pw.ee.aisd2025zex3.services.HashTable;

public class ProfilerRun {

    public static void main(String[] args) {
        int size = 524309; //{5101, 8297, 16493, 32909, 65651, 131111, 263023, 524309};

        String[] words = prepareWordsFromFile();//prepareWordsFromFile(); // prepareRandomWords();
        HashTable<String> hash = new HashDoubleHashing<>(size); // new HashDoubleHashing<>(size);

        putWordsIntoHash(hash, words);
    }

    private static void putWordsIntoHash(HashTable<String> hash, String[] words) {
        int n = words.length;

        for (int i = 0; i < n; i++) {
            hash.put(words[i]);
        }
    }
}
