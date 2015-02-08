/*
 * On.java
 *
 * Created on 11. Juli 2007, 18:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package blocksworld;

import eu.mihosoft.ai.astar.Predicate;


/**
 * On predicate.
 * In blocksworld the predicate On defines whether block a stands on block b
 * or not.
 * @author miho
 */
public final class On extends Predicate
{
    private int blockA;
    private int blockB;
    
    /**
     * Creates a new instance of On.
     *
     * @param s State on which Predicate operates on.
     * @param blockA The block that is to be checked.
     * @param blockB The location.
     * @param value The predicateValue (see Predicate for details). 
     */
    public On (BlocksWorldState s, int blockA, int blockB, boolean value)
    {
        this.blockA = blockA;
        this.blockB = blockB;
        
        setPredicate (computeIndex( s, blockA, blockB),value);
        
        setName("On (" + blockA + ", " + blockB + ")");
    }
    
    /**
     * compute the index of the variable where the information of
     * "block A stand on block B" is stored and returns the index/position
     */
    private int computeIndex(BlocksWorldState s, int blockA, int blockB)
    {   
        int offset = s.getOnOffset ();
        int n = s.getNumberOfBlocks();
        
        //compute y
        // if this is enabled, BlocksworldState has to be
        // 3*n+n*(n-1)+1 instead of 3*n+n*n+1!
        // so we can save n entries.
        
        assert blockA != blockB: "blocks must be different!";
        
        int y = 0;
        
        if (blockB > blockA)
        {
            y = blockB-1;
        }
        else
        {
            y = blockB;
        }
        
        // compute index
        return s.getOnOffset () + (n-1)*blockA + y;
        
//        return s.getOnOffset () + (n) * blockA + blockB;
    }   
    
}
