package palma.model.graph.utilities;

import palma.model.graph.models.Graph;

public class Graphs {

    public static<N,L> void printGraph(Graph<N,L> graph){
        for(N it : graph.getNodes()){
            System.out.print(it.toString() + " [ ");
            for(N itt : graph.getNodes(it)){
                System.out.print(itt.toString() + "(" + graph.getLink(it,itt) + ") ");
            }
            System.out.println("]");
        }
    }

    public static void fillRandomIntegerGraph(Graph<Integer,Object> graph, int size, int links){
        for(;links > 0;--links){
            graph.link((int)(Math.random()*size),(int)(Math.random()*size),new Object());
        }
    }
}
