/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.ai.astar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public class BooleanState implements State<Boolean> {
    private String actionName;
    private final List<Boolean> internalState;
    private Action<Boolean> action;
    
    /**
     * Creates an instance of State.
     *
     * @param n Size of State.
     */
    public BooleanState (int n)
    {
        internalState = new ArrayList<>(n);
        
        for (int i = 0; i < n; i++)
        {
            internalState.add (i,false);
        }
    }
    
    @Override
    public String getActionName ()
    {
        return actionName;
    }
    

    @Override
    public void setActionName (String actionName)
    {
        this.actionName = actionName;
    }
    

    @Override
    public boolean equals (Object o)
    {
        
        if (!(o instanceof BooleanState)) {
            return false;
        }
        
        BooleanState l = (BooleanState) o;
        
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

    @Override
    public State<Boolean> clone() {
        BooleanState newState = new BooleanState(size());
        
        newState.internalState.clear();
        newState.internalState.addAll(internalState);
        newState.action = action;
        newState.actionName = actionName;
        
        return newState;
    }

    @Override
    public Boolean set(int i, Boolean value) {
        return internalState.set(i, value);
    }

    @Override
    public Boolean get(int i) {
        return internalState.get(i);
    }

    @Override
    public int size() {
        return internalState.size();
    }

    @Override
    public State<Boolean> newInstance(int n) {
        return new BooleanState(n);
    }

    @Override
    public Action<Boolean> getAction() {
        return action;
    }

    @Override
    public void setAction(Action<Boolean> a) {
        this.action = a;
    }
}
