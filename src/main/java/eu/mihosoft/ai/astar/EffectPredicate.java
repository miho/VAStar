/*
 * EffectPredicate.java
 *
 * Created on 11. Juli 2007, 17:08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eu.mihosoft.ai.astar;

/**
 * Effect interface. Has to be implemented from every predicate
 * that is interpreted as effect.
 * @author miho
 */
public interface EffectPredicate
{   
     
    /**
     * Applies predicate/effect to State s.
     *
     * @param s State
     */
     public void apply(State s);
     
    /**
     * Returns predicate value, i.e. if predicate is negated or not.
     */
    public boolean getPredicateValue();
    
    /**
     * Returnes the name of the predicate as String.
     */
    public String getName();
}
