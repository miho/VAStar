package eu.mihosoft.ai.astar;

import aima.core.environment.wumpusworld.ManhattanHeuristicFunction;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.HeuristicFunction;
import aima.core.search.framework.Node;
import aima.core.search.framework.Problem;
import aima.core.search.framework.QueueSearch;
import aima.core.search.informed.AStarSearch;
import java.util.List;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AStarSearch astar = new AStarSearch(new GraphSearch(), new HeuristicFunction() {

            @Override
            public double h(Object state) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
}
