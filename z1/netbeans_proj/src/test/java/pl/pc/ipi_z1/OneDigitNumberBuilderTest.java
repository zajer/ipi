
package pl.pc.ipi_z1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class OneDigitNumberBuilderTest {
    
    @Test
    public void createFirstAvailableNumber_ok1() {
        int[] availDigits = {1,0,0,0,0,0,0,0,0,0};
        String expectedResultNumber = Integer.toString(0);
        int expectedAvailableDigitsLeft = 0;
        DigitsBasket basket = new DigitsBasket(availDigits);
        OneDigitNumberBuilder numBuilder = new OneDigitNumberBuilder();
        
        assertEquals(expectedResultNumber, numBuilder.createFirstAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
    }
    @Test
    public void createFirstAvailableNumber_ok2() {
        int[] availDigits = {0,0,0,0,0,0,0,7,0,0};
        String expectedResultNumber = Integer.toString(7);
        int expectedAvailableDigitsLeft = 6;
        DigitsBasket basket = new DigitsBasket(availDigits);
        OneDigitNumberBuilder numBuilder = new OneDigitNumberBuilder();
        
        assertEquals(expectedResultNumber, numBuilder.createFirstAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
    }
    @Test
    public void createFirstAvailableNumber_ok3() {
        int[] availDigits = {1,0,0,2,0,0,5,0,7,0};
        String expectedResultNumber = Integer.toString(0);
        int expectedAvailableDigitsLeft = 14;
        DigitsBasket basket = new DigitsBasket(availDigits);
        OneDigitNumberBuilder numBuilder = new OneDigitNumberBuilder();
        
        assertEquals(expectedResultNumber, numBuilder.createFirstAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
    }
    @Test
    public void createFirstAvailableNumber_notEnoughDigits1() {
        int[] availDigits = {0,0,0,0,0,0,0,0,0,0};
        DigitsBasket basket = new DigitsBasket(availDigits);
        OneDigitNumberBuilder numBuilder = new OneDigitNumberBuilder();
        
        assertThrows(
                IndexOutOfBoundsException.class, 
                () -> { numBuilder.createFirstAvailableNumber(basket); }
        );
    }
    @Test
    public void createNextAvailableNumber_ok1() {
        int[] availDigits = {0,2,1,0,0,0,0,0,0,0};
        String expectedResult = Integer.toString(2);
        int expectedAvailableDigitsLeft = 2;
        DigitsBasket basket = new DigitsBasket(availDigits);
        OneDigitNumberBuilder numBuilder = new OneDigitNumberBuilder();
        numBuilder.createFirstAvailableNumber(basket);
        
        assertEquals(expectedResult, numBuilder.createNextAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits(1));
    }
    @Test
    public void createNextAvailableNumber_ok2() {
        int[] availDigits = {1,0,0,0,0,0,0,0,0,1};
        String expectedResult = Integer.toString(9);
        int expectedAvailableDigitsLeft = 1;
        DigitsBasket basket = new DigitsBasket(availDigits);
        OneDigitNumberBuilder numBuilder = new OneDigitNumberBuilder();
        numBuilder.createFirstAvailableNumber(basket);
        
        assertEquals(expectedResult, numBuilder.createNextAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits(0));
    }
    @Test
    public void createFirstAvailableNumber_outOfRange() {
        int[] availDigits = {0,0,0,0,0,0,0,0,0,1};
        DigitsBasket basket = new DigitsBasket(availDigits);
        OneDigitNumberBuilder numBuilder = new OneDigitNumberBuilder();
        numBuilder.createFirstAvailableNumber(basket);
        assertThrows(
                IndexOutOfBoundsException.class, 
                () -> { numBuilder.createNextAvailableNumber(basket); }
        );
    }
}
