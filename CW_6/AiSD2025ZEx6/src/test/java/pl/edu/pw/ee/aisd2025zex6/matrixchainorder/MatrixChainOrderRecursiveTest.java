package pl.edu.pw.ee.aisd2025zex6.matrixchainorder;

import org.junit.jupiter.api.Test;

public class MatrixChainOrderRecursiveTest extends MatrixChainOrderTest {

    public MatrixChainOrderRecursiveTest() {
        super(new MatrixChainOrderRecursive());
    }
/* 
    wprowadziłem zmiane do metody findBoundaryFor4s tak aby inkrementowala o 1 a nie podwajala,
    ponieważ zbyt długo to trwało i nie dało się znaleźć granicy w "rozsądnym czasie"

 */
    @Test
    void performanceTestLinear() {
        final int boundary = super.findBoundaryFor4s(matrixChain, true);
        System.out.printf("Performance boundary for %s: N = %d%n", matrixChain.getClass().getSimpleName(), boundary);
    }
    
    @Override
    public int findBoundaryFor4s(MatrixChainOrder mc) {
        return super.findBoundaryFor4s(mc, true);
    }

}
