/*
 * Main.java
 *
 * Created on 11. Juli 2007, 15:48
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package blocksworld;

import eu.mihosoft.ai.astar.AStar;
import eu.mihosoft.ai.astar.WorldDescription;
import eu.mihosoft.ai.astar.WorldGenerator;
import eu.mihosoft.ai.heuristics.SimpleHeuristic;

/**
 *
 * @author miho
 */
public class Main
{
    
    /** Creates a new instance of Main */
    public Main ()
    {
        // nothing to do
    }
    
    /**
     * @param args the command line arguments
     *
     * Generates a blocksworld problem with the world generater.
     *
     */
    public static void main (String[] args)
    {
        
        //PARSING PARAMETERS
        
//        if (args.length == 0)
//        {
//            System.out.println (">> Error: missing parameter!");
//            System.out.println (">> Usage: blocksworld-generated [number of blocks]");
//            
//            System.exit (1);
//        }
//        
//        if (args.length > 1)
//        {
//            System.out.println (">> Error: too many parameter!");
//            System.out.println (">> Usage: blocksworld-generated [number of blocks]");
//            
//            System.exit (1);
//        }
//        
//        int numberOfBlocks = 0;
//        
//        try
//        {
//            numberOfBlocks = Integer.parseInt (args[0]);
//        }
//        catch (NumberFormatException ex)
//        {
//            ex.printStackTrace ();
//        }
        
        int numberOfBlocks = 8;
        
        
        
        // INITIAL STATE
        BlocksWorldState initialState = new BlocksWorldState (numberOfBlocks);
        
        // WORLDDESCRIPTION
        WorldDescription w = new WorldDescription (initialState,
                new ActionGenerator (initialState),
                new PredicateGenerator (initialState),
                new SimpleHeuristic ()
                );
        
        // WORLD GENERATOR
        WorldGenerator wgen = new WorldGenerator ();
        wgen.generate (8,w);
        
        // ASTAR
        AStar astar = new AStar (w);
        astar.run ();
        
        System.exit (0);
    }
}
