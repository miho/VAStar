/*
 * Holding.java
 *
 * Created on 11. Juli 2007, 19:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package blocksworld;

import eu.mihosoft.ai.astar.Predicate;


/**
 * Holding predicate.
 * In blocksworld the predicate Holding gives a hint of which block is now 
 * holding by the robot arm.
 *
 * @author miho
 */
public final class Holding extends Predicate
{
    private int block;
    
    /**
     * Creates a new instance of Holding
     *
     * @param s State on which Predicate operates on.
     * @param block The block that is to be checked.
     * @param value The predicateValue (see Predicate for details).
     */
    public Holding (BlocksWorldState s, int block, boolean value)
    {
        this.block = block;
        
        setPredicate (s.getHoldingOffset ()+block,value);
        
        setName("Holding (" + block + ")");
    }   
}
