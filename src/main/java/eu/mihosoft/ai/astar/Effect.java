/*
 * Effect.java
 *
 * Created on 11. Juli 2007, 17:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eu.mihosoft.ai.astar;

import java.util.ArrayList;

/**
 * An Effect is an ArrayList of EffectPredicates. It groups different
 * predicates and applies them to a State s. It does not support logical operators such
 * as OR. All predicates are combined via AND.
 * @author miho
 */
public class Effect<T> extends ArrayList<EffectPredicate<T>>
{
    
    /**
     * Applies all effect predicates to state s.
     * @param s State
     */
    public void apply (State<T> s)
    {
        for (EffectPredicate<T> i : this)
        {
            i.apply (s);
        }
    }
    
//    /**
//     * Overloaded version of the above named method.
//     * It gives you the choice to only apply positive
//     * or negative effects.
//     * @param s State
//     * @param predicateValue defines whether only positive
//     *                       or negative effects will be applied.
//     */
//    public void apply (State s, boolean predicateValue)
//    {
//        for (EffectPredicate i : this)
//        {
//            if (i.getPredicateValue () == predicateValue)
//            {
//                i.apply (s);
//            }
//        }
//    }
}
