
package pl.pc.ipi_z2;

import java.util.Arrays;
import java.util.Stack;
import org.jgrapht.Graph;
import org.jgrapht.traverse.DepthFirstIterator;


public class GardenerIterator<V extends String,E> extends DepthFirstIterator<V,E>{
    
    private int[] reachedVertices;
    private boolean isGraphAcyclic;
    private Stack currentParent;
    private Stack currentVertex;
    
    public GardenerIterator(Graph<V, E> g, V startVertex) {
        super(g, startVertex);
        currentParent = new Stack<V>();
        currentVertex = new Stack<V>();
        reachedVertices = new int[ g.vertexSet().size() ];
        reachedVertices[ Integer.parseInt(startVertex) ]=1;
        isGraphAcyclic = true;
        //currentParent.push(startVertex);
    }

    @Override
    protected void encounterVertex(V vertex, E edge) {
        super.encounterVertex(vertex, edge);
        int vid = Integer.parseInt(vertex);
        reachedVertices[vid]=1;
    }
    
    @Override
    protected void encounterVertexAgain(V vertex, E edge) {
        super.encounterVertexAgain(vertex, edge);
        if( !vertex.equals(currentParent.peek()) )
            isGraphAcyclic = false;
    }

    @Override
    protected V provideNextVertex() {
        V latestVertex = super.provideNextVertex();
        if (!currentVertex.empty())
            currentParent.push(currentVertex.peek());
        currentVertex.push(latestVertex);
        //currentParent = super.provideNextVertex();
        //return currentParent;
        return latestVertex;
        //return super.provideNextVertex();
    }

    @Override
    protected void finishVertex(V vertex) {
        super.finishVertex(vertex);
        if (!currentParent.empty())
            currentParent.pop();
        currentVertex.pop();
    }
    
    public boolean isThisMyTree(){
        return 
                isGraphAcyclic && 
                Arrays
                        .stream(reachedVertices)
                        .allMatch( value -> value == 1);
        
    }
    
    
}
