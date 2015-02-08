/*
 * Stack.java
 *
 * Created on 11. Juli 2007, 20:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package blocksworld;

import eu.mihosoft.ai.astar.Action;


/**
 * Unstack action.
 * In blocksworld the action Unstack discripes what precontions are necessary if
 * you want to pick up a block with the robot arm from another block and which effects 
 * this action has.
 *
 * @author miho
 */
public final class Unstack extends Action
{
    /**
     * Creates a new instance of Unstack
     *
     * @param s State on which Action operates on.
     * @param x The block that is to be unstacked.
     * @param y The location of stack, i.e. 
     *          the highest block of the stack.
     */
    public Unstack (BlocksWorldState s, int x, int y)
    {
        // Set name
        setName( "Unstack (" + x + ", " + y + ")" );
        
        //PRECOND
        precond.add ( new On (s,x, y,true) );
        precond.add ( new Clear (s, x,true) );
        precond.add ( new HandEmpty (s, true) );
        
        //EFFECTS
        effect.add ( new On (s, x, y, false) );
        effect.add ( new Clear (s, x, false) );
        effect.add ( new HandEmpty (s, false) );
        
        effect.add ( new Holding(s, x, true) );
        effect.add ( new Clear(s, y, true) );
    }
}
