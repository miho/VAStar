/*
 * Predicate.java
 *
 * Created on 11. Juli 2007, 16:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eu.mihosoft.ai.astar;

/**
 * Predicate can be used as ConditionPredicate and EffectPredicate
 * as it implements verify() and apply() methods.
 * @author miho
 */
public class Predicate implements EffectPredicate, ConditionPredicate
{
    protected int index;
    protected boolean predicateValue;
    private String name;
    
    /** Creates a new instance of Predicate */
    public Predicate()
    {
        
    }
    
    /**
     * Creates a new instance of Predicate.
     *
     * @param i index of State element the predicate operates on.
     * @param predicateValue defines whether predicate is positive
     *                       or negative.
     */
    public Predicate(int i, boolean predicateValue)
    {
        setPredicate(i, predicateValue);
    }
    
    /**
     * Initializes predicate in the same way the corresponding constructor
     * does.
     *
     * @param i index of State element the predicate operates on.
     * @param predicateValue defines whether predicate is positive
     *                       or negated.
     */
    public void setPredicate (int i, boolean predicateValue)
    {
        index = i;
        this.predicateValue = predicateValue;
    }
    
    /**
     * Applies predicate/effect to State s.
     *
     * @param s State
     */
    public void apply (State s)
    {
        s.set (index,predicateValue);
    }
    
    /**
     * Verifies predicate/condition on State s.
     *
     * @param s State.
     */
    public boolean verify (State s)
    {
        return predicateValue == s.get (index);
    }
    
    /**
     * Returns predicate value, i.e. if predicate is negated or not.
     */
    public boolean getPredicateValue ()
    {
        return predicateValue;
    }
    
    /**
     * Returnes the name of the predicate as String.
     */
    public String getName ()
    {
        return name;
    }
    
    /**
     * Sets the name of the predicate.
     *
     * @param name The name of the predicate.
     */
    public void setName (String name)
    {
        this.name = name;
    }
}
