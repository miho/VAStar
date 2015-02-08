/*
 * Stack.java
 *
 * Created on 11. Juli 2007, 20:19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package blocksworld;

import eu.mihosoft.ai.astar.Action;


/**
 * Stack action.
 * In blocksworld the action Stack discripes what precontions are necessary if
 * you want to put a block from the robot arm on another block and which effects 
 * this action has.
 *
 * @author miho
 */
public final class Stack extends Action
{
    
    /**
     * Creates a new instance of Stack
     *
     * @param s State on which Action operates on.
     * @param x The block that is to be stacked.
     * @param y The location of target stack, i.e. 
     *          the highest block of the target stack.
     */
    public Stack (BlocksWorldState s, int x, int y)
    {
        // Set name
        setName( "Stack (" + x + ", " + y + ")" );
        
        //PRECOND
        precond.add ( new Holding(s, x, true) );
        precond.add ( new Clear (s, y, true) );
        
        //EFFECTS
        effect.add ( new Holding(s, x, false) );
        effect.add ( new Clear (s, y, false) );
        
        effect.add ( new On (s, x, y, true) );
        effect.add ( new Clear(s, x, true) );
        effect.add ( new HandEmpty(s, true) );
    }
}
