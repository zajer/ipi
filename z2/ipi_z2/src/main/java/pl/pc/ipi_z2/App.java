
package pl.pc.ipi_z2;

import java.io.File;
import java.util.Scanner;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.nio.csv.CSVFormat;
import org.jgrapht.nio.csv.CSVImporter;
import org.jgrapht.util.SupplierUtil;

public class App {
    
    private static final String errMsg =
            "▬▬▬.◙.▬▬▬\n" +
            "═▂▄▄▓▄▄▂\n" +
            "◢◤ █▀▀████▄▄▄▄◢◤\n" +
            "█▄ █ █▄ ███▀▀▀▀▀▀▀╬\n" +
            "◥█████◤\n" +
            "══╩══╩═\n" +
            "╬═╬\n" +
            "╬═╬ \n" +
            "╬═╬ \n" +
            "╬═╬       Cos nie dziala!\n" +
            "╬═╬ ☻/\n" +
            "╬═╬/▌\n" +
            "╬═╬/ \\";
    
    private static final String tutMsg = 
            "Instrukcja:\n" +
            "Proszę podać nazwę pliku csv (wraz z rozszerzeniem), w którym zdefiniowane są kolejne krawędzi.\n"+
            "Przykładowo 'moj_pliczek.csv' (bez ').";
    
    public static void main(String[] args) {
        Graph<String, DefaultEdge> g;
        Character delimiter = ',';
        CSVFormat format = CSVFormat.EDGE_LIST;
        g = GraphTypeBuilder
                .undirected().allowingMultipleEdges(true).allowingSelfLoops(true).weighted(false)
                .edgeClass(DefaultEdge.class).vertexSupplier(SupplierUtil.createStringSupplier(0))
                .buildGraph();
       CSVImporter<String, DefaultEdge> importer = new CSVImporter<>(format, delimiter);
       System.out.println(tutMsg);
       try{
        Scanner my_scan = new Scanner(System.in);
        String graphFile = my_scan.nextLine();
        importer.importGraph(g, new File(graphFile));
        TreeEvaluator evaluator = new TreeEvaluator();
        boolean result = evaluator.isItTree(g);
        String resultAsString = result ? "jest" : "nie jest";
        System.out.println("Podany graf "+resultAsString+" drzewem");
       }
       catch(Exception e){
           System.out.println(errMsg);
       }
    }
}
