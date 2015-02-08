/*
 * ConditionPredicate.java
 *
 * Created on 11. Juli 2007, 17:08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eu.mihosoft.ai.astar;

/**
 * Condition interface. Has to be implemented from every predicate
 * that is interpreted as condition.
 * @author miho
 */
public interface ConditionPredicate<T>
{
    /**
     * Verifies if ConditionPredicates are true in State s.
     * @param s State
     */
    public boolean verify(State<T> s);
    
    /**
     * Returnes the name of the predicate as String.
     */
    public String getName();
}
