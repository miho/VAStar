/*
 * Condition.java
 *
 * Created on 11. Juli 2007, 16:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eu.mihosoft.ai.astar;

import java.util.ArrayList;

/**
 * A Condition is an ArrayList of ContitionPredicates. It groups different
 * predicates and verifies them. It does not support logical operators such
 * as OR. All predicates are combined via AND.
 * 
 * @author miho
 */
public class Condition extends ArrayList<ConditionPredicate>
{
    
    /**
     * Verifies if ConditionPredicates are true in State s.
     * @param s State
     */
    public boolean verify (State s)
    {
        
        for (ConditionPredicate i : this)
        {
            if (i.verify (s) == false)
            {
//                System.out.println ( i.getName () +" does not match! ");
                return false;
            }
        }
        return true;
    }
    
    /**
     * Overloaded version of the above named method.
     * Verifies ConditionPredicates in State s.
     * @param s State
     * @param predicateValue one can specify if only positive
     *                       or negative predicates are verified.
     */
    public boolean verify (State s, boolean predicateValue)
    {
        for (ConditionPredicate i : this)
        {
            if ( i.getPredicateValue () == predicateValue)
            {
                if ( i.verify (s) == false )
                {
//                    System.out.println ( i.getName () +" does not match!");
                    return false;
                }
            }
        }
        return true;
    }
}
