
package pl.pc.ipi_z1;

import lombok.Getter;


public class ThreeDigitsNumberBuilderV2 implements NumberBuilder {
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
    
    private StringBuffer createNextAvailableNumber1XXCase(DigitsBasket digitsToUse){
        StringBuffer result = new StringBuffer();
        int usedDigits = 0;
        try {
            //proba zwiekszenia tylko pierwszej cyfry (1XY->1XZ Z<=9)
            digitsToUse.makeDigitsAvail(1); //zwalniam cyfrę reprezentującą 10^0
            
            var firstDigit = digitsToUse.takeNextAvailDigit(currentFirstDigit);
            currentFirstDigit=firstDigit;
            
            result.append(currentThirdDigit);
            result.append(currentSecondDigit);
            result.append(firstDigit);
            return result;
        }
        catch (IllegalStateException e1){
            try{
                //próba zwiększenia co najmniej drugiej cyfry (<199)
                digitsToUse.makeDigitsAvail(1); //zwalniam cyfrę reprezentującą 10^1 
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
                //próba zwiększenia co najmniej trzeciej cyfry, ale wynik mniejszy od 250
                digitsToUse.makeDigitsAvail(usedDigits);
                usedDigits = 0;
                try{
                    
                    digitsToUse.makeDigitsAvail(1); //zwalniam cyfrę reprezentującą 10^2 
                    try{
                        var thirdDigit = digitsToUse.takeFirstTwoAvail();
                        //usedDigits++; //z tego punktu się i tak nie wycofuję
                        currentThirdDigit=thirdDigit;
                    }
                    catch (IllegalStateException e2stop){
                        throw new IndexOutOfBoundsException("It's not possible to construct a number within 100-200 range");
                    }
                    
                    var secondDigit = digitsToUse.takeFirstDigitSmallerThanFiveAvail();
                    usedDigits++;
                    currentSecondDigit=secondDigit;
                    
                    
                    var firstDigit = digitsToUse.takeFirstAvailDigit();
                    usedDigits++;
                    currentFirstDigit=firstDigit;
                    
                    result.append(currentThirdDigit);
                    result.append(currentSecondDigit);
                    result.append(currentFirstDigit);
                    return result;
                }
                catch (IllegalStateException e3){
                    //próba zwiększenia co najmniej trzeciej cyfry i wynik większy od 250
                    digitsToUse.makeDigitsAvail(usedDigits);
                    usedDigits = 0;
                    try{
                        var secondDigit = digitsToUse.takeFirstFiveAvail();
                        usedDigits++;
                        var firstDigit = digitsToUse.takeFirstDigitUpToFiveAvail();
                        usedDigits++;
                        
                        currentSecondDigit=secondDigit;
                        currentFirstDigit=firstDigit;
                        
                        result.append(currentThirdDigit);
                        result.append(secondDigit);
                        result.append(firstDigit);
                        return result;
                    }
                    catch (IllegalStateException e4){
                        digitsToUse.makeDigitsAvail(usedDigits+1); //zwalniam włącznie z 10^2, które wcześniej nie było zwalniane
                        throw new IndexOutOfBoundsException("It's not possible to construct a number within 100-255 range");
                    }
                    //throw new IllegalStateException("It's not possible to construct a number within 100-200 range");
                    
                }
            }
        }
    }
    
    private StringBuffer createNextAvailableNumber2XXCase(DigitsBasket digitsToUse){
        StringBuffer result = new StringBuffer();
        int usedDigits = 0;
        try {
            //próba zwiększaniea tylko 10^0
            digitsToUse.makeDigitsAvail(1); //zwalniam cyfrę reprezentującą 10^0
            
            var firstDigit = digitsToUse.takeNextAvailDigit(currentFirstDigit);
            currentFirstDigit=firstDigit;
            
            result.append(currentThirdDigit);
            result.append(currentSecondDigit);
            result.append(firstDigit);
            return result;
        }
        catch (IllegalStateException e1){
            try{
                //próba zwiększaniea tylko 10^1, ale do max 49
                digitsToUse.makeDigitsAvail(1); //zwalniam cyfrę reprezentującą 10^1 
                
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
                // próba zwiększenia 10^1 powyżej 50
                digitsToUse.makeDigitsAvail(usedDigits);
                usedDigits = 0;
                try{
                    var secondDigit = digitsToUse.takeFirstFiveAvail();
                    usedDigits++;
                    var firstDigit = digitsToUse.takeFirstDigitUpToFiveAvail();
                    usedDigits++;

                    currentSecondDigit=secondDigit;
                    currentFirstDigit=firstDigit;

                    result.append(currentThirdDigit);
                    result.append(secondDigit);
                    result.append(firstDigit);
                    return result;
                }
                catch(IllegalStateException e3){
                    digitsToUse.makeDigitsAvail(usedDigits+1);
                    throw new IndexOutOfBoundsException("It's not possible to construct a number within 200-249 range");   
                }
            }
        }
    }
    private StringBuffer createNextAvailableNumber25XCase(DigitsBasket digitsToUse){
        StringBuffer result = new StringBuffer();
        try {
            digitsToUse.makeDigitsAvail(1); //zwalniam cyfrę reprezentującą 10^0
            
            var firstDigit = digitsToUse.takeNextDigitUpToFiveAvail(currentFirstDigit);
            currentFirstDigit=firstDigit;
            
            result.append(currentThirdDigit);
            result.append(currentSecondDigit);
            result.append(firstDigit);
            return result;
        }
        catch (IllegalStateException e1){
            digitsToUse.makeDigitsAvail(2); //zwalniam cyfrę reprezentującą 10^1 
            throw new IndexOutOfBoundsException("It's not possible to construct a number within 250-255 range");
        }
    }
    public StringBuffer createNextAvailableNumber(DigitsBasket digitsToUse) {
        if (currentThirdDigit < 2)
            return createNextAvailableNumber1XXCase(digitsToUse);
        else {
            if (currentSecondDigit < 5)
                return createNextAvailableNumber2XXCase(digitsToUse);
            else
                return createNextAvailableNumber25XCase(digitsToUse);
        }
    }
    
}
