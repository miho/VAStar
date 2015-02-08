/*
 * PredicateGenerator.java
 *
 * Created on 17. Juli 2007, 17:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package blocksworld;

import eu.mihosoft.ai.astar.Predicate;
import java.util.ArrayList;

/**
 * The Predicate generator generates all possible predicates for blocksworld.
 * This class is for better output only! It has no real functionality. 
 *
 * @author miho
 */
public class PredicateGenerator  extends ArrayList<Predicate>
{
    
    /**
     * Creates a new instance of PredicateGenerator
     */
    public PredicateGenerator (BlocksWorldState s)
    {
        int n = s.getNumberOfBlocks ();
        
        this.add ( new HandEmpty(s, true) );
        
        for (int x = 0; x < n;x++)
        {
            for (int y = 0; y < n; y++)
            {
                if ( y != x )
                {
                    this.add ( new On (s,x,y,true) );
                }
            }
        }
        
        for (int x = 0; x < n;x++)
        {
            this.add ( new Clear (s,x, true) );
            this.add ( new OnTable (s,x, true) );
            this.add ( new Holding (s,x, true) );
        }
    }
}
