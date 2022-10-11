
package pl.pc.ipi_z1;

import java.util.Stack;


public class DigitsBasket {
    private final int[] availDigits;
    private final Stack<Integer> usedDigits;
    
    public DigitsBasket(int[] availableDigits){
        availDigits = new int[10];
        for(int i=0; i<10;i++)
            availDigits[i]=availableDigits[i];
        usedDigits = new Stack<Integer>();    
    }
    private int numOfAvailDigits(int rangeFrom, int rangeTo){
        int totalCount = 0;
        for(int i=rangeFrom;i<=rangeTo;i++)
            totalCount += availDigits[i];
        return totalCount;
    }
    public int numOfAvailDigits(){
        return DigitsBasket.this.numOfAvailDigits(0,9);
    }
    public int numOfPositiveDigitsAvail(){
        return DigitsBasket.this.numOfAvailDigits(1,9);
    }
    public int numOfAvailDigitsSmallerThanFive(){
        return DigitsBasket.this.numOfAvailDigits(0,4);
    }
    public int numOfAvailDigitsUpToFive(){
        return DigitsBasket.this.numOfAvailDigits(0,5);
    }
    public int numOfAvailDigits(int type){
        return availDigits[type];
    }
    private void takeDigit(int type){
        availDigits[type]--;
        usedDigits.push(type); 
    }
    private int takeFirstAvailDigit(int rangeFrom, int rangeTo){
        for(int digit=rangeFrom;digit<=rangeTo;digit++)
            if(availDigits[digit] > 0){
                takeDigit(digit);
                return digit;
            }
        throw new IllegalStateException("No satisfying digits available!");
    }
    public int takeFirstAvailDigit(){
        return takeFirstAvailDigit(0,9);
    }
    public int takeFirstPositiveDigitAvail(){
        return takeFirstAvailDigit(1,9);
    }
    public int takeFirstDigitSmallerThanFiveAvail(){
        return takeFirstAvailDigit(0,4);
    }
    public int takeFirstDigitUpToFiveAvail(){
        return takeFirstAvailDigit(0,5);
    }
    public int takeFirstOneAvail(){
        return takeFirstAvailDigit(1,1);
    }
    public int takeFirstTwoAvail(){
        return takeFirstAvailDigit(2,2);
    }
    public int takeFirstFiveAvail(){
        return takeFirstAvailDigit(5,5);
    }
    private int takeNextAvailDigit(int rangeFrom,int rangeTo, int greaterThan){
        for(int digit=rangeFrom;digit<=rangeTo;digit++)
            if(availDigits[digit] > 0 && digit > greaterThan){
                takeDigit(digit);
                return digit;
            }
        throw new IllegalStateException("No satisfying digits available!");
    }
    public int takeNextAvailDigit(int greaterThan){
        return takeNextAvailDigit(0,9,greaterThan);
    }
    public int takeNextPositiveDigitAvail(int greaterThan){
        return takeNextAvailDigit(1,9,greaterThan);
    }
    public int takeNextDigitSmallerThanFiveAvail(int greaterThan){
        return takeNextAvailDigit(0,4,greaterThan);
    }
    public int takeNextDigitUpToFiveAvail(int greaterThan){
        return takeNextAvailDigit(0,5,greaterThan);
    }
    public void makeDigitsAvail(int numberOfDigits){
        for(int i=0;i<numberOfDigits;i++){
            var digit = usedDigits.pop();
            availDigits[digit]++;
        }
    }
}
