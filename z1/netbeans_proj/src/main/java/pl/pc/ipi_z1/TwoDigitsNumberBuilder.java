
package pl.pc.ipi_z1;

import lombok.Getter;


public class TwoDigitsNumberBuilder {
    @Getter private int currentFirstDigit =-1;
    @Getter private int currentSecondDigit = -1;
    public StringBuffer createFirstAvailableNumber(DigitsBasket digitsToUse){
        int usedDigits = 0;
        StringBuffer result = new StringBuffer();
        try {
            var secondDigit = digitsToUse.takeFirstPositiveDigitAvail();
            usedDigits++;
            currentSecondDigit = secondDigit;
            
            var firstDigit = digitsToUse.takeFirstAvailDigit();
            usedDigits++;
            currentFirstDigit = firstDigit;
            
            result.append(secondDigit);
            result.append(firstDigit);
            return result;
        }
        catch (IllegalStateException e) {
            digitsToUse.makeDigitsAvail(usedDigits);
            throw new IndexOutOfBoundsException("Not enough digits are available to create 10/99 type number");
        }
    }
    public StringBuffer createNextAvailableNumber(DigitsBasket digitsToUse){
        if (currentFirstDigit == -1 || currentSecondDigit == -1)
            throw new IllegalStateException("Calling 'next' before 'first' is illegal!");
        
        int usedDigits = 0;
        StringBuffer result = new StringBuffer();
        String excpMessage = "There are no more numbers in this range to construct";
        
        if (currentFirstDigit == 9 && currentSecondDigit == 9)
            throw new IndexOutOfBoundsException(excpMessage);
        
        try {
            digitsToUse.makeDigitsAvail(1); //zwalniam cyfrę reprezentującą 10^0
            var firstDigit = digitsToUse.takeNextAvailDigit(currentFirstDigit);
            currentFirstDigit=firstDigit;

            result.append(currentSecondDigit);
            result.append(firstDigit);
            return result;
        }
        catch (IllegalStateException e1){
            try{
                digitsToUse.makeDigitsAvail(1); //zwalniam cyfrę reprezentującą 10^1 
                var secondDigit = digitsToUse.takeNextPositiveDigitAvail(currentSecondDigit);
                usedDigits++;

                var firstDigit = digitsToUse.takeFirstAvailDigit();
                usedDigits++;
                currentFirstDigit=firstDigit;
                currentSecondDigit=secondDigit;

                result.append(secondDigit);
                result.append(firstDigit);
                return result;
            }
            catch (IllegalStateException e2){
                digitsToUse.makeDigitsAvail(usedDigits);
                throw new IndexOutOfBoundsException("Not enough digits are available to create 10/99 type of number");
            }
        }
        
    }
    //public int createOneDigitNumber(int greaterThan)
    //public int createTwoDigitsNumber
    //public int createThreeDigitsNumber
}
