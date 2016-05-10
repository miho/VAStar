/*
 * State.java
 *
 * Created on 11. Juli 2007, 16:46
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eu.mihosoft.ai.astar;

/**
 * This class represents a state of the planning domain. It mostly just
 * a list of state values (double, boolean, object).
 * @author miho
 * @param <T>
 */
public interface State<T>
{

    public Action<T> getAction();
    
    public void setAction(Action<T> a);
    
    /**
     * Returns the name of the action that last changed the state.
     * @return action name or {@code null} if no action has been applied 
     */
    public String getActionName ();
    
    /**
     * Sets the name of the action that last changed the state.
     *
     * @param actionName The name of the action.
     */
    public void setActionName (String actionName);
    
    /**
     * Overloaded version of the equals method.
     *
     * @param o the state thats equality is to be checked
     * @return {@code true} if equal; {@code false} otherwise
     */
    @Override
    public boolean equals (Object o);
    
    public State<T> clone();
    
    public T set(int i, T value);
    
    public T get(int i);
    
    public int size();
    
    public State<T> newInstance(int n);

}