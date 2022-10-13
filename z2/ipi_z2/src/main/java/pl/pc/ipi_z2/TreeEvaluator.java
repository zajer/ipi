
package pl.pc.ipi_z2;

import java.util.Iterator;
import java.util.Set;
import org.jgrapht.Graph;


public class TreeEvaluator {
    private final boolean debugMode = false;
    public <V extends String,E> boolean isItTree(Graph<V,E> graphToBeEvaulated){
        Set<V> setOfVertices = graphToBeEvaulated.vertexSet();
        V firstVertex;
        if (setOfVertices.iterator().hasNext())
            firstVertex=setOfVertices.iterator().next();
        else
            return false;
            
        var iterator = new GardenerIterator<>(graphToBeEvaulated, firstVertex);
             while (iterator.hasNext()){
                 V currentVertex = iterator.next();
                 if (debugMode)
                    System.out.println(currentVertex);
                 //var stack = iterator.getStack();
                 //stack.
                 //System.out.println(stack);
             }
        return iterator.isThisMyTree();
    }
}
