
package pl.pc.ipi_z1;

import lombok.Getter;


public class OneDigitNumberBuilder implements NumberBuilder{
    @Getter private int currentNumber =-1;
    
    public StringBuffer createFirstAvailableNumber(DigitsBasket digitsToUse){
        StringBuffer result = new StringBuffer();
        try {
            currentNumber = digitsToUse.takeFirstAvailDigit();
            result.append(currentNumber);
            
            return result;
        }
        catch (IllegalStateException e) {
            throw new IndexOutOfBoundsException("Not enough digits are available to create 100/255 type number");
        }
    }
    public StringBuffer createNextAvailableNumber(DigitsBasket digitsToUse){
        if (currentNumber == -1)
            throw new IllegalStateException("Calling 'next' before 'first' is illegal!");
        
        StringBuffer result = new StringBuffer();
        String excpMessage = "There are no more numbers in this range to construct";
        
        try {
            digitsToUse.makeDigitsAvail(1); //zwalniam cyfrę reprezentującą 10^0
            currentNumber = digitsToUse.takeNextAvailDigit(currentNumber);

            result.append(currentNumber);
            return result;
        }
        catch (IllegalStateException e1){
            throw new IndexOutOfBoundsException("Not enough digits are available to create 0/9 type of number");
        }
        
    }
    
}
