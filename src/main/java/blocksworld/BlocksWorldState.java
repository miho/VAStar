/*
 * BlocksWorldState.java
 *
 * Created on 11. Juli 2007, 17:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package blocksworld;


import eu.mihosoft.ai.astar.Effect;
import eu.mihosoft.ai.astar.State;

/**
 * Describes a state in BlocksWorld. It computes offsets for the predicate
 * indices.
 * @author miho
 */
public final class BlocksWorldState extends State
{
    private int numberOfBlocks;
    
    private int handEmptyOffset;
    private int holdingOffset;
    private int onTableOffset;
    private int clearOffset;
    private int onOffset;
    
    
    /**
     * Creates a new instance of BlocksWorldState
     * @param n The number of blocks.
     */
    public BlocksWorldState (int n)
    {
        super (3*n + n*(n-1) + 1); // set array size
        
        numberOfBlocks = n;
        
        handEmptyOffset = 0;
        holdingOffset = 1;
        onTableOffset = n + holdingOffset;
        clearOffset = n + onTableOffset;
        onOffset = n + clearOffset;
        
        
        initState();
    }
    /**
     * Initialize the new instance of BlocksWorldState
     */
    public void initState ()
    {
        Effect init = new Effect();
        
        init.add ( new HandEmpty(this,true));
        
        for (int i = 0; i < numberOfBlocks; i++)
        {
           init.add ( new OnTable(this,i,true));
           init.add ( new Clear(this,i,true));
        }
        
        init.apply (this);
    }
    
    /**
     * Returns the number of Blocks
     */
    int getNumberOfBlocks ()
    {
        return numberOfBlocks;
    }
    
    /**
     * Returns the offset of the predicate On
     */
    public int getOnOffset ()
    {
        return onOffset;
    }
    
    /**
     * Returns the offset of the predicate Clear
     */
    public int getClearOffset ()
    {
        return clearOffset;
    }
    
    /**
     * Returns the offset of the predicate OnTable
     */
    public int getOnTableOffset ()
    {
        return onTableOffset;
    }
    
    /**
     * Returns the offset of the predicate Holding
     */
    public int getHoldingOffset ()
    {
        return holdingOffset;
    }
    
    /**
     * Returns the offset of the predicate HandEmpty
     */
    public int getHandEmptyOffset ()
    {
        return handEmptyOffset;
    }
}
