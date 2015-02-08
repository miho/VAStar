/*
 * Action.java
 *
 * Created on 11. Juli 2007, 18:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eu.mihosoft.ai.astar;

/**
 * An Action consists of precondition and effects. These are predicates that 
 * can be verified or applied to a state. The action class does combine these
 * two. It can perform an action to a given state s. 
 * @author miho
 */
public class Action
{
    private String name;
    public Condition precond;
    public Effect effect;
    
    /** Creates a new instance of Action */
    public Action ()
    {
        precond = new Condition ();
        effect = new Effect ();
    }
    
    /**
     * Performing an action means to apply all effects (predicates)
     * to State s if preconditions are true.
     *
     * @param s State on which the action operates on.
     */
    public boolean perform (State s)
    {
        boolean performable = precond.verify (s);
        
        if (performable)
        {
            effect.apply (s);
            s.setActionName (this.getName ());
        }
        else
        {
//            System.out.println ("in Action: " + getName ());
        }
        
        
        return performable;
    }
    
    /**
     * Overloaded version of the above named method.
     * Using this method gives you the possibility
     * to verify preconditions on one state and apply
     * effects to another one.
     *
     * @param s1 State on which the action is verified.
     * @param s2 State on which the action operates on.
     */
    public boolean perform (State s1, State s2)
    {
        boolean performable = precond.verify (s1);
        
        if (performable)
        {
            effect.apply (s2);
            s2.setActionName (this.getName ());
        }
        else
        {
//            System.out.println ("in Action: " + getName ());
        }
        
        
        return performable;
    }
    
    
    /**
     * Overloaded version of the above named method.
     * Using this method gives you the choice to only
     * apply positive or negative effects.
     *
     * @param s State on which the action operates on.
     */
    public boolean perform (State s,boolean predicateValue)
    {
        boolean performable = precond.verify (s);
        
        if (performable)
        {
            effect.apply (s,predicateValue);
            s.setActionName (this.getName ());
        }
        else
        {
//            System.out.println("in Action: " + getName());
        }
        
        
        return performable;
    }
    
    /**
     * Overloaded version of the above named method.
     * Using this method gives you the choice to only
     * apply positive or negative effects and the possibility
     * to verify preconditions on one state and apply
     * effects to another.
     *
     * @param s1 State on which the action is verified.
     * @param s2 State on which the action operates on.
     */
    public boolean perform (State s1, State s2,boolean predicateValue)
    {
        boolean performable = precond.verify (s1);
        
        if (performable)
        {
            effect.apply (s2,predicateValue);
            s2.setActionName (this.getName ());
        }
        else
        {
//            System.out.println("in Action: " + getName());
        }
        
        
        return performable;
    }
    
    
    
    /**
     * Returnes the String description of the action.
     * (nice for screen output)
     */
    public String getName ()
    {
        return name;
    }
    
    /**
     * Sets the name (description) of the action.
     */
    protected void setName ( String name )
    {
        this.name = name;
    }
}
