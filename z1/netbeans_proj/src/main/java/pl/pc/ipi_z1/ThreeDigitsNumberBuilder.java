
package pl.pc.ipi_z1;

import java.util.concurrent.atomic.AtomicInteger;
import javax.naming.OperationNotSupportedException;
import lombok.Getter;


public class ThreeDigitsNumberBuilder {
    @Getter private int currentFirstDigit =-1;
    @Getter private int currentSecondDigit = -1;
    @Getter private int currentThirdDigit = -1;

    public StringBuffer createFirstAvailableNumber(DigitsBasket digitsToUse){
        int usedDigits = 0;
        StringBuffer result = new StringBuffer();
        try {
            var thirdDigit = digitsToUse.takeFirstOneOrTwoAvail();
            usedDigits++;
            currentThirdDigit = thirdDigit;
            
            result.append(thirdDigit);
            
            if (thirdDigit == 1) {
                var secondDigit = digitsToUse.takeFirstAvailDigit();
                usedDigits++;
                currentSecondDigit = secondDigit;

                var firstDigit = digitsToUse.takeFirstAvailDigit();
                usedDigits++;
                currentFirstDigit = firstDigit;

                result.append(secondDigit);
                result.append(firstDigit);
            }
            else {
                var secondDigit = digitsToUse.takeFirstDigitUpToFiveAvail();
                usedDigits++;
                currentSecondDigit = secondDigit;
                result.append(secondDigit);
                
                if (secondDigit < 5){
                    var firstDigit = digitsToUse.takeFirstAvailDigit();
                    usedDigits++;
                    currentFirstDigit = firstDigit;
                    result.append(firstDigit);
                }
                else {
                    var firstDigit = digitsToUse.takeFirstDigitUpToFiveAvail();
                    usedDigits++;
                    currentFirstDigit = firstDigit;
                    result.append(firstDigit);
                }
            }
            
            return result;
        }
        catch (IllegalStateException e) {
            digitsToUse.makeDigitsAvail(usedDigits);
            throw new IndexOutOfBoundsException("Not enough digits are available to create 100/255 type number");
        }
    }
    private StringBuffer createNextAvailableNumber1XXCase(DigitsBasket digitsToUse, AtomicInteger alreadyFreed){
        StringBuffer result = new StringBuffer();
        int usedDigits = 0;
        try {
            digitsToUse.makeDigitsAvail(1); //zwalniam cyfrę reprezentującą 10^0
            alreadyFreed.set(1);
            
            var firstDigit = digitsToUse.takeNextAvailDigit(currentFirstDigit);
            currentFirstDigit=firstDigit;
            
            result.append(currentThirdDigit);
            result.append(currentSecondDigit);
            result.append(firstDigit);
            return result;
        }
        catch (IllegalStateException e1){
            try{
                digitsToUse.makeDigitsAvail(1); //zwalniam cyfrę reprezentującą 10^1 
                alreadyFreed.set(2);
                var secondDigit = digitsToUse.takeNextPositiveDigitAvail(currentSecondDigit);
                usedDigits++;

                var firstDigit = digitsToUse.takeFirstAvailDigit();
                usedDigits++;
                currentFirstDigit=firstDigit;
                currentSecondDigit=secondDigit;
                
                result.append(currentThirdDigit);
                result.append(secondDigit);
                result.append(firstDigit);
                return result;
            }
            catch (IllegalStateException e2){
                digitsToUse.makeDigitsAvail(usedDigits);
                
                usedDigits = 0;
                try{
                    if (alreadyFreed.get() <= 2){
                        digitsToUse.makeDigitsAvail(1); //zwalniam cyfrę reprezentującą 10^2 
                        alreadyFreed.set(3);
                    }

                    var thirdDigit = digitsToUse.takeFirstTwoAvail();
                    usedDigits++;
                    currentThirdDigit=thirdDigit;

                    var secondDigit = digitsToUse.takeFirstDigitSmallerThanFiveAvail();
                    usedDigits++;

                    var firstDigit = digitsToUse.takeFirstAvailDigit();
                    usedDigits++;
                    currentFirstDigit=firstDigit;
                    currentSecondDigit=secondDigit;

                    result.append(thirdDigit);
                    result.append(secondDigit);
                    result.append(firstDigit);
                    return result;
                }
                catch (IllegalStateException e3){
                    digitsToUse.makeDigitsAvail(usedDigits);
                    throw new IndexOutOfBoundsException("It's not possible to construct a number within 100-200 range");
                }
            }
        }
    }
    private StringBuffer createNextAvailableNumber2XX_249Case(DigitsBasket digitsToUse, AtomicInteger alreadyFreed){
        StringBuffer result = new StringBuffer();
        int usedDigits = 0;
        try {
            if (alreadyFreed.get() <= 0){
                digitsToUse.makeDigitsAvail(1); //zwalniam cyfrę reprezentującą 10^0
                alreadyFreed.set(1);
            }
            
            var firstDigit = digitsToUse.takeNextAvailDigit(currentFirstDigit);
            currentFirstDigit=firstDigit;
            
            result.append(currentThirdDigit);
            result.append(currentSecondDigit);
            result.append(firstDigit);
            return result;
        }
        catch (IllegalStateException e1){
            try{
                if (alreadyFreed.get() <= 1){
                    digitsToUse.makeDigitsAvail(1); //zwalniam cyfrę reprezentującą 10^1 
                    alreadyFreed.set(2);
                }
                var secondDigit = digitsToUse.takeNextDigitSmallerThanFiveAvail(currentSecondDigit);
                usedDigits++;

                var firstDigit = digitsToUse.takeFirstAvailDigit();
                usedDigits++;
                currentFirstDigit=firstDigit;
                currentSecondDigit=secondDigit;
                
                result.append(currentThirdDigit);
                result.append(secondDigit);
                result.append(firstDigit);
                return result;
            }
            catch (IllegalStateException e2){
                digitsToUse.makeDigitsAvail(usedDigits);
                
                throw new IndexOutOfBoundsException("It's not possible to construct a number within 200-249 range");
            }
        }
    }
    private StringBuffer createNextAvailableNumber25XCase(DigitsBasket digitsToUse, AtomicInteger alreadyFreed){
        StringBuffer result = new StringBuffer();
        int usedDigits = 0;
        try {
            if (alreadyFreed.get() <= 0){
                digitsToUse.makeDigitsAvail(1); //zwalniam cyfrę reprezentującą 10^0
                alreadyFreed.set(1);
            }
            
            var firstDigit = digitsToUse.takeNextAvailDigit(currentFirstDigit);
            currentFirstDigit=firstDigit;
            
            result.append(currentThirdDigit);
            result.append(currentSecondDigit);
            result.append(firstDigit);
            return result;
        }
        catch (IllegalStateException e1){
            try{
                if (alreadyFreed.get() <= 1){
                    digitsToUse.makeDigitsAvail(1); //zwalniam cyfrę reprezentującą 10^1 
                    alreadyFreed.set(2);
                }
                var secondDigit = digitsToUse.takeNextDigitUpToFiveAvail(currentSecondDigit);
                usedDigits++;

                var firstDigit = digitsToUse.takeFirstDigitUpToFiveAvail();
                usedDigits++;
                currentFirstDigit=firstDigit;
                currentSecondDigit=secondDigit;
                
                result.append(currentThirdDigit);
                result.append(secondDigit);
                result.append(firstDigit);
                return result;
            }
            catch (IllegalStateException e2){
                digitsToUse.makeDigitsAvail(usedDigits);
                throw new IndexOutOfBoundsException("It's not possible to construct a number within 250-255 range");
            }
        }
    }
    public StringBuffer createNextAvailableNumber(DigitsBasket digitsToUse){
        int continueFrom=-1;
        AtomicInteger alreadyFreed= new AtomicInteger(0);
        try{
            if (currentThirdDigit == 1){
                continueFrom = 1;
                return createNextAvailableNumber1XXCase(digitsToUse, alreadyFreed);
            }
            else if (currentThirdDigit == 2 && currentSecondDigit < 5){
                continueFrom = 2;
                return createNextAvailableNumber2XX_249Case(digitsToUse, alreadyFreed);
            }
            else{
                continueFrom = 3;
                return createNextAvailableNumber25XCase(digitsToUse, alreadyFreed);
            }
                
        } 
        catch (IndexOutOfBoundsException e1){
            try{
                switch(continueFrom){
                    case 1:
                        continueFrom = 2;
                        return createNextAvailableNumber2XX_249Case(digitsToUse, alreadyFreed);
                    case 2:
                        continueFrom = 3;
                }       return createNextAvailableNumber25XCase(digitsToUse, alreadyFreed);
            }
            catch (IndexOutOfBoundsException e2){
                if (continueFrom == 2)
                    return createNextAvailableNumber25XCase(digitsToUse, alreadyFreed);
                else
                    throw new IndexOutOfBoundsException("Not enough digits are available to construct a number within 100-255 range");
                    
            }
            
        }
    }
    
}
