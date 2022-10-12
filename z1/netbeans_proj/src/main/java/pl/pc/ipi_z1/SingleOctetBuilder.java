package pl.pc.ipi_z1;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;



public class SingleOctetBuilder {
    public List<StringBuffer> createOctet(DigitsBasket digitsToUse, int depth, boolean exhaustive){
         NumberBuilder octetValueBuilder = null;
         List<StringBuffer> results = new LinkedList<>();
         for(int numOfDigits=1;numOfDigits<=3;numOfDigits++){
             switch(numOfDigits){
                 case 1:
                     octetValueBuilder = new OneDigitNumberBuilder();
                     break;
                 case 2:
                     octetValueBuilder = new TwoDigitsNumberBuilder();
                     break;
                 case 3:
                     octetValueBuilder = new ThreeDigitsNumberBuilderV2();
                     break;
             }
             //boolean continueBuilding = true;
             try {
                 var partialResult = octetValueBuilder.createFirstAvailableNumber(digitsToUse);
                 while (true){
                    var unmodifiedPartialRes = partialResult;
                    if (depth > 0){
                        var externalPartialResults = createOctet(digitsToUse,depth-1,exhaustive);
                        
                        var combinedResults = 
                                externalPartialResults
                                    .stream()
                                    .map( extPartRes -> new StringBuffer(unmodifiedPartialRes).append('.').append(extPartRes))
                                    .collect(Collectors.toList());
                        results.addAll(combinedResults);
                    }
                    else {
                        if ( exhaustive && digitsToUse.numOfAvailDigits() == 0 )
                            results.add(partialResult);
                        else if ( !exhaustive ) 
                            results.add(partialResult);
                    }

                    partialResult = octetValueBuilder.createNextAvailableNumber(digitsToUse);
                 }
             }
             catch (IndexOutOfBoundsException e){
                 // można tutaj zapisywać do logów zakończone etapy
             }
         }
         return results;
    }
}
