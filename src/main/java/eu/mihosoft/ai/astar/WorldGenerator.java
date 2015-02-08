/*
 * WorldGenerator.java
 *
 * Created on 17. Juli 2007, 16:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eu.mihosoft.ai.astar;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is a domain independent world generator that randomly
 * generates initial and final states.
 * @author miho
 */
public class WorldGenerator
{
    private ArrayList<String> initialState;
    private ArrayList<String> finalState;
    
    /** Creates a new instance of WorldGenerator */
    public WorldGenerator ()
    {
        initialState = new ArrayList<String>();
        finalState = new ArrayList<String>();
        
        System.out.println ("");
        System.out.println (">> World-Generator initialized!");
        System.out.println ("");
    }
    
    /**
     * Generates initial and final states. It is possible to specify
     * the preffered distance (estimation done by the heuristic of the 
     * WorldDescription) between the initial and the final state.
     * <br><br>
     * <b>Warning:</b> depending on the preferred distance, the generation may take
     *          some time. It is also possible that the WorldGenerator is not 
     *          able to generate initial and final states with the specified
     *          distance.
     */
    public void generate (int n, WorldDescription w)
    {
        
        System.out.println (">> generating WorldDescription (may take some time).");
        
        Random rand = new Random ();
        
        State s = (State) w.getInitialState ().clone ();
        
        Heuristic heuristic = w.getHeuristic ();
        
        
        // Generate initial state
        for (int i = 0; i < 500; i++)
        {
            int nextAction = rand.nextInt (w.getActionSet ().size ());
            
            Action a = w.getActionSet ().get (nextAction);
            
            
            if ( a.perform (s) )
            {
//                System.out.println (a.getName ());
            }
        }
        
        w.setInitialState (s);
        
        for ( ConditionPredicate c : w.getPredicateSet () )
        {
            if ( c.verify (s) )
            {
                getInitialState ().add (c.getName ());
            }
        }
        
        
        
        // Generate final state
        
        w.setFinalState (s);
        
        
        int counter = 30000; // prevent endless loop
        
        while (heuristic.estimate (w.getInitialState (),w) < n )
        {
            int nextAction = rand.nextInt (w.getActionSet ().size ());
            
            w.getActionSet ().get (nextAction).perform (s);
            
            w.setFinalState (s);
            
            counter--;
            
            if (counter == 0)
            {
//                System.out.println ("");
//                System.out.println (">> Error: could not generate final state!");
//                System.out.println (">> Hint: reduce initial distance or try again.");
                break;
            }
        }
        
        
        
        for ( ConditionPredicate c : w.getPredicateSet () )
        {
            if ( c.verify (s) )
            {
                getFinalState ().add (c.getName ());
            }
        }
        
        System.out.println ("");
        System.out.println ("------------------------------");
        System.out.println ("initial state:");
        System.out.println ("");
        
        for(String i : getInitialState ())
        {
            System.out.println (i);
        }
        
        System.out.println ("");
        System.out.println ("------------------------------");
        System.out.println ("goal:");
        System.out.println ("");
        
        for(String i : getFinalState ())
        {
            System.out.println (i);
        }
        
    }
    
    /**
     * Returns the initial state (s_0)
     */
    public ArrayList<String> getInitialState ()
    {
        return initialState;
    }
    
    /**
     * Returns the final state (s_n).
     */
    public ArrayList<String> getFinalState ()
    {
        return finalState;
    }
    
}
