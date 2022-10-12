
package pl.pc.ipi_z1;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SingleOctetBuilderTest {
    private boolean shouldPrintResults=true;
    private void printResults(List<StringBuffer> results){
        results.forEach(
                result -> System.out.println('['+result.toString()+']')
            );
    }
    @Test
    public void howtobasic_ok1() {
        int[] availDigits = {0,1,0,0,0,0,0,0,0,0};
        int expectedNumberOfResults = 1;
        String expectedResult = "1";
        DigitsBasket basket = new DigitsBasket(availDigits);
        SingleOctetBuilder builder = new SingleOctetBuilder();
        
        var result = builder.createOctet(basket, 0, true);
        
        assertEquals(expectedNumberOfResults, result.size());
        assertEquals(expectedResult, result.get(0).toString());
    }
    @Test
    public void howtobasic_ok2() {
        int[] availDigits = {0,4,0,0,0,0,0,0,0,0};
        int expectedNumberOfResults = 1;
        String expectedResult = "1.1.1.1";
        DigitsBasket basket = new DigitsBasket(availDigits);
        SingleOctetBuilder builder = new SingleOctetBuilder();
        
        var result = builder.createOctet(basket, 3, true);
        
        assertEquals(expectedNumberOfResults, result.size());
        assertEquals(expectedResult, result.get(0).toString());
    }
    @Test
    public void howtobasic_ok3() {
        int[] availDigits = {0,2,0,0,0,0,0,0,0,0};
        int expectedNumberOfResults = 1;
        DigitsBasket basket = new DigitsBasket(availDigits);
        SingleOctetBuilder builder = new SingleOctetBuilder();
        
        var result = builder.createOctet(basket, 0, true);
        if (shouldPrintResults)
            printResults(result);
        assertEquals(expectedNumberOfResults, result.size());
    }
    @Test
    public void howtobasic_ok4() {
        int[] availDigits = {0,2,0,0,0,0,0,0,0,0};
        int expectedNumberOfResults = 2;
        DigitsBasket basket = new DigitsBasket(availDigits);
        SingleOctetBuilder builder = new SingleOctetBuilder();
        
        var result = builder.createOctet(basket, 0, false);
        if (shouldPrintResults)
            printResults(result);
        assertEquals(expectedNumberOfResults, result.size());
    }
    @Test
    public void howtobasic_ok5() {
        int[] availDigits = {0,2,1,0,0,0,0,0,0,0};
        int expectedNumberOfResults = 6;
        DigitsBasket basket = new DigitsBasket(availDigits);
        SingleOctetBuilder builder = new SingleOctetBuilder();
        
        var result = builder.createOctet(basket, 1, true);
        if (shouldPrintResults)
            printResults(result);
        assertEquals(expectedNumberOfResults, result.size());
    }
    //Poniższy test odpowiwada danym z treści zadania, ale wynik jest na tyle duży, że trudno go sprawdzić.
    //Chyba poprawny wynik to 6720 kombinacji.
    /*
    @Test
    public void howtobasic_tresc1() {
        int[] availDigits = {0,3,2,1,0,5,0,0,0,0};
        int expectedNumberOfResults = 6;
        DigitsBasket basket = new DigitsBasket(availDigits);
        SingleOctetBuilder builder = new SingleOctetBuilder();
        
        var result = builder.createOctet(basket, 3, true);
        if (shouldPrintResults)
            printResults(result);
        assertEquals(expectedNumberOfResults, result.size());
    }
    */
    @Test
    public void howtobasic_tresc2() {
        int[] availDigits = {0,5,0,0,0,0,0,0,0,0};
        int expectedNumberOfResults = 4;
        DigitsBasket basket = new DigitsBasket(availDigits);
        SingleOctetBuilder builder = new SingleOctetBuilder();
        
        var result = builder.createOctet(basket, 3, true);
        if (shouldPrintResults)
            printResults(result);
        assertEquals(expectedNumberOfResults, result.size());
    }
    @Test
    public void howtobasic_tresc3() {
        int[] availDigits = {2,3,0,0,0,0,0,0,0,0};
        int expectedNumberOfResults = 24;
        DigitsBasket basket = new DigitsBasket(availDigits);
        SingleOctetBuilder builder = new SingleOctetBuilder();
        
        var result = builder.createOctet(basket, 3, true);
        if (shouldPrintResults)
            printResults(result);
        assertEquals(expectedNumberOfResults, result.size());
    }
    @Test
    public void howtobasic_ok6() {
        int[] availDigits = {0,1,1,1,0,0,0,0,0,1};
        int expectedNumberOfResults = 24;
        DigitsBasket basket = new DigitsBasket(availDigits);
        SingleOctetBuilder builder = new SingleOctetBuilder();
        
        var result = builder.createOctet(basket, 3, true);
        if (shouldPrintResults)
            printResults(result);
        assertEquals(expectedNumberOfResults, result.size());
    }
}
