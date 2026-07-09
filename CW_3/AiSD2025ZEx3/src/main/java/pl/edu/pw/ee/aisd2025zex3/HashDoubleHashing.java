package pl.edu.pw.ee.aisd2025zex3;



public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAddressing<T> {
    
    public HashDoubleHashing() {
        super();
    }

    public HashDoubleHashing(int size) {
        super(size);
    }
    
    @Override
    int hashFunc(int key, int i) {
        int m = getSize();
        key = key & Integer.MAX_VALUE;
        int hash = Math.abs((key % m + i*(1 + key % (m-1)))%m);
        
        return hash;
    }

}
