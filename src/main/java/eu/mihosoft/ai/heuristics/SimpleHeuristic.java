/*
 * SimpleHeuristic.java
 *
 * Created on 14. Juli 2007, 17:13
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eu.mihosoft.ai.heuristics;

import eu.mihosoft.ai.astar.Action;
import eu.mihosoft.ai.astar.Heuristic;
import eu.mihosoft.ai.astar.State;
import eu.mihosoft.ai.astar.WorldDescription;


/**
 * This is a very simple heuristic. It estimates the distance between
 * two states by solving the relaxed problem 
 * (no negative preconditions and effects).
 * @author miho
 */
public final class SimpleHeuristic implements Heuristic
{
    
    int distance;
    
    /**
     * Estimates the distance between two states.
     * 
     * @param s This is the first State.
     * @param w The WorldDescription, containing the final state as well as
     *          the necessary actionSet.
     */
    public int estimate (State s, WorldDescription w)
    {
        distance = 0;
        State sOld = (State) s.clone ();
        State sNew = (State) s.clone ();
        
        while ( !w.getGoal ().verify (sNew, true) )
        {
            distance++;
            
            for( Action a : w.getActionSet () )
            {
                boolean b = a.perform ( sOld, sNew, true );
            }
            
            sOld = (State) sNew.clone ();
        }
        
        return distance;
    }
}
