
package pl.pc.ipi_z1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TwoDigitsNumberBuilderTest {
    

    @Test
    public void createFirstAvailableNumber_ok1() {
        int[] availDigits = {0,1,0,1,0,0,0,0,0,0};
        String expectedResultNumber = Integer.toString(13);
        int expectedAvailableDigitsLeft = 0;
        DigitsBasket basket = new DigitsBasket(availDigits);
        TwoDigitsNumberBuilder numBuilder = new TwoDigitsNumberBuilder();
        
        assertEquals(expectedResultNumber, numBuilder.createFirstAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
    }
    @Test
    public void createFirstAvailableNumber_ok2() {
        int[] availDigits = {0,0,3,0,0,0,0,0,0,0};
        String expectedResult = Integer.toString(22);
        int expectedAvailableDigitsLeft = 1;
        DigitsBasket basket = new DigitsBasket(availDigits);
        TwoDigitsNumberBuilder numBuilder = new TwoDigitsNumberBuilder();
        
        assertEquals(expectedResult, numBuilder.createFirstAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
    }
    @Test
    public void createFirstAvailableNumber_notEnoughDigits1() {
        int[] availDigits = {0,0,0,0,0,0,0,0,0,0};
        DigitsBasket basket = new DigitsBasket(availDigits);
        TwoDigitsNumberBuilder numBuilder = new TwoDigitsNumberBuilder();
        
        assertThrows(
                IndexOutOfBoundsException.class, 
                () -> { numBuilder.createFirstAvailableNumber(basket); }
        );
    }
    @Test
    public void createFirstAvailableNumber_notEnoughDigits2() {
        int[] availDigits = {7,0,0,0,0,0,0,0,0,0};
        DigitsBasket basket = new DigitsBasket(availDigits);
        TwoDigitsNumberBuilder numBuilder = new TwoDigitsNumberBuilder();
        
        assertThrows(
                IndexOutOfBoundsException.class, 
                () -> { numBuilder.createFirstAvailableNumber(basket); }
        );
    }
    @Test
    public void createNextAvailableNumber_withinSameDecile1() {
        int[] availDigits = {0,2,1,0,0,0,0,0,0,0};
        String expectedResult = Integer.toString(12);
        int expectedAvailableDigitsLeft = 1;
        DigitsBasket basket = new DigitsBasket(availDigits);
        TwoDigitsNumberBuilder numBuilder = new TwoDigitsNumberBuilder();
        numBuilder.createFirstAvailableNumber(basket);
        
        assertEquals(expectedResult, numBuilder.createNextAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits(1));
    }
    @Test
    public void createNextAvailableNumber_withinSameDecile2() {
        int[] availDigits = {0,1,0,0,2,0,0,0,0,1};
        String expectedResult = Integer.toString(19);
        int expectedAvailableDigitsLeft = 2;
        DigitsBasket basket = new DigitsBasket(availDigits);
        TwoDigitsNumberBuilder numBuilder = new TwoDigitsNumberBuilder();
        numBuilder.createFirstAvailableNumber(basket);
        
        assertEquals(expectedResult, numBuilder.createNextAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits(4));
    }
    @Test
    public void createNextAvailableNumber_withinSameDecile3() {
        int[] availDigits = {1,0,0,2,0,0,0,0,0,0};
        String expectedResult = Integer.toString(33);
        int expectedAvailableDigitsLeft = 1;
        DigitsBasket basket = new DigitsBasket(availDigits);
        TwoDigitsNumberBuilder numBuilder = new TwoDigitsNumberBuilder();
        numBuilder.createFirstAvailableNumber(basket);
        
        assertEquals(expectedResult, numBuilder.createNextAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits(0));
    }
    @Test
    public void createNextAvailableNumber_differentDecile1() {
        int[] availDigits = {0,1,0,0,0,0,0,0,0,1};
        String expectedResult = Integer.toString(91);
        int expectedAvailableDigitsLeft = 0;
        DigitsBasket basket = new DigitsBasket(availDigits);
        TwoDigitsNumberBuilder numBuilder = new TwoDigitsNumberBuilder();
        numBuilder.createFirstAvailableNumber(basket);
        
        assertEquals(expectedResult, numBuilder.createNextAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
    }
    @Test
    public void createNextAvailableNumber_differentDecile2() {
        int[] availDigits = {1,1,1,0,0,0,0,0,0,0};
        String expectedResult = Integer.toString(20);
        int expectedAvailableDigitsLeft = 1;
        DigitsBasket basket = new DigitsBasket(availDigits);
        TwoDigitsNumberBuilder numBuilder = new TwoDigitsNumberBuilder();
        numBuilder.createFirstAvailableNumber(basket);
        numBuilder.createNextAvailableNumber(basket);
        
        assertEquals(expectedResult, numBuilder.createNextAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
    }
    @Test
    public void createNextAvailableNumber_notEnoughDigits() {
        int[] availDigits = {3,1,0,0,0,0,0,0,0,0};
        
        DigitsBasket basket = new DigitsBasket(availDigits);
        TwoDigitsNumberBuilder numBuilder = new TwoDigitsNumberBuilder();
        numBuilder.createFirstAvailableNumber(basket);
        
        assertThrows(
                IndexOutOfBoundsException.class, 
                () -> { numBuilder.createNextAvailableNumber(basket); }
        );
    }
    @Test
    public void createNextAvailableNumber_outOfRange() {
        int[] availDigits = {0,0,0,0,0,0,0,0,0,2};
        
        DigitsBasket basket = new DigitsBasket(availDigits);
        TwoDigitsNumberBuilder numBuilder = new TwoDigitsNumberBuilder();
        numBuilder.createFirstAvailableNumber(basket);
        
        assertThrows(
                IndexOutOfBoundsException.class, 
                () -> { numBuilder.createNextAvailableNumber(basket); }
        );
    }
}
