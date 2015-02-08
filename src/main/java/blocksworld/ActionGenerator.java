/*
 * ActionGenerator.java
 *
 * Created on 11. Juli 2007, 21:42
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package blocksworld;


import eu.mihosoft.ai.astar.Action;
import java.util.ArrayList;

/**
 * Generates an action set depending on number of blocks.
 * @author miho
 */
public final class ActionGenerator extends ArrayList<Action>
{
    /**
     * Creates a new instance of ActionGenerator
     * @param s
     */
    public ActionGenerator (BlocksWorldState s)
    {
        int n = s.getNumberOfBlocks ();
        
        for (int x = 0; x < n;x++)
        {
            for (int y = 0; y < n; y++)
            {
                if ( y != x )
                {
                    this.add ( new Unstack (s,x,y) );
                    this.add ( new Stack (s,x,y) );
                }
            }
        }
        
        for (int x = 0; x < n;x++)
        {
            this.add ( new Pickup (s,x) );
            this.add ( new PutDown (s,x) );
        }
    }
}
