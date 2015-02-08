/*
 * Heuristic.java
 *
 * Created on 12. Juli 2007, 14:53
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eu.mihosoft.ai.astar;

/**
 * Heuristic is used to estimate distance between states.
 * @author night
 */
public interface Heuristic<T>
{
    /**
     *	compare two states and return the difference
     */
    public double estimate(State<T> s, Condition<T> goal, WorldDescription<T> w);
}
