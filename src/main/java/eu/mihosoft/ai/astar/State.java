/*
 * State.java
 *
 * Created on 11. Juli 2007, 16:46
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eu.mihosoft.ai.astar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a state of the planning domain. It mostly just
 * a list of booleans.
 * @author miho
 */
public class State extends ArrayList<Boolean>
{
    private String actionName;
    
    /**
     * Creates an instance of State.
     *
     * @param n Size of State.
     */
    public State (int n)
    {
        for (int i = 0; i < n; i++)
        {
            this.add (i,false);
        }
    }
    
    /**
     * Returns the name of the action that last changed the state.
     */
    public String getActionName ()
    {
        return actionName;
    }
    
    /**
     * Sets the name of the action that last changed the state.
     *
     * @param actionName The name of the action.
     */
    public void setActionName (String actionName)
    {
        this.actionName = actionName;
    }
    
    /**
     * Overloaded version of the equals method.
     *
     * @param o the state thats equality is to be checked
     * @return {@code true} if equal; {@code false} otherwise
     */
    @Override
    public boolean equals (Object o)
    {
        
        if (!(o instanceof State)) {
            return false;
        }
        
        State l = (State) o;
        
        if (this.size () != l.size () )
        {
            return false;
        }
        
        for (int i =0; i < this.size (); i++)
        {
            if ( !Objects.equals(this.get (i), l.get (i)) )
            {
                return false;
            }
        }
        
        return true;
    }

}