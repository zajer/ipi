
package pl.pc.ipi_z1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DigitsBasketTest {
    
    @Test
    public void takeFirstAvailDigit_OneAvailable() {
        int[] availDigits = {0,1,0,0,0,0,0,0,0,0};
        int expectedResult = 1;
        DigitsBasket basket = new DigitsBasket(availDigits);
        assertEquals(expectedResult, basket.takeFirstAvailDigit());
    }
    @Test
    public void takeFirstAvailDigit_OneAvailable2() {
        int[] availDigits = {0,7,0,0,0,0,0,0,0,0};
        int expectedResult = 1;
        DigitsBasket basket = new DigitsBasket(availDigits);
        assertEquals(expectedResult, basket.takeFirstAvailDigit());
    }
    @Test
    public void takeFirstAvailDigit_MultipleAvailable() {
        int[] availDigits = {0,1,1,1,0,0,1,0,0,0};
        int expectedResult = 1;
        DigitsBasket basket = new DigitsBasket(availDigits);
        assertEquals(expectedResult, basket.takeFirstAvailDigit());
    }
    @Test
    public void takeFirstAvailDigit_MultipleAvailable2() {
        int[] availDigits = {0,7,3,2,0,0,5,0,0,0};
        int expectedResult = 1;
        DigitsBasket basket = new DigitsBasket(availDigits);
        assertEquals(expectedResult, basket.takeFirstAvailDigit());
    }
    @Test
    public void takeFirstAvailDigit_NoAvailable() {
        int[] availDigits = {0,0,0,0,0,0,0,0,0,0};
        DigitsBasket basket = new DigitsBasket(availDigits);
        assertThrows(
                IllegalStateException.class, 
                () -> { basket.takeFirstAvailDigit(); }
        );
    }
    @Test
    public void takeFirstAvailDigit_NotEnoughAvailable() {
        int[] availDigits = {0,0,0,0,0,0,3,0,0,0};
        DigitsBasket basket = new DigitsBasket(availDigits);
        assertThrows(
                IllegalStateException.class, 
                () -> { 
                    basket.takeFirstAvailDigit();
                    basket.takeFirstAvailDigit();
                    basket.takeFirstAvailDigit();
                    basket.takeFirstAvailDigit();
                }
        );
    }
    @Test
    public void takeNextAvailDigit() {
        int[] availDigits = {0,1,1,0,0,0,0,0,0,0};
        int expectedResult = 2;
        DigitsBasket basket = new DigitsBasket(availDigits);
        assertEquals(expectedResult, basket.takeNextAvailDigit(1));
    }
    /*
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
    */
}
