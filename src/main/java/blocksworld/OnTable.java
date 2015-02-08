/*
 * OnTable.java
 *
 * Created on 11. Juli 2007, 17:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package blocksworld;

import eu.mihosoft.ai.astar.Predicate;


/**
 * OnTable predicate.
 * In blocksworld the predicate OnTable defines if a block stands on 
 * on the table or on another block.
 *
 * @author miho
 */
public final class OnTable extends Predicate
{
    private int block;
    
    /**
     * Creates a new instance of OnTable.
     *
     * @param s State on which Predicate operates on.
     * @param block The block that is to be checked.
     * @param value The predicateValue (see Predicate for details). 
     */
    public OnTable (BlocksWorldState s, int block, boolean value)
    {
        setName("OnTable (" + block + ")");
        
        this.block = block;
        
        setPredicate (s.getOnTableOffset ()+block,value);
    }
}
