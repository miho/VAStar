/*
 * HandEmpty.java
 *
 * Created on 11. Juli 2007, 18:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package blocksworld;

import eu.mihosoft.ai.astar.Predicate;


/**
 * HandEmpty predicate.
 * In blocksworld the predicate HandEmpty defines if  the robot is empty or not.
 *
 * @author miho
 */
public final class HandEmpty extends Predicate
{
    /**
     * Creates a new instance of HandEmpty
     *
     * @param s State on which Predicate operates on.
     * @param value The predicateValue (see Predicate for details).
     */
    public HandEmpty (BlocksWorldState s, boolean value)
    {
        setPredicate (s.getHandEmptyOffset (),value);
        
        setName("HandEmpty ( )");
    }
}
