package pl.edu.pw.ee.aisd2025zex2;

public class HashListChainingMultiplicativeHashing<T extends Comparable<T>> extends HashListChaining<T> {

    public HashListChainingMultiplicativeHashing() {
        super();
    }

    public HashListChainingMultiplicativeHashing(int size) {
        super(size);
    }

    @Override
    public int countHashId(T value) {
        int hashCode = value.hashCode() & Integer.MAX_VALUE;
        double A = Math.E - 2;
        double fractionalPart = (hashCode * A) % 1;



        return (int) Math.floor(fractionalPart * size);
    }
}
