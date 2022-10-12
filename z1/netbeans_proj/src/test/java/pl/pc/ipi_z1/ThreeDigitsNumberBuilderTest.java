
package pl.pc.ipi_z1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ThreeDigitsNumberBuilderTest {

    @Test
    public void createFirstAvailableNumber_ok1() {
        int[] availDigits = {2,2,0,0,0,0,0,0,0,0};
        String expectedResultNumber = Integer.toString(100);
        int expectedAvailableDigitsLeft = 1;
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        
        assertEquals(expectedResultNumber, numBuilder.createFirstAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
    }
    @Test
    public void createFirstAvailableNumber_ok2() {
        int[] availDigits = {1,2,0,0,0,0,0,0,0,0};
        String expectedResultNumber = Integer.toString(101);
        int expectedAvailableDigitsLeft = 0;
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        
        assertEquals(expectedResultNumber, numBuilder.createFirstAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
    }
    @Test
    public void createFirstAvailableNumber_ok3() {
        int[] availDigits = {2,0,1,0,0,0,0,0,0,0};
        String expectedResultNumber = Integer.toString(200);
        int expectedAvailableDigitsLeft = 0;
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        
        assertEquals(expectedResultNumber, numBuilder.createFirstAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
    }
    @Test
    public void createFirstAvailableNumber_ok4() {
        int[] availDigits = {0,0,1,0,1,0,0,0,0,1};
        String expectedResultNumber = Integer.toString(249);
        int expectedAvailableDigitsLeft = 0;
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        
        assertEquals(expectedResultNumber, numBuilder.createFirstAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
    }
    @Test
    public void createFirstAvailableNumber_notEnoughDigits1() {
        int[] availDigits = {0,0,0,0,0,0,0,0,0,0};
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        
        assertThrows(
                IndexOutOfBoundsException.class, 
                () -> { numBuilder.createFirstAvailableNumber(basket); }
        );
    }
    @Test
    public void createFirstAvailableNumber_notEnoughDigits2() {
        int[] availDigits = {0,0,0,0,0,0,0,0,0,2};
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        
        assertThrows(
                IndexOutOfBoundsException.class, 
                () -> { numBuilder.createFirstAvailableNumber(basket); }
        );
    }
    @Test
    public void createFirstAvailableNumber_notEnoughDigits3() {
        int[] availDigits = {0,0,0,0,0,0,0,0,0,3};
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        
        assertThrows(
                IndexOutOfBoundsException.class, 
                () -> { numBuilder.createFirstAvailableNumber(basket); }
        );
    }
    @Test
    public void createNextAvailableNumber_withinSameDecile1() {
        int[] availDigits = {2,2,0,0,0,0,0,0,0,0};
        String expectedResult = Integer.toString(101);
        int expectedAvailableDigitsLeft = 1;
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        numBuilder.createFirstAvailableNumber(basket);
        
        assertEquals(expectedResult, numBuilder.createNextAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits(0));
    }
    @Test
    public void createNextAvailableNumber_withinSameDecile2() {
        int[] availDigits = {1,2,1,0,0,0,0,0,0,0};
        String expectedResult = Integer.toString(102);
        int expectedAvailableDigitsLeft = 1;
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        numBuilder.createFirstAvailableNumber(basket);
        
        assertEquals(expectedResult, numBuilder.createNextAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits(1));
    }
    @Test
    public void createNextAvailableNumber_withinSameHundred1() {
        int[] availDigits = {0,2,1,0,0,0,0,0,0,0};
        String expectedResult = Integer.toString(121);
        int expectedAvailableDigitsLeft = 0;
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        numBuilder.createFirstAvailableNumber(basket);
        
        assertEquals(expectedResult, numBuilder.createNextAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
    }
    @Test
    public void createNextAvailableNumber_withinSameHundred2() {
        int[] availDigits = {1,1,1,0,0,0,0,0,0,0};
        String expectedResult = Integer.toString(120);
        int expectedAvailableDigitsLeft = 0;
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        numBuilder.createFirstAvailableNumber(basket);
        
        assertEquals(expectedResult, numBuilder.createNextAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
    }
    @Test
    public void createNextAvailableNumber_withinSameHundred3() {
        int[] availDigits = {0,0,1,1,1,0,0,0,0,0};
        String expectedResult = Integer.toString(243);
        int expectedAvailableDigitsLeft = 0;
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        numBuilder.createFirstAvailableNumber(basket);
        
        assertEquals(expectedResult, numBuilder.createNextAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
    }
    @Test
    public void createNextAvailableNumber_withinSameHundred4() {
        int[] availDigits = {0,0,1,0,1,1,0,0,0,0}; //245->254
        String expectedResult = Integer.toString(254);
        int expectedAvailableDigitsLeft = 0;
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        numBuilder.createFirstAvailableNumber(basket);
        
        assertEquals(expectedResult, numBuilder.createNextAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
    }
    @Test
    public void createNextAvailableNumber_nextHundred1() {
        int[] availDigits = {0,1,2,0,0,0,0,0,0,0};
        String expectedResult = Integer.toString(212);
        int expectedAvailableDigitsLeft = 0;
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        numBuilder.createFirstAvailableNumber(basket); //122
        
        assertEquals(expectedResult, numBuilder.createNextAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
    }
    @Test
    public void createNextAvailableNumber_nextHundred2() {
        int[] availDigits = {0,1,1,0,0,0,0,0,0,1};
        String expectedResult = Integer.toString(219);
        int expectedAvailableDigitsLeft = 0;
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        numBuilder.createFirstAvailableNumber(basket); //129
        numBuilder.createNextAvailableNumber(basket); //192
        
        assertEquals(expectedResult, numBuilder.createNextAvailableNumber(basket).toString());
        assertEquals(expectedAvailableDigitsLeft, basket.numOfAvailDigits());
    }
    @Test
    public void createNextAvailableNumber_notEnoughDigits1() {
        int[] availDigits = {2,1,0,0,0,0,0,0,0,0};
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        numBuilder.createFirstAvailableNumber(basket);
        
        assertThrows(
                IndexOutOfBoundsException.class, 
                () -> { numBuilder.createNextAvailableNumber(basket); }
        );
    }
    @Test
    public void createNextAvailableNumber_notEnoughDigits2() {
        int[] availDigits = {0,0,1,0,1,0,0,0,0,1};
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        numBuilder.createFirstAvailableNumber(basket);
        
        assertThrows(
                IndexOutOfBoundsException.class, 
                () -> { numBuilder.createNextAvailableNumber(basket); }
        );
    }
    @Test
    public void createNextAvailableNumber_notEnoughDigits3() {
        int[] availDigits = {0,0,1,0,0,2,1,0,0,0};
        DigitsBasket basket = new DigitsBasket(availDigits);
        ThreeDigitsNumberBuilderV2 numBuilder = new ThreeDigitsNumberBuilderV2();
        numBuilder.createFirstAvailableNumber(basket);
        
        assertThrows(
                IndexOutOfBoundsException.class, 
                () -> { numBuilder.createNextAvailableNumber(basket); }
        );
    }
}
