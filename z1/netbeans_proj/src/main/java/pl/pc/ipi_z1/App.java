
package pl.pc.ipi_z1;

import java.util.Scanner;

public class App {
    private static final String errorMsg =
"─▄▀▀▀▀▄─█──█────▄▀▀█─▄▀▀▀▀▄─█▀▀▄ \n" +
"─█────█─█──█────█────█────█─█──█ \n" +
"─█────█─█▀▀█────█─▄▄─█────█─█──█ \n" +
"─▀▄▄▄▄▀─█──█────▀▄▄█─▀▄▄▄▄▀─█▄▄▀ \n" +
"\n" +
"─────────▄██████▀▀▀▀▀▀▄\n" +
"─────▄█████████▄───────▀▀▄▄\n" +
"──▄█████████████───────────▀▀▄\n" +
"▄██████████████─▄▀───▀▄─▀▄▄▄──▀▄\n" +
"███████████████──▄▀─▀▄▄▄▄▄▄────█\n" +
"█████████████████▀█──▄█▄▄▄──────█\n" +
"███████████──█▀█──▀▄─█─█─█───────█\n" +
"████████████████───▀█─▀██▄▄──────█\n" +
"█████████████████──▄─▀█▄─────▄───█\n" +
"█████████████████▀███▀▀─▀▄────█──█\n" +
"████████████████──────────█──▄▀──█\n" +
"████████████████▄▀▀▀▀▀▀▄──█──────█\n" +
"████████████████▀▀▀▀▀▀▀▄──█──────█\n" +
"▀████████████████▀▀▀▀▀▀──────────█\n" +
"──███████████████▀▀─────█──────▄▀\n" +
"──▀█████████████────────█────▄▀\n" +
"────▀████████████▄───▄▄█▀─▄█▀\n" +
"──────▀████████████▀▀▀──▄███\n" +
"──────████████████████████─█\n" +
"─────████████████████████──█\n" +
"────████████████████████───█\n" +
"────██████████████████─────█\n" +
"────██████████████████─────█\n" +
"────██████████████████─────█\n" +
"────██████████████████─────█\n" +
"────██████████████████▄▄▄▄▄█\n" +
"\n" +
"─────────────█─────█─█──█─█───█\n" +
"─────────────█─────█─█──█─▀█─█▀\n" +
"─────────────█─▄█▄─█─█▀▀█──▀█▀\n" +
"─────────────██▀─▀██─█──█───█";
    private static final String tutMsg = 
            "Instrukcja:\n"
            + "Najpierw należy wpisać ciąg cyfr (np. '123456789'), a następnie wybrać wybrać spośród dostępnych opcji działania programu.";
    private static final String digitsInputMsg = "Wpisz ciąg cyfr:";
    private static final String printingResultInputMsg = 
            "Czy chcesz żeby wypisać wszystkie znalezione kombinacje?\n"
            + "Wpisz 't' (tak) lub 'n'(nie, wypisz tylko ile kombinacji znaleziono):\n"
            + "(wpisanie czegoś innego lub enter spowoduje, że wybrana zostanie opcja 't')";
    private static final String exhaustiveSearchInputMsg =
            "Czy wszystkie wpisany cyfry muszą zostać wykorzystane przy szukaniu kombinacji?\n"
            + "Wpisz 'w' (tak, wszystkie) lub 'd' (dowolna liczba cyfr, aby wynik był poprawny):\n"
            + "(wpisanie czegoś innego lub enter spowoduje, że wybrana zostanie opcja 'w')";
    private static DigitsBasket parseBasket(String digitsAsString){
        int[] availableDigits = new int[10];
        //char[] digitsAsChars = digitsAsString.toCharArray();
        for (int i = 0; i < digitsAsString.length(); i++){
            char digitAsChar = digitsAsString.charAt(i);
            int digit = Character.getNumericValue(digitAsChar);
            availableDigits[digit]++;
        }
        return new DigitsBasket(availableDigits);
    }
    
    public static void main(String[] args) {
        DigitsBasket availableDigits;
        boolean printing;
        boolean exhaustiveSearch;
        System.out.println(tutMsg);
        System.out.println(digitsInputMsg);
        Scanner my_scan = new Scanner(System.in);
        String digitsAsText = my_scan.nextLine();
        System.out.println(printingResultInputMsg);
        String printingResultsOption = my_scan.nextLine();
        System.out.println(exhaustiveSearchInputMsg);
        String exhaustiveSearchOption = my_scan.nextLine();
        try{
            availableDigits = parseBasket(digitsAsText);
            switch(printingResultsOption){
                case "t":
                    printing = true;
                    break;
                case "n":
                    printing = false;
                    break;
                default :
                    printing = true;
            }
            switch(exhaustiveSearchOption){
                case "w":
                    exhaustiveSearch = true;
                    break;
                case "d":
                    exhaustiveSearch = false;
                    break;
                default :
                    exhaustiveSearch = true;
            }
            SingleOctetBuilder builder = new SingleOctetBuilder();
            var results = builder.createOctet(availableDigits, 3, exhaustiveSearch);
            if (printing)
                results.forEach(
                    result -> System.out.println('['+result.toString()+']')
                );
            else
                System.out.println("Liczba znalezionych kombinacji="+results.size());
        }
        catch(Exception e){
            System.out.println(errorMsg);
            System.out.println("Coś nie działa.");
        }
        
    }
 
}
