
package pl.pc.ipi_z1;

public interface NumberBuilder {
    public StringBuffer createFirstAvailableNumber(DigitsBasket digitsToUse);
    public StringBuffer createNextAvailableNumber(DigitsBasket digitsToUse);
}
