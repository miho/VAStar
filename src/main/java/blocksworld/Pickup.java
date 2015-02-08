/*
 * Pickup.java
 *
 * Created on 11. Juli 2007, 20:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package blocksworld;

import eu.mihosoft.ai.astar.Action;


/**
 * Pickup action.
 * In blocksworld the action Pickup discripes what precontions are necessary if
 * you want to pick up a block with the robot arm from the table and which 
 * effects this action has.
 *
 * @author miho
 */
public final class Pickup extends Action
{
    /**
     * Creates a new instance of Pickup.
     * @param s State on which Action operates on.
     * @param x The block that is to be picked up.
     */
    public Pickup (BlocksWorldState s, int x)
    {
        // Set name
        setName( "Pickup (" + x + ")" );
        
        //PRECOND
        precond.add ( new OnTable (s, x, true) );
        precond.add ( new Clear (s, x, true) );
        precond.add ( new HandEmpty (s, true) );
        
        //EFFECTS
        effect.add ( new OnTable (s, x, false) );
        effect.add ( new Clear (s, x, false) );
        effect.add ( new HandEmpty (s, false) );
        
        effect.add ( new Holding (s, x, true) );
    }
}
