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
 * Heuristic is used to estimate the distance between a world state and a 
 * specified goal.
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public interface Heuristic<T> {

    /**
     * Computes the estimated distance between a world state and a 
     * specified goal.
     *
     * @param s current state
     * @param goal goal state
     * @param w world description, includes domain knowledge that can be used by
     * the heuristic to improve the estimation
     * @return estimated distance
     */
    public double estimate(State<T> s, Condition<T> goal, WorldDescription<T> w);
}
