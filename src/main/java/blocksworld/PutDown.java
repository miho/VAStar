/*
 * PutDown.java
 *
 * Created on 11. Juli 2007, 20:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package blocksworld;

import eu.mihosoft.ai.astar.Action;


/**
 * PutDown action.
 * In blocksworld the action PutDown discribes what precontions are necessary if
 * you want to put down a block on the table from the robot arm and which 
 * effects this action has.
 *
 * @author miho
 */
public final class PutDown extends Action
{
    
    /** 
     * Creates a new instance of PutDown.
     *
     * @param s State on which Action operates on.
     * @param x The block that is to be put down.
     */
    public PutDown (BlocksWorldState s, int x)
    {
        // Set name
        setName( "PutDown (" + x + ")" );
        
        //PRECOND
        precond.add ( new Holding (s, x, true) );
        
        //EFFECTS
        effect.add ( new Holding (s, x, false) );
        
        effect.add ( new OnTable (s, x, true) );
        effect.add ( new Clear (s, x, true) );
        effect.add ( new HandEmpty (s, true) );
    }
}
