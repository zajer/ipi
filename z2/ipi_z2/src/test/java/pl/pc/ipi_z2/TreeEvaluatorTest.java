
package pl.pc.ipi_z2;


import java.io.StringReader;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.nio.csv.CSVFormat;
import org.jgrapht.nio.csv.CSVImporter;
import org.jgrapht.util.SupplierUtil;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


public class TreeEvaluatorTest {
    
    private void evaluateGraphDefault(String edgeList, boolean expectedResultOfEvaluation){
        Graph<String, DefaultEdge> g;
        Character delimiter = ',';
        CSVFormat format = CSVFormat.EDGE_LIST;
        g = GraphTypeBuilder
                .undirected().allowingMultipleEdges(true).allowingSelfLoops(true).weighted(false)
                .edgeClass(DefaultEdge.class).vertexSupplier(SupplierUtil.createStringSupplier(0))
                .buildGraph();
        CSVImporter<String, DefaultEdge> importer = new CSVImporter<>(format, delimiter);
        importer.importGraph(g, new StringReader(edgeList));
        //System.out.println(g);
        TreeEvaluator evaluator = new TreeEvaluator();
        boolean result = evaluator.isItTree(g);
        assertEquals(expectedResultOfEvaluation, result);
    }
    
    public static String[] trees2evaluate() {
        return new String[] { 
              "1,4\n"
            + "2,4\n"
            + "3,4\n"
            + "4,5\n"
            + "5,6\n",
            
              "5,1\n"
            + "1,3\n"
            + "1,4\n"
            + "4,2\n"
            + "4,6\n",
              
              "a,b\n"
            + "b,c\n"
            + "c,d\n"
            + "c,e\n"
            + "e,g\n"
            + "g,f\n"
        };
    }
    
    public static String[] nonTrees2evaluate() {
        return new String[] { 
              "1,2\n"
            + "2,3\n"
            + "3,4\n"
            + "1,4\n",
            
              "5,1\n"
            + "3,4\n"
            + "2,4\n"
            + "4,6\n",
              
              "1,4\n"
            + "2,4\n"
            + "3,4\n"
            + "4,5\n"
            + "5,6\n"
            + "6,5\n",
              
              "a,b\n"
            + "a,c\n"
            + "a,d\n"
            + "b,d\n"
            + "c,d\n"
            + "d,e\n"
            + "b,e\n",
              
              "1,2\n"
            + "2,3\n"
            + "2,4\n"
            + "5,6\n"
            + "6,7\n"
            + "6,8\n"
        };
    }
    
    @ParameterizedTest
    @MethodSource(value =  "trees2evaluate")
    public void evaluatingGraph_isATree(String edgeList) {
        boolean expectedResultOfEvaluation = true;
        evaluateGraphDefault(edgeList, expectedResultOfEvaluation);
    }
    
    @ParameterizedTest
    @MethodSource(value =  "nonTrees2evaluate")
    public void evaluatingGraph_isNotATree(String edgeList) {
        boolean expectedResultOfEvaluation = false;
        evaluateGraphDefault(edgeList, expectedResultOfEvaluation);
    }
    
}
