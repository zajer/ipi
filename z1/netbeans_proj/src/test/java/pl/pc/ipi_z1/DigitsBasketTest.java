
package pl.pc.ipi_z1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DigitsBasketTest {
    
    @Test
    public void takeFirstAvailDigit() {
        int[] availDigits = {1};
        int expectedResult = 1;
        DigitsBasket basket = new DigitsBasket(availDigits);
        assertEquals(expectedResult, basket.takeFirstAvailDigit());
    }
    
    @ParameterizedTest(name = "in1{0} in2{1} in3{2} out{3}")
    @CsvSource({
                    "1,    1,   1,  1",
                    "1,    2,   3,  1",
                    "4,  51, 100,  51",
                    "1,  100, 101,  101"
    })
    public void takeAvailDigits(int firstIn, int secondIn, int thirdIn, int out) {
        //int[] availDigits = {firstIn;secondIn};
        int[] availDigits = {firstIn, secondIn, thirdIn};
        DigitsBasket basket = new DigitsBasket(availDigits);
        
    }
    
}
