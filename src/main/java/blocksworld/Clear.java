/*
 * Clear.java
 *
 * Created on 11. Juli 2007, 18:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package blocksworld;

import eu.mihosoft.ai.astar.Predicate;



/**
 * Clear predicate.
 * In blocksworld the predicate Clear defines if a block is
 * on top of a stack of blocks resp. on the table or not.
 *
 * @author miho
 */
public final class Clear extends Predicate
{
    private int block;
    
    
    /**
     * Creates a new instance of Clear
     * @param s State on which Predicate operates on.
     * @param value The predicateValue (see Predicate for details).
     */
    public Clear (BlocksWorldState s, int block, boolean value)
    {
        this.block = block;
        
        setPredicate (s.getClearOffset ()+block,value);
        
        setName("Clear (" + block + ")");
    }
}
